package az.emanat.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateRequestDto implements Serializable {
    private String name;
    private String surname;
    private String age;
    private String salary;
}
