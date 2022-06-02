package az.emanat.service.impl;

import az.emanat.data.dao.Database;
import az.emanat.data.dto.InsertRequestDto;
import az.emanat.data.dto.PersonResponseDto;
import az.emanat.data.dto.SelectRequestDto;
import az.emanat.data.dto.UpdateRequestDto;
import az.emanat.service.EmanatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmanatServiceImpl implements EmanatService {

    private final Database database;

    @Override
    public int insert(InsertRequestDto requestDto) {
        return database.insert(requestDto.getTableName(), requestDto.getValues());
    }

    @Override
    public PersonResponseDto selectById(SelectRequestDto requestDto) {
        return database.selectById(requestDto.getTableName(), requestDto.getId());
    }



}
