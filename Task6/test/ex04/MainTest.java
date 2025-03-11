package ex04;

import ex02.Calc;
import org.junit.Test;
import static org.junit.Assert.*; // Використовуємо JUnit 4 Assertions
import ex03.ViewableTable;
import ex03.ViewTable;
import java.util.Random;

/**
 * Клас MainTest містить JUnit тести для перевірки функціональності команд та шаблону Singleton.
 * ...
 */
public class MainTest {

    /**
     * Тестує виконання команди ChangeItemCommand та її скасування.
     * ...
     */
    @Test
    public void testChangeItemCommandExecuteUndo() throws Exception {
        Application app = Application.getInstance();
        Calc calc = app.getCalc();
        ViewableTable viewableTable = new ViewableTable();
        ViewTable view = (ViewTable) viewableTable.getView();
        view.setResult(calc.getResult());

        int initialSide = calc.getResult().getSideLength();
        int newSide = 10;

        ChangeItemCommand changeCommand = new ChangeItemCommand(calc, newSide);
        changeCommand.execute();
        assertEquals(newSide, calc.getResult().getSideLength()); // Виправлено порядок assertEquals
        changeCommand.undo();
        assertEquals(initialSide, calc.getResult().getSideLength()); // Виправлено порядок assertEquals
    }

    /**
     * Тестує шаблон Singleton для класу Application.
     * ...
     */
    @Test
    public void testSingletonApplication() {
        Application app1 = Application.getInstance();
        Application app2 = Application.getInstance();
        assertSame(app1, app2); // Перевірка, що це один і той самий об'єкт
    }

    /**
     * Тест для перевірки виконання команди ChangeConsoleCommand з випадковою довжиною сторони.
     * ...
     */
    @Test
    public void testChangeConsoleCommandWithRandomSide() throws Exception {
        Application app = Application.getInstance();
        Calc calc = app.getCalc();
        ViewableTable viewableTable = new ViewableTable();
        ViewTable view = (ViewTable) viewableTable.getView();
        view.setResult(calc.getResult());

        ChangeConsoleCommand sideCommand = new ChangeConsoleCommand(calc, view);

        // 1. Генеруємо випадкову довжину сторони
        Random random = new Random();
        int randomSide = random.nextInt(100) + 1;
        String sideArg = String.valueOf(randomSide);

        // 2. Виконуємо команду side, передаючи випадкову довжину сторони як аргумент
        sideCommand.execute(sideArg);

        // 3. Перевіряємо, що довжина сторони встановлена правильно
        assertEquals(randomSide, calc.getResult().getSideLength()); // Виправлено порядок assertEquals
    }


    /**
     * Тест для перевірки виконання макрокоманди та її скасування.
     * ...
     */
    @Test
    public void testMacroCommandExecuteUndo() throws Exception {
        Application app = Application.getInstance();
        Calc calc = app.getCalc();
        ViewableTable viewableTable = new ViewableTable();
        ViewTable view = (ViewTable) viewableTable.getView();
        view.setResult(calc.getResult());

        MacroCommand macroCommand = new MacroCommand("testmacro", "Тестова макрокоманда");

        int initialSide = calc.getResult().getSideLength(); // Запам'ятовуємо початкову довжину сторони
        int newSide = 20; // Нова довжина сторони для макрокоманди

        // Створюємо ChangeConsoleCommand і безпосередньо встановлюємо сторону в його методі execute
        ChangeConsoleCommand changeCmd = new ChangeConsoleCommand(calc, view) {
            @Override
            public void execute() throws Exception {
                execute(String.valueOf(newSide)); // Примусово використовуємо newSide в execute
            }
        };

        macroCommand.add(changeCmd); // Додаємо ChangeConsoleCommand
        macroCommand.add(new SaveConsoleCommand(calc, view)); // Додаємо SaveConsoleCommand

        macroCommand.execute(); // Виконуємо макрокоманду - ТЕПЕР execute команди ChangeConsoleCommand має запуститися

        assertEquals(newSide, calc.getResult().getSideLength()); // Перевірка - очікуємо 20 (рядок 99, де тест зазнає невдачі)
        macroCommand.undo(); // Скасовуємо макрокоманду

        assertEquals(initialSide, calc.getResult().getSideLength()); // Перевірка - має повернутися до початкової сторони
    }
    // Додаємо метод getCommands() в MacroCommand для доступу до списку команд
    // в цілях тестування (лише для тестування, в реальному коді краще уникати таких публічних методів для внутрішніх даних)
}