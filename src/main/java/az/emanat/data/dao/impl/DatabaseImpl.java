package az.emanat.data.dao.impl;

import az.emanat.data.dao.Database;
import az.emanat.data.dto.InsertRequestDto;
import az.emanat.data.dto.PersonResponseDto;
import az.emanat.data.dto.UpdateRequestDto;
import az.emanat.exception.NotFoundException;
import az.emanat.service.EmanatService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DatabaseImpl implements Database {

    List<String[]> theRows = new ArrayList<>();

    private static int autoIncrementId = 1;

    @Override
    public int insert(String tableName, List<String> values) {
        File file = new File(tableName + ".csv");
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
    public void update(String tableName, Long rowId,List<String> values) {
//        PersonResponseDto personResponseDto = selectById(tableName, rowId);
//        personResponseDto.setName(requestDto.getName());
//        personResponseDto.setSurname(requestDto.getSurname());
//        personResponseDto.setAge(requestDto.getAge());
//        personResponseDto.setSalary(requestDto.getSalary());
//        List<String> updateData = new ArrayList<>();
//        updateData.add(personResponseDto.toString());
//        insert(tableName, updateData);

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


}
