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
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    @Override
    protected Node<K,V> insertValue(K key, V value) {
        Node<K,V> child = getChild(key);
        // 添加到子节点中
        Node node = child.insertValue(key,value);
        System.out.println(String.format("child insertValue %s",node));

        if(this.isFull()){
            InternalNode rightBrother = (InternalNode)split();
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
        return this.findRoot();
    }

    private Node<K,V> getChild(K key) {
        Collections.binarySearch(this.keys,key);
        //1.查找key在关键字列表中的位置，集合中不存在则返回的为在keys中第一个小于key的为位置+1
        int position = Collections.binarySearch(this.keys,key);
        // 大于等于0时说明位置说在关键字中已经找到，index=0是存放的左孩子，右孩子的第一个key会放入到父节点关键字中
        // 小于0时没有找到，在（-position - 1）位置插入新值即可
        int childIndex =  position >= 0 ? position + 1 : -position - 1;
        return children.get(childIndex);
    }

    @Override
    protected Node<K, V> split() {
        // 创建右兄弟节点
        InternalNode rightBrother = new InternalNode();
        // 计算右孩子拷贝范围
        int startIndex = (this.getKeySize()+1)/2 , endIndex = (this.getKeySize());
        System.out.println(String.format("split getKeySize[%s] startIndex[%s] endIndex[%s]",this.getKeySize(),startIndex,endIndex));
        rightBrother.keys.addAll(this.keys.subList(startIndex,endIndex));
        rightBrother.children.addAll(this.children.subList(startIndex,endIndex));

        System.out.println(String.format("internalNode 1 split my keys %s",this.keys));
        System.out.println(String.format("internalNode 1 split rightBrother keys %s",rightBrother.keys));

        this.keys.subList(startIndex,endIndex).clear();
        this.children.subList(startIndex,endIndex).clear();

        System.out.println(String.format("internalNode 2 split my keys %s",this.keys));
        System.out.println(String.format("internalNode 2 split rightBrother keys %s",rightBrother.keys));

        return rightBrother;
    }

    @Override
    protected K getFirstKey() {
        return keys.get(0);
    }


}
