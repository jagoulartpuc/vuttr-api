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

import java.util.Arrays;
import java.util.List;

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
    public void toolServiceShouldlistTools() {
        Tool tool1 = new Tool();
        Tool tool2 = new Tool();
        Tool tool3 = new Tool();

        given(repository.findAll()).willReturn(Arrays.asList(tool1, tool2, tool3));

        List<Tool> toolsReturned = service.listTools();
        Assert.assertEquals(Arrays.asList(tool1, tool2, tool3), toolsReturned);
    }

    @Test
    public void toolServiceShouldlistToolsByTag() {
        Tool tool1 = new Tool();
        tool1.setTags(Arrays.asList("tag1", "tag2", "tag3", "tag4"));

        Tool tool2 = new Tool();
        tool2.setTags(Arrays.asList("tag1", "tag2"));

        Tool tool3 = new Tool();
        tool3.setTags(Arrays.asList("tag1", "tag2", "tag3"));

        given(repository.findAll()).willReturn(Arrays.asList(tool1, tool2, tool3));

        List<Tool> toolsReturned = service.listToolsByTag("tag3");
        Assert.assertEquals(Arrays.asList(tool1, tool3), toolsReturned);
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