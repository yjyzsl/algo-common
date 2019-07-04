package com.shilei.algo.advanced.bplusTree;

import java.util.List;

/**
 * @Description B+Tree节点
 * @Author shil20
 * @Date 2019/7/4 9:37
 **/
public abstract class Node<K extends Comparable<? super K>,V> {

    /** 默认节点容量 */
    private static final int DEFAULT_BRANCHING_FACTOR = 4;
    /** 父节点 */
    protected Node parent;
    /** 关键字列表 */
    protected List<K> keys;
    /** 节点容量，即最多多少个子节点 */
    protected int branchingFactor;

    public Node() {
        this(DEFAULT_BRANCHING_FACTOR);
    }

    public Node(int branchingFactor) {
        if(branchingFactor <= 2){
            throw new IllegalArgumentException("Illegal branching factor: " + branchingFactor);
        }
        this.branchingFactor = branchingFactor;
    }

    protected int getKeySize(){
        return keys.size();
    }

    /**
     * 查找根节点
     * @return
     */
    protected Node<K,V> findRoot(){
        Node<K,V> node = this;
        while (node.parent != null){
            node = node.parent;
        }
        return node;
    }

    protected boolean isFull(){
        // 关键字个数最多为 maxSize - 1个
        return keys.size() >= branchingFactor;
    }

    /**
     * 插入一个值,并返回根节点
     * @param key
     * @param value
     */
    protected abstract Node<K,V> insertValue(K key,V value);

    /**
     * 将当前节点分裂成两个节点，然后返回兄弟节点
     * @return
     */
    protected abstract Node<K,V> split();

    /**
     * 获取节点的第一个key
     * @return
     */
    protected abstract K getFirstKey();

    @Override
    public String toString() {
        return "Node{" +
                "parent=" + parent +
                ", keys=" + keys +
                '}';
    }
}
