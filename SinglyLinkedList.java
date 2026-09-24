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

    public void swap() {
        ArrayList<E> elementList = new ArrayList<>();
        ArrayList<Node<E>> originalNodes = new ArrayList<>();
        HashMap<E, Node<E>> nodeByValue = new HashMap<>();

        Node<E> current = head;
        while (current != null) {
            elementList.add(current.getElement());
            originalNodes.add(current);
            nodeByValue.put(current.getElement(), current);
            current = current.getNext();
        }

        int size = elementList.size();

        if (size == 0) {
            return;
        }
        Collections.sort(elementList);

        HashMap<E, E> oppositeValue = new HashMap<>();

        for (int i = 0; i < size; i++) {
            oppositeValue.put(
                    elementList.get(i),
                    elementList.get(size - 1 - i));
        }


        Node<E> previous = null;

        for (int i = 0; i < size; i++) {
            Node<E> original = originalNodes.get(i);
            E valueToReplaceItWith = oppositeValue.get(original.getElement());
            Node<E> replacement = nodeByValue.get(valueToReplaceItWith);

            if (previous == null) {
                head = replacement;
            } else {
                previous.setNext(replacement);
            }

            previous = replacement;
        }

        tail = previous;
        tail.setNext(null);
    }

}
