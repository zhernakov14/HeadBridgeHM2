package ru.andr;

import lombok.Getter;
import ru.andr.services.DataService;
import ru.andr.services.EditorMenuService;
import ru.andr.services.MainMenuService;
import ru.andr.views.MenuShower;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class Initializer {
    @Getter
    private static DataService dataService;
    @Getter
    private static MainMenuService mainMenuService;
    @Getter
    private static EditorMenuService editorMenuService;
    @Getter
    private static MenuShower menuShower;

    public static void init() {
        Properties props = new Properties();
        try {
            props.load((new InputStreamReader(Objects.requireNonNull(Initializer.class.getClassLoader()
                    .getResourceAsStream("application.properties")), StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Ошибка инициализации!Не найден файл application.properties, программа будет завершена");
            System.exit(1);
        }

        if (dataService == null) {
            String dataPath = props.getProperty("data.path");
            if (dataPath == null) {
                System.out.println("Ошибка инициализации! Не указан путь к файлу с данными в файле application.properties, программа будет завершена.");
                System.exit(1);
            } else {
                dataService = DataService.getInstance(dataPath);
            }
        }

        if (menuShower == null) {
            menuShower = MenuShower.getInstance();
        }

        if (editorMenuService == null) {
            editorMenuService = EditorMenuService.getInstance(dataService, menuShower);
        }

        if (mainMenuService == null) {
            mainMenuService = MainMenuService.getInstance(dataService, editorMenuService, menuShower);
        }
    }
}
