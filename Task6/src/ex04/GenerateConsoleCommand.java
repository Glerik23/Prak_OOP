package ex04;

import ex02.Calc;
import ex02.ViewResult;
import java.util.Random;

/**
 * Консольна команда для генерації випадкової довжини сторони.
 * Реалізує ConsoleCommand та генерує випадкову сторону для обчислень.
 * Підтримує скасування операції генерації.
 */
public class GenerateConsoleCommand implements ConsoleCommand {
    private Calc calc;
    private ViewResult view;
    private int oldSide;
    private int newSide;

    /**
     * Конструктор GenerateConsoleCommand.
     * @param calc Об'єкт Calc для виконання операцій.
     * @param view Об'єкт ViewResult для відображення результатів.
     */
    public GenerateConsoleCommand(Calc calc, ViewResult view) {
        this.calc = calc;
        this.view = view;
    }

    /**
     * Повертає ключ команди для меню.
     * @return Ключ команди "generate".
     */
    @Override
    public String getKey() {
        return "generate";
    }

    /**
     * Повертає інформацію про команду для відображення в меню.
     * @return Опис команди "Згенерувати випадкову довжину сторони".
     */
    @Override
    public String getInfo() {
        return "Згенерувати випадкову довжину сторони";
    }

    /**
     * Виконує команду генерації випадкової довжини сторони.
     * Генерує випадкову сторону, ініціалізує обчислення в Calc та відображає результати.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    @Override
    public void execute() throws Exception {
        Random random = new Random();
        newSide = random.nextInt(100) + 1; // Генеруємо випадкову сторону від 1 до 100
        oldSide = calc.getResult().getSideLength();
        calc.init(newSide);
        view.setResult(calc.getResult());
        view.viewShow();
    }

    /**
     * Скасовує команду генерації випадкової довжини сторони.
     * Відновлює попередню довжину сторони, що була до генерації.
     * @throws Exception Виникає у разі помилок при скасуванні команди.
     */
    @Override
    public void undo() throws Exception {
        calc.init(oldSide); // Відновлюємо попередню сторону
        view.setResult(calc.getResult());
        view.viewShow();
    }
}