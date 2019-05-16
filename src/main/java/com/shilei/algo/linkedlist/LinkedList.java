package com.shilei.algo.linkedlist;

/**
 * @Description: 基于链表实现一个功能
 *
 *  1.单链表反转
 *  2.找到链表环中的某个节点
 *  3.找链表环中的入口节点
 *  4.两个有序链表合并
 *   5.删除链表倒数第n个节点
 *  6.求链表的中间节点
 *
 * @Author: shilei
 * @Date: 2019/5/15 20:04
 **/
public class LinkedList<T> {

    private Node<T> head;

    private Node<T> node;

    private int size = 0;


    public int getSize() {
        return size;
    }

    public Node<T> getHead() {
        return head;
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
        size++;
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

    /**
     * 查找第index索引下的节点
     * @param index
     * @return
     */
    public Node<T> index(int index){
        Node<T> _node = null;
        if(index < 0 && index > size){
            return _node;
        }
        if(index == 0){
            return head;
        }
        if(index == size){
            return node;
        }
        _node = head;
        for(int i=1; i<=index; i++){
            _node = _node.next;
        }
        return _node;
    }

    /**
     * 查看链表中是否存在这个节点
     * @param t
     * @return
     */
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
        Node<T> _node,prevNode,nextNode;
        if(t.equals(head.value)){
            _node = head;
            // 移除头结点
            head = head.next;
            // 将头节点指向null,释放此节点
            _node.next = null;
            if(head != null){
                head.prev = null;
            }else{
                node.prev = null;
                node = head;
            }
            size--;
            return true;
        }
        _node = head.next;
        boolean flag = false;
        while (_node != null){//直到遍历到节点为空
            if(t.equals(_node.value)){
                prevNode = _node.prev;
                nextNode = _node.next;
                if(prevNode != null){
                    prevNode.next = nextNode;
                    if(nextNode == null){// 遍历到最后一个节点，控制好边界
                        node = prevNode;
                    }
                }
                if(nextNode != null){
                    nextNode.prev = prevNode;
                }
                _node.next = null;
                _node.prev = null;
                flag = true;
                size--;
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
     * 1.单链表反转
     */
    public void reverse(){
        if(head == null){
            return ;
        }
        Node<T> _node = head,prev = null,next;
        while (_node != null){
            next = _node.next;
//            if(next == null){// 在这里设置头节点，有要增加一次判断所以效率比循环外设置慢一点
//                head = _node;
//            }
            // 将当前节点指向前一个节点，进行反转
            _node.next = prev;
            prev = _node;
            _node = next;
        }
        // 指定头节点为之前的尾节点
        head = prev;
    }

    /**
     * 两个链表合并
     * @param aList
     * @param bList
     * @return
     */
    public static Node<Integer> mergeLinkedList(Node<Integer> aList,Node<Integer> bList){
        if(aList == null){
            return bList;
        }
        if(bList == null){
            return aList;
        }
        Node<Integer> mergeHead = null;
        if(aList.value < bList.value){
            mergeHead = aList;
            mergeHead.next = mergeLinkedList(aList.next,bList);
        }else{
            mergeHead = bList;
            mergeHead.next = mergeLinkedList(aList,bList.next);
        }
        return mergeHead;
    }

    /**
     * 查找倒数第K个位置的节点
     * @return
     */
    public Node<T> findKthToTail(int k){
        if(head == null){
            return head;
        }
        Node<T> aNode = head,bNode = head;
        for(int i=0; i<k; i++){
            aNode = aNode.next;
            if(aNode == null){
                return aNode;
            }
        }
        System.out.println(aNode);
        while(aNode != null){
            aNode = aNode.next;
            bNode = bNode.next;
        }
        return bNode;
    }

    /**
     * 移除倒数第index个位置的数组
     * @param index
     */
    public void removeIndex(int index){
        if(index < 1 && index > size){
            return ;
        }
        if(head == null){
            return ;
        }
        Node<T> _node = head,prev,next;
        int m = size-index;
        System.out.println("m:"+m);
        if(m == 0){//移除链表头节点
            remove(head.value);
            return ;
        }
//        for(int j=1; j<=m; j++){
//            _node = _node.next;
//        }

        _node = this.index(m);
        System.out.println("_node:"+_node);
        prev = _node.prev;
        next = _node.next;
        if(prev != null){
            prev.next = next;
        }
        if(next != null){
            next.prev = prev;
        }
        size--;
        _node.prev = null;
        _node.next = null;

    }

    /**
     * 查找链表的中间节点
     * @return
     */
    public Node<T> findMidNode(){
        int mid = size%2 == 0 ? (size/2):(size/2+1);
        System.out.println("mid:"+mid);
        mid--;
        return this.index(mid);
    }

    /**
     * 查找链表的中间节点-通过快慢遍历
     * @return
     */
    public Node<T> findMidNodeByFL(){
        if(head == null){
            return null;
        }
        Node<T> fast = head;
        Node<T> slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 找到链表环中的某个节点
     * @return
     */
    public Node<T> meetingNode(){
        if(head == null){
            return head;
        }
        // TODO 制造环
        if(size > 3){
            System.out.println("head.next.next:"+head.next.next);
            node.next = head.next.next;
        }
        Node<T> fast = head.next,slow = head;
        while (fast != null && fast.next != null){// 如果存在环会一直喜欢下去
            if(fast == slow){
                return slow;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return null;
    }

    /**
     * 查找链表中环的入口节点
     * @return
     */
    public Node<T> entryNodeOfLoop(){
        if(head == null){
            return head;
        }
        // 1.找到相遇的节点
        Node<T> meetingNode = meetingNode();
        System.out.println("meetingNode:"+meetingNode);
        // 2.计算环有几个节点
        Node<T> _node = meetingNode.next;
        int cnt = 1;
        while(_node != meetingNode){
            ++cnt;
            _node = _node.next;
        }
        System.out.println("cnt:"+cnt);
        //
        Node<T> aNode=head,bNode=head;
        for(int i=0; i<cnt; ++i){// 3.a指针先走完环的数量走完
            aNode = aNode.next;
        }
        int j = 0;
        while(aNode != bNode){// 4.a,b指针一起移动，当相遇时则为环的入口，移动的步数为(size-cnt)
            aNode = aNode.next;
            bNode = bNode.next;
            j++;
        }
        System.out.println("j:"+j);
        return aNode;
    }


    public static class Node<T>{

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

        /**
         * 遍历出当前节点以及后面的节点
         */
        public void printAll(){
            System.out.print(value+",");
            Node<T> _node = next;
            while(_node != null){
                System.out.print(_node.value+",");
                _node = _node.next;
            }
        }

    }

    public static class User{

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
        linkedList.add(user1);
        linkedList.add(user2);
        linkedList.add(user3);
        linkedList.add(user4);

        linkedList.print();
        linkedList.reversePrint();

        System.out.println("====================");
        linkedList.reverse();
        linkedList.print();

//        System.out.println("remove(user1):"+linkedList.remove(user1));
//
//        System.out.println("size:"+linkedList.getSize());
//        linkedList.print();
//        System.out.println("======================");
//        linkedList.reversePrint();

    }



}
