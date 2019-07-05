package com.shilei.algo.advanced.bplusTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description 内部节点，为索引节点不存放数据
 * @Author shil20
 * @Date 2019/7/4 9:39
 **/
public class InternalNode<K extends Comparable<? super K>,V> extends Node<K,V>{

    List<Node<K,V>> children;

    public InternalNode() {
        super();
        this.keys = new ArrayList<>(super.branchingFactor);
        this.children = new ArrayList<>(super.branchingFactor);
    }

    public InternalNode(int branchingFactor){
        super(branchingFactor);
        this.keys = new ArrayList<>(branchingFactor);
        this.children = new ArrayList<>(branchingFactor);
    }

    @Override
    protected Node<K,V> insertValue(K key, V value) {
        // 根据关键字查找对应的孩子节点
        Node<K,V> child = getChild(key);
        // 添加到子节点中,并返回子节点的父亲节点
        child.insertValue(key,value);

        if(this.isFull()){
            this.split();
        }
        return this.findRoot();
    }

    /**
     * 删除指定key的关键字
     * @return
     */
    @Override
    protected V deleteValue(K key) {
        return null;
    }

    /**
     * 合并兄弟节点
     * @param brother
     */
    @Override
    protected void merge(Node<K, V> brother) {

    }

    /**
     * 根据关键字查找对应的孩子节点
     * @param key 关键字
     * @return
     */
    private Node<K,V> getChild(K key) {
        //1.查找key在关键字列表中的位置，集合中不存在则返回的为在keys中第一个小于key的为位置+1
        int position = Collections.binarySearch(this.keys,key);
        // 大于等于0时说明位置说在关键字中已经找到，index=0是存放的左孩子，右孩子的第一个key会放入到父节点关键字中
        // 小于0时没有找到，在（-position - 1）位置插入新值即可
        int childIndex =  position >= 0 ? position + 1 : -position - 1;

        // 指定的孩子节点索引超过节点大小时取最后一个孩子
        if(childIndex >= children.size()){
            childIndex = children.size() - 1;
        }
        return children.get(childIndex);
    }

    /**
     * 节点满了,对节点进行分裂层两个节点
     * 第(m+1)/2个节点作为父节点,(m+1)/2左边的为左孩子，(m+1)/2右边的为右孩子
     * 分裂后的右兄弟的孩子刷新父亲节点
     * @return 返回分裂后的右兄弟节点
     */
    @Override
    protected Node<K, V> split() {
        // 创建右兄弟节点
        InternalNode<K,V> rightBrother = new InternalNode<K,V>();
        // 计算中间节点
        int midIndex = (this.getKeySize()+1)/2;

        rightBrother.keys.addAll(this.keys.subList(midIndex,this.keys.size()));
        rightBrother.children.addAll(this.children.subList(midIndex,this.children.size()));

        // 将右兄弟的叶子节点的父节点指向右兄弟
        List<Node<K,V>> rightChildren = rightBrother.children;
        for(Node<K,V> child:rightChildren){
            child.parent = rightBrother;
        }

        System.out.println(String.format("internalNode 1 split my keys %s",this.keys));        System.out.println(String.format("internalNode 1 split rightBrother keys %s",rightBrother.keys));

        // 删除已经移动到右兄弟中的数据
        this.keys.subList(midIndex,this.keys.size()).clear();
        this.children.subList(midIndex,this.children.size()).clear();

        // midIndex-1下标的数据作为父节点的关键字
        K parentKey = keys.remove(midIndex-1);

        System.out.println(String.format("internalNode 2 split my parentKey[%s] keys %s",parentKey,this.keys));
        System.out.println(String.format("internalNode 2 split rightBrother keys %s",rightBrother.keys));

        // 将分裂的两个节点设置到父节点上
        this.propogate(parentKey,this,rightBrother);

        return rightBrother;
    }


    @Override
    protected Node<K,V> getFirstNode(){
        return children.get(0).getFirstNode();
    }

    @Override
    protected K getFirstKey() {
        return keys.get(0);
    }


}
