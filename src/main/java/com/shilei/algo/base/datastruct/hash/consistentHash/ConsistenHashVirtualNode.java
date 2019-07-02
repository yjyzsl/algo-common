package com.shilei.algo.base.datastruct.hash.consistentHash;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Description 带虚拟节点的Hash一致性算法实现,解决节点数据分布不均匀的情况
 * 使用TreeMap存储hash环，TreeMap底层实现为红黑树,查找时间复杂度为O(logN)
 * 插入和删除速度相比ArrayList和LinkedList性能差,毕竟扩容和缩容是不频繁操作
 *
 * 当扩容时Hash一致性算法只会丢失一小部分数据，尽量把扩容节点带来的影响降低到最小是Hash一致性算法的优点
 * 集群节点越多增加加点带来的影响就越小
 *
 * Hash取余场景
 * 如原有三个节点，请求的hashcode范围在0-19时，增加到4个节点时hashcode命中只有6次了，分别是0,1,2,12,13,14
 * 6/20=0.3  是原来命中率的30%
 * Hash取余扩容缩容节点时，影响范围比较大，系统升缩性较差
 *
 * @Author shil20
 * @Date 2019/7/2 10:40
 **/
public class ConsistenHashVirtualNode {

    /** hash环的服务器列表   */
    private static final String[] NODES = {"192.168.1.100:8080"
                                    ,"192.168.1.101:8080"
                                    ,"192.168.1.102:8080"
                                    ,"192.168.1.103:8080"};

    /** 服务器节点链表,服务器需要扩容和缩容 */
    private final List<String> SERVER_NODE_LIST = new LinkedList<>();

    /** 服务器节点对应的hash与服务器节点的有序Map */
    private final SortedMap<Integer,String> SERVER_NODE_MAP = new TreeMap<>();

    /** 默认每个节点虚拟节点数量 */
    private final static int DEFAULT_VIRTUAL_NODE_NUM = 5;

    private int virtualNodeNum;

    public ConsistenHashVirtualNode(){
        this(DEFAULT_VIRTUAL_NODE_NUM);
    }

    public ConsistenHashVirtualNode(int virtualNodeNum){
        this.virtualNodeNum = virtualNodeNum;
        int nodeLength = NODES.length;
        for(int i=0; i<nodeLength; i++){
            // 服务器节点添加到链表
            SERVER_NODE_LIST.add(NODES[i]);
            // 先放服务器真实节点
            String serverNodeName = NODES[i];
            int hashCode = HashCode.getHash(serverNodeName);
            SERVER_NODE_MAP.put(hashCode,NODES[i]);
            // 存放服务器虚拟节点
            for(int j=1; j<=virtualNodeNum; j++){
                serverNodeName = NODES[i]+"_"+j;
                hashCode = HashCode.getHash(serverNodeName);
                System.out.println(String.format("serverNodeName:%s,hashCode:%s",serverNodeName,hashCode));
                SERVER_NODE_MAP.put(hashCode,NODES[i]);
            }
        }
        // 设置一个默认节点
        SERVER_NODE_MAP.put(Integer.MAX_VALUE,NODES[nodeLength-1]);
    }

    /**
     * 查找节点
     * @param key
     * @return
     */
    public String getNode(String key){
        int hashCode = HashCode.getHash(key);
        // 查找大于等于hashCode的所有节点
        SortedMap<Integer,String> gteMap = SERVER_NODE_MAP.tailMap(hashCode);
        // 返回第一个大于等于hashCode的节点
        return SERVER_NODE_MAP.get(gteMap.firstKey());
    }


    /**
     * 扩容节点
     * @param node
     */
    public void insertNode(String node){
        if(null == node || node.trim().length() == 0){
            System.out.println("insert node is null");
            return;
        }
        // 服务器节点添加到链表
        SERVER_NODE_LIST.add(node);
        int hashCode = HashCode.getHash(node);
        // 先放服务器真实节点
        SERVER_NODE_MAP.put(hashCode,node);
        // 存放服务器虚拟节点
        for(int j=1; j<=virtualNodeNum; j++){
            String serverNodeName = node+"_"+j;
            hashCode = HashCode.getHash(serverNodeName);
            System.out.println(String.format("serverNodeName:%s,hashCode:%s",serverNodeName,hashCode));
            SERVER_NODE_MAP.put(hashCode,node);
        }
    }

    /**
     * 删除节点
     * @param node
     */
    public void removeNode(String node){
        if(null == node || node.trim().length() == 0){
            System.out.println("remove node is null");
            return;
        }
        SERVER_NODE_LIST.remove(node);
        int hashCode = HashCode.getHash(node);
        // 移除服务器真实节点
        SERVER_NODE_MAP.remove(hashCode);
        // 移除服务器虚拟节点
        for(int j=1; j<=virtualNodeNum; j++){
            String serverNodeName = node+"_"+j;
            hashCode = HashCode.getHash(serverNodeName);
            SERVER_NODE_MAP.remove(hashCode);
        }
    }

    public List<String> getServerNodeList(){
        return SERVER_NODE_LIST;
    }

    public SortedMap<Integer,String> getServerNodeMap(){
        return SERVER_NODE_MAP;
    }


}
