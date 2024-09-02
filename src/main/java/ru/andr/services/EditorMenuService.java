package ru.andr.services;

import lombok.RequiredArgsConstructor;
import ru.andr.views.MenuShower;

import java.util.Scanner;

@RequiredArgsConstructor
public class EditorMenuService {
    private static EditorMenuService editorMenuService;
    private final DataService dataService;
    private final MenuShower menuShower;

    public static EditorMenuService getInstance(DataService dataService, MenuShower menuShower) {
        if(editorMenuService == null)
            editorMenuService = new EditorMenuService(dataService, menuShower);
        return editorMenuService;
    }

    public void start(Scanner scanner) {
        String input = "";
        while(!input.equals("g")) {
            input = scanner.nextLine();
            switch (input) {
                case "a":
                    System.out.println("Введите имя нового ученика");
                    if(dataService.addNewStudent(scanner.nextLine().toLowerCase())) {
                        System.out.println("Новый ученик добавлен");
                    }
                    menuShower.showEditMenu();
                    break;
                case "b":
                    System.out.println("Введите имя удаляемого ученика");
                    if(dataService.removeStudent(scanner.nextLine().toLowerCase())) {
                        System.out.println("Ученик удален");
                    }
                    menuShower.showEditMenu();
                    break;
                case "c":
                    System.out.println("Введите имя ученика");
                    if(dataService.addMark(scanner, scanner.nextLine().toLowerCase())) {
                        System.out.println("Оценка добавлена");
                    }
                    menuShower.showEditMenu();
                    break;
                case "d":
                    System.out.println("Введите имя ученика");
                    if(dataService.editMark(scanner, scanner.nextLine().toLowerCase())) {
                        System.out.println("Оценка изменена");
                    }
                    menuShower.showEditMenu();
                    break;
                case "e":
                    dataService.showAllMarks();
                    menuShower.showEditMenu();
                    break;
                case "f":
                    System.out.println("Введите имя ученика");
                    String name = scanner.nextLine();
                    dataService.showMarksByName(name);
                    menuShower.showEditMenu();
                    break;
                case "g":
                    dataService.saveDataToFile();
                    menuShower.showMainMenu();
                    break;
            }
        }

    }

}

