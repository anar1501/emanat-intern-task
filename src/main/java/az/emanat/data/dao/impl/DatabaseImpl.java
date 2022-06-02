package az.emanat.data.dao.impl;

import az.emanat.data.dao.Database;
import az.emanat.service.EmanatService;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DatabaseImpl implements Database {

    List<String[]> theRows = new ArrayList<>();

    private static int autoIncrementId = 1;

    @Override
    public int insert(String tableName, List<String> values) {
        File file = new File(tableName + ".csv");
        if (!file.exists()) {
            return prepareCsvFileAndInsertDataToCsvFile(values, file);
        } else
            return prepareCsvFileAndInsertDataToCsvFile(values, file);
    }

    private int prepareCsvFileAndInsertDataToCsvFile(List<String> values, File file) {
        if (!file.exists()) {
            theRows.clear();
            autoIncrementId = 1;
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = new String[]{"id", "name", "surname", "age", "salary"};
            String id = String.valueOf(autoIncrementId);
            values.add(0, id);
            theRows.add(values.toArray(String[]::new));//arasdiracam
            writer.writeNext(header);
            writer.writeAll(theRows, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        autoIncrementId++;
        return Integer.parseInt(values.get(0));
    }

    @Override
    public void update(String tableName, int rowId, List<String> values) {

    }

    @Override
    public List<String> select(String tableName, int rowId) {
        return null;
    }
}
