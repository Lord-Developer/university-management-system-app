package lord.dev.controller;

import lord.dev.dto.request.StudentRequest;
import lord.dev.service.StudentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public HttpEntity<?> createStudent(@RequestBody StudentRequest studentRequest){
        return ResponseEntity.ok(studentService.create(studentRequest));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateStudent(@PathVariable long id, @RequestBody StudentRequest studentRequest){
        return ResponseEntity.ok(studentService.update(id, studentRequest));
    }

    @GetMapping("/subjects/{id}")
    public HttpEntity<?> getStudentSubjectsById(@PathVariable long id){
        return ResponseEntity.ok(studentService.getStudentSubjectsById(id));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getStudentById(@PathVariable long id){
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping
    public HttpEntity<?> getStudentByFirstName(@RequestParam String firstName){
        return ResponseEntity.ok(studentService.getStudentByFirstName(firstName));
    }

    @GetMapping("/list")
    public HttpEntity<?> getStudentList(){
        return ResponseEntity.ok(studentService.getStudentList());
    }
}
