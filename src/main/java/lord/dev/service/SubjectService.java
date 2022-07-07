package lord.dev.service;

import lord.dev.dto.request.SubjectRequest;
import lord.dev.dto.response.RestAPIResponse;

public interface SubjectService {

    RestAPIResponse create(SubjectRequest subjectRequest);

    RestAPIResponse update(long id, SubjectRequest subjectRequest);

    RestAPIResponse getById(long id);

    RestAPIResponse getSubjectList();
}
