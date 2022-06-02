package az.emanat.data.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class PersonResponseDto {
    @CsvBindByPosition(position = 0)
    private String id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private String surname;
    @CsvBindByPosition(position = 3)
    private String age;
    @CsvBindByPosition(position = 4)
    private String salary;
}
