package ex04;

import ex02.Calc;
import ex02.View;
import ex02.ViewResult;
import ex03.ViewTable;
import ex03.ViewableTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Головний клас для взаємодії з користувачем, розширений для підтримки табличного виводу та командної структури.
 * @author Glerik
 * @version 1.3
 * @see Main#main
 */
public class Main extends ex02.Main {

    private View view;
    private Calc calc;
    private Menu menu;

    /**
     * Конструктор класу Main.
     * Ініціалізує об'єкти Calc та View, використовуючи патерн Factory Method.
     * За замовчуванням використовує табличний вивід {@linkplain ViewTable}.
     */
    public Main() {
        Application app = Application.getInstance();
        this.calc = app.getCalc();
        ViewableTable viewableResult = new ViewableTable();
        view = viewableResult.getView();
        ((ViewTable) view).setResult(calc.getResult());

        this.menu = new Menu();
        menu.addCommand(new ChangeConsoleCommand(calc, (ViewTable)view));
        menu.addCommand(new GenerateConsoleCommand(calc, (ViewResult)view));
        menu.addCommand(new SaveConsoleCommand(calc, (ViewResult)view));
        menu.addCommand(new RestoreConsoleCommand(calc, (ViewResult)view));
        menu.addCommand(new ViewConsoleCommand((ViewResult)view));
    }

    /**
     * Конструктор для вибору типу View.
     * @param view Об'єкт View, який буде використовуватись для відображення.
     */
    public Main(View view) {
        Application app = Application.getInstance();
        this.calc = app.getCalc();
        this.view = view;
        if (view instanceof ViewTable) {
            ((ViewTable) view).setResult(calc.getResult());
        } else if (view instanceof ViewResult) {
            ((ViewResult) view).setResult(calc.getResult());
        }
        
        this.menu = new Menu();
        menu.addCommand(new ChangeConsoleCommand(calc, (ViewTable)view)); 
        menu.addCommand(new GenerateConsoleCommand(calc, (ViewResult)view));
        menu.addCommand(new SaveConsoleCommand(calc, (ViewResult)view));
        menu.addCommand(new RestoreConsoleCommand(calc, (ViewResult)view));
        menu.addCommand(new ViewConsoleCommand((ViewResult)view));
    }


    /**
     * Метод для відображення консольного меню та обробки команд користувача.
     * Використовує структуру команд з ex04 та табличний вивід з ex03.
     * @throws IOException Виникає при помилках вводу/виводу.
     */
    @Override
    protected void menu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;


        while (true) {
            menu.showMenu();
            input = reader.readLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equalsIgnoreCase("undo")) {
                try {
                    menu.undoLastCommand();
                } catch (Exception e) {
                    System.out.println("Помилка при скасуванні: " + e.getMessage());
                }
            } else {
                try {
                    menu.executeCommand(input);
                } catch (Exception e) {
                    System.out.println("Помилка виконання команди: " + e.getMessage());
                }
            }
        }
    }


    /**
     * Головний метод програми.
     * Створює екземпляр класу Main та запускає консольне меню для взаємодії з користувачем.
     * Використовує табличний вивід за замовчуванням.
     * @param args Аргументи командної строки (не використовуються).
     * @throws IOException Виникає при помилках вводу/виводу.
     */
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.menu();
    }
}