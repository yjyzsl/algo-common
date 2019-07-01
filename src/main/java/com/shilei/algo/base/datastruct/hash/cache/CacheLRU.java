package com.shilei.algo.base.datastruct.hash.cache;

/**
 * @Description 通过散列表+双向链表实现缓存LRU算法
 * 通过散列表查找可以时间复杂度为O(1),比链表查找的时间复杂度O(n)要更快
 * @Author shil20
 * @Date 2019/7/1 13:37
 **/
public class CacheLRU {

    // 散列表数据
    private Node[] datas;

    // 头节点
    private Node head = new Node();

    // 尾节点
    private Node tail = head;

    // 散列表容量
    private int capacity;

    // 散列表大小
    private int size;

    // 散列表最大容量
    private int MAX_CAPACITY;

    // 散列表最大默认容量
    private static final int DEFAULT_MAX_CAPACITY = 1 << 16;

    // 散列表默认容量
    private static final int DEFAULT_CAPACITY = 16;

    public CacheLRU(){
        this(DEFAULT_CAPACITY);
    }

    public CacheLRU(int capacity){
        this(capacity,DEFAULT_MAX_CAPACITY);
    }

    public CacheLRU(int capacity,int maxCapacity){
        this.capacity = capacity;
        this.MAX_CAPACITY = maxCapacity;
        this.datas = new Node[capacity];
        this.size = 0;
    }

    class Node{
        // cache key
        String key;
        // cache value
        Object value;
        // 前驱节点
        Node prev;
        // 后继节点;
        Node next;
        // 散列表拉链节点
        Node hnext;

        @Override
        public String toString() {
            return String.format("{%s:%s}",key,value);
        }
    }

    /**
     * 添加数据
     * 1.缓存中存在数据，将节点值替换，然后将节点移动到尾部
     * 2.缓存中不存在数据，并且数据没有满，将节点插入到链表尾部
     * 3.缓存中不存在数据，并且数据满了，将头节点删除，然后将节点插入到链表尾部
     * @param key
     * @param value
     */
    public void put(String key, Object value){
        if(null == key || key.trim().length() == 0){
            return;
        }
        int index = hash(key);
        Node findNode = datas[index];
        Node prevHNode = null;
        while (findNode != null){
            if(key.equals(findNode.key)){// key相等说明找到了
                break;
            }
            prevHNode = findNode;
            findNode = findNode.hnext;
        }
        Node newNode = null;

        // 1.缓存中存在数据，将节点值替换
        if(null != findNode){
            findNode.value = value;
        }else{
            // 添加节点到散列表
            newNode = new Node();
            newNode.key = key;
            newNode.value = value;

            // 当节点没找到时,前驱节点则为拉链的尾节点
            Node tailHNode = prevHNode;
            if(null != tailHNode){// 拉链尾节点不为空,将新节点添加到尾节点
                tailHNode.hnext = newNode;
            }else{// 拉链尾节点为空,将新节点添加到对应索引下标的数据
                datas[index] = newNode;
            }
        }
        // 2.将节点添加到双向链表中
        linkedLRU(findNode,newNode);

    }

    private void linkedLRU(Node findNode, Node newNode) {
        // 存储中存在,将节点移动到尾部
        if(null != findNode){
            moveToTailNode(findNode);
        }else if(null != newNode){
            Node headNode = head.next;
            if(size >= MAX_CAPACITY){// 数据量已经大于等于最大容量
                // 删除头节点
                // 查找头节点在散列表中拉链的前驱节点, head节点为空数据节点
                removeHNode(headNode);
                // 从链表中删除
                head = headNode.next;
                head.prev = null;
                headNode.next = null;
                headNode.prev = null;
                headNode.hnext = null;
                --size;
            }
            // 添加到链表尾节点
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            ++size;
        }
    }

    /**
     * 从散列表中移除节点
     * @param node
     */
    private void removeHNode(Node node) {
        int index = hash(node.key);
        Node prevHNode = datas[index];

        while (prevHNode != null){
            if(prevHNode.hnext == node){
                break;
            }
            prevHNode = prevHNode.hnext;
        }
        // 从散列表中删除
        if (null != prevHNode) {// 前驱节点不为空
            prevHNode.hnext = node.hnext;
        } else {// 前驱节点为空，则数量指定下标设置为null
            datas[index] = node.hnext;
        }
    }

    /**
     * 将节点移动到链表尾部
     * @param node
     */
    private void moveToTailNode(Node node) {
        if(node != tail){// 查找的节点不是尾节点
            // 从双向链表中移除
            deleteNode(node);
        }else{// 尾节点不处理
            return;
        }
        if(head == node){// 查找的节点是头节点
            head = node.next;
        }
        // 将findNode节点移动到链表尾部
        tail.next = node;
        node.prev = tail;
        node.next = null;
        // 链表尾节点指向查找的节点
        tail = node;
    }

    /**
     * 删除节点
     * @param node
     */
    private void deleteNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        if (null != prevNode) {
            prevNode.next = nextNode;
        }
        if (null != nextNode) {
            nextNode.prev = prevNode;
        }
    }


    /**
     * 先在缓存中查找数据，如果查找到节点则将节点从散列表和双向链表中移除
     * @param key
     * @return
     */
    public Object remove(String key){
        Node findNode = findNode(key);
        Object value = null;
        if(null != findNode){
            // 1.先从双向链表中移除
            deleteNode(findNode);
            findNode.prev = null;
            findNode.next = null;
            // 2.从散列表中删除
            removeHNode(findNode);
            findNode.hnext = null;
            value = findNode.value;

            --size;
        }

        return value;
    }


    /**
     * 在缓存中如果查找到了数据,则将该元素移动到链表尾部
     * 查找时间复杂度为O(1)
     * @param key
     * @return
     */
    public Object get(String key){
        Node findNode = findNode(key);

        if(null != findNode){
            // 找到前驱节点
            moveToTailNode(findNode);
            return findNode.value;
        }
        return null;
    }

    /**
     * 查找拉链表尾节点
     * @param key
     * @return
     */
    private Node findTailHnode(String key) {
        int index = hash(key);
        Node findNode = datas[index];
        while(findNode != null){
            if(findNode.hnext == null){
                break;
            }
            findNode = findNode.hnext;
        }
        return findNode;
    }

    /**
     * 查找指定的元素
     * @param key
     * @return
     */
    private Node findNode(String key){
        if(null == key || key.trim().length() == 0){
            return null;
        }
        int index = hash(key);
        Node findNode = datas[index];
        while (findNode != null){
            if(key.equals(findNode.key)){// key相等说明找到了
                return findNode;
            }
            findNode = findNode.hnext;
        }
        return null;
    }

    public void printHashTable(){
        Node node;
        System.out.print("printHashTable size:"+size+" --> ");
        for(int i=0; i<capacity; i++){
            node = datas[i];
            while (node != null) {
                System.out.print(node+",");
                node = node.hnext;
            }
        }
        System.out.println();
        System.out.println("--------------------------------------------");
    }

    /**
     * 链表的形式打印
     */
    public void printLinkList(){
        Node node = head;
        System.out.print("printLinkList  size:"+size+" --> ");
        while (node != null){
            if(node.key != null){
                System.out.print(node + ",");
            }
            node = node.next;
        }
        System.out.println();
        //System.out.println("==========================================");
    }


    public int hash(String key){
        int hashCode = key.hashCode();
        return hashCode % capacity;
    }
}
