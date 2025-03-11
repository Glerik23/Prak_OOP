package ex05;

import java.util.Collection;

/**
 * Абстрактний клас AbstractCollectionCommand для команд, що обробляють колекції чисел.
 * Реалізує загальну логіку для всіх команд обробки колекцій.
 * @param <T> Тип елементів у колекції, що розширює Number.
 */
public abstract class AbstractCollectionCommand<T extends Number> implements Command {
    protected Collection<T> collection;
    protected double result;

    /**
     * Конструктор AbstractCollectionCommand.
     * @param collection Колекція чисел для обробки.
     */
    public AbstractCollectionCommand(Collection<T> collection) {
        this.collection = collection;
        this.result = 0;
    }

    /**
     * Отримати результат виконання команди.
     * @return Результат обробки колекції.
     */
    public double getResult() {
        return result;
    }

    /**
     * Абстрактний метод для виконання специфічної логіки обробки колекції.
     * Реалізується у підкласах для конкретних операцій (Max, Avg, MinMax).
     * @throws InterruptedException якщо виконання команди перервано.
     */
    @Override
    public abstract void execute() throws InterruptedException;
}