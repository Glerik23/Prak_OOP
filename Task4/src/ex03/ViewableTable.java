package ex03;
import ex02.ViewableResult;
import ex02.View;

/** ConcreteCreator (шаблон проєктування Factory Method).
 * Клас, що "фабрикує" об'єкти {@linkplain ViewTable}.
 * @author Glerik
 * @version 1.1
 * @see ViewableResult
 * @see ViewableTable#getView()
 */
public class ViewableTable extends ViewableResult {
    /** Створює відображуваний об'єкт {@linkplain ViewTable}.
     * @return Об'єкт ViewTable.
     */
    @Override
    public View getView() {
        return new ViewTable();
    }
}