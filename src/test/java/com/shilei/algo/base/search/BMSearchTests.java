package com.shilei.algo.base.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description BM算法单元测试
 * @Author shil20
 * @Date 2020/2/15 20:07
 **/
public class BMSearchTests {

    private BMSearch bmSearch;

    @Before
    public void init(){
        bmSearch = new BMSearch();
    }

    /**
     * 坏字符测试
     */
    @Test
    public void bm_bad_char_test(){
        String mainStr = "acaabdefg";
        String patternStr = "abd";

        int result = bmSearch.bm(mainStr, patternStr);
        System.out.println(String.format("mainStr:%s , patternStr:%s , result: %s", mainStr, patternStr, result));
        Assert.assertEquals(result, 3);


        mainStr = "aaaaaaaaaaa";
        patternStr = "baaa";

        result = bmSearch.bm(mainStr, patternStr);
        System.out.println(String.format("mainStr:%s , patternStr:%s , result: %s", mainStr, patternStr, result));
        Assert.assertEquals(result, -1);
    }

    /**
     * 好后缀测试
     */
    @Test
    public void bm_good_suffix_test(){
        String mainStr = "acabcbcg";
        String patternStr = "abcbc";

        int result = bmSearch.bm(mainStr, patternStr);
        System.out.println(String.format("mainStr:%s , patternStr:%s , result: %s", mainStr, patternStr, result));
        Assert.assertEquals(result, 2);
    }

    /**
     * 好前缀测试
     */
    @Test
    public void bm_good_prefix_test(){
        String mainStr = "abcdababdcabcde";
        String patternStr = "abdcab";

        int result = bmSearch.bm(mainStr, patternStr);
        System.out.println(String.format("mainStr:%s , patternStr:%s , result: %s", mainStr, patternStr, result));
        Assert.assertEquals(result, 6);
    }


    @Test
    public void bm_test(){
        String mainStr = "cdefg";
        String patternStr = "efg";

        int result = bmSearch.bm(mainStr, patternStr);
        System.out.println(String.format("mainStr:%s , patternStr:%s , result: %s", mainStr, patternStr, result));
        Assert.assertEquals(result, 2);
    }

    @Test
    public void bm_not_find_test(){
        String mainStr = "abcdabaadcabcde";
        String patternStr = "abdcab";

        int result = bmSearch.bm(mainStr, patternStr);
        System.out.println(String.format("mainStr:%s , patternStr:%s , result: %s", mainStr, patternStr, result));
        Assert.assertEquals(result, -1);
    }



}
