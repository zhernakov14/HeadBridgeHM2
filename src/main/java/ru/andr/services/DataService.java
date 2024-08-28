package ru.andr.services;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DataService {
    private static DataService dataService;
    private final String dataPath;
    @Getter
    private final Map<String, List<String>> data;

    public static DataService getInstance(String dataPath) {
        if (dataService == null) {
            dataService = new DataService(dataPath, new HashMap<>());
        }
        return dataService;
    }

    public boolean loadDataFromFile() {
        Path path = Paths.get(dataPath);
        if(!Files.exists(path)) {
           System.out.printf("Не найдем файл в %s\n", dataPath);
           return false;
        }
        try (Stream<String> lines = Files.lines(path)) {
            lines.filter(line -> line.contains(", ")).forEach(line -> {
                String[] afterSplit = line.split(", ");
                String name = afterSplit[0];
                String[] marks = Arrays.copyOfRange(afterSplit, 1, afterSplit.length);
                data.put(name, Arrays.asList(marks));
            });
            return true;
        } catch (IOException e) {
            System.out.println("Не удалось сохранить данные");
            return false;
        }
    }

    public boolean createDataFile() {
        try {
            Files.deleteIfExists(Path.of(dataPath));
            if(!data.isEmpty())
                data.clear();
            return true;
        } catch (IOException e) {
            System.out.println("Не удалось создать файл");
            return false;
        }
    }
}
