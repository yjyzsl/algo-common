package com.shilei.algo.base.datastruct.tree.binaryTree.array;

/**
 * @Description 通过数组实现二叉数
 * 完全二叉树：叶子节点都在最后两层，最后一层的叶子节点都是靠左排列，除了最后一层，其他层节点个数要达到最大
 * 数组顺序存储的方式比较适合完全二叉树，其他二叉树存储数据比较浪费空间
 * @Author shil20
 * @Date 2019/6/12 14:25
 **/
public class ArrayBinaryTree {

    private int[] array;

    private final int DEFAULT_CAPACITY = 64;

    private int capacity;

    public ArrayBinaryTree(int capacity){
        if(capacity <= 0){
            capacity = DEFAULT_CAPACITY;
        }
        array = new int[capacity+1];
        this.capacity = capacity;
    }

    /**
     * 初始化树，从下标为1的位置存储数据
     */
    public void initTree(){
        for(int i = 1; i <= capacity; i++){
            array[i] = i;
        }
    }

    public void print(){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]+",");
        }
        System.out.println();
    }

    /**
     * 前序遍历
     * 对于任意一个节点先打印节点本身，然后打印左子树，最后打印右子树
     * @param index
     */
    public void leftPrint(int index){
        if(index> capacity || array[index] == 0){
            return;
        }
        System.out.print(array[index]+",");
        leftPrint(2 * index);
        leftPrint(2 * index + 1);
    }

    /**
     * 中序遍历
     * 对于任意一个节点先打印它的左子树，然后打印它本身，最后打印右子树
     * @param index
     */
    public void minPrint(int index){
        if(index> capacity || array[index] == 0){
            return;
        }
        minPrint(2 * index);
        System.out.print(array[index]+",");
        minPrint(2 * index + 1);
    }

    /**
     * 后序遍历
     * 对于任意一个节点先打印它的左子树，然后打印右子树，最后打印本身
     * @param index
     */
    public void rightPrint(int index){
        if(index> capacity || array[index] == 0){
            return;
        }
        rightPrint(2 * index);
        rightPrint(2 * index+1);
        System.out.print(array[index]+",");
    }



}
