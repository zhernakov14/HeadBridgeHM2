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
        menuShower.showEditMenu();
        String input = "";

        while(!input.equals("c"))
            input = scanner.nextLine();
            switch (input) {
                case "a":

            }
    }

}

