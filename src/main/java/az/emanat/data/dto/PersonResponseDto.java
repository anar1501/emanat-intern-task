package az.emanat.data.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class PersonResponseDto {
    private String id;
    private String name;
    private String surname;
    private String age;
    private String salary;
}
