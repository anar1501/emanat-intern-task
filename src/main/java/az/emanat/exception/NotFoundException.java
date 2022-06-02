package az.emanat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Id mövcud deyil!")
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Id mövcud deyil!");
    }
}
