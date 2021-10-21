package agh.ii.prinjava.proj1.impl;

public class DLinkList<E> {

    /**
     * function that add the given element at the beginning
     * of the doubly linked list
     * if it's the first element of the list, a no new Node is created
     * @param el
     */
    void addFirst(E el){
        if(first.elem != null) {
            Node<E> new_node = new Node<>();
            new_node.elem = el;
            new_node.next = first;
            first.prev = new_node;
            first = new_node;
        }
        first.elem = el;
    }

    /**
     * function that add the given element at the end
     * of the doubly linked list
     * if it's the first element of the list, a no new Node is created
     * @param el
     */
    void addLast(E el){
        Node<E> last = getLast();
        if(last.elem != null) {
            Node<E> new_node = new Node<>();
            new_node.elem = el;
            new_node.prev = last;
            last.next = new_node;
        }
        else{
            last.elem = el;
        }
    }

    /**
     *
     * @return the last element of the doubly linked list
     * used in addLast and removeLast
     */
    protected Node<E> getLast(){
        Node<E> temp = first;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp;
    }

    protected E getLastValue(){
        Node<E> temp = first;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp.elem;
    }

    protected E getFirst(){
        return first.elem;
    }

    /**
     *
     * @return the first element of the doubly linked list
     */
    E removeFirst(){
        if (first.elem != null) {
            Node<E> temp = first;
            E toReturn = temp.elem;
            if (numOfElems() > 1) {
                first.next.prev = null;
                first = first.next;
                temp.next = null;
            }else {
                first.elem = null;
            }
            return toReturn;
        }
        return null;
    }

    /**
     *
     * @return the last element of the doubly linked list
     */
    E removeLast(){
        Node<E> last = getLast();
        if (last.elem != null) {
            Node<E> temp = last;
            E toReturn = temp.elem;
            if (numOfElems() > 1) {
                last.prev.next = null;
                temp.prev = null;
            }else {
                last.elem = null;
            }
            return toReturn;
        }
        return null;
    }


    /**
     *
     * @return toString of the doubly linked list
     */
    @Override
    public String toString() {
        Node<E> temp = first;
        String tostring = "DLinkList{ ";
        if(numOfElems() != 0) {
            tostring += temp.elem + " ";
            temp = temp.next;
            while (temp != null) {
                tostring += temp.elem + " ";
                temp = temp.next;
            }
        }
        return tostring + "}";
    }

    int numOfElems(){
        if(first.elem == null){
            return 0;
        }
        else{
            int counter = 1;
            Node<E> temp = first;
            while(temp.next != null) {
                counter++;
                temp = temp.next;
            }
            return counter;
        }
    }

    private static class Node<T> {
        T elem;
        Node<T> next;
        Node<T> prev;
    }

    Node<E> first = new Node<>();
}
