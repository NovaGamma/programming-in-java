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
        else{
            first.elem = el;
        }
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
        Node<E> temp = first;
        first.next.prev = null;
        first = first.next;
        temp.next = null;
        return temp.elem;
    }

    /**
     *
     * @return the last element of the doubly linked list
     */
    E removeLast(){
        Node<E> last = getLast();
        last.prev.next = null;
        last.prev = null;
        return last.elem;
    }


    /**
     *
     * @return toString of the doubly linked list
     */
    @Override
    public String toString() {
        Node<E> temp = first;
        String tostring = "DLinkList{ ";
        while(temp.next != null){
            tostring += temp.elem + " / ";
            temp = temp.next;
        }
        return tostring + " }";
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

    Node<E> first;
}
