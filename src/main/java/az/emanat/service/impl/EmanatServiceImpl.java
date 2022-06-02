package az.emanat.service.impl;

import az.emanat.data.dao.Database;
import az.emanat.data.dto.EmanatRequestDto;
import az.emanat.service.EmanatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmanatServiceImpl implements EmanatService {

    private final Database database;

    @Override
    public int insert(EmanatRequestDto requestDto) throws IOException {
        return database.insert(requestDto.getTableName(), requestDto.getValues());
    }
}
