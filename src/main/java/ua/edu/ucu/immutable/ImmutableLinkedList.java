package ua.edu.ucu.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private final Node head;
    private final int size;

    private static class Node {
        private  Object data;
        private  Node next;

        public Node(Object d, Node next) {
            this.data = d;
            this.next = next;
        }
    }

    public ImmutableLinkedList() {
        head = null;
        size = 0;
    }

    public ImmutableLinkedList(Object[] c) {
        size = c.length;
        head = new Node(c[0], null);
        Node cur = head;
        for (int i = 1; i < c.length; i++) {
            Node el = new Node(c[i], null);
            cur.next = el;
            cur = el;
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return addAll(size(), new Object[] {e});
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        Object[] newArrayList = new Object[size + c.length];
        int i = 0;
        int j = 0;
        Node cur = this.head;
        while (i < index) {
            newArrayList[i] = cur.data;
            i++;
            cur = cur.next;
        }
        while (j < c.length) {
            newArrayList[i+j] = c[j];
            j++;
        }
        while (cur != null) {
            newArrayList[j+i] = cur.data;
            i++;
            cur = cur.next;
        }
        return new ImmutableLinkedList(newArrayList);
    }


    @Override
    public Object get(int index) {
        Node cur = head;
        int i = 0;
        while (i < index) {
            cur = cur.next;
            i++;
        }
        if (i == index) {
            return cur.data;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        Object[] newArrayList = new Object[size()-1];
        Node cur = head;
        int i = 0;
        int j = 0;
        while (cur != null)
        {
            if (i != index)
            {
                newArrayList[j] = cur.data;
                j++;
            }
            cur = cur.next;
            i++;
        }

        return new ImmutableLinkedList(newArrayList);
    }


    @Override
    public ImmutableLinkedList set(int index, Object e) {
        if (indexOf(index) == -1) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newArrayList = new Object[size()];
        Node cur = head;
        int i = 0;
        while (cur != null) {
            if (i != index) {
                newArrayList[i] = cur.data;
            }
            else {
                newArrayList[i] = e;
            }
            i++;
            cur = cur.next;
        }
        return new ImmutableLinkedList(newArrayList);
    }

    @Override
    public int indexOf(Object e) {
        Node cur = head;
        int i = 0;
        while (cur != null)
        {
            if (cur.data == e)
            {
                return i;
            }
            cur = cur.next;
            i++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newArrayList = new Object[size()];
        Node cur = head;
        int i = 0;
        while (cur != null)
        {
            newArrayList[i] = cur.data;
            i++;
            cur = cur.next;
        }
        return newArrayList;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public ImmutableLinkedList addFirst(Object obj)
    {
        return add(0, obj);
    }

    public ImmutableLinkedList addLast(Object obj)
    {
        return add(obj);
    }

    public Object getFirst()
    {
        return get(0);
    }

    public Object getLast()
    {
        return get(size()-1);
    }

    public ImmutableLinkedList removeFirst()
    {
        return remove(0);
    }

    public  ImmutableLinkedList removeLast()
    {
        return remove(size()-1);
    }
}
