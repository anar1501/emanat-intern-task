package az.emanat.service;

import az.emanat.data.dto.InsertRequestDto;
import az.emanat.data.dto.PersonResponseDto;
import az.emanat.data.dto.SelectRequestDto;

import java.io.IOException;

public interface EmanatService {
    int insert(InsertRequestDto requestDto) throws IOException;
    PersonResponseDto selectById(SelectRequestDto requestDto);

}
