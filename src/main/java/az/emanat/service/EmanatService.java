package az.emanat.service;

import az.emanat.data.dto.EmanatRequestDto;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface EmanatService {
    int insert(EmanatRequestDto requestDto) throws IOException;
}
