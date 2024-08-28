package ru.andr.views;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuShower {
    private static MenuShower menuShower;

    public static MenuShower getInstance() {
        if(menuShower == null)
            menuShower = new MenuShower();
        return menuShower;
    }

    public void showMainMenu() {
        System.out.println("""
                ----------------------------------------------------
                Выберите необходимое действие
                a: Загрузить данные из файла
                b: Создать новый файл
                c: Выход
                """);
    }

    public void showEditMenu() {
        System.out.println("""
                ------------------------------------------
                a: Добавьте нового ученика
                b: Удалите ученика
                c: Обновите оценку ученика
                d: Просмотр оценок всех учащихся
                е: Просмотр оценок конкретного учащегося
                f: Выход
                """);
    }
}
