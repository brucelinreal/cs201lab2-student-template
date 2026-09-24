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
        if (aBefore == null || bBefore == null || aBefore == bBefore) {
            return;
        }

        Node<E> aA = aBefore.getNext();
        Node<E> bB = bBefore.getNext();

        if (aA == bB || aA == null || bB == null) {
            return;
        }

        if (aA == bBefore) {
            aBefore.setNext(bB);
            aA.setNext(bB.getNext());
            bB.setNext(aA);;
        }

        if (bB == aBefore) {
            bBefore.setNext(aA);
            bB.setNext(aA.getNext());
            aA.setNext(bB);;
        }

        Node<E> temp = aA.getNext();
        aBefore.setNext(bB);
        bBefore.setNext(aA);

        aA.setNext(bB.getNext());
        bB.setNext(temp);
    }

    // write your codes here
    public void swap() {
        if (size < 2) {
            return;
        }
        Node<E> dummy = new Node<>(null, head);
        Node<E> max = dummy;
        Node<E> min = dummy;
        Node<E> curr = dummy;
        while (curr.getNext() != null) {
            if (max.getNext().getElement().compareTo(curr.getNext().getElement()) < 0) {
                max = curr;
            }

            if (min.getNext().getElement().compareTo(curr.getNext().getElement()) > 0) {
                min = curr;
            }
            curr = curr.getNext();
        }
        swap2nodes(min, max);
        Node<E> tmptmp = min;
        min = max;
        max = tmptmp;
        for (int i = 0; i < size / 2 - 1; i++) {
            Node<E> currmax = null;
            Node<E> currmin = null;
            curr = dummy;
            while (curr.getNext() != null) {
                if ((curr.getNext().getElement().compareTo(max.getNext().getElement()) < 0) && (currmax == null
                        || curr.getNext().getElement().compareTo(currmax.getNext().getElement()) > 0)) {
                    currmax = curr;
                }

                if ((curr.getNext().getElement().compareTo(min.getNext().getElement()) > 0) && (currmin == null
                        || curr.getNext().getElement().compareTo(currmin.getNext().getElement()) < 0)) {
                    currmin = curr;
                }
                curr = curr.getNext();
            }

            if (currmax == null || currmin == null) {
                return;
            }
            swap2nodes(currmax, currmin);
            max = currmin;
            min = currmax;
        }
        head = dummy.getNext();
        tail = head;
        for (int i = 0; i < size - 1; i++) {
            tail = tail.getNext();
        }
    }

}
