package lord.dev.service;


import lord.dev.dto.request.MarkRequest;
import lord.dev.dto.response.RestAPIResponse;

public interface MarkService {

    RestAPIResponse create(MarkRequest markRequest);

    RestAPIResponse update(long id, MarkRequest markRequest);

    RestAPIResponse getById(long id);

    RestAPIResponse getMarkList();
}
