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
        // 根据关键字查找对应的孩子节点
        Node<K,V> child = getChild(key);
        V value = child.deleteValue(key);
        return value;
    }


    @Override
    protected V getValue(K key) {
        Node<K,V> child = getChild(key);
        return child.getValue(key);
    }

    @Override
    protected List<V> getRange(K key1, K key2) {
        Node<K,V> child = getChild(key1);
        List<V> valueList = child.getRange(key1,key2);
        return valueList;
    }

    /**
     * 合并兄弟节点
     *
     * 6.当前节点指向父节点，当节点数大于等于m/2时，则删除操作结束，否则执行第7步
     * 7.若兄弟节点有富余(大于等于m/2)，父节点下移，兄弟节点key上移，删除结束，否则执行第8步
     * 8.当前节点和兄弟节点以及父节点下移合成一个节点，将当前节点指向父节点，重复第6步
     */
    @Override
    protected void merge() {
        //int mergeSize = (branchingFactor-1)/2 - 1;
        int mergeSize = branchingFactor/2;
        // 6.当前节点指向父节点，当节点数大于等于(m-1)/2-1时，则删除操作结束，否则执行第7步
        if(this.keys.size() >= mergeSize){
            return;
        }
        // 查找兄弟节点
        InternalNode<K,V> brotherNode = findBrothNode();
        if(null == brotherNode){
            return;
        }
        InternalNode<K,V> parentNode = (InternalNode)this.parent;
        K maxKey = this.keys.get(this.keys.size()-1);
        // 计算法父节点索引下标
        int pPosition = Collections.binarySearch(parentNode.keys,maxKey);
        int moveParentKeyIndex = -pPosition - 1;
        K moveParentKey = parentNode.keys.get(moveParentKeyIndex);

        // 7.若兄弟节点有富余(大于（m-1）/2-1)，父节点下移，兄弟节点key上移，删除结束，否则执行第8步
        if(brotherNode.keys.size() > mergeSize){

            // 移除兄弟节点的第一个关键字
            K moveBrothKey = brotherNode.keys.remove(0);
            Node<K,V> moveBrothFistChild =  brotherNode.children.remove(0);
            // 将兄弟节点的关键字移动到父节点
            parentNode.keys.set(moveParentKeyIndex,moveBrothKey);

            // 父节点的key是当前最大的key,添加到最后
            this.keys.add(moveParentKey);
            this.children.add(moveBrothFistChild);
            moveBrothFistChild.parent = this;

        }else{
            // 8.当前节点和兄弟节点以及父节点下移合成一个节点(合并后不会造成节点分裂情况)，将当前节点指向父节点，重复第6步
            this.keys.add(moveParentKey);
            this.keys.addAll(brotherNode.keys);

            for(Node brotherChild:brotherNode.children){
                brotherChild.parent = this;
            }
            this.children.addAll(brotherNode.children);

            // 移除父节点中对应的关键字
            parentNode.keys.remove(moveParentKey);

            // 删除节点
            deleteNode(brotherNode);

            // 调用父节点合并
            if(parentNode.keys.size() > 0){
                parentNode.merge();
            }else{
                // 父节点没有子节点，当前节点就为根节点
                this.parent = null;
                BPlusTree.INSTANCE.root = this;
            }
        }
    }

    private InternalNode<K,V> findBrothNode() {
        if(null == this.parent){
            return null;
        }
        InternalNode<K,V> parentNode = (InternalNode)this.parent;
        if(parentNode.children.size() <= 1){
            return null;
        }
        int pPosition = Collections.binarySearch(parentNode.keys,this.keys.get(this.keys.size()-1));
        // pPosition不会存在于父节点关键字中，所以为负数
        int brotherIndex = -pPosition;
//        if(brotherIndex == parentNode.children.size()){
//            // 取左兄弟
//            brotherIndex = brotherIndex-2;
//        }
        InternalNode<K,V> brotherNode = (InternalNode)parentNode.children.get(brotherIndex);
        return brotherNode;
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

    /**
     * 删除节点
     * @param node
     */
    private void deleteNode(InternalNode<K,V> node){
        node.keys.clear();
        node.children.clear();
        System.out.println(String.format("this.keys [%s] ---- brotherNode.keys [%s]",this.keys, node.keys));
        System.out.println(String.format("this.values [%s] ---- brotherNode.children [%s]",this.children, node.children));
        node.keys = null;
        node.children = null;
        node.parent = null;
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
