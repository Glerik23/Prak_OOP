package ex05;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

/**
 * Клас MinMaxCommand для пошуку мінімального позитивного та максимального негативного значень в колекції чисел.
 */
public class MinMaxCommand<T extends Number> extends AbstractCollectionCommand<T> {
    private double minPositive;
    private double maxNegative;
    private CountDownLatch latch;

    /**
     * Конструктор MinMaxCommand.
     * @param collection Колекція чисел для обробки.
     */
    public MinMaxCommand(Collection<T> collection, CountDownLatch latch) {
        super(collection);
        this.minPositive = Double.POSITIVE_INFINITY;
        this.maxNegative = Double.NEGATIVE_INFINITY;
        this.latch = latch;
    }

    public double getMinPositive() {
        return minPositive;
    }

    public double getMaxNegative() {
        return maxNegative;
    }

    /**
     * Знаходить мінімальне позитивне та максимальне негативне значення в колекції.
     * @throws InterruptedException якщо виконання команди перервано.
     */
    @Override
    public void execute() throws InterruptedException {
        if (collection == null || collection.isEmpty()) {
            minPositive = Double.NaN;
            maxNegative = Double.NaN;
            latch.countDown();
            return;
        }

        minPositive = Double.POSITIVE_INFINITY;
        maxNegative = Double.NEGATIVE_INFINITY;

        for (Number number : collection) {
            double value = number.doubleValue();
            if (value > 0 && value < minPositive) {
                minPositive = value;
            }
            if (value < 0 && value > maxNegative) {
                maxNegative = value;
            }
            Thread.sleep(50);
        }

        if (minPositive == Double.POSITIVE_INFINITY) {
            minPositive = Double.NaN;
        }
        if (maxNegative == Double.NEGATIVE_INFINITY) {
            maxNegative = Double.NaN;
        }

        System.out.println("MinMaxCommand: Мінімальне позитивне = " + minPositive +
                ", Максимальне негативне = " + maxNegative +
                " в потоці " + Thread.currentThread().getName());
        result = minPositive;
        latch.countDown();
    }
}