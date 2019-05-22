package com.shilei.algo.queue;

/**
 * @Description: 基于链表实现队列
 * @Author: shilei
 * @Date: 2019/5/20 20:03
 **/
public class LinkedQueue<E> {

    private Node<E> head;
    private Node<E> tail;

    private int size = 0;

    public LinkedQueue(){
    }

    /**
     * 从队尾添加节点
     * @param e
     * @return
     */
    public boolean add(E e){
        Node<E> node = new Node<>(e);
        if(head == null){// 队列为空
            head = node;
        }else{
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        size++;
        return true;
    }

    /**
     * 从队头弹出节点，并移除
     * @return
     */
    public E poll(){
        if(head == null){
            System.out.println("poll -- queue is empty.");
            return null;
        }
        Node<E> next = head.next;
        if(next != null){
            next.prev = null;
        }else{// 已经队头和队尾指向一个元素了
            tail = next;
        }
        E value = head.value;
        head.next = null;
        head = next;
        return value;
    }

    private void print(){
        if(head == null){
            System.out.println("queue is empty.");
        }
        Node<E> node = head;
        while (node != null){
            System.out.print(node.value+",");
            node = node.next;
        }
        System.out.println("  print");
    }

    private void reversePrint(){
        if(head == null){
            System.out.println("queue is empty.");
        }
        Node<E> node = tail;
        while (node != null){
            System.out.print(node.value+",");
            node = node.prev;
        }
        System.out.println("  reversePrint");
    }


    public static class Node<E>{

        private E value;
        private Node<E> prev;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }


    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.add(1);
        linkedQueue.add(2);
        linkedQueue.add(3);
        linkedQueue.add(4);

        linkedQueue.print();
        linkedQueue.reversePrint();
        linkedQueue.poll();
        linkedQueue.poll();
        linkedQueue.print();
        linkedQueue.reversePrint();
        linkedQueue.add(5);
        linkedQueue.print();
        linkedQueue.reversePrint();

        linkedQueue.poll();
        linkedQueue.poll();
        linkedQueue.print();
        linkedQueue.reversePrint();

        linkedQueue.poll();
        linkedQueue.print();
        linkedQueue.reversePrint();

    }

}
