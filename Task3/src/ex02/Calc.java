package ex02;

import java.io.IOException;

/**
 * Клас для обчислення та збереження площ геометричних фігур.
 */
public class Calc {

    private GeometryData result;

    /**
     * Конструктор за замовчуванням класу Calc.
     * Ініціалізує поле result новим об'єктом GeometryData.
     */
    public Calc() {
        result = new GeometryData();
    }

    /**
     * Встановлює об'єкт GeometryData для збереження результатів обчислень.
     * @param result Об'єкт GeometryData, що містить дані для обчислень та результати.
     */
    public void setResult(GeometryData result) {
        this.result = result;
    }

    /**
     * Повертає поточний об'єкт GeometryData, що містить результати обчислень.
     * @return Об'єкт GeometryData з результатами обчислень.
     */
    public GeometryData getResult() {
        return result;
    }

    /**
     * Ініціалізує обчислення площ для заданої сторони.
     * Обчислює площі трикутника та прямокутника на основі заданої довжини сторони та зберігає їх у об'єкті GeometryData.
     * @param side Довжина сторони для обчислення площ.
     */
    public void init(int side) {
        result.setSideLength(side);
        result.calculateAreas();
    }

    /**
     * Зберігає поточні результати обчислень у файл.
     * Використовує об'єкт ViewResult для збереження даних через механізм серіалізації.
     * @throws IOException Виникає у випадку помилок вводу/виводу при збереженні даних.
     */
    public void save() throws IOException {
        ViewableResult viewableResult = new ViewableResult();
        ViewResult viewResult = (ViewResult) viewableResult.getView();
        viewResult.setResult(result);
        viewResult.viewSave();
    }

    /**
     * Відновлює результати обчислень з файлу.
     * Використовує об'єкт ViewResult для відновлення даних через механізм десеріалізації та оновлює поточний об'єкт result.
     * @throws Exception Виникає у випадку помилок при відновленні даних.
     */
    public void restore() throws Exception {
        ViewableResult viewableResult = new ViewableResult();
        ViewResult viewResult = (ViewResult) viewableResult.getView();
        viewResult.viewRestore();
        result = viewResult.getResult();
        result.calculateAreas();
    }
}