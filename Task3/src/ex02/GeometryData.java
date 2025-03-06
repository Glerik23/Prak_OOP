package ex02;

import java.io.Serializable;

/**
 * Клас для зберігання даних про геометричні фігури та результатів обчислень.
 */
public class GeometryData implements Serializable {

    private int sideLength;
    private transient double triangleArea;
    private transient double rectangleArea;
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор за замовчуванням класу GeometryData.
     * Ініціалізує поля sideLength, triangleArea та rectangleArea початковими значеннями.
     */
    public GeometryData() {
        this.sideLength = 0;
        this.triangleArea = 0.0;
        this.rectangleArea = 0.0;
    }

    /**
     * Встановлює довжину сторони для обчислення площ.
     * @param sideLength Довжина сторони, що буде використана для обчислень.
     */
    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * Повертає довжину сторони, що використовується для обчислень.
     * @return Довжина сторони.
     */
    public int getSideLength() {
        return sideLength;
    }

    /**
     * Повертає площу трикутника.
     * @return Площа трикутника.
     */
    public double getTriangleArea() {
        return triangleArea;
    }

    /**
     * Повертає площу прямокутника.
     * @return Площа прямокутника.
     */
    public double getRectangleArea() {
        return rectangleArea;
    }

    /**
     * Обчислює площі трикутника та прямокутника на основі встановленої довжини сторони.
     * Використовує довжину сторони, перетворену з двійкової в десяткову систему, для розрахунку площ.
     */
    public void calculateAreas() {
        String binarySide = Integer.toBinaryString(sideLength);
        int decimalSide = Integer.parseInt(binarySide, 2);
        this.triangleArea = (Math.sqrt(3) / 4) * decimalSide * decimalSide;
        this.rectangleArea = decimalSide * decimalSide;
    }

    /**
     * Повертає рядкове представлення об'єкта GeometryData.
     * Включає довжину сторони в двійковому форматі, площі трикутника та прямокутника, а також загальну площу.
     * @return Рядок, що містить інформацію про об'єкт GeometryData.
     */
    @Override
    public String toString() {
        return "Side Length (binary): " + Integer.toBinaryString(sideLength) +
               ", Triangle Area: " + triangleArea +
               ", Rectangle Area: " + rectangleArea +
               ", Total Area: " + (triangleArea + rectangleArea);
    }

    /**
     * Порівнює поточний об'єкт GeometryData з іншим об'єктом на рівність.
     * Два об'єкти GeometryData вважаються рівними, якщо їх довжини сторін та площі трикутника і прямокутника співпадають.
     * @param obj Об'єкт для порівняння.
     * @return true, якщо об'єкти рівні, інакше - false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GeometryData other = (GeometryData) obj;
        return sideLength == other.sideLength &&
               Double.compare(other.triangleArea, triangleArea) == 0 &&
               Double.compare(other.rectangleArea, rectangleArea) == 0;
    }
}