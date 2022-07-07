package lord.dev.service.impl;

import lord.dev.dto.request.GroupRequest;
import lord.dev.dto.response.GroupStudentsResponse;
import lord.dev.dto.response.ResponseMessage;
import lord.dev.dto.response.RestAPIResponse;
import lord.dev.dto.response.StudentResponse;
import lord.dev.exception.NoSuchElementExistsException;
import lord.dev.exception.SuchElementAlreadyExistsException;
import lord.dev.model.*;
import lord.dev.repository.FacultyRepository;
import lord.dev.repository.GroupRepository;
import lord.dev.repository.JournalRepository;
import lord.dev.repository.MarkRepository;
import lord.dev.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;
    private final JournalRepository journalRepository;
    private final ModelMapper mapper;
    private final MarkRepository markRepository;

    public GroupServiceImpl(GroupRepository groupRepository, FacultyRepository facultyRepository, JournalRepository journalRepository, ModelMapper mapper, MarkRepository markRepository) {
        this.groupRepository = groupRepository;
        this.facultyRepository = facultyRepository;
        this.journalRepository = journalRepository;
        this.mapper = mapper;
        this.markRepository = markRepository;
    }

    @Override
    public RestAPIResponse create(GroupRequest groupRequest) {
        Faculty faculty = facultyRepository.findById(groupRequest.getFacultyId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.FACULTY_NOT_FOUND.getMessage()));
        if (groupRepository.existsGroupInTheFaculty(groupRequest.getName(), groupRequest.getFacultyId()))
            throw new SuchElementAlreadyExistsException(ResponseMessage.GROUP_ALREADY_EXISTS.getMessage());

        Group group = new Group();
        group.setName(groupRequest.getName());
        group.setFaculty(faculty);
        groupRepository.save(group);
        return new RestAPIResponse(ResponseMessage.FACULTY_SAVE.getMessage(), HttpStatus.CREATED.value());
    }

    @Override
    public RestAPIResponse update(long id, GroupRequest groupRequest) {
        Faculty faculty = facultyRepository.findById(groupRequest.getFacultyId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.UNIVERSITY_NOT_FOUND.getMessage()));
        Group group = groupRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.GROUP_NOT_FOUND.getMessage()));
        if (groupRepository.existsGroupInTheFaculty(groupRequest.getName(), groupRequest.getFacultyId()))
            throw new SuchElementAlreadyExistsException(ResponseMessage.GROUP_ALREADY_EXISTS.getMessage());
        group.setName(groupRequest.getName());
        group.setFaculty(faculty);
        groupRepository.save(group);
        return new RestAPIResponse(ResponseMessage.GROUP_UPDATE.getMessage(), HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getById(long id) throws SQLException {
        Group group = groupRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.GROUP_NOT_FOUND.getMessage()));
        return new RestAPIResponse(ResponseMessage.STUDENT_LIST.getMessage(), getStudentList(group),HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getGroupList() {
        return new RestAPIResponse(ResponseMessage.GROUP_LIST.getMessage(),  groupRepository.findAll(), HttpStatus.OK.value());
    }

    private GroupStudentsResponse getStudentList(Group group) throws SQLException {
        GroupStudentsResponse response = new GroupStudentsResponse();
        StudentResponse studentResponse;
        response.setId(group.getId());
        response.setName(group.getName());
        List<StudentResponse> students = new ArrayList<>();
        Journal journal = journalRepository.findByGroup(group)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.JOURNAL_NOT_FOUND.getMessage()));
        List<Subject> subjects = journal.getSubjects();
        int len = subjects.size();
        double score;
        for(Student student: group.getStudents()){
            try {
                score = markRepository.sumMark(student.getId());
            }catch (Exception ex){
                throw new SQLException(ResponseMessage.SQL_EXCEPTION.getMessage());
            }
            studentResponse = mapper.map(student, StudentResponse.class);
            studentResponse.setAverageScore(score/((len > 0)?len:1));
            students.add(studentResponse);

        }
        students.sort(Comparator.comparing(StudentResponse::getAverageScore, Comparator.reverseOrder()));
        response.setStudents(students);
        return response;
    }
}
