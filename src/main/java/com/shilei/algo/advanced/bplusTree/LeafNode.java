package com.shilei.algo.advanced.bplusTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description 叶子节点，存放数据
 * @Author shil20
 * @Date 2019/7/4 9:40
 **/
public class LeafNode<K extends Comparable<? super K>,V> extends Node<K,V>{

    /** 数据集合 */
    List<V> values;
    /** 右兄弟 */
    LeafNode<K,V> next;
    /** 左兄弟 */
    LeafNode<K,V> prev;

    public LeafNode() {
        super();
        this.keys = new ArrayList<>(super.branchingFactor);
        this.values = new ArrayList<>(super.branchingFactor);
    }

    public LeafNode(int branchingFactor){
        super(branchingFactor);
        this.keys = new ArrayList<>(branchingFactor);
        this.values = new ArrayList<>(branchingFactor);
    }

    /**
     * 插入叶子节点数据，并返回根节点
     * @param key
     * @param value
     * @return
     */
    @Override
    protected Node<K,V> insertValue(K key,V value) {
        //1.查找key在关键字列表中的位置，集合中不存在则返回的为在keys中第一个小于key的为位置+1
        int position = Collections.binarySearch(this.keys,key);
        // 大于等于0时说明位置说在关键字中已经找到，只需要替换原值，小于0时没有找到，在（-position - 1）位置插入新值即可
        int valueIndex =  position >= 0 ? position : -position - 1;

        // 叶子节点关键字中能找到,则直接设置对应下标的值
        if(position >= 0){
            this.values.set(valueIndex,value);
        }else{// 新增关键字和值
            this.keys.add(valueIndex,key);
            this.values.add(valueIndex,value);
        }

        // 关键字已满需要分裂
        if(this.isFull()){
            // 右兄弟
            this.split();
        }
        return this.findRoot();
    }




    /**
     * 叶子节点分裂
     * @return
     */
    @Override
    protected Node<K, V> split() {
        // 创建右兄弟节点
        LeafNode rightBrother = new LeafNode();
        // 计算右孩子拷贝范围
        int startIndex = (this.getKeySize()+1)/2 , endIndex = (this.getKeySize());
        System.out.println(String.format("split getKeySize[%s] startIndex[%s] endIndex[%s]",this.getKeySize(),startIndex,endIndex));
        rightBrother.keys.addAll(this.keys.subList(startIndex,endIndex));
        rightBrother.values.addAll(this.values.subList(startIndex,endIndex));
        System.out.println(String.format("leafnode 1 split my keys %s",this.keys));
        System.out.println(String.format("leafnode 1 split rightBrother keys %s",rightBrother.keys));

        // 本节点后继指针指向右兄弟
        this.next = rightBrother;
        // 右兄弟前驱指针指向本节点
        rightBrother.prev = this;

        this.keys.subList(startIndex,endIndex).clear();
        this.values.subList(startIndex,endIndex).clear();
        System.out.println(String.format("leafnode 2 split my keys %s",this.keys));
        System.out.println(String.format("leafnode 2 split rightBrother keys %s",rightBrother.keys));

        // 将分裂的两个节点设置到父节点上
        this.propogate(this,rightBrother);

        return rightBrother;
    }

    @Override
    protected Node<K, V> getFirstNode() {
        return this;
    }

    @Override
    protected K getFirstKey() {
        return keys.get(0);
    }


}
