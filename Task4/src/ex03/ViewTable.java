package ex03;

import ex02.GeometryData;
import ex02.ViewResult;
import java.util.Formatter;
import java.io.IOException;

/** ConcreteProduct (Шаблон проєктування Factory Method).
 * Клас для відображення результатів обчислень площ фігур у вигляді таблиці.
 * Розширює {@linkplain ViewResult}, реалізуючи табличне представлення даних.
 * @author Glerik
 * @version 1.1
 * @see ViewResult
 */
public class ViewTable extends ViewResult {

    /** Ширина таблиці за замовчуванням. */
    private static final int DEFAULT_WIDTH = 30;
    /** Поточна ширина таблиці. */
    private int width;

    /**
     * Конструктор за замовчуванням.
     * Встановлює ширину таблиці за замовчуванням {@linkplain ViewTable#DEFAULT_WIDTH}.
     */
    public ViewTable() {
        this(DEFAULT_WIDTH);
    }

    /**
     * Конструктор з параметром ширини таблиці.
     * @param width Ширина таблиці, яку потрібно встановити.
     */
    public ViewTable(int width) {
        super(); // Виклик конструктора суперкласу ViewResult
        this.width = width;
    }

    /**
     * Встановлює ширину таблиці.
     * @param width Нова ширина таблиці.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Повертає поточну ширину таблиці.
     * @return Ширина таблиці.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Встановлює ширину таблиці до значення за замовчуванням {@linkplain ViewTable#DEFAULT_WIDTH}.
     */
    public void resetWidthToDefault() {
        this.width = DEFAULT_WIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewHeader() {
        System.out.println("\nРезультати обчислень площ (Таблиця):");
        printLine();
        Formatter fmt = new Formatter();
        fmt.format("%s%-" + ((width/3) + 2) + "s | %-" + ((width/3) + 2) + "s | %-" + ((width/3) + 2) + "s | %-" + ((width/3) + 2) + "s%n",
                "", "Довжина сторони (бінарна)", "Площа трикутника", "Площа прямокутника", "Загальна площа");
        System.out.print(fmt);
        printLine();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewBody() {
        GeometryData data = getResult();
        if (data != null) {
            Formatter fmt = new Formatter();
            fmt.format("%s%-" + ((width/3) + 2) + "s | %-" + ((width/3) + 2) + ".3f | %-" + ((width/3) + 2) + ".3f | %-" + ((width/3) + 2) + ".3f%n",
                    "",
                    Integer.toBinaryString(data.getSideLength()),
                    data.getTriangleArea(),
                    data.getRectangleArea(),
                    data.getTotalArea());
            System.out.print(fmt);
        } else {
            System.out.println("Дані для відображення відсутні.");
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void viewFooter() {
        printLine();
        System.out.println("Кінець таблиці.");
    }

    /**
     * Друкує горизонтальну лінію для розділення частин таблиці.
     */
    private void printLine() {
        for (int i = 0; i < width * 2 + 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * {@inheritDoc}
     * Розширена для можливості задання ширини таблиці.
     */
    @Override
    public void viewInit(int side) {
        super.viewInit(side);
    }

    /**
     * Перевантаження методу {@linkplain ViewTable#viewInit(int side)} для встановлення ширини таблиці.
     * @param side Довжина сторони для ініціалізації обчислень.
     * @param width Ширина таблиці для встановлення.
     */
    public void viewInit(int side, int width) {
        this.width = width;
        super.viewInit(side);
    }

    /**
     * Перевантаження методу {@linkplain ViewTable#viewShow()} для використання заданої ширини таблиці.
     * Відображає таблицю з поточною шириною.
     */
    public void viewShow(int width) {
        this.width = width;
        viewShow();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void viewSave() throws IOException {
        super.viewSave();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void viewRestore() throws Exception {
        super.viewRestore();
    }
}