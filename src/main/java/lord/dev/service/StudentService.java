package lord.dev.service;

import lord.dev.dto.request.StudentRequest;
import lord.dev.dto.response.RestAPIResponse;

public interface StudentService {

    RestAPIResponse create(StudentRequest studentRequest);

    RestAPIResponse update(long id, StudentRequest studentRequest);

    RestAPIResponse getById(long id);

    RestAPIResponse getStudentList();

    RestAPIResponse getStudentSubjectsById(long id);

    RestAPIResponse getStudentByFirstName(String firstName);



}
