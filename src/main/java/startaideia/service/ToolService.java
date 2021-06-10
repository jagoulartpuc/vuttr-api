package startaideia.service;

import startaideia.domain.Tool;
import startaideia.repository.ToolRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter
public class ToolService {

    @Autowired
    private ToolRepository repository;

    public Tool insertTool(Tool tool) {
        return repository.insert(tool);
    }

    public List<Tool> listTools() {
        return repository.findAll();
    }

    public List<Tool> listToolsByTag(String tag) {
        return repository.findAll()
                .stream()
                .filter(tool -> tool.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    public void deleteTool(String id) {
        repository.deleteById(id);
    }
}
