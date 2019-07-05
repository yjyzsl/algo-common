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

    private Node<K,V> root;

    public void insertValue(K key,V value){
        root = root.insertValue(key,value);
    }

    public V deleteValue(K key){
        V deleteValue = root.deleteValue(key);
        return deleteValue;
    }




    public static void main(String[] args) {

        Node<Integer,String> root = new LeafNode<>();
        root = root.insertValue(1,"1");
        System.out.println(String.format("root:%S",root));
        root = root.insertValue(2,"2");
        System.out.println(String.format("root:%S",root));
        root = root.insertValue(3,"3");
        System.out.println(String.format("root:%S",root));
        root = root.insertValue(4,"4");
        System.out.println(String.format("root:%S",root));
        root = root.insertValue(5,"5");
        System.out.println(String.format("root:%S",root));

        root = root.insertValue(6,"6");
        System.out.println(String.format("root:%S",root));

        root = root.insertValue(7,"7");
        root = root.insertValue(8,"8");
        root = root.insertValue(9,"9");
        root = root.insertValue(10,"10");
        root = root.insertValue(11,"11");
        root = root.insertValue(12,"12");
        root = root.insertValue(22,"22");
        root = root.insertValue(14,"14");
        root = root.insertValue(10,"10");
        root = root.insertValue(10,"10");
        root = root.insertValue(30,"30");
        root = root.insertValue(13,"13");
        root = root.insertValue(7,"7");
        root = root.insertValue(25,"25");

//        for(int i=1; i<=50; i++){
//            if(i >= 10){
//                System.out.println(root);
//            }
//            root = root.insertValue(i,String.valueOf(i));
//        }

        System.out.println(String.format("root:%S",root));

        System.out.println("===================================");
        LeafNode node = (LeafNode)root.getFirstNode();
        System.out.println("FirstNode:"+node);

        while (node != null){
            System.out.println(node.values);
            node = node.next;
        }

    }



}
