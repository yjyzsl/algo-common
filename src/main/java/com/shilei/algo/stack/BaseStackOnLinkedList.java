package com.shilei.algo.stack;

/**
 *
 * @Description: 基于链表实现栈
 * @Author: shilei
 * @Date: 2019/5/17 17:32
 **/
public class BaseStackOnLinkedList<T> {

    private Node<T> head;

    /**
     * 弹出栈顶元素
     * @return
     */
    public T pop(){
        if(head == null){
            return null;
        }
        Node<T> node = head;
        // 头节点指向下一个节点
        head = node.next;
        // 断开头节点指针
        node.next = null;
        return node.value;
    }

    /**
     * 向栈顶添加节点
     * @param t
     */
    public void push(T t){
        if(t == null){
            return ;
        }
        Node<T> node = head;
        head = new Node(t);
        // 新栈顶指向旧栈顶
        head.next = node;
    }

    public void print(){

        if(head == null){
            return ;
        }
        Node<T> node = head;
        while (node != null){
            System.out.print(node+",");
            node = node.next;
        }
        System.out.println();
    }



    public static class Node<T>{
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public static void main(String[] args) {

        BaseStackOnLinkedList baseStack = new BaseStackOnLinkedList();

        baseStack.push(1);
        baseStack.push(2);
        System.out.println(baseStack.pop());
        baseStack.push(3);
        baseStack.push(4);
        baseStack.print();
        System.out.println(baseStack.pop());
        System.out.println(baseStack.pop());
        System.out.println(baseStack.pop());
        System.out.println(baseStack.pop());

    }

}
