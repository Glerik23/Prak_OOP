package ex04;

import ex02.Calc;
import ex02.ViewResult;
import ex02.GeometryData;

/**
 * Консольна команда для відновлення збережених результатів.
 * Реалізує ConsoleCommand та дозволяє користувачу відновлювати дані з файлу.
 * Підтримує скасування операції відновлення.
 */
public class RestoreConsoleCommand implements ConsoleCommand {
    private Calc calc;
    private ViewResult view;
    private GeometryData previousData;

    /**
     * Конструктор RestoreConsoleCommand.
     * @param calc Об'єкт Calc для виконання операцій.
     * @param view Об'єкт ViewResult для відображення результатів.
     */
    public RestoreConsoleCommand(Calc calc, ViewResult view) {
        this.calc = calc;
        this.view = view;
    }

    /**
     * Повертає ключ команди для меню.
     * @return Ключ команди "restore".
     */
    @Override
    public String getKey() {
        return "restore";
    }

    /**
     * Повертає інформацію про команду для відображення в меню.
     * @return Опис команди "Відновити збережені результати (restore)".
     */
    @Override
    public String getInfo() {
        return "Відновити збережені результати (restore)";
    }

    /**
     * Виконує команду відновлення збережених результатів.
     * Зберігає поточні дані, відновлює дані з файлу в Calc та відображає результати.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    @Override
    public void execute() throws Exception {
        previousData = calc.getResult();
        calc.restore();
        System.out.println("Відновлено.");
        view.setResult(calc.getResult());
        view.viewShow();
    }

    /**
     * Скасовує команду відновлення збережених результатів.
     * Відновлює дані Calc до стану, який був до виконання команди відновлення.
     * @throws Exception Виникає у разі помилок при скасуванні команди.
     */
    @Override
    public void undo() throws Exception {

        if (previousData != null) {
            calc.setResult(previousData);
            view.setResult(calc.getResult());
            view.viewShow();
            System.out.println("Відновлення скасовано до попереднього стану.");
        } else {
            System.out.println("Неможливо скасувати відновлення, попередній стан невідомий.");
        }
    }
}