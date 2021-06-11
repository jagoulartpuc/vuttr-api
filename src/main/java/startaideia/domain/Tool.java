package startaideia.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Tool {

    @Id
    private String id;
    private String title;
    private String link;
    private String description;
    private List<String> tags = new ArrayList<>();

}
