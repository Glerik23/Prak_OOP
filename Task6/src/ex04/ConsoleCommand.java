package ex04;

/**
 * Інтерфейс ConsoleCommand розширює інтерфейс Command для консольних команд.
 * Додає методи для отримання ключа команди та інформації про команду для меню.
 */
public interface ConsoleCommand extends Command {
    /**
     * Повертає ключ команди, який використовується для ідентифікації команди в меню.
     * @return Ключ команди (наприклад, "side", "view").
     */
    String getKey();

    /**
     * Повертає інформацію про команду для відображення в меню користувачу.
     * @return Опис команди для меню.
     */
    String getInfo();
}