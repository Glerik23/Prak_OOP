package ex04;

import ex02.Calc;
import ex03.ViewTable;

/**
 * Консольна команда для зміни довжини сторони.
 */
public class ChangeConsoleCommand implements ConsoleCommand {
    private Calc calc;
    private ViewTable view;
    private ChangeItemCommand lastChangeItemCommand;
    
    /**
     * Конструктор ChangeConsoleCommand.
     * @param calc Об'єкт Calc для виконання операцій.
     * @param view Об'єкт ViewTable для відображення результатів у таблиці.
     */
    public ChangeConsoleCommand(Calc calc, ViewTable view) {
        this.calc = calc;
        this.view = view;
    }

    /**
     * Повертає ключ команди для меню.
     */
    @Override
    public String getKey() {
        return "side";
    }

    /**
     * Повертає інформацію про команду для відображення в меню.
     */
    @Override
    public String getInfo() {
        return "Встановити довжину сторони (side <довжина>)";
    }

    /**
     * Виконує команду зміни довжини сторони, запитуючи довжину у користувача (без аргументів в команді).
     */
    @Override
    public void execute() throws Exception {
        System.out.println("Введіть нову довжину сторони:");
        String sideStr = System.console().readLine();
        execute(sideStr);
    }

    /**
     * Перевантажений метод execute для обробки команди з аргументом (довжиною сторони).
     * Виконує команду зміни довжини сторони, використовуючи наданий аргумент.
     * @param sideArg Рядок, що представляє нову довжину сторони.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    public void execute(String sideArg) throws Exception {
        if (sideArg != null && !sideArg.trim().isEmpty()) {
            try {
                int side = Integer.parseInt(sideArg.trim());
                ChangeItemCommand changeItemCommand = new ChangeItemCommand(calc, side);
                lastChangeItemCommand = changeItemCommand;
                changeItemCommand.execute();
                view.setResult(calc.getResult());
                view.viewShow();
            } catch (NumberFormatException e) {
                System.out.println("Невірна довжина сторони: '" + sideArg + "'. Будь ласка, введіть ціле число.");
            }
        } else {
            System.out.println("Будь ласка, введіть довжину сторони після команди 'side'.");
        }
    }


    /**
     * Метод undo для ConsoleCommand, який викликає undo у збереженого ChangeItemCommand.
     */
    @Override
    public void undo() throws Exception {
        if (lastChangeItemCommand != null) {
            lastChangeItemCommand.undo();
        }
    }
}