package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        public Node front;
        public Node next;
        public T data;
        public Node(T n, Node pre, Node aft) {
            data = n;
            front = pre;
            next = aft;
        }
    }

    private Node first;
    private Node end;
    private int size;

    public LinkedListDeque() {
        first = new Node(null, first, first);
        end = first;
        size = 0;
    }

    public LinkedListDeque(T data) {
        first = new Node(null, null, null);
        first.next = new Node(data, first, first);
        end = first.next;
        first.front = end;
        size = 1;
    }

    @Override
    public void addFirst(T item) {
        first.next = new Node(item, first, first.next);
        if (size == 0) {
            first.front = first.next;
            end = first.next;
            end.next = first;
        } else {
            first.next.next.front = first.next;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        end.next = new Node(item, end, first);
        end = end.next;
        first.front = end;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (Node n = first.next; n != first; n = n.next) {
            System.out.print(n.data);
            System.out.print(' ');
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size > 0) {
            T n = first.next.data;
            first.next = first.next.next;
            first.next.front = first;
            size -= 1;
            return n;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size > 0) {
            T n = end.data;
            end = end.front;
            end.next = first;
            first.front = end;
            size -= 1;
            return n;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index <= size) {
            Node n = first.next;
            for (int i = 0; i < index; i += 1) {
                n = n.next;
            }
            return n.data;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return getRecursiveHelper(index, first);
    }

    private T getRecursiveHelper(int index, Node n) {
        if (index == 0) {
            return n.data;
        }
        return getRecursiveHelper(index - 1, n.next);
    }

    private class DequeIterator implements Iterator<T> {
        private int curPos; // 表示即将访问的索引位置，初始即将访问索引0
        private DequeIterator() {
            curPos = 0;
        }

        @Override
        public boolean hasNext() {
            return curPos < size;
        }

        @Override
        public T next() {
            return get(curPos++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
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
        for (int i = 0; i < this.size(); ++i) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
