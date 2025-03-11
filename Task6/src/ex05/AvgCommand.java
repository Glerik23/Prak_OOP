package ex05;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

/**
 * Клас AvgCommand для обчислення середнього значення в колекції чисел.
 */
public class AvgCommand<T extends Number>  extends AbstractCollectionCommand<T> {

    private CountDownLatch latch;

    /**
     * Конструктор AvgCommand.
     * @param collection Колекція чисел для обробки.
     */
    public AvgCommand(Collection<T> collection, CountDownLatch latch) {
        super(collection);
        this.latch = latch;
    }

    /**
     * Обчислює середнє значення в колекції.
     * @throws InterruptedException якщо виконання команди перервано.
     */
    @Override
    public void execute() throws InterruptedException {
        if (collection == null || collection.isEmpty()) {
            result = Double.NaN;
            latch.countDown();
            return;
        }

        double sum = 0;
        for (Number number : collection) {
            sum += number.doubleValue();
            Thread.sleep(50);
        }
        result = sum / collection.size();
        System.out.println("AvgCommand: Середнє значення обчислено = " + result +
                " в потоці " + Thread.currentThread().getName());
        latch.countDown();
    }
}