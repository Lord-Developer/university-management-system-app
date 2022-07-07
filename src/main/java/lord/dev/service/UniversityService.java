package lord.dev.service;

import lord.dev.dto.request.UniversityRequest;
import lord.dev.dto.response.RestAPIResponse;

public interface UniversityService  {

    RestAPIResponse create(UniversityRequest universityRequest);

    RestAPIResponse update(long id, UniversityRequest universityRequest);

    RestAPIResponse getById(long id);

    RestAPIResponse getUniversityList();
}
