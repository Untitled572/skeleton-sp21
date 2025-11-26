package deque;

public class LinkedListDeque<T> {
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

    public void addFirst(T item) {
        first.next = new Node(item, first, first.next);
        end = first.next;
        if (size == 0) {
            first.front = end;
            end.next = first;
        }
        size += 1;
    }

    public void addLast(T item) {
        end.next = new Node(item, end, first);
        end = end.next;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node n = first.next; n != first; n = n.next) {
            System.out.print(n.data);
            System.out.print(' ');
        }
        System.out.println();
    }

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

    public T removeLast() {
        if (size > 0) {
            T n = end.data;
            end = end.front;
            end.next = first;
            size -= 1;
            return n;
        }
        return null;
    }

    public T get(int index) {
        if (index <= size) {
            Node n = first;
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
}
