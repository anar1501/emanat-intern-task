package az.emanat.data.dao;

import az.emanat.data.dto.PersonResponseDto;

import java.util.Collection;
import java.util.List;

public interface Database {
    int insert(String tableName, List<String> values);
    void update(String tableName, Long rowId, List<String> values);
    PersonResponseDto selectById(String tableName, Long rowId);
    List<String> select(String tableName, Long rowId);
}

