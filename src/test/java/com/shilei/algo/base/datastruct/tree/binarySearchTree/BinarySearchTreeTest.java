package com.shilei.algo.base.datastruct.tree.binarySearchTree;

import org.junit.Test;

/**
 * @Description TODO
 * @Author shil20
 * @Date 2019/6/12 17:04
 **/
public class BinarySearchTreeTest {

    private BinarySearchTree binarySearchTree = new BinarySearchTree();

    @Test
    public void insertTest(){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(6);
        binarySearchTree.insert(4);
        binarySearchTree.insert(10);
        binarySearchTree.insert(3);
        binarySearchTree.insert(5);
        binarySearchTree.insert(7);
        binarySearchTree.insert(15);
        binarySearchTree.insert(2);

        binarySearchTree.leftPrint(binarySearchTree.getRoot());
        System.out.println();

        binarySearchTree.insert(13);
        binarySearchTree.insert(20);
        binarySearchTree.leftPrint(binarySearchTree.getRoot());
        System.out.println();

        binarySearchTree.printLevelNode(binarySearchTree.getRoot());
        System.out.println();

    }

    @Test
    public void findTest(){
        insertTest();
        binarySearchTree.leftPrint(binarySearchTree.getRoot());
        System.out.println();

        System.out.println(binarySearchTree.find(12));
        System.out.println(binarySearchTree.find(0));
        System.out.println(binarySearchTree.find(20));
    }

    @Test
    public void removeTest(){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(6);
        binarySearchTree.insert(4);
        binarySearchTree.insert(13);

        binarySearchTree.remove(6);
        binarySearchTree.leftPrint(binarySearchTree.getRoot());
        System.out.println();

        binarySearchTree.remove(4);
        binarySearchTree.leftPrint(binarySearchTree.getRoot());
        System.out.println();

        binarySearchTree.remove(13);
        binarySearchTree.leftPrint(binarySearchTree.getRoot());
        System.out.println();
    }


}
