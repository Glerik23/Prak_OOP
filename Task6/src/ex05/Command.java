package ex05;

/**
 * Інтерфейс Command для представлення завдань, що виконуються в черзі.
 */
public interface Command {
    /**
     * Виконує команду.
     * @throws InterruptedException якщо виконання команди перервано.
     */
    void execute() throws InterruptedException;
}