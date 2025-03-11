package ex05;

/**
 * Головний клас програми, що запускає консольний інтерфейс та паралельну обробку колекції.
 */
public class Main {

    /**
     * Головний метод програми.
     * Створює чергу команд, меню та запускає діалоговий інтерфейс з користувачем.
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        CommandQueue queue = new CommandQueue();
        Menu menu = new Menu();

        try {
            menu.runMenu(queue);
        } catch (Exception e) {
            System.err.println("Виникла помилка під час роботи програми: " + e.getMessage());
            e.printStackTrace();
        } finally {
            queue.stopQueue();
            try {
                queue.join();
            } catch (InterruptedException e) {
                System.err.println("Переривання під час очікування завершення Worker Thread: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}