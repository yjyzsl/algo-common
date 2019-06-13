package com.shilei.algo.base.datastruct.tree.binaryTree.linked;

/**
 * @Description 通过链表实现二叉树
 * @Author shil20
 * @Date 2019/6/12 15:20
 **/
public class LinkedBinaryTree {

    private Node root;

    public Node getRoot(){
        return this.root;
    }

    public void add(Node node){
        if(root == null){
            root = node;
        }
        node.leftChild = new Node();
        node.leftChild.data = 2*node.data;

        node.rightChild = new Node();
        node.rightChild.data = 2*node.data+1;

    }

    /**
     * 前序遍历
     * 对于任意一个节点先打印节点本身，然后打印左子树，最后打印右子树
     * @param node
     */
    public void leftPrint(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.data+",");
        leftPrint(node.leftChild);
        leftPrint(node.rightChild);
    }

    /**
     * 中序遍历
     * 对于任意一个节点先打印它的左子树，然后打印它本身，最后打印右子树
     * @param node
     */
    public void minPrint(Node node){
        if(node == null){
            return;
        }
        minPrint(node.leftChild);
        System.out.print(node.data+",");
        minPrint(node.rightChild);
    }

    /**
     * 后序遍历
     * 对于任意一个节点先打印它的左子树，然后打印右子树，最后打印本身
     * @param node
     */
    public void rightPrint(Node node){
        if(node == null){
            return;
        }
        rightPrint(node.leftChild);
        rightPrint(node.rightChild);
        System.out.print(node.data+",");
    }


    public static class Node{
        /** 数据 */
        int data;
        /** 左节点 */
        Node leftChild;
        /** 右节点 */
        Node rightChild;

    }

}
