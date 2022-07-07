package lord.dev.service.impl;

import lord.dev.dto.request.FacultyRequest;
import lord.dev.dto.response.FacultyGroupsResponse;
import lord.dev.dto.response.GroupResponse;
import lord.dev.dto.response.ResponseMessage;
import lord.dev.dto.response.RestAPIResponse;
import lord.dev.exception.NoSuchElementExistsException;
import lord.dev.exception.SuchElementAlreadyExistsException;
import lord.dev.model.Faculty;
import lord.dev.model.Group;
import lord.dev.model.University;
import lord.dev.repository.FacultyRepository;
import lord.dev.repository.UniversityRepository;
import lord.dev.service.FacultyService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final ModelMapper mapper;
    private final UniversityRepository universityRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, ModelMapper mapper, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.mapper = mapper;
        this.universityRepository = universityRepository;
    }

    @Override
    public RestAPIResponse create(FacultyRequest facultyRequest) {
        University university = universityRepository.findById(facultyRequest.getUniversityId())
                .orElseThrow(()-> new NoSuchElementExistsException(ResponseMessage.UNIVERSITY_NOT_FOUND.getMessage()));

        if (facultyRepository.existsByNameAndUniversity(facultyRequest.getName(), university))
            throw new SuchElementAlreadyExistsException(ResponseMessage.FACULTY_ALREADY_EXISTS.getMessage());

        Faculty faculty = new Faculty();
        faculty.setName(facultyRequest.getName());
        faculty.setUniversity(university);
        facultyRepository.save(faculty);
        return new RestAPIResponse(ResponseMessage.FACULTY_SAVE.getMessage(), HttpStatus.CREATED.value());
    }

    @Override
    public RestAPIResponse update(long id, FacultyRequest facultyRequest) {

        University university = universityRepository.findById(facultyRequest.getUniversityId())
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.UNIVERSITY_NOT_FOUND.getMessage()));
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementExistsException(ResponseMessage.FACULTY_NOT_FOUND.getMessage()));

        faculty.setName(facultyRequest.getName());
        faculty.setUniversity(university);
        facultyRepository.save(faculty);
        return new RestAPIResponse(ResponseMessage.FACULTY_UPDATE.getMessage(), HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getById(long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.FACULTY_NOT_FOUND.getMessage()));
        return new RestAPIResponse(ResponseMessage.GROUP_LIST.getMessage(), getGroupList(faculty), HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getFacultyList() {
        return new RestAPIResponse(ResponseMessage.FACULTY_LIST.getMessage(),  facultyRepository.findAll(), HttpStatus.OK.value());
    }

    private FacultyGroupsResponse getGroupList(Faculty faculty){
        FacultyGroupsResponse response = new FacultyGroupsResponse();
        response.setId(faculty.getId());
        response.setFaculty(faculty.getName());
        List<GroupResponse> groups = new ArrayList<>();
        long count = 0;
         for(Group group: faculty.getGroups()) {
             groups.add(mapper.map(group, GroupResponse.class));
             count += group.getStudents().size();
         }
         response.setTotalNumberOfStudents(count);
         response.setGroups(groups);
         return response;
    }

}
