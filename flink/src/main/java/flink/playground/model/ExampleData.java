package flink.playground.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ExampleData {
    private String itemId;
    private Date receptionTime;
    private String hopId;
    private String coolId;
}
