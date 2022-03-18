package flink.playground.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Son {
    private String itemId;
    private Date receptionTime;
    private String opId;
}
