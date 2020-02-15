package com.shilei.algo.base.search;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 字符串匹配查找
 * @Author shil20
 * @Date 2020/2/1 15:53
 **/
public class RKSearch {


    public int rkSearch(String mainStr, String patternStr) {
        if (null == mainStr
                || mainStr.length() == 0
                || null == patternStr
                || patternStr.length() == 0) {
            return -1;
        }
        // 字符转化为26进制
        int radix = 26;

        // 计算所有子串hash
        return countSubStrHash(mainStr, patternStr, radix);
    }

    /**
     * 计算模式串hash
     *
     * @param matchStr
     * @param mPowers
     */
    private int countMatchStrHash(String matchStr, int[] mPowers) {
        int hash = 0;
        int n = matchStr.length();
        for (int j = 0; j < n; j++) {
            hash = mPowers[n - j - 1] * (matchStr.charAt(j) - 'a') + hash;
        }
        return hash;
    }

    /**
     * 将主串mainStr的所有子串计算出hash保存到map
     * 假设字符串全为小写，将子串转换成16进制作为子串的hash值
     * 模式串长度为m, 则计算hash公式为：
     * h[i-1] = 26^(m-1)*(s[i-1] - 'a') + 26^(m-2)*(s[i] - 'a') + 26^(m-3)*(s[i+1] - 'a') + ... + 26^0 * (s[i+m-2] - 'a')
     * h[i]   =                           26^(m-1)*(s[i] - 'a') + 26^(m-2)*(s[i+1] - 'a') + ... + 26^1 * (s[i+m-2] - 'a') + 26^0 * (s[i+m-1] - 'a')
     * h[i]   = 26*(h[i-1] - 26^(m-1)*(s[i-1] - 'a')) + (s[i+m-1] - 'a')
     *
     * @param mainStr  主串
     * @param matchStr 模式串
     * @return
     */
    private int countSubStrHash(String mainStr, String matchStr, int radix) {
        int n = mainStr.length();
        int m = matchStr.length();
        int hash = mainStr.charAt(0) - 'a';

        int[] mPowers = countPowers(m, radix);
        // 计算模式串hash
        int matchStrHash = countMatchStrHash(matchStr, mPowers);
        System.out.println("matchStrHash:" + matchStrHash);

        Map<Integer, Integer> hashMap = new HashMap();
        hashMap.put(0, hash);

        // 计算子串hash
        for (int j = 1; j < n; j++) {
            hash = hashMap.get(j - 1);
            int i = j - m + 1;
            if (j < m) {
                hash = mPowers[j] * (mainStr.charAt(j) - 'a') + hash;
            } else {
                // "ababdabcdefg" aba:26 , bab:1*26*26+1=677 , abd:29 , bda:754 , dab:2096 , abc:28
                hash = radix * (hash - mPowers[m - 1] * (mainStr.charAt(i - 1) - 'a')) + (mainStr.charAt(i + m - 1) - 'a');
            }
            System.out.println("i:" + i + ", j:" + j + ", hash:" + hash);
            if (matchStrHash == hash) {
                return i;
            }
            hashMap.put(j, hash);
        }
        return -1;
    }

    private int[] countPowers(int m, int radix) {
        int[] mPowers = new int[m + 1];
        mPowers[0] = 1;
        for (int i = 1; i <= m; i++) {
            mPowers[i] = radix * mPowers[i - 1];
        }
        return mPowers;
    }

    public static void main(String[] args) {
        String mainStr = "ababdabcdefg";
        String matchStr = "abc";
        RKSearch rkSearch = new RKSearch();
        int index = rkSearch.rkSearch(mainStr, matchStr);
        System.out.println("5:" + index);
    }

}
