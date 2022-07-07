package lord.dev.service.impl;

import lord.dev.dto.request.JournalRequest;
import lord.dev.dto.response.ResponseMessage;
import lord.dev.dto.response.RestAPIResponse;
import lord.dev.exception.NoSuchElementExistsException;
import lord.dev.exception.SuchElementAlreadyExistsException;
import lord.dev.model.Group;
import lord.dev.model.Journal;
import lord.dev.repository.GroupRepository;
import lord.dev.repository.JournalRepository;
import lord.dev.service.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;
    private final GroupRepository groupRepository;

    public JournalServiceImpl(JournalRepository journalRepository, GroupRepository groupRepository) {
        this.journalRepository = journalRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public RestAPIResponse create(JournalRequest journalRequest) {
        Group group = groupRepository.findById(journalRequest.getGroupId())
                .orElseThrow(()-> new NoSuchElementExistsException(ResponseMessage.GROUP_NOT_FOUND.getMessage()));
        if (journalRepository.existsByJournalAndGroup(journalRequest.getName(), journalRequest.getGroupId())) {
            throw new SuchElementAlreadyExistsException(ResponseMessage.JOURNAL_ALREADY_EXISTS.getMessage());
        }
        Journal journal = new Journal();
        journal.setName(journalRequest.getName());
        journal.setGroup(group);
        journalRepository.save(journal);
        return new RestAPIResponse(ResponseMessage.JOURNAL_SAVE.getMessage(), HttpStatus.CREATED.value());
    }

    @Override
    public RestAPIResponse update(long id, JournalRequest journalRequest) {

        Journal journal = journalRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.JOURNAL_NOT_FOUND.getMessage()));
        Group group = groupRepository.findById(journalRequest.getGroupId())
                .orElseThrow(()-> new NoSuchElementExistsException(ResponseMessage.GROUP_NOT_FOUND.getMessage()));
        if (journalRepository.existsByJournalAndGroup(journalRequest.getName(), journalRequest.getGroupId()))
            throw new SuchElementAlreadyExistsException(ResponseMessage.JOURNAL_ALREADY_EXISTS.getMessage());

        journal.setName(journalRequest.getName());
        journal.setGroup(group);
        return new RestAPIResponse(ResponseMessage.JOURNAL_UPDATE.getMessage(), HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getById(long id) {
        Journal journal = journalRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.JOURNAL_NOT_FOUND.getMessage()));
        return new RestAPIResponse(journal);
    }

    @Override
    public RestAPIResponse getJournalList() {
        return new RestAPIResponse(ResponseMessage.JOURNAL_LIST.getMessage(), journalRepository.findAll(), HttpStatus.OK.value());
    }
}
