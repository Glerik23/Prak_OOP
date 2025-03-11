package ex04;

import ex02.Calc;
import ex02.ViewResult;

/**
 * Консольна команда для збереження поточних результатів обчислень.
 * Реалізує ConsoleCommand та дозволяє користувачу зберігати дані у файл.
 * Скасування для цієї команди не передбачено.
 */
public class SaveConsoleCommand implements ConsoleCommand {
    private Calc calc;
    private ViewResult view;

    /**
     * Конструктор SaveConsoleCommand.
     * @param calc Об'єкт Calc для виконання операцій.
     * @param view Об'єкт ViewResult для відображення результатів.
     */
    public SaveConsoleCommand(Calc calc, ViewResult view) {
        this.calc = calc;
        this.view = view;
    }

    /**
     * Повертає ключ команди для меню.
     * @return Ключ команди "save".
     */
    @Override
    public String getKey() {
        return "save";
    }

    /**
     * Повертає інформацію про команду для відображення в меню.
     * @return Опис команди "Зберегти поточні результати (save)".
     */
    @Override
    public String getInfo() {
        return "Зберегти поточні результати (save)";
    }

    /**
     * Виконує команду збереження результатів.
     * Зберігає поточні результати обчислень Calc у файл та відображає їх.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    @Override
    public void execute() throws Exception {
        calc.save();
        System.out.println("Збережено.");
        view.viewShow();
    }

    /**
     * Скасування операції збереження не передбачено.
     * Виводить повідомлення про відсутність скасування для даної команди.
     * @throws Exception Виникає у разі помилок при скасуванні команди.
     */
    @Override
    public void undo() throws Exception {
        System.out.println("Скасування збереження не передбачено.");
    }
}