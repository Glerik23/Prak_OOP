package ex05;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch; // Імпортуємо CountDownLatch

/**
 * Клас ExecuteConsoleCommand реалізує консольну команду для виконання обробки колекції в паралельних потоках.
 */
public class ExecuteConsoleCommand implements ConsoleCommand {
    private CommandQueue queue;

    /**
     * Конструктор ExecuteConsoleCommand.
     * @param queue Черга команд, в яку будуть додаватися завдання.
     */
    public ExecuteConsoleCommand(CommandQueue queue) {
        this.queue = queue;
    }

    /**
     * Повертає ключ команди для меню.
     * @return Ключ команди "execute".
     */
    @Override
    public String getKey() {
        return "execute";
    }

    /**
     * Повертає інформацію про команду для відображення в меню.
     * @return Опис команди "Виконати обробку колекції в паралельних потоках (execute)".
     */
    @Override
    public String getInfo() {
        return "Виконати обробку колекції в паралельних потоках (execute)";
    }

    /**
     * Виконує команду: створює колекцію чисел, додає команди обробки в чергу та очікує завершення обробки.
     * @throws InterruptedException Виникає у разі помилок при виконанні команди.
     */
    @Override
    public void execute() throws InterruptedException {
        List<Integer> numbers = generateRandomNumbers(20); // Випадкова колекція чисел

        CountDownLatch latch = new CountDownLatch(3);

        MaxCommand<Integer> maxCommand = new MaxCommand<>(numbers, latch);
        AvgCommand<Integer> avgCommand = new AvgCommand<>(numbers, latch);
        MinMaxCommand<Integer> minMaxCommand = new MinMaxCommand<>(numbers, latch);

        queue.put(maxCommand);
        queue.put(avgCommand);
        queue.put(minMaxCommand);

        System.out.println("Основний потік: Додано команди до черги.");

        latch.await();

        System.out.println("\nРезультати обробки:");
        System.out.println("MaxCommand Result: " + maxCommand.getResult());
        System.out.println("AvgCommand Result: " + avgCommand.getResult());
        System.out.println("MinMaxCommand Min Positive: " + minMaxCommand.getMinPositive() +
                ", Max Negative: " + minMaxCommand.getMaxNegative());
    }


    /**
     * Скасування команди не передбачено.
     * Виводить повідомлення про відсутність скасування для даної команди.
     * @throws Exception Виникає у разі помилок при скасуванні команди.
     */
    @Override
    public void undo() throws Exception {
        System.out.println("Скасування для команди 'execute' не передбачено.");
    }

    /**
     * Генерує список випадкових цілих чисел.
     * @param count Кількість чисел для генерації.
     * @return Список випадкових цілих чисел.
     */
    private List<Integer> generateRandomNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(200) - 100); // Числа від -100 до 99
        }
        return numbers;
    }
}