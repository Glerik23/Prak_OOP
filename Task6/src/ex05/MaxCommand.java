package ex05;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

/**
 * Клас MaxCommand для знаходження максимального значення в колекції чисел.
 */
public class MaxCommand<T extends Number> extends AbstractCollectionCommand<T> {

    private CountDownLatch latch;

    /**
     * Конструктор MaxCommand.
     * @param collection Колекція чисел для обробки.
     */
    public MaxCommand(Collection<T> collection, CountDownLatch latch) {
        super(collection);
        this.latch = latch;
    }

    /**
     * Знаходить максимальне значення в колекції.
     * @throws InterruptedException якщо виконання команди перервано.
     */
    @Override
    public void execute() throws InterruptedException {
        if (collection == null || collection.isEmpty()) {
            result = Double.NaN;
            latch.countDown();
            return;
        }

        double max = Double.NEGATIVE_INFINITY;
        for (Number number : collection) {
            if (number.doubleValue() > max) {
                max = number.doubleValue();
            }
            Thread.sleep(50);
        }
        result = max;
        System.out.println("MaxCommand: Максимальне значення знайдено = " + result +
                " в потоці " + Thread.currentThread().getName());
        latch.countDown();
    }
}