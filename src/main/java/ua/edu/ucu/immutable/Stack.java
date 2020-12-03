package ua.edu.ucu.immutable;

import ua.edu.ucu.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList stack;
    public Stack(ImmutableLinkedList stack) {
        this.stack = stack;
    }

    public void push(Object el) {
        stack = stack.addFirst(el);
    }

    public Object pop() {
        Object el = stack.getFirst();
        stack = stack.removeFirst();
        return el;
    }

    public Object peek() {
        return  stack.getFirst();
    }

    public ImmutableLinkedList getStack() {
        return stack;
    }
}
