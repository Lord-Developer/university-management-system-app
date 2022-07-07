package lord.dev.controller;

import lord.dev.dto.request.GroupRequest;
import lord.dev.service.GroupService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Validated
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public HttpEntity<?> createGroup(@RequestBody GroupRequest groupRequest){
        return ResponseEntity.ok(groupService.create(groupRequest));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateGroup(@PathVariable long id, @RequestBody GroupRequest groupRequest){
        return ResponseEntity.ok(groupService.update(id, groupRequest));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getGroupById(@PathVariable long id)throws SQLException {
        return ResponseEntity.ok(groupService.getById(id));
    }

    @GetMapping
    public HttpEntity<?> getGroupList(){
        return ResponseEntity.ok(groupService.getGroupList());
    }
}
