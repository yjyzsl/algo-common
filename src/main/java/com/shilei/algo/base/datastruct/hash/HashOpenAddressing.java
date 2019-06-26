package com.shilei.algo.base.datastruct.hash;

/**
 * @Description 使用开放寻址法实现散列表
 * @Author shil20
 * @Date 2019/6/18 19:50
 **/
public class HashOpenAddressing {

    static class Node{
        String key;   // 散列表Key
        Object value; // 散列表值
        boolean isDelete; // 是否已经删除

        public Node(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] nodes; // 数组
    private int capacity; // 数组容量
    transient int size;     // 目前数据大小

    public HashOpenAddressing(int capacity){
        nodes = new Node[capacity];
        this.capacity = capacity;
    }

    /**
     * 插入
     * @param key
     * @param value
     * @return
     */
    public boolean put(String key,Object value){
        if(key == null){
            return false;
        }
        // 1.计算key的hashcode
        int hashcode = key.hashCode();
        // 2.hashcode对数组容量取余，算出数组索引下标index
        int index = hash(hashcode);
        // 3.判断数组index下标是否有值
        Node node = nodes[index];

        Node newNode = new Node(key,value);
        // 4.如果没有则插入
        if(node == null){
            nodes[index] = newNode;
            return true;
        }else{
            // 5.有则往后探测直到有空闲时再插入
            int i = index+1;
            while ( i % capacity != index){
                node = nodes[i];
                if(node == null){
                    nodes[i] = newNode;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 查找
     * @param key
     * @return
     */
    public Object get(String key){
        if(key == null){
            return null;
        }
        // 1.计算key的hashcode
        int hashcode = key.hashCode();
        // 2.hashcode对数组容量取余，算出数组索引下标index
        int index = hash(hashcode);
        // 3.判断数组index下标是否有值
        Node node = nodes[index];
        if(node != null){
            if(key.equals(node.key)){
                return node;
            }
            int i = index+1;
            // 4.查找到
            while ( i % capacity != index){
                node = nodes[i];
                if(node == null){// 查找到为空的节点时说明数组中不存在
                    return null;
                }else if(key.equals(node.key)){// 直到寻找到key时返回
                    return node;
                }
                i++;
            }
            return null;
        }else{
            return null;
        }
    }

    public int hash(int hashCode){
        // capacity为2的指数
        return hashCode % capacity;
    }

}
