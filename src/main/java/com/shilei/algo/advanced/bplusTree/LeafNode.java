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
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public LeafNode(int branchingFactor){
        super(branchingFactor);
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
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
        if(position >= 0){
            this.values.set(valueIndex,value);
        }else{// 新增关键字和值
            this.keys.add(valueIndex,key);
            this.values.add(valueIndex,value);
        }
        if(this.isFull()){// 关键字已满需要分裂
            // 右兄弟
            LeafNode<K, V> rightBrother = (LeafNode)this.split();

            // 生成父节点
            InternalNode parentNode = new InternalNode();
            parentNode.keys.add(rightBrother.getFirstKey());
            // 将本节点和右兄弟添加父节点几的孩子列表中
            parentNode.children.add(this);
            parentNode.children.add(rightBrother);

            // 孩子节点指向父节点
            this.parent = parentNode;
            rightBrother.parent = parentNode;
        }
        return this.parent;
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

        // 本节点指向右兄弟
        this.next = rightBrother;


        this.keys.subList(startIndex,endIndex).clear();
        this.values.subList(startIndex,endIndex).clear();
        System.out.println(String.format("leafnode 2 split my keys %s",this.keys));
        System.out.println(String.format("leafnode 2 split rightBrother keys %s",rightBrother.keys));

        return rightBrother;
    }

    @Override
    protected K getFirstKey() {
        return keys.get(0);
    }


}
