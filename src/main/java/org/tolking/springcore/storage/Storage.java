package org.tolking.springcore.storage;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.tolking.springcore.util.Constants.USER_ID;

public class Storage {
    @Getter
    private ConcurrentHashMap<Long, Map<String, String>> entityMap = new ConcurrentHashMap<>();
    private final Reader reader;
    private final CSVFormat csvFormat;

    @SneakyThrows
    public Storage(String fileName){
        reader = new FileReader("src" + fileName);
        csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();
    }

    @PostConstruct
    @SneakyThrows
    public void init(){
        Iterable<CSVRecord> records = csvFormat.parse(reader);

        for (var record : records){
            int uniqueId;
            try {
                uniqueId = Integer.parseInt(record.get(USER_ID));
            }catch (IllegalArgumentException e){
                uniqueId = record.hashCode();
            }

            entityMap.put((long) uniqueId, record.toMap());
        }
    }
}
