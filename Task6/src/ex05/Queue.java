package ex05;

/**
 * Інтерфейс Queue для черги команд.
 */
public interface Queue {
    /**
     * Додає команду до черги.
     * @param command Команда для додавання.
     */
    void put(Command command);

    /**
     * Отримує та видаляє команду з черги.
     * @return Видалена команда.
     * @throws InterruptedException якщо очікування команди перервано.
     */
    Command take() throws InterruptedException;
}