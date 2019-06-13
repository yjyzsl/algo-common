package com.shilei.algo.base.datastruct.tree.binarySearchTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 查找二叉树
 * 在树中的任意一个节点，其左子树的所有节点都要小于该节点，右子树的所有节点都要大于该节点
 * 支持插入、删除、查找
 * @Author shil20
 * @Date 2019/6/12 16:14
 **/
public class BinarySearchTree {

    // 根节点
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 在查找二叉树插入一个值
     * @param value
     */
    public void insert(int value){
        if(null == root){
            root = new TreeNode();
            root.data = value;
            return;
        }
        TreeNode newTreeNode = new TreeNode();
        newTreeNode.data = value;
        TreeNode treeNode = root;
        while (treeNode != null){
            // 1.查找值小于该节点则在左子树中查找
            if(value < treeNode.data) {
                if (treeNode.left == null) {// 如果左节点为空，则将新节点插入到左节点
                    treeNode.left = newTreeNode;
                    return;
                }
                treeNode = treeNode.left;
            }else{  // 2.查找值大于该节点则在右子树中查找
                if(treeNode.right == null){// 如果右节点为空，则将新节点插入到右节点
                    treeNode.right = newTreeNode;
                    return;
                }
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 在查找二叉树中查找对应的节点
     * @param value
     * @return
     */
    public TreeNode find(int value){
        if(root == null){
            return root;
        }
        TreeNode treeNode = root;
        while (treeNode != null){
            if(value < treeNode.data){// 在左子树中查找
                treeNode = treeNode.left;
            }else if(value > treeNode.data){// 在右子树中查找
                treeNode = treeNode.right;
            }else{
                return treeNode;
            }
        }
        return null;
    }

    /**
     * 在查找二叉树中删除节点
     * 有三种情况
     * 情况一、查找到的节点没有子节点，则其父节点指向null
     * 情况二、查找到的节点只有一个子节点，将父节点指向其子节点
     * 情况三、查找到的节点有两个子节点，找到右子树中的最小节点替换该节点,然后再删除右子树中最小的节点
     * @param value
     */
    public void remove(int value){
        TreeNode treeNode = root;
        // 查找值的父节点
        TreeNode pTreeNode = null;
        // 查找节点
        while (treeNode != null){
            if(value < treeNode.data){// 在左子树中查找
                pTreeNode = treeNode;
                treeNode = treeNode.left;
            }else if(value > treeNode.data){// 在右子树中查找
                pTreeNode = treeNode;
                treeNode = treeNode.right;
            }else{
                break;
            }
        }
        if(treeNode == null){// 未找到值对应的节点
            return;
        }
        System.out.println("step1 pTreeNode:"+pTreeNode);
        System.out.println("step1 treeNode:"+treeNode);
        TreeNode minTreeNode,minParentTreeNode;
        // 情况三、查找到的节点有两个子节点，找到右子树中的最小节点替换该节点,然后再删除右子树中最小的节点
        if (treeNode.left != null && treeNode.right != null){
            minTreeNode = treeNode.right;
            minParentTreeNode = treeNode;
            while (minTreeNode.left != null){// 最小节点在左孩子节点上
                minParentTreeNode = minTreeNode;
                minTreeNode = minTreeNode.left;
            }
            treeNode.data = minTreeNode.data; // 替换删除节点和最小节点的值
            if(minParentTreeNode.left == minTreeNode){// 将右子树中的最小节点删除
                minParentTreeNode.left = null;
            }else{
                minParentTreeNode.right = null;
            }
        }else{

            // 情况一和二、查找到的节点只有一个子节点，将父节点指向其子节点或无节点，查找替换删除节点的下一个节点
            TreeNode findNextNode;
            if(treeNode.left != null){
                findNextNode = treeNode.left;
            }else if(treeNode.right != null){
                findNextNode = treeNode.right;
            }else{
                findNextNode = null;
            }

            if(pTreeNode == null){// 查找到的节点的父节点为空，表明删除节点为根节点
                root = findNextNode;
            }else if(pTreeNode.left == treeNode){// 查找到的节点为父节点的左孩子
                pTreeNode.left = findNextNode;
            }else{ // 查找到的节点为父节点的右孩子
                pTreeNode.right = findNextNode;
            }
        }
    }



    public void leftPrint(TreeNode treeNode){
        if(null == treeNode){
            //System.out.print("null");
            return ;
        }
        System.out.print(treeNode.data+",");
        leftPrint(treeNode.left);
        leftPrint(treeNode.right);
    }


    /**
     * 打印每层的节点
     * @param treeNode
     */
    public void printLevelNode(TreeNode treeNode){
        Map<Integer, List<TreeNode>> levelNodeMap = new HashMap<>();
        groupLevelNode(treeNode,levelNodeMap,1);

        for(Map.Entry<Integer, List<TreeNode>> entry:levelNodeMap.entrySet()){
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

    /**
     * 分组查询每一层节点放到Map中
     * @param treeNode 遍历到的节点
     * @param levelNodeMap
     * @param level 遍历的层次
     */
    public void groupLevelNode(TreeNode treeNode, Map<Integer, List<TreeNode>> levelNodeMap, int level){
        if(treeNode == null){
            return;
        }
        List<TreeNode> treeNodeList = levelNodeMap.get(level);
        if(treeNodeList == null){
            treeNodeList = new ArrayList<>();
            levelNodeMap.put(level,treeNodeList);
        }
        treeNodeList.add(treeNode);
        level = level + 1; // 入栈，层数加一
        groupLevelNode(treeNode.left,levelNodeMap,level);
        level = level - 1; // 出栈，层数减一
        level = level + 1; // 入栈，层数加一
        groupLevelNode(treeNode.right,levelNodeMap,level);
        level = level - 1; // 出栈，层数减一
    }


    public class TreeNode{
        int data;       // 数据值
        TreeNode left;  // 左节点
        TreeNode right; // 右节点

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    '}';
        }
    }
}
