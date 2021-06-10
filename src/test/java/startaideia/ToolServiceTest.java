package startaideia;

import startaideia.domain.Tool;
import startaideia.repository.ToolRepository;
import startaideia.service.ToolService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ToolServiceTest {

    @InjectMocks
    private ToolService service;

    @Mock
    private ToolRepository repository;

    @Test
    public void toolServiceShouldInsertTool() {
        Tool tool = new Tool();

        given(repository.insert(tool)).willReturn(tool);

        service.insertTool(tool);
        Assert.assertEquals(service.insertTool(tool), repository.insert(tool));
    }

    @Test
    public void toolServiceShouldDeleteTool() {
        String id = "432423bh43534cvs";

        Tool tool = new Tool();
        tool.setId(id);
        service.insertTool(tool);
        service.deleteTool(id);
        assertTrue(service.listTools().isEmpty());
    }

}