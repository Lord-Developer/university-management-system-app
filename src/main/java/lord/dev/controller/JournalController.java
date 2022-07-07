package lord.dev.controller;

import lord.dev.dto.request.JournalRequest;
import lord.dev.service.JournalService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/journal")
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping
    public HttpEntity<?> createJournal(@RequestBody JournalRequest journalRequest){
        return ResponseEntity.ok(journalService.create(journalRequest));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateJournal(@PathVariable long id, @RequestBody JournalRequest journalRequest){
        return ResponseEntity.ok(journalService.update(id, journalRequest));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getJournalById(@PathVariable long id){
        return ResponseEntity.ok(journalService.getById(id));
    }

    @GetMapping
    public HttpEntity<?> getJournalList(){
        return ResponseEntity.ok(journalService.getJournalList());
    }
}
