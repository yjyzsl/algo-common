package com.shilei.algo.base.datastruct.hash;

import javax.jws.Oneway;

/**
 * @Description 通过链表法实现散列表
 * @Author shil20
 * @Date 2019/6/26 14:38
 **/
public class LindedListHash {

    // 默认初始化大小
    private final static int DEFAULT_INITIAL_CAPACITY = 16;
    // 默认加载因子
    private final static float DEFAULT_LOAD_FACTOR = 0.75f;
    // 最大容量
    private final static int DEFAULT_MAX_CAPACITY = 1 << 30;

    // 存放数据的数组
    private Node[] nodes;
    // 数组容量
    private int capacity;
    // 加载因子
    private float loadFactor;

    private int size;

    public LindedListHash(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public LindedListHash(int capacity){
        this(capacity,DEFAULT_LOAD_FACTOR);
    }

    public LindedListHash(int capacity,float loadFactor){
        if(capacity < 0){
            capacity = DEFAULT_INITIAL_CAPACITY;
        }
        if(capacity > DEFAULT_MAX_CAPACITY){
            capacity = DEFAULT_MAX_CAPACITY;
        }
        this.capacity = capacity;
        this.nodes = new Node[capacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    /**
     * 添加元素
     * @param key
     * @param value
     * @return
     */
    public boolean put(String key,Object value){
        if(key == null){
            return false;
        }
        int index = hash(key);
        Node newNode = new Node(key,value);
        Node indexNode = nodes[index];
        if(indexNode == null){// 索引下标没有被占用
            nodes[index] = newNode;
            size++;
        }else {// 索引下标已经被占用
            boolean findFlag = false;
            while (indexNode.next != null){
                if(indexNode.key.equals(key)){// 找到相同的key,则对原值进行替换
                    indexNode.value = value;
                    findFlag = true;
                    break;
                }
                indexNode = indexNode.next;
            }
            if(!findFlag){// 没有找到相同的key
                indexNode.next = newNode;
                size++;
            }
        }

        return true;
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public Object get(String key){
        if(key == null){
            return null;
        }
        int index = hash(key);
        Node indexNode = nodes[index];
        if(indexNode != null){
            while (indexNode != null){
                if(indexNode.key.equals(key)){
                    break;
                }
                indexNode = indexNode.next;
            }
        }
        if(indexNode != null){
            return indexNode.value;
        }
        return null;
    }

    /**
     * 根据key移除元素，并返回值
     * @param key
     * @return
     */
    public Object remove(String key){
        if(key == null){
            return null;
        }
        int index = hash(key);
        Node indexNode = nodes[index];
        Node removeNode = null;
        if(indexNode != null){
            Node perNode = null;
            while (indexNode != null){
                if(indexNode.key.equals(key)){
                    removeNode = indexNode;
                    indexNode = null;
                    if(perNode != null){// 记录前驱节点
                        perNode.next =  removeNode.next;
                    }else {//移除的为链表的头节点
                        nodes[index] = removeNode.next;
                    }
                    size--;
                    break;
                }
                perNode = indexNode;
                indexNode = indexNode.next;
            }
        }
        return removeNode;
    }

    public void printAll(){
        Node node;
        for(int i=0; i<capacity; i++){
            node = nodes[i];
            while (node != null) {
                System.out.print(node+",");
                node = node.next;
            }
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    /**
     * 根据hashcode获取数组索引下标
     * @param key
     * @return
     */
    private int hash(Object key){
        int hashCode = key.hashCode();
        return hashCode % capacity;
    }

    static class Node{
        String key;
        Object value;
        // 链表
        Node next;

        public Node(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s:%s",key,value);
        }
    }



}
