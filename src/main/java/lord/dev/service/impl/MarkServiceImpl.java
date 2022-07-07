package lord.dev.service.impl;

import lord.dev.dto.request.MarkRequest;
import lord.dev.dto.response.ResponseMessage;
import lord.dev.dto.response.RestAPIResponse;
import lord.dev.exception.NoSuchElementExistsException;
import lord.dev.exception.SuchElementAlreadyExistsException;
import lord.dev.model.Journal;
import lord.dev.model.Mark;
import lord.dev.model.Student;
import lord.dev.model.Subject;
import lord.dev.repository.JournalRepository;
import lord.dev.repository.MarkRepository;
import lord.dev.repository.StudentRepository;
import lord.dev.repository.SubjectRepository;
import lord.dev.service.MarkService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final JournalRepository journalRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public MarkServiceImpl(MarkRepository markRepository, JournalRepository journalRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.markRepository = markRepository;
        this.journalRepository = journalRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public RestAPIResponse create(MarkRequest markRequest) {
        Student student = studentRepository.findById(markRequest.getStudentId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.STUDENT_NOT_FOUND.getMessage()));
        Journal journal = journalRepository.findById(markRequest.getJournalId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.JOURNAL_NOT_FOUND.getMessage()));
        if(!Objects.equals(student.getGroup().getId(), journal.getGroup().getId()))
            return new RestAPIResponse(ResponseMessage.JOURNAL_NOT_BELONG_THAT_GROUP.getMessage(), false, HttpStatus.BAD_REQUEST.value());
        Subject subject = subjectRepository.findById(markRequest.getSubjectId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.SUBJECT_NOT_FOUND.getMessage()));
        if(subject.getJournal().getId() != journal.getId())
            return new RestAPIResponse(ResponseMessage.SUBJECT_NOT_BELONG_THAT_JOURNAL.getMessage(),false, HttpStatus.BAD_REQUEST.value());
        if (markRepository.existsByStudentAndSubjectAndJournal(markRequest.getStudentId(), markRequest.getSubjectId(), markRequest.getJournalId())) {
            throw new SuchElementAlreadyExistsException(ResponseMessage.MARK_ALREADY_EXISTS.getMessage());
        }
        Mark mark = new Mark();
        mark.setMark(markRequest.getMark());
        mark.setSubject(subject);
        mark.setStudent(student);
        mark.setJournal(journal);
        markRepository.save(mark);
        return new RestAPIResponse(ResponseMessage.MARK_SAVE.getMessage(),HttpStatus.CREATED.value());
    }

    @Override
    public RestAPIResponse update(long id, MarkRequest markRequest) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.MARK_NOT_FOUND.getMessage()));
        Student student = studentRepository.findById(markRequest.getStudentId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.STUDENT_NOT_FOUND.getMessage()));
        Journal journal = journalRepository.findById(markRequest.getJournalId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.JOURNAL_NOT_FOUND.getMessage()));
        if(!Objects.equals(student.getGroup().getId(), journal.getGroup().getId()))
            return new RestAPIResponse(ResponseMessage.JOURNAL_NOT_BELONG_THAT_GROUP.getMessage(), false, HttpStatus.BAD_REQUEST.value());
        Subject subject = subjectRepository.findById(markRequest.getSubjectId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.SUBJECT_NOT_FOUND.getMessage()));
        if(subject.getJournal().getId() != journal.getId())
            return new RestAPIResponse(ResponseMessage.SUBJECT_NOT_BELONG_THAT_JOURNAL.getMessage(),false, HttpStatus.BAD_REQUEST.value());
        if (markRepository.existsByStudentAndSubjectAndJournal(markRequest.getStudentId(), markRequest.getSubjectId(), markRequest.getJournalId())) {
            throw new SuchElementAlreadyExistsException(ResponseMessage.MARK_ALREADY_EXISTS.getMessage());
        }
        mark.setMark(markRequest.getMark());
        mark.setSubject(subject);
        mark.setStudent(student);
        mark.setJournal(journal);
        markRepository.save(mark);
        return new RestAPIResponse(ResponseMessage.MARK_UPDATE.getMessage(),HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getById(long id) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.MARK_NOT_FOUND.getMessage()));
        return new RestAPIResponse(mark, HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getMarkList() {
        return new RestAPIResponse(ResponseMessage.MARK_LIST.getMessage(), markRepository.findAll(), HttpStatus.OK.value());

    }
}
