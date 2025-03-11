package ex03;

import ex02.Calc;
import ex02.View;
import ex02.ViewResult;
import ex02.ViewableResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Головний клас для взаємодії з користувачем, розширений для підтримки табличного виводу.
 * @author Glerik
 * @version 1.1
 * @see Main#main
 */
public class Main extends ex02.Main {

    private View view;
    private Calc calc = new Calc();
    private boolean useTableView = false; // Прапорець для вибору табличного виводу
    private int tableWidth = 30; // Ширина таблиці за замовчуванням

    /**
     * Конструктор класу Main.
     * Ініціалізує об'єкти Calc та View, використовуючи патерн Factory Method.
     * За замовчуванням використовує звичайний вивід {@linkplain ViewResult}.
     */
    public Main() {
        ViewableResult viewableResult = new ViewableResult();
        view = viewableResult.getView();
        ((ViewResult) view).setResult(calc.getResult());
    }

    /**
     * Конструктор для вибору типу View.
     * @param view Об'єкт View, який буде використовуватись для відображення.
     */
    public Main(View view) {
        this.view = view;
        if (view instanceof ViewResult) {
            ((ViewResult) view).setResult(calc.getResult());
        }
    }


    /**
     * Метод для відображення консольного меню та обробки команд користувача.
     * Додано підтримку команд для вибору табличного виводу та встановлення ширини таблиці.
     * @throws IOException Виникає при помилках вводу/виводу.
     */
    @Override
    protected void menu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (true) {
            System.out.println("\nВведіть команду (side [ширина] <сторона>, view, save, restore, tableview, setwidth <ширина>, exit):");
            input = reader.readLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String lowerInput = input.toLowerCase();

            if (lowerInput.startsWith("side")) {
                try {
                    String sideInput = lowerInput.substring(4).trim();
                    int widthInput = tableWidth; // За замовчуванням використовуємо поточну ширину таблиці

                    String[] parts = sideInput.split("\\s+", 2); // Розділяємо ввід на ширину та сторону
                    if (parts.length > 1) {
                        try {
                            widthInput = Integer.parseInt(parts[0]); // Перше слово може бути шириною
                            sideInput = parts[1]; // Друге слово - сторона
                            tableWidth = widthInput; // Оновлюємо поточну ширину таблиці
                            useTableView = true; // Автоматично переходимо в табличний вивід при заданні ширини
                        } catch (NumberFormatException e) {
                            sideInput = parts[0]; // Якщо не вдалося розпарсити ширину, вважаємо перше слово стороною
                        }
                    }


                    int side = Integer.parseInt(sideInput.trim());

                    calc.init(side);

                    if (useTableView) {
                        ViewTable tableView = (ViewTable) new ViewableTable().getView();
                        tableView.setResult(calc.getResult());
                        tableView.viewShow(widthInput); // Використовуємо задану ширину або ширину за замовчуванням
                        view = tableView; // Оновлюємо поточний view для 'view', 'save', 'restore' команд, щоб використовувався табличний вивід
                    } else {
                        ((ViewResult) view).setResult(calc.getResult());
                        view.viewShow();
                    }


                } catch (NumberFormatException e) {
                    System.out.println("Невірна довжина сторони. Будь ласка, введіть число після 'side'.");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Будь ласка, введіть довжину сторони після 'side'.");
                }
            } else {
                switch (lowerInput) {
                    case "view":
                        if (useTableView) {
                            ((ViewTable) view).viewShow(tableWidth); // Відображення таблиці з поточною шириною
                        } else {
                            view.viewShow();
                        }
                        break;
                    case "save":
                        calc.save();
                        System.out.println("Збережено.");
                        if (useTableView) {
                            ((ViewTable) view).viewShow(tableWidth);
                        } else {
                            view.viewShow();
                        }
                        break;
                    case "restore":
                        try {
                            calc.restore();
                            System.out.println("Відновлено.");
                            if (useTableView) {
                                ((ViewTable) view).setResult(calc.getResult()); // Оновлення результату для табличного виводу
                                ((ViewTable) view).viewShow(tableWidth);
                            } else {
                                ((ViewResult) view).setResult(calc.getResult());
                                view.viewShow();
                            }
                        } catch (Exception e) {
                            System.out.println("Помилка відновлення: " + e.getMessage());
                        }
                        break;
                    case "tableview":
                        useTableView = true;
                        System.out.println("Перехід до табличного виводу.");
                        break;
                    case "setwidth":
                        System.out.println("Введіть ширину таблиці:");
                        String widthStr = reader.readLine();
                        try {
                            tableWidth = Integer.parseInt(widthStr);
                            System.out.println("Ширина таблиці встановлена на " + tableWidth + ".");
                            useTableView = true; // Автоматично переходимо в табличний вивід при заданні ширини
                        } catch (NumberFormatException e) {
                            System.out.println("Невірна ширина таблиці. Будь ласка, введіть число.");
                        }
                        break;
                    default:
                        System.out.println("Невідома команда.");
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
        Main main = new Main(new ViewableTable().getView()); // Використовуємо табличний вивід за замовчуванням
        main.menu();
    }
}