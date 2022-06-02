package az.emanat.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EmanatRequestDto implements Serializable {
    @ApiModelProperty(example = "table-name", notes = "table name", required = true, position = 1)
    private String tableName;
    @ApiModelProperty(example = "\"Anar\",\"Mammadov\",\"25\",\"1500\",", notes = "row values", required = true, position = 2)
    private List<String> values;
}
