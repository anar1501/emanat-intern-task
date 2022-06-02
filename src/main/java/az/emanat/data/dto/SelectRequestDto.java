package az.emanat.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SelectRequestDto implements Serializable {
    @ApiModelProperty(example = "table-name", notes = "table name", required = true, position = 1)
    private String tableName;
    @ApiModelProperty(example = "1", notes = "row id", required = true, position = 2)
    private Long id;
}
