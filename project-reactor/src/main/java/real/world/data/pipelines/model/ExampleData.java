package real.world.data.pipelines.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
public class ExampleData {
    private String itemId;
    private Date receptionTime;
    private String hopId;
    private String coolId;
    private String source;
    private List<File> files;
}
