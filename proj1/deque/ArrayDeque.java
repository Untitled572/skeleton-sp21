package deque;

public class ArrayDeque<T> {
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

    public ArrayDeque(T item) {
        capacity = 8;
        array = (T[]) new Object[capacity];
        array[0] = item;
        first = 0;
        nextLast = 1;
        size = 1;
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

    public void addFirst(T item) {
        if (size == capacity) resize(capacity * 2);
        first = (first - 1 + capacity) % capacity;
        array[first] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == capacity) resize(capacity * 2);
        array[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size++;
    }

    public T removeFirst() {
        if (size == 0) return null;
        T item = array[first];
        array[first] = null;
        first = (first + 1) % capacity;
        size--;
        if (capacity >= 16 && size * 4 < capacity) {
            resize(capacity / 2);
        }
        return item;
    }

    public T removeLast() {
        if (size == 0) return null;
        nextLast = (nextLast - 1 + capacity) % capacity;
        T item = array[nextLast];
        array[nextLast] = null;
        size--;
        if (capacity >= 16 && size * 4 < capacity) {
            resize(capacity / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return array[(first + index) % capacity];
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}