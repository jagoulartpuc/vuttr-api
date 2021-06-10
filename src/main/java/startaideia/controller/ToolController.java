package startaideia.controller;

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
    public Tool postTool(
            @RequestBody Tool tool
    ) {
        return service.insertTool(tool);
    }

    @GetMapping
    public List<Tool> listAllTools() {
        return service.listTools();
    }

    @GetMapping("/{tag}")
    public List<Tool> listAllToolsByTag(
            @PathVariable String tag
    ) {
        return service.listToolsByTag(tag);
    }

    @DeleteMapping
    public boolean deleteStudentNote(
            @PathVariable String id
    ) {
        service.deleteTool(id);
        return true;
    }

}
