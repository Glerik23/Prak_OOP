package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Главный класс для взаимодействия с пользователем.
 */
public class Main {

    private Calc calc = new Calc();
    private View view; // Изменено: используем интерфейс View

    public Main() {
        ViewableResult viewableResult = new ViewableResult();
        view = viewableResult.getView(); // Получаем View через Factory Method
        ((ViewResult) view).setResult(calc.getResult()); // Инициализируем ViewResult объектом из Calc
    }

    protected void menu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (true) {
            System.out.println("Enter command (side, view, save, restore, exit):");
            input = reader.readLine();

            if (input.equalsIgnoreCase("exit")) { // **ИСПРАВЛЕНО**: "exit" вместо "вихід"
                break;
            }

            String lowerInput = input.toLowerCase();

            if (lowerInput.startsWith("side")) { // **ИСПРАВЛЕНО**:  Обработка команды "side" через startsWith
                try {
                    String sideStr = lowerInput.substring(4).trim();
                    int side = Integer.parseInt(sideStr);
                    calc.init(side);
                    ((ViewResult) view).setResult(calc.getResult());
                    view.viewShow();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid side length. Please enter a number after 'side'."); // Более конкретное сообщение об ошибке
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please enter a side length after 'side'."); // Обработка случая, когда введено только "side"
                }
            } else {
                switch (lowerInput) {
                    case "view": // **ИСПРАВЛЕНО**: "view" вместо "перегляд"
                        view.viewShow();
                        break;
                    case "save": // **ИСПРАВЛЕНО**: "save" вместо "зберегти"
                        calc.save();
                        System.out.println("Saved."); // **ИСПРАВЛЕНО**: "Saved." вместо "Збережено."
                        view.viewShow();
                        break;
                    case "restore": // **ИСПРАВЛЕНО**: "restore" вместо "відновити"
                        try {
                            calc.restore();
                            System.out.println("Restored."); // **ИСПРАВЛЕНО**: "Restored." вместо "Відновлено."
                            ((ViewResult) view).setResult(calc.getResult());
                            view.viewShow();
                        } catch (Exception e) {
                            System.out.println("Error restoring: " + e.getMessage()); // **ИСПРАВЛЕНО**: "Error restoring:" вместо "Помилка відновлення:"
                        }
                        break;
                    default:
                        System.out.println("Invalid command.");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().menu();
    }
}