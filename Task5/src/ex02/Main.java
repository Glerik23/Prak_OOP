package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Головний клас для взаємодії з користувачем.
 */
public class Main {

    private Calc calc = new Calc();
    private View view;

    /**
     * Конструктор класу Main.
     * Ініціалізує об'єкти Calc та View, використовуючи патерн Factory Method для створення ViewResult.
     */
    public Main() {
        ViewableResult viewableResult = new ViewableResult();
        view = viewableResult.getView();
        ((ViewResult) view).setResult(calc.getResult());
    }

    /**
     * Метод для відображення консольного меню та обробки команд користувача.
     * Забезпечує взаємодію з користувачем в циклі, поки не буде введена команда "exit".
     * @throws IOException Виникає при помилках вводу/виводу.
     */
    protected void menu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (true) {
            System.out.println("Enter command (side, view, save, restore, exit):");
            input = reader.readLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String lowerInput = input.toLowerCase();

            if (lowerInput.startsWith("side")) {
                try {
                    String sideStr = lowerInput.substring(4).trim();
                    int side = Integer.parseInt(sideStr);
                    calc.init(side);
                    ((ViewResult) view).setResult(calc.getResult());
                    view.viewShow();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid side length. Please enter a number after 'side'.");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please enter a side length after 'side'.");
                }
            } else {
                switch (lowerInput) {
                    case "view":
                        view.viewShow();
                        break;
                    case "save":
                        calc.save();
                        System.out.println("Saved.");
                        view.viewShow();
                        break;
                    case "restore":
                        try {
                            calc.restore();
                            System.out.println("Restored.");
                            ((ViewResult) view).setResult(calc.getResult());
                            view.viewShow();
                        } catch (Exception e) {
                            System.out.println("Error restoring: " + e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid command.");
                }
            }
        }
    }

    /**
     * Головний метод програми.
     * Створює екземпляр класу Main та запускає консольне меню для взаємодії з користувачем.
     * @param args Аргументи командної строки (не використовуються).
     * @throws IOException Виникає при помилках вводу/виводу.
     */
    public static void main(String[] args) throws IOException {
        new Main().menu();
    }
}