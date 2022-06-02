package az.emanat.data.dao.impl;

import az.emanat.data.dao.Database;
import az.emanat.data.dto.PersonResponseDto;
import az.emanat.exception.NotFoundException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DatabaseImpl implements Database {

    List<String[]> theRows = new ArrayList<>();

    private static int autoIncrementId = 1;

    @SneakyThrows
    @Override
    public List<String> select(String tableName, Long rowId) {
        PersonResponseDto personResponseDto = selectById(tableName, rowId);
        if (personResponseDto == null) {
            throw new NotFoundException();
        }
        LinkedList<String> responseValue = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(tableName + ".csv")); CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br)) {
            for (CSVRecord record : parser) {
                long recordNumber = record.getRecordNumber();
                if (recordNumber == rowId) {
                    Map<String, String> stringStringMap = record.toMap();
                    for (Map.Entry<String, String> m : stringStringMap.entrySet()) {
                        responseValue.add(m.getValue());
                    }
                }
            }
        }
        return responseValue;
    }

    @Override
    public int insert(String tableName, List<String> values) {
        File file = new File(tableName + ".csv");
        return prepareCsvFileAndInsertDataToCsvFile(values, file);
    }

    @SneakyThrows
    @Override
    public void update(String tableName, Long rowId, List<String> values) {
        File file = new File(tableName + ".csv");
        if (!file.exists())
            throw new FileNotFoundException();
        List<String> select = select(tableName, rowId);
        if (select.isEmpty()) {
            throw new NotFoundException();
        }
        select.clear();
        select.add(String.valueOf(rowId));
        select.addAll(values);
        copyCsv(tableName, select, rowId);
    }


    @SneakyThrows
    @Override
    public PersonResponseDto selectById(String tableName, Long rowId) {
        PersonResponseDto responseDto;
        File file = new File(tableName + ".csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file)); CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br)) {
            Predicate<CSVRecord> predicate = ((record) -> record.get("id").equals(String.valueOf(rowId)));
            responseDto = parser.getRecords().stream().filter(predicate).map(this::preparePersonResponseDto).findFirst().orElseThrow(NotFoundException::new);
        }
        return responseDto;
    }


    private PersonResponseDto preparePersonResponseDto(CSVRecord record) {
        PersonResponseDto responseDto;
        responseDto = new PersonResponseDto();
        responseDto.setId(record.get("id"));
        responseDto.setName(record.get("name"));
        responseDto.setSurname(record.get("surname"));
        responseDto.setAge(record.get("age"));
        responseDto.setSalary(record.get("salary"));
        return responseDto;
    }

    @SneakyThrows
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
        }
        autoIncrementId++;
        return Integer.parseInt(values.get(0));
    }


    @SneakyThrows
    private void copyCsv(String tableName, List<String> values, Long rowId) {
        CSVReader reader2 = new CSVReader(new FileReader(tableName + ".csv"));
        List<String[]> allElements = reader2.readAll();
        allElements.add(Math.toIntExact(rowId), values.toArray(new String[0]));
        allElements.remove(Math.toIntExact(++rowId));
        CSVWriter writer = new CSVWriter(new FileWriter(tableName + ".csv"));
        writer.writeAll(allElements);
        writer.flush();
        writer.close();
    }
}
