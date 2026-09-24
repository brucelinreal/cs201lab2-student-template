import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    private void swap2nodes(Node<E> a, Node<E> b) {
        if (a == b)
            return;

        Node<E> beforeA = null;
        Node<E> beforeB = null;
        Node<E> current = head;

        while (current != null) {
            if (current.getNext() == a)
                beforeA = current;
            if (current.getNext() == b)
                beforeB = current;
            current = current.getNext();
        }

        if (a.getNext() == b) {
            if (beforeA == null)
                head = b;
            else
                beforeA.setNext(b);

            a.setNext(b.getNext());
            b.setNext(a);
        } else if (b.getNext() == a) {
            if (beforeB == null)
                head = a;
            else
                beforeB.setNext(a);

            b.setNext(a.getNext());
            a.setNext(b);
        } else {
            if (beforeA == null)
                head = b;
            else
                beforeA.setNext(b);

            if (beforeB == null)
                head = a;
            else
                beforeB.setNext(a);

            Node<E> temp = a.getNext();
            a.setNext(b.getNext());
            b.setNext(temp);
        }

        if (tail == a)
            tail = b;
        else if (tail == b)
            tail = a;
    }

    public void swap() {
        if (size < 2)
            return;

        ArrayList<Node<E>> nodes = new ArrayList<>();
        Node<E> current = head;

        while (current != null) {
            nodes.add(current);
            current = current.getNext();
        }

        nodes.sort((a, b) -> a.getElement().compareTo(b.getElement()));

        for (int i = 0; i < size / 2; i++) {
            swap2nodes(nodes.get(i), nodes.get(size - 1 - i));
        }
    }

}
