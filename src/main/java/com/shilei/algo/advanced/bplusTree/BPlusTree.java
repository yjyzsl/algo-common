package com.shilei.algo.advanced.bplusTree;

/**
 * @Description B+Tree
 * B+Tree具有快速查找，新增，删除特性，并且支持区间范围查找
 *
 * m阶B+Tree，其内部节点关键字个数为m-1,叶子节点数据个数最多也为m-1
 * 当叶子节点超过了m-1,那么节点则分裂，左边节点为m/2个，剩余节点放入右节点中，右节点的第一个关键字放入父节点关键字列表中
 * 再将左右节点放入父节点孩子列表中
 *
 *
 *
 * @Author shil20
 * @Date 2019/7/4 9:36
 **/
public class BPlusTree<K extends Comparable<? super K>,V> {


    public static void main(String[] args) {

        Node<Integer,String> root = new LeafNode<>();
        root = root.insertValue(1,"a");
        System.out.println(String.format("root:%S",root));
        root = root.insertValue(3,"b");
        System.out.println(String.format("root:%S",root));
        root = root.insertValue(2,"c");
        System.out.println(String.format("root:%S",root));
        root = root.insertValue(6,"d");
        System.out.println(String.format("root:%S",root));
        root = root.insertValue(5,"e");
        System.out.println(String.format("root:%S",root));

        root = root.insertValue(7,"e");
        root = root.insertValue(8,"e");
        root = root.insertValue(9,"e");
        root = root.insertValue(10,"e");
        root = root.insertValue(11,"e");
        root = root.insertValue(12,"e");
        root = root.insertValue(13,"e");
        root = root.insertValue(14,"e");
        root = root.insertValue(15,"e");
        root = root.insertValue(16,"e");

        System.out.println(String.format("root:%S",root));

    }



}
