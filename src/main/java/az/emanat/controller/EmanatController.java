package az.emanat.controller;

import az.emanat.data.dto.InsertRequestDto;
import az.emanat.data.dto.PersonResponseDto;
import az.emanat.data.dto.SelectRequestDto;
import az.emanat.data.dto.UpdateRequestDto;
import az.emanat.service.EmanatService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Api(tags = "Emanat Processing RESTful Services", value = "PaymentController", description = "Controller for Emanat Service")
public class EmanatController {

    private final EmanatService emanatService;

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
    ) throws IOException {
        return emanatService.insert(requestDto);
    }

    @GetMapping(value = "/select{id}")
    @ApiOperation(value = "select data from table-name by id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful code"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found")
            })
    public ResponseEntity<PersonResponseDto> selectById(@RequestBody SelectRequestDto requestDto) {
        return ResponseEntity.ok(emanatService.selectById(requestDto));
    }

    @PutMapping(value = "/update/{id}")
    public HttpStatus updateById(@RequestBody UpdateRequestDto requestDto, @PathVariable Long id, @RequestParam String tableName) {
        return HttpStatus.OK;
    }
}
