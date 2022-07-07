package lord.dev.controller;

import lord.dev.dto.request.MarkRequest;
import lord.dev.service.MarkService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/mark")
public class MarkController {

    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping
    public HttpEntity<?> createMark(@RequestBody MarkRequest markRequest){
        return ResponseEntity.ok(markService.create(markRequest));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateMark(@PathVariable long id, @RequestBody MarkRequest markRequest){
        return ResponseEntity.ok(markService.update(id, markRequest));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMarkById(@PathVariable long id){
        return ResponseEntity.ok(markService.getById(id));
    }

}
