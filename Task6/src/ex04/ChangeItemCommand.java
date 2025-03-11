package ex04;

import ex02.Calc;

/**
 * Команда для зміни довжини сторони в об'єкті Calc.
 * Реалізує інтерфейс Command та забезпечує можливість скасування операції зміни сторони.
 */
public class ChangeItemCommand implements Command {
    private Calc calc;
    private int oldSide;
    private int newSide;

    /**
     * Конструктор ChangeItemCommand.
     * @param calc Об'єкт Calc, для якого виконується зміна.
     * @param newSide Нова довжина сторони, яку потрібно встановити.
     */
    public ChangeItemCommand(Calc calc, int newSide) {
        this.calc = calc;
        this.newSide = newSide;
        this.oldSide = calc.getResult().getSideLength();
    }

    /**
     * Виконує команду зміни довжини сторони.
     * Ініціалізує обчислення в Calc з новою довжиною сторони.
     * @throws Exception Виникає у разі помилок при виконанні команди.
     */
    @Override
    public void execute() throws Exception {
        calc.init(newSide);
    }

    /**
     * Скасовує команду зміни довжини сторони.
     * Відновлює попередню довжину сторони, яка була до виконання команди.
     * @throws Exception Виникає у разі помилок при скасуванні команди.
     */
    @Override
    public void undo() throws Exception {
        calc.init(oldSide);
    }
}