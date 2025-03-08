package ex04;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Клас Menu - контейнер для консольних команд.
 * Відповідає за управління командами, їх реєстрацію, виконання та скасування.
 */
public class Menu {
    private Map<String, ConsoleCommand> commands = new HashMap<>();
    private Stack<Command> history = new Stack<>();

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
     * Додає виконану команду до історії для можливості скасування.
     * @param inputLine Рядок вводу користувача, що містить команду та можливі аргументи.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    public void executeCommand(String inputLine) throws Exception {
        String[] parts = inputLine.split(" ", 2);
        String commandKey = parts[0];
        String commandArgument = (parts.length > 1) ? parts[1] : null;

        ConsoleCommand command = commands.get(commandKey);
        if (command != null) {

            if (command instanceof ChangeConsoleCommand) {
                ((ChangeConsoleCommand) command).execute(commandArgument);
            } else {
                command.execute();
            }
            history.push(command);
        } else {
            System.out.println("Невідома команда.");
        }
    }

    /**
     * Скасовує останню виконану команду.
     * Використовує історію команд для виклику методу undo останньої команди.
     * @throws Exception Виникає у разі помилок при скасуванні команди.
     */
    public void undoLastCommand() throws Exception {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
            System.out.println("Останню команду скасовано.");
        } else {
            System.out.println("Немає команд для скасування.");
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
        System.out.println("undo - Скасувати останню операцію");
        System.out.println("exit - Вихід");
        System.out.println("Введіть команду:");
    }

    /**
     * Повертає мапу всіх зареєстрованих команд.
     * @return Map, де ключ - ключ команди, значення - об'єкт ConsoleCommand.
     */
    public Map<String, ConsoleCommand> getCommands() {
        return commands;
    }
}