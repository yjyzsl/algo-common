package com.shilei.algo.base.datastruct.hash.consistentHash;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description TODO
 * @Author shil20
 * @Date 2019/7/2 11:35
 **/
public class ConsistenHashVirtualNodeTest {

    @Test
    public void testInsertNode(){
        ConsistenHashVirtualNode consistenHashVirtualNode = new ConsistenHashVirtualNode();
        System.out.println(consistenHashVirtualNode.getServerNodeList());
        System.out.println(consistenHashVirtualNode.getServerNodeMap());
        System.out.println("---------------------------------------------------------");
        consistenHashVirtualNode.insertNode("192.168.1.104:8080");
        System.out.println(consistenHashVirtualNode.getServerNodeList());
        System.out.println(consistenHashVirtualNode.getServerNodeMap());

        consistenHashVirtualNode.insertNode("192.168.1.105:8080");
        System.out.println(consistenHashVirtualNode.getServerNodeList());
        System.out.println(consistenHashVirtualNode.getServerNodeMap().keySet());
    }

    @Test
    public void test(){
        ConsistenHashVirtualNode consistenHashVirtualNode = new ConsistenHashVirtualNode(10);
        System.out.println(consistenHashVirtualNode.getServerNodeList());
        System.out.println(consistenHashVirtualNode.getServerNodeMap());
        System.out.println("---------------------------------------------------------");

        Map<String,Integer> serverAccessCntMap = new TreeMap<>();
        String node = null,request = null;
        Integer serverAccessCnt = null;
        for(int i=1; i<=100000; i++){
            request = String.valueOf(i);
            node = consistenHashVirtualNode.getNode(request);
            serverAccessCnt = serverAccessCntMap.get(node);
            if(serverAccessCnt == null){
                serverAccessCnt = 0;
            }
            serverAccessCnt++;
            serverAccessCntMap.put(node,serverAccessCnt);
        }
        System.out.println("添加节点前:"+serverAccessCntMap);

        consistenHashVirtualNode.insertNode("192.168.1.104:8080");
        System.out.println(consistenHashVirtualNode.getServerNodeList());
        System.out.println(consistenHashVirtualNode.getServerNodeMap());

        consistenHashVirtualNode.removeNode("192.168.1.102:8080");
        System.out.println(consistenHashVirtualNode.getServerNodeList());
        System.out.println(consistenHashVirtualNode.getServerNodeMap().keySet());

        consistenHashVirtualNode.insertNode("192.168.1.105:8080");
        System.out.println(consistenHashVirtualNode.getServerNodeList());
        System.out.println(consistenHashVirtualNode.getServerNodeMap());

        consistenHashVirtualNode.insertNode("192.168.1.106:8080");
        System.out.println(consistenHashVirtualNode.getServerNodeList());
        System.out.println(consistenHashVirtualNode.getServerNodeMap());

        for(int i=100001; i<=400000; i++){
            request = String.valueOf(i);
            node = consistenHashVirtualNode.getNode(request);
            serverAccessCnt = serverAccessCntMap.get(node);
            if(serverAccessCnt == null){
                serverAccessCnt = 0;
            }
            serverAccessCnt++;
            serverAccessCntMap.put(node,serverAccessCnt);
        }
        System.out.println("添加节点后:"+serverAccessCntMap);

    }


}
