package ex04;

import ex02.ViewResult;

/**
 * Консольна команда для відображення поточних результатів.
 * Реалізує ConsoleCommand та дозволяє користувачу переглядати поточні обчислення.
 * Скасування для цієї команди не передбачено, оскільки вона не змінює стан програми.
 */
public class ViewConsoleCommand implements ConsoleCommand {
    private ViewResult view;

    /**
     * Конструктор ViewConsoleCommand.
     * @param view Об'єкт ViewResult для відображення результатів.
     */
    public ViewConsoleCommand(ViewResult view) {
        this.view = view;
    }

    /**
     * Повертає ключ команди для меню.
     * @return Ключ команди "view".
     */
    @Override
    public String getKey() {
        return "view";
    }

    /**
     * Повертає інформацію про команду для відображення в меню.
     * @return Опис команди "Показати поточні результати (view)".
     */
    @Override
    public String getInfo() {
        return "Показати поточні результати (view)";
    }

    /**
     * Виконує команду відображення результатів.
     * Показує поточні результати обчислень, використовуючи ViewResult.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    @Override
    public void execute() throws Exception {
        view.viewShow();
    }

    /**
     * Скасування для команди перегляду не передбачено.
     * Виводить повідомлення про відсутність скасування для даної команди.
     * @throws Exception Виникає у разі помилок при скасуванні команди.
     */
    @Override
    public void undo() throws Exception {
        System.out.println("Скасування для перегляду не передбачено.");
    }
}