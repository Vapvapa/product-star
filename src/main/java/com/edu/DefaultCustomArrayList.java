package com.edu;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class DefaultCustomArrayList<E> implements CustomArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] elementData;

    private int size;

    @SuppressWarnings("unchecked") // Подавляем предупреждение о непроверенном приведении типов
    public DefaultCustomArrayList() {
        setElementData((E[]) new Object[DEFAULT_CAPACITY]);
        setSize(0);
    }

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

    @Override
    public boolean add(E element) {
        if (getSize() == getElementData().length) {
            growElementData();
        }
        E[] res = getElementData();
        res[getSize()] = element;
        setSize(getSize() + 1);
        setElementData(res);
        return true;
    }

    private void growElementData() {
        setElementData(Arrays.copyOf(getElementData(), getSize() * 2));
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementData[i])) {
                remove(i); // вызов приватного метода remove по индексу
                return true;
            }
        }
        return false;
    }

    private void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[size - 1] = null; // сброс ссылки для GC
        size--;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + getSize());
        }
        return getElementData()[index];
    }

    @Override
    public int size() {
        return getSize();
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    @SuppressWarnings("unchecked") // Подавляем предупреждение о непроверенном приведении типов
    public void clear() {
        setElementData((E[]) new Object[DEFAULT_CAPACITY]);
        setSize(0);
    }

    @Override
    public boolean contains(E element) {
        E[] elementData = getElementData();
        for (int i = 0; i < getSize(); i++) {
            if (elementData[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return Arrays.stream(Arrays.copyOf(getElementData(), getSize())).iterator();
    }
}