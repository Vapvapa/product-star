import com.edu.CustomArrayList;
import com.edu.DefaultCustomArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса {@link DefaultCustomArrayList}.
 * Этот класс содержит набор юнит-тестов для проверки корректности
 * реализации всех методов кастомного списка.
 */
public class DefaultCustomArrayListTest {

    private CustomArrayList<Integer> list;

    /**
     * Метод, который выполняется перед каждым тестом.
     * Инициализирует новый пустой список {@link DefaultCustomArrayList}
     * для обеспечения независимости тестов друг от друга.
     */
    @BeforeEach
    public void setUp() {
        list = new DefaultCustomArrayList<>();
    }

    /**
     * Тест проверяет корректность добавления элементов и получение текущего размера списка.
     * Ожидается, что после добавления двух элементов размер списка будет равен 2.
     */
    @Test
    public void testAddAndGetSize() {
        assertTrue(list.add(1));
        assertTrue(list.add(2));
        assertEquals(2, list.size());
    }

    /**
     * Тест проверяет корректность получения элементов по индексу.
     * Ожидается, что элементы будут возвращены в порядке их добавления.
     */
    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    /**
     * Тест проверяет корректность удаления элемента по значению.
     * Ожидается, что элемент будет удален, размер списка уменьшится,
     * а сам элемент больше не будет содержаться в списке.
     */
    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        assertTrue(list.remove(1));
        assertEquals(1, list.size());
        assertFalse(list.contains(1));
    }

    /**
     * Тест проверяет очистку списка.
     * Ожидается, что после вызова метода {@link CustomArrayList#clear()}
     * список станет пустым, а его размер будет равен 0.
     */
    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    /**
     * Тест проверяет наличие элемента в списке.
     * Ожидается, что метод {@link CustomArrayList#contains(Object)} вернет
     * true для существующего элемента и false для несуществующего.
     */
    @Test
    public void testContains() {
        list.add(1);
        list.add(2);
        assertTrue(list.contains(1));
        assertFalse(list.contains(3));
    }

    /**
     * Тест проверяет метод {@link CustomArrayList#isEmpty()}.
     * Ожидается, что метод вернет true для пустого списка и false
     * после добавления хотя бы одного элемента.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    /**
     * Тест проверяет корректность работы итератора.
     * Ожидается, что итератор правильно проходит по всем элементам
     * и возвращает их в правильном порядке.
     */
    @Test
    public void testIterator() {
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Тест проверяет, что метод {@link CustomArrayList#get(int)} выбрасывает
     * исключение {@link IndexOutOfBoundsException} при попытке доступа
     * к несуществующему индексу.
     */
    @Test
    public void testGetWithIndexOutOfBoundShouldThrowException() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    /**
     * Тест проверяет поведение при добавлении null-элемента.
     * Согласно спецификации, добавление null не должно быть разрешено.
     */
    @Test
    public void testAddNull() {
        assertFalse(list.add(null));
        assertEquals(list.size(), 0);
    }

    /**
     * Тест проверяет поведение при попытке удаления несуществующего элемента.
     * Ожидается, что метод {@link CustomArrayList#remove(Object)} вернет false,
     * а размер списка не изменится.
     */
    @Test
    public void removeNotExistElement() {
        list.add(5);
        assertFalse(list.remove(10));
        assertEquals(1, list.size());
    }

    /**
     * Тест проверяет, что массив списка увеличивается при переполнении.
     * С помощью рефлексии получаем доступ к приватному полю "elementData"
     * и проверяем, что его ёмкость удваивается после добавления 11-го элемента.
     */
    @Test
    public void checkingSizeIncreaseInArrayOverflow() throws IllegalAccessException, NoSuchFieldException {
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        assertEquals(10, list.size());

        // Получение доступа к приватному полю "elementData" с помощью рефлексии
        Class<?> customArrayListClass = list.getClass();
        Field elementDataField = customArrayListClass.getDeclaredField("elementData");
        elementDataField.setAccessible(true); // Отключение проверки доступа

        int initialCapacity = ((Object[]) elementDataField.get(list)).length;
        assertEquals(10, initialCapacity);

        list.add(11); // Добавление 11-го элемента, который должен вызвать расширение массива

        assertEquals(11, list.size());
        int newCapacity = ((Object[]) elementDataField.get(list)).length;
        assertEquals(initialCapacity * 2, newCapacity);
    }
}