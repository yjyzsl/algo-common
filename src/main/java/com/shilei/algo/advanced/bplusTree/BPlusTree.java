package com.shilei.algo.advanced.bplusTree;

import java.util.*;

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

    public final static BPlusTree<Integer,String> INSTANCE = new BPlusTree<>();

    /** 根节点 */
    protected Node root;

    public BPlusTree(){
        root = new LeafNode<K,V>();
    }

    public void insertValue(K key,V value){
        root = root.insertValue(key,value);
    }

    public Object deleteValue(K key){
        Object deleteValue = root.deleteValue(key);
        return deleteValue;
    }

    public Object getValue(K key){
        Object value = root.getValue(key);
        return value;
    }

    public List<Object> getRange(K smallKey,K bigKey){
        return root.getRange(smallKey,bigKey);
    }

    public String toString() {
        Queue<List<Node>> queue = new LinkedList<List<Node>>();
        queue.add(Arrays.asList(root));
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Queue<List<Node>> nextQueue = new LinkedList<List<Node>>();
            while (!queue.isEmpty()) {
                List<Node> nodes = queue.remove();
                sb.append('{');
                if(null == nodes){
                    sb.append(nodes).append(',');
                    continue;
                }
                Iterator<Node> it = nodes.iterator();
                while (it.hasNext()) {
                    Node node = it.next();
                    sb.append(node.keys);
                    if (it.hasNext()) sb.append(", ");
                    if (node instanceof InternalNode) nextQueue.add(((InternalNode) node).children);
                }
                sb.append('}');
                if (!queue.isEmpty()) sb.append(", ");
                else sb.append('\n');
            }
            queue = nextQueue;
        }

        return sb.toString();
    }


    public static void main(String[] args) {

        BPlusTree<Integer,String> bPlusTree = BPlusTree.INSTANCE;

        bPlusTree.insertValue(1,"1");
        System.out.println(String.format("root:%S",bPlusTree.root));
        bPlusTree.insertValue(2,"2");
        System.out.println(String.format("root:%S",bPlusTree.root));
        bPlusTree.insertValue(3,"3");
        System.out.println(String.format("root:%S",bPlusTree.root));
        bPlusTree.insertValue(4,"4");
        System.out.println(String.format("root:%S",bPlusTree.root));
        bPlusTree.insertValue(5,"5");
        System.out.println(String.format("root:%S",bPlusTree.root));

        bPlusTree.insertValue(6,"6");
        System.out.println(String.format("root:%S",bPlusTree.root));

        bPlusTree.insertValue(7,"7");
        bPlusTree.insertValue(8,"8");
        bPlusTree.insertValue(9,"9");
        bPlusTree.insertValue(10,"10");
        bPlusTree.insertValue(11,"11");
        bPlusTree.insertValue(12,"12");
        bPlusTree.insertValue(22,"22");
        bPlusTree.insertValue(15,"15");
        bPlusTree.insertValue(10,"10");
        bPlusTree.insertValue(10,"10");
        bPlusTree.insertValue(30,"30");
        bPlusTree.insertValue(13,"13");
        bPlusTree.insertValue(7,"7");
        bPlusTree.insertValue(25,"25");

        System.out.println(String.format("root:%S",bPlusTree.root));

        System.out.println("===================================");
        LeafNode node = (LeafNode)bPlusTree.root.getFirstNode();
        System.out.println("FirstNode:"+node);

        while (node != null){
            System.out.println(node.values);
            node = node.next;
        }

        System.out.println("===============================");
        System.out.println(bPlusTree.toString());

        System.out.println("===============================3");
        System.out.println(bPlusTree.deleteValue(3));
        System.out.println(bPlusTree.toString());

        System.out.println("===============================4");
        System.out.println(bPlusTree.deleteValue(4));
        System.out.println(bPlusTree.toString());

        System.out.println("=============================== 5");
        System.out.println(bPlusTree.deleteValue(5));
        System.out.println(bPlusTree.toString());

        System.out.println("=============================== 6");
        System.out.println(bPlusTree.deleteValue(6));
        System.out.println(bPlusTree.toString());

        System.out.println("=============================== 9");
        System.out.println(bPlusTree.deleteValue(9));
        System.out.println(bPlusTree.toString());

        System.out.println("=============================== 10");
        System.out.println(bPlusTree.deleteValue(10));
        System.out.println(bPlusTree.toString());


        System.out.println("=============================== insert 14");
        bPlusTree.insertValue(14,"14");
        System.out.println(bPlusTree.toString());

        System.out.println("=============================== insert 16");
        bPlusTree.insertValue(16,"16");
        System.out.println(bPlusTree.toString());

        System.out.println(bPlusTree.getValue(25));
        System.out.println(bPlusTree.getValue(100));

        System.out.println(bPlusTree.getRange(12,14));
        System.out.println(bPlusTree.getRange(7,15));
        System.out.println(bPlusTree.getRange(6,19));
    }



}
