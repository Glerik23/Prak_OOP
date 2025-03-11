package ex05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Клас CommandQueue реалізує чергу команд та керує Worker Thread.
 */
public class CommandQueue implements Queue {
    private BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
    private Thread workerThread;
    private volatile boolean isRunning = true;

    /**
     * Внутрішній клас Worker, що виконує команди з черги в окремому потоці.
     */
    private class Worker extends Thread {
        @Override
        public void run() {
            while (isRunning || !queue.isEmpty()) {
                try {
                    Command command = take();
                    System.out.println("Worker Thread: Виконую команду " + command.getClass().getSimpleName() +
                            " в потоці " + Thread.currentThread().getName());
                    command.execute();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Worker Thread: Завершення роботи " + Thread.currentThread().getName());
        }
    }


    /**
     * Конструктор CommandQueue.
     * Запускає Worker Thread при створенні черги.
     */
    public CommandQueue() {
        Worker worker = new Worker();
        workerThread = worker;
        workerThread.start();
    }

    /**
     * Додає команду до черги.
     * @param command Команда для додавання.
     */
    @Override
    public void put(Command command) {
        try {
            queue.put(command);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Отримує та видаляє команду з черги.
     * @return Видалена команда.
     * @throws InterruptedException якщо очікування команди перервано.
     */
    @Override
    public Command take() throws InterruptedException {
        return queue.take();
    }

    /**
     * Зупиняє Worker Thread.
     * Встановлює флаг isRunning в false і перериває потік обробника.
     */
    public void stopQueue() {
        isRunning = false;
        workerThread.interrupt();
    }

    /**
     * Очікує завершення роботи Worker Thread.
     * @throws InterruptedException якщо очікування перервано.
     */
    public void join() throws InterruptedException {
        workerThread.join();
    }
}