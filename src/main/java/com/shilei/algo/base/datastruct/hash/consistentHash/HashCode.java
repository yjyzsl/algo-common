package com.shilei.algo.base.datastruct.hash.consistentHash;

/**
 * 获取hash的方法
 */
public class HashCode {

  /** 使用FNV1_32_HASH算法计算服务器的Hash值,解决String生成hashCode分布小范围问题 */
  public static int getHash(String str) {
    final int p = 16777619;
    int hash = (int) 2166136261L;
    for (int i = 0; i < str.length(); i++) hash = (hash ^ str.charAt(i)) * p;
    hash += hash << 13;
    hash ^= hash >> 7;
    hash += hash << 3;
    hash ^= hash >> 17;
    hash += hash << 5;

    // 如果算出来的值为负数则取其绝对值
    if (hash < 0) hash = Math.abs(hash);
    return hash;
  }
}
