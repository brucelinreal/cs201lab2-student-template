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

    public void swap2nodes(Node<E> aBefore, Node<E> bBefore) {
        if (aBefore == bBefore) {
            return;
        }

        Node<E> aA = aBefore.getNext();
        Node<E> bB = bBefore.getNext();

        if (aA == null || bB == null) {
            return;
        }

        if (aA == bBefore) {
            aBefore.setNext(bB);
            aA.setNext(bB.getNext());
            bB.setNext(aA);
            return;
        }

        if (bB == aBefore) {
            bBefore.setNext(aA);
            bB.setNext(aA.getNext());
            aA.setNext(bB);
            return;
        }

        Node<E> temp = aA.getNext();
        aBefore.setNext(bB);
        bBefore.setNext(aA);
        aA.setNext(bB.getNext());
        bB.setNext(temp);
    }

    public void swap() {
        if (size < 2) {
            return;
        }

        Node<E> dummy = new Node<>(null, head);
        ArrayList<Node<E>> nodes = new ArrayList<>();
        Node<E> curr = head;

        while (curr != null) {
            nodes.add(curr);
            curr = curr.getNext();
        }

        // Sort the references using ordinary loops (no lambda).
        for (int i = 0; i < nodes.size() - 1; i++) {
            int smallestIndex = i;

            for (int j = i + 1; j < nodes.size(); j++) {
                if (nodes.get(j).getElement()
                        .compareTo(nodes.get(smallestIndex).getElement()) < 0) {
                    smallestIndex = j;
                }
            }

            Node<E> temp = nodes.get(i);
            nodes.set(i, nodes.get(smallestIndex));
            nodes.set(smallestIndex, temp);
        }

        for (int i = 0; i < size / 2; i++) {
            Node<E> min = nodes.get(i);
            Node<E> max = nodes.get(size - 1 - i);

            Node<E> currmin = null;
            Node<E> currmax = null;
            curr = dummy;

            while (curr.getNext() != null) {
                if (curr.getNext() == min) {
                    currmin = curr;
                }
                if (curr.getNext() == max) {
                    currmax = curr;
                }
                curr = curr.getNext();
            }

            swap2nodes(currmin, currmax);
        }

        head = dummy.getNext();
        tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
    }

}
