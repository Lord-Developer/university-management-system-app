package lord.dev.service;

import lord.dev.dto.request.JournalRequest;
import lord.dev.dto.response.RestAPIResponse;

public interface JournalService {

    RestAPIResponse create(JournalRequest journalRequest);

    RestAPIResponse update(long id, JournalRequest journalRequest);

    RestAPIResponse getById(long id);

    RestAPIResponse getJournalList();
}
