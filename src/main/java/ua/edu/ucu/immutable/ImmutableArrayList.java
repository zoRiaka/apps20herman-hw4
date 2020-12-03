package ua.edu.ucu.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {
    private final Object[] arrayList;

    public ImmutableArrayList(Object[] arrayList) {
        this.arrayList = arrayList;
    }

    public ImmutableArrayList() {
        this.arrayList = new Object[0];
    }

    public ImmutableArrayList add(Object e) {
        return addAll(size(), new Object[]{e});
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        int size = size();
        Object[] newArrayList = new Object[size + c.length];
        System.arraycopy(arrayList, 0, newArrayList, 0, index);
        System.arraycopy(c, 0, newArrayList, index, c.length);
        System.arraycopy(arrayList, index, newArrayList, index
                         + c.length, size - index);
        return new ImmutableArrayList(newArrayList);
    }


    @Override
    public Object get(int index) {
        return arrayList[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        Object[] newArrayList = new Object[size() - 1];
        System.arraycopy(arrayList, 0, newArrayList, 0, index);
        System.arraycopy(arrayList, index+1, newArrayList, index, size()
                         - index - 1);
        return new ImmutableArrayList(newArrayList);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        Object[] newArrayList = new Object[size()];
        System.arraycopy(arrayList, 0, newArrayList, 0, size());
        newArrayList[index] = e;
        return new ImmutableArrayList(newArrayList);
    }

    @Override
    public int indexOf(Object e) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.arrayList.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        return arrayList;
    }
    public String toString() {
        return Arrays.toString(arrayList);
    }

}
