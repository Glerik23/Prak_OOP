package ex04;

import ex02.Calc;

/**
 * Клас Application, реалізований як Singleton, керує основними аспектами програми.
 * Забезпечує єдину точку доступу до об'єкту Calc та інших глобальних ресурсів.
 */
public class Application {
    private static Application instance;
    private Calc calc;

    /**
     * Приватний конструктор для забезпечення Singleton.
     * Ініціалізує об'єкт Calc.
     */
    private Application() {
        calc = new Calc();
    }

    /**
     * Метод для отримання єдиного екземпляра Application (Singleton pattern).
     * Якщо екземпляр не існує, створює новий.
     * @return Єдиний екземпляр класу Application.
     */
    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    /**
     * Повертає об'єкт Calc, що використовується в Application.
     * @return Об'єкт Calc для виконання обчислень.
     */
    public Calc getCalc() {
        return calc;
    }
}