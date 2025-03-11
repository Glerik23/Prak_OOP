package ex04;

import java.util.List;
import java.util.ArrayList;

/**
 * Клас MacroCommand реалізує макрокоманду, що складається з декількох підкоманд.
 * Дозволяє виконувати послідовність команд як одну операцію та підтримує скасування для всіх підкоманд.
 */
public class MacroCommand implements ConsoleCommand {
    private List<ConsoleCommand> commands = new ArrayList<>();
    private String key;
    private String info;

    /**
     * Конструктор MacroCommand.
     * @param key Ключ макрокоманди для меню.
     * @param info Інформація про макрокоманду для відображення в меню.
     */
    public MacroCommand(String key, String info) {
        this.key = key;
        this.info = info;
    }

    /**
     * Додає підкоманду до макрокоманди.
     * @param cmd ConsoleCommand, яку потрібно додати до макрокоманди.
     */
    public void add(ConsoleCommand cmd) {
        commands.add(cmd);
    }

    /**
     * Повертає ключ макрокоманди для меню.
     * @return Ключ макрокоманди.
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * Повертає інформацію про макрокоманду для відображення в меню.
     * @return Опис макрокоманди.
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * Виконує всі підкоманди, що входять до складу макрокоманди, послідовно.
     * @throws Exception Виникає у разі помилок при виконанні будь-якої з підкоманд.
     */
    @Override
    public void execute() throws Exception {
        for (Command cmd : commands) {
            cmd.execute();
        }
    }

    /**
     * Скасовує виконання макрокоманди, скасовуючи підкоманди у зворотному порядку.
     * @throws Exception Виникає у разі помилок при скасуванні будь-якої з підкоманд.
     */
    @Override
    public void undo() throws Exception {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }

    public List<ConsoleCommand> getCommands() {
        return commands;
    }
}