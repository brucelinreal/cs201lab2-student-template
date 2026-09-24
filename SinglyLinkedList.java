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
        Node<E> current = head;

        while (current != null) {
            E value = current.getElement();
            elementList.add(value);
            current = current.getNext();
        }
        Collections.sort(elementList);

        int size = elementList.size();
        for (int i = 0; i < size / 2; i++) {
            E smallestValue = elementList.get(i);
            E largestValue = elementList.get(size - i - 1);

            if (smallestValue.equals(largestValue)) {
                continue;
            }

            Node<E> largest = head;
            Node<E> prevLargest = null;
            while (!largest.getElement().equals(largestValue)) {
                prevLargest = largest;
                largest = largest.getNext();
            }

            Node<E> smallest = head;
            Node<E> prevSmallest = null;
            while (!smallest.getElement().equals(smallestValue)) {
                prevSmallest = smallest;
                smallest = smallest.getNext();
            }

            if (prevLargest != null) {
                prevLargest.setNext(smallest);
            } else {
                head = smallest;
            }

            if (prevSmallest != null) {
                prevSmallest.setNext(largest);
            } else {
                head = largest;
            }

            Node<E> temp = smallest.getNext();
            smallest.setNext(largest.getNext());
            largest.setNext(temp);
        }
        tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
    }

}
