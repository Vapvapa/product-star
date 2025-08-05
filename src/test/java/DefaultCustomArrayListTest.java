import com.edu.CustomArrayList;
import com.edu.DefaultCustomArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса {@link DefaultCustomArrayList}.
 * Этот класс содержит набор unit-тестов для проверки корректности
 * реализации всех методов списка.
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
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
    }

    /**
     * Тест проверяет, что элементы корректно добавляются в список
     * как по одному, так и через передачу коллекции (в пустой и с элементами список).
     * Ожидается, что после добавления размер списка соответствует
     * количеству добавленных элементов, а сами элементы находятся
     * на ожидаемых позициях.
     */
    @Test
    public void testAddAll() {
        assertTrue(list.addAll(Arrays.asList(1, 2)));
        assertTrue(list.add(3));
        assertTrue(list.addAll(Arrays.asList(4, 5)));
        assertEquals(5, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
        assertEquals(Integer.valueOf(4), list.get(3));
        assertEquals(Integer.valueOf(5), list.get(4));
    }

    /**
     * Тест проверяет поведение при передаче в метод значения {@code null}
     * или коллекции, содержащей {@code null} элементы.
     * Ожидается, что в обоих случаях будет выброшено исключение {@link NullPointerException}.
     * Также проверяется, что после таких вызовов список остаётся пустым.
     */
    @Test
    public void testAddAllWithNullPointerThrowException() {
        assertThrows(NullPointerException.class,
                () -> list.addAll(null));
        assertThrows(NullPointerException.class,
                () -> list.addAll(Arrays.asList(null, null)));
        assertTrue(list.isEmpty());
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
        assertEquals(Integer.valueOf(2), list.get(0));
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
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
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
        assertEquals(Integer.valueOf(1), list.get(0));
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
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
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
        assertEquals(Integer.valueOf(1), list.get(0));
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
        list.add(1);
        assertFalse(list.remove(2));
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
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
        for (int i = 0; i < 11; i++) {
            assertEquals(Integer.valueOf(i + 1), list.get(i));
        }
    }
}