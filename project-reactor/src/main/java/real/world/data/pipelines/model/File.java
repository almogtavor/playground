package real.world.data.pipelines.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class File {
    private String itemId;
    private String parentId;
    private Date receptionTime;
    private String hopId;
    private String coolId;
    private String text;
}
