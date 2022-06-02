package az.emanat.controller;

import az.emanat.data.dao.Database;
import az.emanat.data.dto.InsertRequestDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Emanat Processing RESTful Services", value = "PaymentController", description = "Controller for Emanat Service")
public class EmanatController {

    private final Database database;

    @PostMapping(value = "/insert")
    @ApiOperation(value = "Insert table-name and values")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful code"),
                    @ApiResponse(code = 400, message = "Bad Request")
            }
    )
    public int insert(
            @ApiParam("Insert operation ")
            @RequestBody InsertRequestDto requestDto
    ) {
        return database.insert(requestDto.getTableName(),requestDto.getValues());
    }

    @GetMapping(value = "/select{id}")
    @ApiOperation(value = "select data from table-name by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful code"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found")
            })
    public ResponseEntity<List<String>> selectById(@RequestParam String tableName, @PathVariable Long id) {
        return ResponseEntity.ok(database.select(tableName, id));
    }

    @PutMapping(value = "/update/{id}")
    @ApiOperation(value = "update data from table-name given by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful code"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found")
            })
    public HttpStatus updateById(@RequestParam List<String> values, @PathVariable Long id, @RequestParam String tableName) {
        database.update(tableName,id,values);
        return HttpStatus.OK;
    }
}
