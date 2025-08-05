package com.edu;
import java.util.Collection;
import java.util.Iterator;

/**
 * Интерфейс, который определяет контракт для списка,
 * основанного на массиве.
 * <p>
 * Этот интерфейс расширяет {@link java.lang.Iterable}, что позволяет
 * использовать его в циклах for-each.
 *
 * @param <E> тип элементов, хранящихся в этом списке.
 */
public interface CustomArrayList<E> extends Iterable<E> {

    /**
     * Добавляет указанный элемент в конец этого списка.
     *
     * @param element элемент для добавления.
     * @return {@code true}, если элемент был успешно добавлен, иначе {@code false}.
     */
    boolean add(E element);

    /**
     * Добавляет все элементы из указанной коллекции в конец этого списка.
     *
     * @param c коллекция элементов, которые необходимо добавить в список
     * @return {@code true}, если список был изменён в результате вызова (т.е. были добавлены новые элементы),
     *         иначе {@code false}
     * @throws NullPointerException если переданная коллекция {@code c} равна {@code null}
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * Удаляет первое вхождение указанного элемента из этого списка.
     *
     * @param element элемент для удаления.
     * @return {@code true}, если элемент был найден и удален, иначе {@code false}.
     */
    boolean remove(E element);

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно вернуть.
     * @return элемент по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона.
     */
    E get(int index);

    /**
     * Возвращает количество элементов в этом списке.
     *
     * @return количество элементов в списке.
     */
    int size();

    /**
     * Проверяет, является ли этот список пустым.
     *
     * @return {@code true}, если список не содержит элементов, иначе {@code false}.
     */
    boolean isEmpty();

    /**
     * Удаляет все элементы из этого списка.
     */
    void clear();

    /**
     * Проверяет, содержит ли список указанный элемент.
     *
     * @param element элемент для проверки.
     * @return {@code true}, если список содержит указанный элемент, иначе {@code false}.
     */
    boolean contains(E element);

    /**
     * Возвращает итератор, который обходит элементы списка.
     *
     * @return итератор для элементов списка.
     */
    Iterator<E> iterator();
}