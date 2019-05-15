package com.shilei.algo.linkedlist;

/**
 * @Description: 基于链表实现一个功能
 *
 *  1.单链表反转
 *  2.链表中环的检测
 *  3.两个有序链表合并
 *  4.删除链表倒数第n个节点
 *  5.求链表的中间节点
 *
 * @Author: shilei
 * @Date: 2019/5/15 20:04
 **/
public class LinkedList<T> {

    private Node<T> head;

    private Node<T> node;

    private class Node<T>{

        public Node(T value) {
            this.value = value;
        }

        // 值
        T value;

        // 下一个节点
        Node<T> next;

        // 上一个节点
        Node<T> prev;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     * 添加新节点到链表尾部，无序
     * @param t
     */
    public void add(T t){
        // 创建新节点
        Node _node = new Node(t);
        if(head == null){// 如果链表为空则将头节点指向新节点
            head = _node;
        }else{
            // 尾节点后继指针指向新增节点
            node.next = _node;
            // 新增节点前驱指针指向尾节点
            _node.prev = node;
        }
        // 将尾指针指向新节点
        node = _node;
    }

    /**
     * 在链表中查找一个值为t节点
     * @param t
     * @return
     */
    public Node<T> find(T t){
        if(head == null){
            return null;
        }
        if(t == null){
            return null;
        }
        // 指向对头，_node相当于一个哨兵
        Node<T> _node = head;
        while (_node != null){//直到遍历到节点为空
            if(t.equals(_node.value)){
                return _node;
            }
            _node = _node.next;
        }
        return null;
    }


    public boolean contains(T t){
        Node<T> _node = this.find(t);
        return _node == null ? false:true;
    }

    /**
     * 删除链表中的一个元素
     * @param t
     * @return
     */
    public boolean remove(T t){
        if(head == null){
            return false;
        }
        if(t == null){
            return false;
        }
        Node<T> _node;
        if(t == head.value){
            _node = head;
            // 移除头结点
            head = head.next;
            // 将头节点指向null,释放此节点
            _node.next = null;
            return true;
        }
        _node = head.next;
        boolean flag = false;
        while (_node != null){//直到遍历到节点为空
            if(t.equals(_node.value)){
                Node<T> prevNode = _node.prev;
                Node<T> nextNode = _node.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
                _node.next = null;
                _node.prev = null;
                flag = true;
            }
            _node = _node.next;
        }
        return flag;
    }

    /**
     * 正序打印链表中的元素
     */
    public void print(){
        // 链表为空则直接返回
        if(head == null){
            return ;
        }
        System.out.print(head);
        System.out.print(",");
        Node<T> _node = head.next;
        while (_node != null){//直到遍历到节点为空
            System.out.print(_node);
            System.out.print(",");
            _node = _node.next;
        }
        System.out.println();
    }

    /**
     * 逆序打印链表中的元素
     */
    public void reversePrint(){
        // 链表为空则直接返回
        if(node == null){
            return ;
        }
        System.out.print(node);
        System.out.print(",");
        Node<T> _node = node.prev;
        while (_node != null){//直到遍历到节点为空
            System.out.print(_node);
            System.out.print(",");
            _node = _node.prev;
        }
        System.out.println();
    }

    /**
     * 单链表反转
     */
    public void reverse(){
        if(head == null){
            return ;
        }
        Node<T> _node = head;
        Node<T> prev = null;
        Node<T> next;
        while (_node != null){
            next = _node.next;
            // 将当前节点指向前一个节点，进行反转
            _node.next = prev;
            prev = _node;
            _node = next;
        }
        // 指定头节点为之前的尾节点
        head = prev;
    }



    private static class User{

        String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        LinkedList<User> linkedList = new LinkedList<>();

        User user1 = new User("zhangsan1");
        User user2 = new User("zhangsan2");
        User user3 = new User("zhangsan3");
        User user4 = new User("zhangsan4");
        User user5 = new User("zhangsan2");
        User user7 = new User("zhangsan5");
        User user6 = new User("zhangsan2");

        linkedList.add(user1);
        linkedList.add(user2);
        linkedList.add(user3);
        linkedList.add(user4);
        linkedList.add(user5);
        linkedList.add(user7);

        linkedList.print();
        linkedList.reversePrint();

        System.out.println("find(user2):"+linkedList.find(user2));
        System.out.println("find(user6):"+linkedList.find(user6));
        System.out.println("contains(user2):"+linkedList.contains(user2));
        System.out.println("contains(user6):"+linkedList.contains(user6));

        System.out.println("remove(user5):"+linkedList.remove(user5));
        System.out.println("remove(user6):"+linkedList.remove(user6));

        linkedList.print();
        linkedList.reversePrint();
        System.out.println("==============");
        linkedList.reverse();
        linkedList.print();


    }



}
