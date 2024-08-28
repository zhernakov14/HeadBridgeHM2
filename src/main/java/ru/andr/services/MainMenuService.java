package ru.andr.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import ru.andr.views.MenuShower;

import java.util.Scanner;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MainMenuService {
    private static MainMenuService mainMenuService;
    private final DataService dataService;
    private final EditorMenuService editorMenuService;
    private final MenuShower menuShower;

    public static MainMenuService getInstance(DataService dataService, EditorMenuService editorMenuService, MenuShower menuShower) {
        if (mainMenuService == null) {
            mainMenuService = new MainMenuService(dataService, editorMenuService, menuShower);
        }
        return mainMenuService;
    }

    public void start() {
        menuShower.showMainMenu();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(!input.equals("c")) {
            input = scanner.nextLine();
            switch (input) {
                case "a":
                    if(dataService.loadDataFromFile()) {
                        System.out.println("Данные загружены");
                    }
                    menuShower.showMainMenu();
                    break;
                case "b":
                    if(dataService.createDataFile()) {
                        System.out.println("Файл создан");
                    }
                    menuShower.showMainMenu();
                    break;
                case "c":
                    System.out.println("Выход");
                default:
                    System.out.println("Выберите один из трех предложенных вариантов");
                    menuShower.showMainMenu();
            }
        }
    }

}
