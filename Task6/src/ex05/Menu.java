package ex05;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Клас Menu - контейнер для консольних команд.
 * Відповідає за управління командами, їх реєстрацію та виконання через консольний інтерфейс.
 */
public class Menu {
    private Map<String, ConsoleCommand> commands = new HashMap<>();

    /**
     * Додає команду до меню.
     * @param command ConsoleCommand, яку потрібно додати до меню.
     */
    public void addCommand(ConsoleCommand command) {
        commands.put(command.getKey(), command);
    }

    /**
     * Видаляє команду з меню за її ключем.
     * @param key Ключ команди, яку потрібно видалити.
     */
    public void removeCommand(String key) {
        commands.remove(key);
    }

    /**
     * Виконує команду за заданим ключем.
     * @param commandKey Ключ команди, яку потрібно виконати.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    public void executeCommand(String commandKey) throws Exception {
        ConsoleCommand command = commands.get(commandKey);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Невідома команда.");
        }
    }

    /**
     * Виводить меню команд у консоль.
     * Показує доступні команди та їх описи.
     */
    public void showMenu() {
        System.out.println("\n=== Меню ===");
        for (ConsoleCommand command : commands.values()) {
            System.out.println(command.getKey() + " - " + command.getInfo());
        }
        System.out.println("exit - Вихід");
        System.out.println("Введіть команду:");
    }

    /**
     * Запускає діалоговий інтерфейс з користувачем, обробляючи введені команди.
     * @param queue Черга команд для виконання.
     * @throws Exception Виникає у разі помилок вводу/виводу або виконання команд.
     */
    public void runMenu(CommandQueue queue) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;

        addCommand(new ExecuteConsoleCommand(queue));

        while (true) {
            showMenu();
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Завершення програми.");
                break;
            } else {
                try {
                    executeCommand(input);
                } catch (Exception e) {
                    System.out.println("Помилка виконання команди: " + e.getMessage());
                }
            }
        }
        scanner.close();
    }
}