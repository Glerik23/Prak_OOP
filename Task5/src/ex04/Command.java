package ex04;

/**
 * Інтерфейс Command для реалізації шаблону Command.
 * Оголошує методи для виконання та скасування операцій.
 */
public interface Command {
    /**
     * Метод для виконання команди.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    void execute() throws Exception;

    /**
     * Метод для скасування команди.
     * @throws Exception Виникає у разі помилок при скасуванні команди.
     */
    void undo() throws Exception;
}