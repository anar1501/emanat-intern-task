package az.emanat.controller;

import az.emanat.data.dto.EmanatRequestDto;
import az.emanat.service.EmanatService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
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
            @RequestBody EmanatRequestDto requestDto
                     ) throws IOException {
        return emanatService.insert(requestDto);
    }
}
