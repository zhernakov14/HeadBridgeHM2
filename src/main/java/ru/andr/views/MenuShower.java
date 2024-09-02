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
                c: Выйти
                """);
    }

    public void showEditMenu() {
        System.out.println("""
                ------------------------------------------
                a: Добавить нового ученика
                b: Удалить ученика
                c: Добавить оценку ученику
                d: Изменить оценку ученика
                e: Просмотр оценок всех учеников
                f: Просмотр оценок конкретного ученика
                g: Сохранить и выйти
                """);
    }
}
