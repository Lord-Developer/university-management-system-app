package lord.dev.service.impl;

import lord.dev.dto.request.SubjectRequest;
import lord.dev.dto.response.ResponseMessage;
import lord.dev.dto.response.RestAPIResponse;
import lord.dev.exception.NoSuchElementExistsException;
import lord.dev.exception.SuchElementAlreadyExistsException;
import lord.dev.model.Journal;
import lord.dev.model.Student;
import lord.dev.model.Subject;
import lord.dev.repository.JournalRepository;
import lord.dev.repository.StudentRepository;
import lord.dev.repository.SubjectRepository;
import lord.dev.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final JournalRepository journalRepository;
    private final StudentRepository studentRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, JournalRepository journalRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.journalRepository = journalRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public RestAPIResponse create(SubjectRequest subjectRequest) {
        Journal journal = journalRepository.findById(subjectRequest.getJournalId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.JOURNAL_NOT_FOUND.getMessage()));
        if(journalRepository.existsByJournalAndGroup(subjectRequest.getName(), subjectRequest.getJournalId()))
            throw  new SuchElementAlreadyExistsException(ResponseMessage.SUBJECT_ALREADY_EXISTS.getMessage());

        Subject subject = new Subject();
        subject.setName(subjectRequest.getName());
        subject.setJournal(journal);
        subjectRepository.save(subject);
        return new RestAPIResponse(ResponseMessage.SUBJECT_SAVE.getMessage(), HttpStatus.CREATED.value());
    }

    @Override
    public RestAPIResponse update(long id, SubjectRequest subjectRequest) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.SUBJECT_NOT_FOUND.getMessage()));
        Journal journal = journalRepository.findById(subjectRequest.getJournalId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.JOURNAL_NOT_FOUND.getMessage()));
        if(journalRepository.existsByJournalAndGroup(subjectRequest.getName(), subjectRequest.getJournalId()))
            throw  new SuchElementAlreadyExistsException(ResponseMessage.SUBJECT_ALREADY_EXISTS.getMessage());

        subject.setName(subjectRequest.getName());
        subject.setJournal(journal);
        subjectRepository.save(subject);
        return new RestAPIResponse(ResponseMessage.SUBJECT_UPDATE.getMessage(), HttpStatus.OK.value());
    }


    @Override
    public RestAPIResponse getById(long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.SUBJECT_NOT_FOUND.getMessage()));
        return new RestAPIResponse(subject, HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getSubjectList() {
        return new RestAPIResponse(ResponseMessage.SUBJECT_LIST.getMessage(), subjectRepository.findAll(), HttpStatus.OK.value());
    }


}
