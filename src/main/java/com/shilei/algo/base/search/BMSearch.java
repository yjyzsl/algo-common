package com.shilei.algo.base.search;

/**
 * @Description BM算法实现字符串匹配
 * @Author shil20
 * @Date 2020/2/8 12:49
 **/
public class BMSearch {

    public int bm(String mainStr, String patternStr) {


        return 0;
    }

    /**
     * 生成坏字符串索引数组
     * @param patternStr
     * @param bc
     */
    public void generateBC(String patternStr, int[] bc){
        for(int i = 0; i < bc.length; i++){
            bc[i] = -1;
        }

        for(int i = 0; i<patternStr.length(); i++){
            int ascii = patternStr.charAt(i);
            // 存放每个字符最大的索引位置
            bc[ascii] = i;
        }
    }


}
