package az.emanat.data.dao;

import az.emanat.data.dto.PersonResponseDto;

import java.util.Collection;
import java.util.List;

public interface Database {

    int insert(String tableName, List<String> values);

    /**
     * Replaces the values of the row identified by the given ID in the given table.
     *
     * @param tableName identifies the table containing the row to be updated
     * @param rowId     the numerical ID identifying the row to be updated
     * @param values    a list of values to replace the current row values with
     * @throws NotFoundException if the table / row ID combination did not match an existing row
     */
    void update(String tableName, Long rowId, List<String> values);

    PersonResponseDto selectById(String tableName, Long rowId);
}

