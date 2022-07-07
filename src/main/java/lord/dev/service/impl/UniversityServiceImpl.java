package lord.dev.service.impl;

import lord.dev.dto.request.UniversityRequest;
import lord.dev.dto.response.ResponseMessage;
import lord.dev.dto.response.RestAPIResponse;
import lord.dev.exception.NoSuchElementExistsException;
import lord.dev.exception.SuchElementAlreadyExistsException;
import lord.dev.mapper.UniversityMapper;
import lord.dev.model.Address;
import lord.dev.model.University;
import lord.dev.repository.UniversityRepository;
import lord.dev.service.UniversityService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;
    private final ModelMapper mapper;
    private final UniversityMapper universityMapper;

    public UniversityServiceImpl(UniversityRepository universityRepository,
                                 ModelMapper mapper, UniversityMapper universityMapper) {
        this.universityRepository = universityRepository;
        this.mapper = mapper;
        this.universityMapper = universityMapper;
    }

    @Override
    public RestAPIResponse create(UniversityRequest universityRequest) {
        if(universityRepository.existsByName(universityRequest.getName()))
            throw new SuchElementAlreadyExistsException(ResponseMessage.UNIVERSITY_ALREADY_EXISTS.getMessage());
        universityRepository.save(universityMapper.convertUniversityRequestToUniversityEntity(universityRequest));
        return new RestAPIResponse(ResponseMessage.UNIVERSITY_SAVE.getMessage(), true, HttpStatus.CREATED.value());
    }

    @Override
    public RestAPIResponse update(long id, UniversityRequest universityRequest) {
        Optional<University> optional = universityRepository.findById(id);
        if(!optional.isPresent())
            throw new NoSuchElementExistsException(ResponseMessage.UNIVERSITY_NOT_FOUND.getMessage());
        University university = universityMapper.convertUniversityRequestToUniversityEntity(universityRequest);
        university.setId(id);
        Address address = university.getAddress();
        address.setId(optional.get().getAddress().getId());
        universityRepository.save(university);
        return new RestAPIResponse(ResponseMessage.UNIVERSITY_UPDATE.getMessage(),true,  HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getById(long id) {
        University university = universityRepository.findById(id)
                .orElseThrow(()->new NoSuchElementExistsException(ResponseMessage.UNIVERSITY_NOT_FOUND.getMessage()));
        return new RestAPIResponse(university, HttpStatus.OK.value());
    }

    @Override
    public RestAPIResponse getUniversityList() {
        return new RestAPIResponse(ResponseMessage.UNIVERSITY_LIST.getMessage(),  universityRepository.findAll(),HttpStatus.OK.value());
    }
}
