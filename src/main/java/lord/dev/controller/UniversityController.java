package lord.dev.controller;

import lord.dev.dto.request.UniversityRequest;
import lord.dev.service.UniversityService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping
    public HttpEntity<?> createUniversity(@Valid @RequestBody UniversityRequest universityRequest){
        return ResponseEntity.ok(universityService.create(universityRequest));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateUniversity(@PathVariable long id, @Valid @RequestBody UniversityRequest universityRequest){
        return ResponseEntity.ok(universityService.update(id, universityRequest));
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getUniversityById(@PathVariable long id){
        return ResponseEntity.ok(universityService.getById(id));
    }

    @GetMapping
    public HttpEntity<?> getUniversityList(){
        return ResponseEntity.ok(universityService.getUniversityList());
    }
}
