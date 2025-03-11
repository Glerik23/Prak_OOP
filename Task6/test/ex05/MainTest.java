package ex05;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Клас MainTest для тестування функціональності класів CommandQueue, MaxCommand, AvgCommand, MinMaxCommand.
 */
public class MainTest {

    @Test
    public void testMaxCommand() throws InterruptedException {
        List<Integer> numbers = Arrays.asList(1, 5, 2, 8, 3);
        CountDownLatch latch = new CountDownLatch(1);
        MaxCommand<Integer> maxCommand = new MaxCommand<>(numbers, latch);
        maxCommand.execute();
        latch.await();
        assertEquals(8.0, maxCommand.getResult(), 0.001);
    }

    @Test
    public void testAvgCommand() throws InterruptedException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        CountDownLatch latch = new CountDownLatch(1);
        AvgCommand<Integer> avgCommand = new AvgCommand<>(numbers, latch);
        avgCommand.execute();
        latch.await();
        assertEquals(3.0, avgCommand.getResult(), 0.001);
    }

    @Test
    public void testMinMaxCommand() throws InterruptedException {
        List<Integer> numbers = Arrays.asList(-5, 1, -2, 8, 3, -10);
        CountDownLatch latch = new CountDownLatch(1);
        MinMaxCommand<Integer> minMaxCommand = new MinMaxCommand<>(numbers, latch);
        minMaxCommand.execute();
        latch.await();
        assertEquals(1.0, minMaxCommand.getMinPositive(), 0.001);
        assertEquals(-2.0, minMaxCommand.getMaxNegative(), 0.001);
    }

    @Test
    public void testMaxCommandQueue() throws InterruptedException {
        CommandQueue queue = new CommandQueue();
        List<Integer> numbers = Arrays.asList(1, 5, 2, 8, 3);
        CountDownLatch latch = new CountDownLatch(1);
        MaxCommand<Integer> maxCommand = new MaxCommand<>(numbers, latch);
        queue.put(maxCommand);
        latch.await();
        queue.stopQueue();
        queue.join();
        assertEquals(8.0, maxCommand.getResult(), 0.001);
    }

    @Test
    public void testAvgCommandQueue() throws InterruptedException {
        CommandQueue queue = new CommandQueue();
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        CountDownLatch latch = new CountDownLatch(1);
        AvgCommand<Integer> avgCommand = new AvgCommand<>(numbers, latch);
        queue.put(avgCommand);
        latch.await();
        queue.stopQueue();
        queue.join();
        assertEquals(6.0, avgCommand.getResult(), 0.001);
    }

    @Test
    public void testMinMaxCommandQueue() throws InterruptedException {
        CommandQueue queue = new CommandQueue();
        List<Integer> numbers = Arrays.asList(-7, 2, -3, 9, 4, -12);
        CountDownLatch latch = new CountDownLatch(1);
        MinMaxCommand<Integer> minMaxCommand = new MinMaxCommand<>(numbers, latch);
        queue.put(minMaxCommand);
        latch.await();
        queue.stopQueue();
        queue.join();
        assertEquals(2.0, minMaxCommand.getMinPositive(), 0.001);
        assertEquals(-3.0, minMaxCommand.getMaxNegative(), 0.001);
    }
}