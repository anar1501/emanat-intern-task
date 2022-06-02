package az.emanat.exception;

import az.emanat.data.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(NotFoundException customerNotFoundException) {
        ErrorResponse ex = new ErrorResponse(customerNotFoundException.getMessage(), "404");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleFileNotFoundException(FileNotFoundException fileNotFoundException) {
        ErrorResponse ex = new ErrorResponse("Belə bir cədvəl mövcud deyil", "404");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

}
