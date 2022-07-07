package lord.dev.service;

import lord.dev.dto.request.GroupRequest;
import lord.dev.dto.response.RestAPIResponse;

import java.sql.SQLException;

public interface GroupService {

    RestAPIResponse create(GroupRequest groupRequest);

    RestAPIResponse update(long id, GroupRequest groupRequest);


    RestAPIResponse getById(long id) throws SQLException;

    RestAPIResponse getGroupList();
}
