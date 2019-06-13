package com.shilei.algo.base.datastruct.tree.binaryTree.linked;

import org.junit.Test;

/**
 * @Description TODO
 * @Author shil20
 * @Date 2019/6/12 15:42
 **/
public class LinkedBinaryTreeTest {

    @Test
    public void linkedBinaryTreeTest(){

        LinkedBinaryTree binaryTree = new LinkedBinaryTree();

        LinkedBinaryTree.Node node = new LinkedBinaryTree.Node();
        node.data = 1;
        binaryTree.add(node);

        binaryTree.add(node.leftChild);

        binaryTree.add(node.rightChild);

        binaryTree.add(node.leftChild.leftChild);

        binaryTree.leftPrint(binaryTree.getRoot());
        System.out.println();

        binaryTree.minPrint(binaryTree.getRoot());
        System.out.println();

        binaryTree.rightPrint(binaryTree.getRoot());
        System.out.println();

    }


}
