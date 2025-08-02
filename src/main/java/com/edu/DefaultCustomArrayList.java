package com.edu;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * {@code DefaultCustomArrayList} — это реализация интерфейса {@link com.edu.CustomArrayList},
 * основанная на массиве.
 * <p>
 * Она предоставляет базовый функционал для работы со списком, включая добавление,
 * удаление, получение элементов, а также проверку размера и итерацию.
 * <p>
 * Класс является параметризованным, что позволяет хранить элементы любого типа.
 *
 * @param <E> тип элементов в этом списке.
 */
public class DefaultCustomArrayList<E> implements CustomArrayList<E> {
    /**
     * Начальная емкость массива по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Массив, в котором хранятся элементы списка.
     */
    private E[] elementData;

    /**
     * Количество элементов в списке.
     */
    private int size;

    /**
     * Создает пустой список с начальной емкостью по умолчанию ({@value #DEFAULT_CAPACITY}).
     */
    @SuppressWarnings("unchecked")
    public DefaultCustomArrayList() {
        setElementData((E[]) new Object[DEFAULT_CAPACITY]);
        setSize(0);
    }

    /**
     * Создает список, содержащий элементы указанного массива.
     *
     * @param elementData массив, элементы которого будут помещены в новый список.
     */
    public DefaultCustomArrayList(E[] elementData) {
        setElementData(Arrays.copyOf(elementData, elementData.length));
        setSize(elementData.length);
    }

    private E[] getElementData() {
        return elementData;
    }

    private void setElementData(E[] elementData) {
        this.elementData = elementData;
    }

    private int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    /**
     * Добавляет указанный элемент в конец списка.
     * Если массив достигает своей максимальной емкости, он автоматически увеличивается вдвое.
     *
     * @param element элемент, который нужно добавить.
     * @return {@code true} (согласно спецификации {@link com.edu.CustomArrayList#add(Object)}), если элемент был добавлен.
     * {@code false}, если переданный элемент является {@code null}.
     */
    @Override
    public boolean add(E element) {
        if (element == null) {
            return false;
        }
        if (getSize() == getElementData().length) {
            growElementData();
        }
        E[] res = getElementData();
        res[getSize()] = element;
        setSize(getSize() + 1);
        setElementData(res);
        return true;
    }

    /**
     * Увеличивает емкость массива, удваивая его текущий размер.
     */
    private void growElementData() {
        setElementData(Arrays.copyOf(getElementData(), getSize() * 2));
    }

    /**
     * Удаляет первое вхождение указанного элемента из этого списка, если он присутствует.
     *
     * @param element элемент, который нужно удалить из списка.
     * @return {@code true}, если элемент был найден и удален; {@code false} в противном случае.
     */
    @Override
    public boolean remove(E element) {
        for (int i = 0; i < getSize(); i++) {
            if (Objects.equals(elementData[i], element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно удалить.
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона ({@code index < 0 || index >= size()}).
     */
    private void remove(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + getSize());
        }
        int numMoved = getSize() - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[getSize() - 1] = null; // сброс ссылки для GC
        setSize(getSize() - 1);
    }

    /**
     * Возвращает элемент по указанному индексу в этом списке.
     *
     * @param index индекс элемента для возврата.
     * @return элемент по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона ({@code index < 0 || index >= size()}).
     */
    @Override
    public E get(int index) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + getSize());
        }
        return getElementData()[index];
    }

    /**
     * Возвращает количество элементов в этом списке.
     *
     * @return количество элементов в этом списке.
     */
    @Override
    public int size() {
        return getSize();
    }

    /**
     * Проверяет, является ли этот список пустым.
     *
     * @return {@code true}, если список не содержит элементов; {@code false} в противном случае.
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Удаляет все элементы из этого списка, сбрасывая его до начального состояния.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        setElementData((E[]) new Object[DEFAULT_CAPACITY]);
        setSize(0);
    }

    /**
     * Проверяет, содержит ли список указанный элемент.
     *
     * @param element элемент, наличие которого проверяется в списке.
     * @return {@code true}, если список содержит указанный элемент; {@code false} в противном случае.
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        E[] elementData = getElementData();
        for (int i = 0; i < getSize(); i++) {
            if (Objects.equals(elementData[i], element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает итератор по элементам этого списка.
     *
     * @return итератор по элементам списка.
     */
    @Override
    public Iterator<E> iterator() {
        return Arrays.stream(Arrays.copyOf(getElementData(), getSize())).iterator();
    }
}