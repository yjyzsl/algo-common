package com.shilei.algo.base.datastruct.tree.binaryTree.array;

import org.junit.Test;

/**
 * @Description TODO
 * @Author shil20
 * @Date 2019/6/12 14:48
 **/
public class ArrayBinaryTreeTest {

    @Test
    public void arrayBinaryTreeTest(){
        ArrayBinaryTree binaryTree = new ArrayBinaryTree(8);
        binaryTree.initTree();
        binaryTree.print();
        binaryTree.leftPrint(1);
        System.out.println();

        binaryTree.leftPrint(2);
        System.out.println();

        binaryTree.minPrint(1);
        System.out.println();

        binaryTree.rightPrint(1);
        System.out.println();


    }

}




