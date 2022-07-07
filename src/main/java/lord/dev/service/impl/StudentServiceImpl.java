package lord.dev.service.impl;

import lord.dev.dto.request.StudentRequest;
import lord.dev.dto.response.ResponseMessage;
import lord.dev.dto.response.RestAPIResponse;
import lord.dev.dto.response.StudentByFirstNameResponse;
import lord.dev.dto.response.StudentInfoResponse;
import lord.dev.exception.NoSuchElementExistsException;
import lord.dev.mapper.StudentMapper;
import lord.dev.model.Journal;
import lord.dev.model.Student;
import lord.dev.repository.JournalRepository;
import lord.dev.repository.StudentRepository;
import lord.dev.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ModelMapper mapper;
    private final JournalRepository journalRepository;


    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, ModelMapper mapper, JournalRepository journalRepository) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.mapper = mapper;
        this.journalRepository = journalRepository;
    }

    @Override
    public RestAPIResponse create(StudentRequest studentRequest) {
        Student student = studentMapper.convertStudentRequestToStudentEntity(studentRequest);
        studentRepository.save(student);
        return new RestAPIResponse(ResponseMessage.STUDENT_SAVE.getMessage(), HttpStatus.CREATED);
    }

    @Override
    public RestAPIResponse update(long id, StudentRequest studentRequest) {
        Student student = studentMapper.convertStudentRequestToStudentEntity(id, studentRequest);
        studentRepository.save(student);
        return new RestAPIResponse(ResponseMessage.STUDENT_UPDATE.getMessage(), HttpStatus.OK.value());
    }


    @Override
    public RestAPIResponse getById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.STUDENT_NOT_FOUND.getMessage()));
        return new RestAPIResponse(student, HttpStatus.OK.value());
    }


    public RestAPIResponse getStudentSubjectsById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.STUDENT_NOT_FOUND.getMessage()));
        System.out.println(student.getGroup());
        Journal journal = journalRepository.findByGroup(student.getGroup())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.JOURNAL_NOT_FOUND.getMessage()));
        StudentInfoResponse response = new StudentInfoResponse();
        response.setId(student.getId());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setSubjects(journal.getSubjects());
        return new RestAPIResponse(ResponseMessage.STUDENT_SUBJECT_LIST.getMessage(),response, HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getStudentByFirstName(String firstName) {
        List<Student> students = studentRepository.findByFirstNameIgnoreCase(firstName);
        List<StudentByFirstNameResponse> list = new ArrayList<>();
        StudentByFirstNameResponse byFirstNameResponse;

        for (Student student:students) {
            byFirstNameResponse = mapper.map(student, StudentByFirstNameResponse.class);
            byFirstNameResponse.setGroup(student.getGroup().getName());
            byFirstNameResponse.setFaculty(student.getGroup().getFaculty().getName());
            list.add(byFirstNameResponse);
        }
        return new RestAPIResponse(ResponseMessage.STUDENT_LIST.getMessage(), list, HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getStudentList() {
        return new RestAPIResponse(ResponseMessage.STUDENT_LIST.getMessage(), studentRepository.findAll(), HttpStatus.OK.value());

    }

}
