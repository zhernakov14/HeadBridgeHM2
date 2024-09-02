package ru.andr.services;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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
           System.out.printf("Не найден файл %s\nНажмите \"Создать новый файл\"\n", dataPath);
           return false;
        }
        try (Stream<String> lines = Files.lines(path)) {
            lines.filter(line -> line.contains(": ")).forEach(line -> {
                String[] afterSplit = line.split(": ");
                String name = afterSplit[0];
                if(afterSplit.length > 1) {
                    String[] marks = afterSplit[1].split(", ");
                    data.put(name, new ArrayList<>(Arrays.asList(marks)));
                }
                else
                    data.put(name, new ArrayList<>());

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

    public boolean addNewStudent(String name) {
        if(data.containsKey(name)) {
            System.out.println("Ученик с таким именем уже существует");
            return false;
        }
        data.put(name, new ArrayList<>());
        return true;
    }

    public boolean removeStudent(String name) {
        if(!data.containsKey(name)) {
            System.out.println("Ученика с таким именем не существует");
            return false;
        }
        data.remove(name);
        return true;
    }

    public void saveDataToFile() {
        try (FileWriter fileWriter = new FileWriter(dataPath, false)) {
            data.forEach((name, marks) -> {
                try {
                    fileWriter.append(name);
                    fileWriter.append(": ");
                    fileWriter.append(String.join(", ", marks));
                    fileWriter.append("\n");
                } catch (IOException e) {
                    System.out.println("Не удалось записать данные в файл");
                }
            });
        } catch (IOException e) {
            System.out.println("Не удалось записать данные в файл");
        }
    }

    public boolean addMark(Scanner scanner, String name) {
        if(!data.containsKey(name)) {
            System.out.println("Ученика с таким именем не существует");
            return false;
        }
        System.out.println("Введите оценку от 1 до 5");
        String mark = enterMark(scanner);
        data.get(name).add(mark);
        return true;
    }

    public boolean editMark(Scanner scanner, String name) {
        if(data.get(name).isEmpty()) {
            System.out.println("У данного ученика нет оценок");
            return false;
        }
        if(!showMarksByName(name)) {
            return false;
        }
        System.out.println("Введите порядковый номер оценки, которую хотите изменить");
        String number = scanner.nextLine();
        String lengthList = String.valueOf(data.get(name).size());
        StringBuilder regex = new StringBuilder("[1-").append(lengthList).append("]");
        while(!number.matches(regex.toString())) {
            System.out.printf("Введите порядковый номер оценки от 1 до %s\n", lengthList);
            number = scanner.nextLine();
        }

        System.out.println("Введите новую оценку");
        String newMark = enterMark(scanner);
        data.get(name).set(Integer.parseInt(number) - 1, newMark);
        return true;
    }

    public void showAllMarks() {
        data.forEach((name, marks) -> System.out.println(name + " " + marks));
    }

    public boolean showMarksByName(String name) {
        if(!data.containsKey(name)) {
            System.out.println("Ученика с таким именем не существует");
            return false;
        }
        System.out.println(data.get(name));
        return true;
    }

    private String enterMark(Scanner scanner) {
        String mark = scanner.nextLine();
        while(!mark.matches("[12345]")) {
            System.out.println("Введите оценку от 1 до 5");
            mark = scanner.nextLine();
        }
        return mark;
    }


}
