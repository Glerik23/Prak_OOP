package ex02;

import java.io.IOException;

/** Product (шаблон проєктування Factory Method).
 * Інтерфейс "фабрикованих" об'єктів.
 * Оголошує методи відображення об'єктів.
 * @author Glerik
 * @version 1.0
 */
public interface View {

    /** Відображає заголовок. */
    void viewHeader();

    /** Відображає основну частину (тіло). */
    void viewBody();

    /** Відображає підвал. */
    void viewFooter();

    /** Відображає об'єкт цілком (заголовок, тіло, підвал). */
    void viewShow();

    /** Виконує ініціалізацію даних для відображення. */
    void viewInit(int side);

    /** Зберігає дані для подальшого відновлення.
     * @throws IOException Виникає при помилках серіалізації.
     */
    void viewSave() throws IOException;

    /** Відновлює раніше збережені дані.
     * @throws Exception Виникає при помилках десеріалізації.
     */
    void viewRestore() throws Exception;
}