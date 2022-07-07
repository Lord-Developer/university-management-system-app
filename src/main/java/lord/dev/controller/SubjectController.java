package lord.dev.controller;

import lord.dev.dto.request.SubjectRequest;
import lord.dev.service.SubjectService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public HttpEntity<?> createSubject(@RequestBody SubjectRequest subjectRequest){
        return ResponseEntity.ok(subjectService.create(subjectRequest));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateSubject(@PathVariable long id, @RequestBody SubjectRequest subjectRequest){
        return ResponseEntity.ok(subjectService.update(id, subjectRequest));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getSubjectById(@PathVariable long id){
        return ResponseEntity.ok(subjectService.getById(id));
    }


    @GetMapping
    public HttpEntity<?> getSubjectList(){
        return ResponseEntity.ok(subjectService.getSubjectList());
    }
}
