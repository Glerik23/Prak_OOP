package ex02;

import java.io.IOException;

/**
 * Клас для обчислення та збереження площ геометричних фігур.
 */
public class Calc {

    private GeometryData result;

    public Calc() {
        result = new GeometryData();
    }

    public void setResult(GeometryData result) {
        this.result = result;
    }

    public GeometryData getResult() {
        return result;
    }

    /**
     * Обчислює суму площ трикутника та прямокутника за заданою стороною.
     *
     * @param side Довжина сторони в двійковій системі.
     */
    public void init(int side) {
        result.setSideLength(side);
        result.calculateAreas();
    }

    /**
     * Зберігає результат у файл.
     *
     * @throws IOException Виникає при помилках вводу/виводу під час збереження.
     */
    public void save() throws IOException {
        ViewableResult viewableResult = new ViewableResult();
        ViewResult viewResult = (ViewResult) viewableResult.getView();
        viewResult.setResult(result); // Передаємо поточний result до ViewResult
        viewResult.viewSave();
    }

    /**
     * Відновлює результат з файлу.
     *
     * @throws Exception Виникає при помилках під час відновлення.
     */
    public void restore() throws Exception {
        ViewableResult viewableResult = new ViewableResult();
        ViewResult viewResult = (ViewResult) viewableResult.getView();
        viewResult.viewRestore();
        result = viewResult.getResult(); // Отримуємо відновлений result з ViewResult
        result.calculateAreas();
    }
}