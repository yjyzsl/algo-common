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
     * 插入叶子节点数据，并返回根节点,如果存在相同的key则直接覆盖掉不做其他处理
     * @param key
     * @param value
     * @return
     */
    @Override
    protected Node<K,V> insertValue(K key,V value) {
        // 查找key在关键字列表中的位置，集合中不存在则返回的为在keys中第一个小于key的为位置+1
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
     * 删除指定key的关键字
     * 1.查看关键字中是否存在,存在执行第2步，否则返回空
     * 2.如果存在则获取指定的值,然后在删除对应的关键字和值
     * 3.查看关键字数量是不是少于(m-1)/2-1个，如果是则需要向兄弟节点借执行第4步，否则不进行处理
     * 4.向兄弟节点借，如果兄弟节点个数大于(m-1)/2-1个，则可以借，否则执行第5步
     * 5.当被借的兄弟节点个数不大于(m-1)/2-1个时，当前节点和兄弟节点合并成一个节点(合并之后节点数还是小于m)，并删除父节点中的key
     * 6.当前节点指向父节点，当父节点数大于等于(m-1)/2-1时，则删除操作结束，否则执行第7步
     * 7.若兄弟节点有富余(大于等于（m-1）/2-1)，父节点下移，兄弟节点key上移，删除结束，否则执行第8步
     * 8.当前节点和兄弟节点以及父节点下移合成一个节点，将当前节点指向父节点，重复第6步
     * @return 返回关键字对应的值，不存在返回空
     */
    @Override
    protected V deleteValue(K key) {
        // 1.查看关键字中是否存在,存在执行第2步，否则返回空
        int position = Collections.binarySearch(keys,key);
        if(position < 0){
            return null;
        }

        // 2.如果存在则获取指定的值,然后在删除对应的关键字和值
        V value = this.values.remove(position);
        System.out.println(String.format("deleteValue position[%s] , value[%s] , keys size[%s]",position,value,this.keys.size()));
        this.keys.remove(position);

        // 3.查看关键字数量是不是少于(m-1)/2-1个，如果是则需要向兄弟节点借执行第4步，否则不进行处理
        int mergeSize = (branchingFactor-1)/2 - 1;
        System.out.println(String.format("deleteValue mergeSize[%s]",mergeSize));
        if(this.keys.size() >= mergeSize){
            return value;
        }

        // 4.向兄弟节点借，如果兄弟节点个数大于(m-1)/2-1个，则可以借，否则执行第5步
        LeafNode<K,V> brotherNode = this.next;
        if(brotherNode == null){
            // 只处理合并右兄弟节点
            return value;
        }
        if(brotherNode.keys.size() > mergeSize){

        }

        // 5.当被借的兄弟节点个数不大于(m-1)/2-1个时，当前节点和兄弟节点合并成一个节点(合并之后节点数还是小于m)，并删除父节点中的key
        // 6.当前节点指向父节点，当父节点数大于等于(m-1)/2-1时，则删除操作结束，否则执行第7步
        // 7.若兄弟节点有富余(大于（m-1）/2-1)，父节点下移，兄弟节点key上移，删除结束，否则执行第8步
        // 8.当前节点和兄弟节点以及父节点下移合成一个节点，将当前节点指向父节点，重复第6步
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
     * 节点满了,对节点进行分裂层两个节点
     * m为偶数是左右两个节点各一半，m为奇数时左边节点为(m+1)/2-1个，右边节点为(m+1)/2个
     * @return
     */
    @Override
    protected Node<K, V> split() {
        // 创建右兄弟节点
        LeafNode<K,V> rightBrother = new LeafNode<>();
        // 计算右孩子拷贝范围
        int midIndex = this.branchingFactor/2;
        rightBrother.keys.addAll(this.keys.subList(midIndex,this.keys.size()));
        rightBrother.values.addAll(this.values.subList(midIndex,this.values.size()));
        System.out.println(String.format("leafnode 1 split my keys %s",this.keys));
        System.out.println(String.format("leafnode 1 split rightBrother keys %s",rightBrother.keys));

        // 本节点后继指针指向右兄弟
        this.next = rightBrother;
        // 右兄弟前驱指针指向本节点
        rightBrother.prev = this;

        // 删除已经移动到右兄弟中的数据
        this.keys.subList(midIndex,this.keys.size()).clear();
        this.values.subList(midIndex,this.values.size()).clear();

        K parentKey = rightBrother.getFirstKey();

        System.out.println(String.format("leafnode 2 split my parentKey[%s] keys %s",parentKey,this.keys));
        System.out.println(String.format("leafnode 2 split rightBrother keys %s",rightBrother.keys));

        // 将分裂的两个节点设置到父节点上
        this.propogate(parentKey,this,rightBrother);

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
