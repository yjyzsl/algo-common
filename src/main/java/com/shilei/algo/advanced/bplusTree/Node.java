package com.shilei.algo.advanced.bplusTree;

import java.util.List;

/**
 * @Description B+Tree节点
 * @Author shil20
 * @Date 2019/7/4 9:37
 **/
public abstract class Node<K extends Comparable<? super K>,V> {

    /** 默认节点容量 */
    protected static final int DEFAULT_BRANCHING_FACTOR = 4;
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
     * 将分裂的两个节点设置到父节点上
     * @param curNode
     * @param rightBrother
     */
    protected void propogate(Node<K,V> curNode,Node<K,V> rightBrother){
        // 父节点为空
        if(null == this.parent){
            // 生成父节点
            InternalNode parentNode = new InternalNode();
            parentNode.keys.add(rightBrother.getFirstKey());
            // 将本节点和右兄弟添加父节点几的孩子列表中
            parentNode.children.add(curNode);
            parentNode.children.add(rightBrother);

            // 孩子节点指向父节点
            this.parent = parentNode;
            //rightBrother.parent = parentNode;
        }else{// 父节点不为空
            // 先将右兄弟和关键字插入到父节点
            InternalNode parentNode = (InternalNode)this.parent;
            parentNode.keys.add(rightBrother.getFirstKey());
            parentNode.children.add(rightBrother);

            // 判断父节点是否已满
            if(parentNode.isFull()){
                // 父节点满了则分裂
                parentNode.split();
            }
        }
        // 右兄弟设置父节点
        rightBrother.parent = this.parent;
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

    protected abstract Node<K,V> getFirstNode();

    @Override
    public String toString() {
        return "Node{" +
                "parent=" + parent +
                ", keys=" + keys +
                '}';
    }
}
