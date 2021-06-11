package startaideia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import startaideia.domain.Tool;
import startaideia.service.ToolService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
@Setter
public class ToolController {

    @Autowired
    private ToolService service;

    @PostMapping
    public ResponseEntity<Tool> postTool(
            @RequestBody Tool tool
    ) {
        return new ResponseEntity<>(service.insertTool(tool), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Tool> listAllTools() {
        return service.listTools();
    }

    @GetMapping("/tag={tag}")
    public List<Tool> listAllToolsByTag(
            @PathVariable String tag
    ) {
        return service.listToolsByTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tool> deleteStudentNote(
            @PathVariable String id
    ) {
        service.deleteTool(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
