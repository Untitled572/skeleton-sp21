package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size;
    private int capacity;
    private int first;
    private int nextLast;

    public ArrayDeque() {
        capacity = 8;
        array = (T[]) new Object[capacity];
        size = 0;
        first = 0;
        nextLast = 0;
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        array = newArray;
        first = 0;
        nextLast = size;
        capacity = newCapacity;
    }

    @Override
    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        first = (first - 1 + capacity) % capacity;
        array[first] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        array[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = array[first];
        array[first] = null;
        first = (first + 1) % capacity;
        size--;
        if (capacity >= 16 && size * 4 < capacity) {
            resize(capacity / 2);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast - 1 + capacity) % capacity;
        T item = array[nextLast];
        array[nextLast] = null;
        size--;
        if (capacity >= 16 && size * 4 < capacity) {
            resize(capacity / 2);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[(first + index) % capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    private class ArrayListIterater implements Iterator<T> {
        private int seer;
        private ArrayListIterater() {
            seer = 0;
        }

        @Override
        public boolean hasNext() {
            return seer < size;
        }

        @Override
        public T next() {
            return get(seer++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterater();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i += 1) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
