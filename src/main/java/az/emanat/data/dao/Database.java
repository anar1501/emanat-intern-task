package az.emanat.data.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Database {
    /**
     * Stores the given values in the given table as a new row, returning the ID of the newly inserted row. If the table
     * does not exist, it must be created.
     *
     * <p>Once an ID is returned, the row is guaranteed to be retrievable by subsequent {@link #select} calls.
     *
     * @param tableName identifies the table to insert the values into. If the table does not exist, it will be created.
     * @param values a list of values to populate the new row
     * @return a numerical ID that uniquely identifies the newly inserted row within the table
     */
    int insert(String tableName, List<String> values) throws IOException;

    /**
     * Replaces the values of the row identified by the given ID in the given table.
     *
     * @param tableName identifies the table containing the row to be updated
     * @param rowId the numerical ID identifying the row to be updated
     * @param values a list of values to replace the current row values with
     * @throws NotFoundException if the table / row ID combination did not match an existing row
     */
    void update(String tableName, int rowId, List<String> values);

    /**
     * Retrieves the values associated with the given row ID in the given table (as previously inserted / updated).
     *
     * @param tableName identifies the table containing the row to be retrieved
     * @param rowId the numerical ID identifying the row to be retrieved
     * @return the list of values associated with the given table / row ID
     * @throws NotFoundException if the table / row ID combination did not match an existing row
     */
    List<String> select(String tableName, int rowId);
}

