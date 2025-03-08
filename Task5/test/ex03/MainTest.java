package ex03;

import ex02.Calc;
import ex02.GeometryData;
import ex02.ViewableResult;
import ex02.ViewResult;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Клас для тестування функціональності Calc, GeometryData та ViewTable в пакеті ex03.
 * Розширено для перевірки табличного виводу та його особливостей.
 * @author Glerik
 * @version 1.1
 */
public class MainTest {

    /**
     * Тестує обчислення площ та табличне відображення результатів.
     * Перевіряє коректність обчислень та базове відображення таблиці.
     */
    @Test
    public void testAreaCalculationAndViewTable() {
        Calc calc = new Calc();
        calc.init(5);

        ViewableTable viewableTable = new ViewableTable();
        ViewTable viewTable = (ViewTable) viewableTable.getView();
        viewTable.setResult(calc.getResult());

        GeometryData result = viewTable.getResult();

        assertEquals(10.825, result.getTriangleArea(), 0.001);
        assertEquals(25.0, result.getRectangleArea(), 0.001);

        
        viewTable.viewShow(40);
        assertEquals(40, viewTable.getWidth());
        viewTable.resetWidthToDefault();
        viewTable.viewShow();
        assertEquals(30, viewTable.getWidth());
    }

    /**
     * Тестує серіалізацію та відновлення даних, використовуючи табличний вивід.
     * Перевіряє, що дані коректно зберігаються та відновлюються при використанні ViewTable.
     */
    @Test
    public void testSerializationAndViewTableRestore() {
        Calc calc1 = new Calc();
        calc1.init(5);

        try {
             calc1.save();

            Calc calc2 = new Calc();
            calc2.restore();

            GeometryData result1 = calc1.getResult();
            GeometryData result2 = calc2.getResult();

            assertEquals(result1, result2); // Перевірка, що об'єкти GeometryData однакові після відновлення

            ViewableTable viewableTable = new ViewableTable();
            ViewTable viewTable = (ViewTable) viewableTable.getView();
            viewTable.setResult(calc2.getResult());
            viewTable.viewShow(); // Виводимо відновлений результат через ViewTable для візуальної перевірки

        } catch (Exception e) {
            fail("Помилка серіалізації/десеріалізації: " + e.getMessage());
        }
    }

    /**
     * Тестує основну функціональність звичайного виводу (без таблиці).
     * Перевіряє, що звичайний вивід ViewResult працює коректно.
     */
    @Test
    public void testAreaCalculationAndPlainView() {
        Calc calc = new Calc();
        calc.init(5);

        ViewableResult viewableResult = new ViewableResult();
        ViewResult viewResult = (ViewResult) viewableResult.getView();
        viewResult.setResult(calc.getResult());

        GeometryData result = viewResult.getResult();

        assertEquals(10.825, result.getTriangleArea(), 0.001);
        assertEquals(25.0, result.getRectangleArea(), 0.001);

        // Перевірка виводу viewResult.viewShow() тут може бути складною без перехоплення System.out.
        // Залишаємо базову перевірку обчислень.
    }

    /**
     * Тестує серіалізацію та відновлення даних для звичайного виводу.
     * Перевіряє, що серіалізація/десеріалізація працює коректно зі звичайним ViewResult.
     */
    @Test
    public void testSerializationAndPlainViewRestore() {
        Calc calc1 = new Calc();
        calc1.init(5);

        try {
            calc1.save();

            Calc calc2 = new Calc();
            calc2.restore();

            GeometryData result1 = calc1.getResult();
            GeometryData result2 = calc2.getResult();

            assertEquals(result1, result2); // Перевірка, що об'єкти GeometryData однакові після відновлення

            ViewableResult viewableResult = new ViewableResult();
            ViewResult viewResult = (ViewResult) viewableResult.getView();
            viewResult.setResult(calc2.getResult());
            viewResult.viewShow(); // Виводимо відновлений результат через ViewResult для візуальної перевірки

        } catch (Exception e) {
            fail("Помилка серіалізації/десеріалізації: " + e.getMessage());
        }
    }
}