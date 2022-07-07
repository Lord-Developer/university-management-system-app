package lord.dev.controller;

import lord.dev.dto.request.FacultyRequest;
import lord.dev.service.FacultyService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public HttpEntity<?> createFaculty(@Valid @RequestBody FacultyRequest facultyRequest){
        return ResponseEntity.ok(facultyService.create(facultyRequest));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateFaculty(@PathVariable long id, @Valid @RequestBody FacultyRequest facultyRequest){
        return ResponseEntity.ok(facultyService.update(id, facultyRequest));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getFacultyById(@PathVariable long id){
        return ResponseEntity.ok(facultyService.getById(id));
    }

    @GetMapping
    public HttpEntity<?> getFacultyList(){
        return ResponseEntity.ok(facultyService.getFacultyList());
    }

}
