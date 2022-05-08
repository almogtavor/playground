package real.world.data.pipelines.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ExampleEnrichment extends ExampleBaseModel {
    private String itemId;
    private Date receptionTime;
    private String coolEnrichment;
}
