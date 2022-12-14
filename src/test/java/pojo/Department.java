package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter@Setter
@ToString
public class Department {

    //instruction jackson to bind json field called department_id to below java field
    @JsonProperty("department_id")
    private int departmentId;  //json field : department_id
@JsonProperty("department_name")
    private String name;       //json field : department_name
    private int manager_id;
    private  int location_id;

}
