package deque;

public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int MAXSIZE = 8;
    private int firstPosition;
    private int nextLastPosition;

    public ArrayDeque() {
        array = (T[]) new Object[MAXSIZE];
        size = 0;
        firstPosition = 1;
        nextLastPosition = 0;
    }

    public ArrayDeque(T item) {
        array = (T[]) new Object[MAXSIZE];
        array[1] = item;
        firstPosition = 1;
        nextLastPosition = 0;
        size = 1;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        if (firstPosition > nextLastPosition) {
            System.arraycopy(array, nextLastPosition + 1, a, 1, size);
            firstPosition = size;
            nextLastPosition = 0;
        } else {
            int startPosition = MAXSIZE - firstPosition - 1;
            int remainSize = size - firstPosition - 1;
            System.arraycopy(array, 0, a, startPosition, firstPosition + 1);
            System.arraycopy(array, nextLastPosition + 1, a, startPosition - remainSize, remainSize);
            nextLastPosition = startPosition - remainSize - 1;
            firstPosition = MAXSIZE - 1;
        }
        array = a;
    }

    public void addFirst(T item) {
        if (firstPosition == nextLastPosition) {
            MAXSIZE *= 2;
            resize(MAXSIZE);
        }
        firstPosition += 1;
        if (firstPosition >= MAXSIZE) {
            firstPosition = 0;
        }
        array[firstPosition] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (firstPosition == nextLastPosition) {
            MAXSIZE *= 2;
            resize(MAXSIZE);
        }
        if (size == 0) {
            array[firstPosition] = item;
            size += 1;
            return;
        }
        nextLastPosition -= 1;
        if (nextLastPosition < 0) {
            nextLastPosition = MAXSIZE - 1;
            array[0] = item;
            size += 1;
            return;
        }
        array[nextLastPosition + 1] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = firstPosition;
        while (true) {
            System.out.print(array[i]);
            System.out.print(' ');
            i -= 1;
            if (i < 0) {
                i = MAXSIZE - 1;
            } else if (i == nextLastPosition) {
                break;
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if ((size < MAXSIZE / 4) && (size > 4)) {
            MAXSIZE /= 4;
            resize(MAXSIZE);
        }
        if (size <= 0) {
            return null;
        }
        T res = array[firstPosition];
        firstPosition -= 1;
        if (firstPosition < 0) {
            firstPosition = MAXSIZE - 1;
        }
        size -= 1;
        return res;
    }

    public T removeLast() {
        if ((size < MAXSIZE / 4) && (size > 4)) {
            MAXSIZE /= 4;
            resize(MAXSIZE);
        }
        if (size <= 0) {
            return null;
        }
        T res;
        if (nextLastPosition == MAXSIZE - 1) {
            res = array[0];
        } else {
            res = array[nextLastPosition + 1];
        }
        nextLastPosition += 1;
        if (nextLastPosition == MAXSIZE - 1) {
            nextLastPosition = 0;
        }
        size -= 1;
        return res;
    }

    public T get(int index) {
        if (firstPosition + index - 1 < MAXSIZE) {
            return array[firstPosition + index - 1];
        }
        return array[firstPosition + index - 1 - MAXSIZE];
    }
}
