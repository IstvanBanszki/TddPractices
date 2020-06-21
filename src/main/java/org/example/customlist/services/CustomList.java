package org.example.customlist.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class CustomList<E> implements List<E> {

    private int index = 0;

    private final static int DEF_CAPACITY = 10;

    private Object[] objects;

    public CustomList(final Object[] objects) {
        this.objects = Arrays.copyOf(objects, objects.length);
        this.index = objects.length;
    }
    public CustomList() {
        this.objects = new Object[DEF_CAPACITY];
        this.index = DEF_CAPACITY;
    }

    public int size() {
        return index;
    }

    public boolean isEmpty() {
        return objects.length == 0;
    }

    public boolean contains(Object o) {
        if (0 == index) {
            return false;
        }
        return Arrays.stream(this.objects).anyMatch(object -> object.equals(o));
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(E e) {
        if (index == objects.length) {
            increaseCapacity();
        }
        objects[index++] = e;
        return true;
    }

    public boolean remove(Object o) {
        if (!this.contains(o)) {
            return false;
        }
        this.objects = Arrays.stream(this.objects).filter(object -> !object.equals(o)).toArray();
        index--;
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public E get(int index) {
        return null;
    }

    public E set(int index, E element) {
        return null;
    }

    public void add(int index, E element) {

    }

    public E remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<E> listIterator() {
        return null;
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void increaseCapacity() {
        objects = Arrays.copyOf(objects, objects.length + 10);
    }
}
