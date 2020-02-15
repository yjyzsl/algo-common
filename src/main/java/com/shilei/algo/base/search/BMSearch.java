package com.shilei.algo.base.search;

/**
 * @Description BM算法实现
 *
 * <pre>
 * 1. 坏字符规则
 *    模式串从前往后比较，找到主串中与模式串不匹配的字符该字符则为坏字符，模式串的没一个字符用数组记录每一个字符出现的索引xi（多次出现则记录最靠后的那个）,
 *    坏字符在模式串出现的位置为si, 那么往后移动长度为：k = (si - xi)，如：
 *    主串: acaabdefg, 模式串: abd, si=2, xi=2, k=2, 往后移动2位
 *    主串: aaaaaaaaaaa, 模式串: baaa, si=0, xi=0, k=-2, 则出现了倒退的情况，所以需要配合好后缀规则
 *
 * 2.好后缀规则
 *   2.1. 跟换字符类似，模式串中存在好后缀，则往后移动到好后缀位置，如:
 *        主串是: acabcbcg, 模式串是: abcbc, 则bc为好后缀, si=3, xi=1, k=2, 往后移动3位
 *
 *   2.2. 不存在好后缀，存在好前缀，则往后移动到好前缀位置，如：
 *        主串是: acadbccdbce, 模式串是: bccdbc, 后缀dbc虽然一个与主串匹配，但在模式中没有，则在dbc找到一个最长的好前缀bc, 则 si=4 , xi=0, k=4, 往后移动4位
 *
 *   2.3. 即不满足好后缀又不满足好前缀，则向后移动模式串的长度：m
 *
 * 最终取坏字符和好后缀两者之间移动最大的那个作为向后移动的距离
 *
 * </pre>
 * @Author shil20
 * @Date 2020/2/8 12:49
 **/
public class BMSearch {

    /**
     * 在主串中查找模式串，返回主串中与模式串匹配的第一个位置
     *
     * @param mainStr    主串
     * @param patternStr 模式串
     * @return
     */
    public int bm(String mainStr, String patternStr) {
        if (null == mainStr || mainStr.trim().length() == 0
                || null == patternStr || patternStr.trim().length() == 0
                || patternStr.length() > mainStr.length()) {
            return -1;
        }

        // 模式串坏字符索引, 256为ascii码范围， 当ascii值范围很大时效率也会下降
        int[] bc = new int[256];
        generateBC(patternStr, bc);

        int n = mainStr.length();
        int m = patternStr.length();
        // 构建好后缀和好前缀数组
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(patternStr, suffix, prefix);

        // 首先实现坏字符原则
        for (int i = 0; i <= n - m;) {
            // 记录后缀比较的长度
            int j = m - 1;

            // 从后往前比较
            for (; j >= 0; j--) {
                // 不相等时说明遇到了坏字符
                if (mainStr.charAt(i + j) != patternStr.charAt(j)) break;
            }

            // 当j小于0时说明模式串完全匹配，返回主串中与模式串匹配的第一个位置
            if (j < 0) {
                return i;
            }

            // 主串(i+j)位置的字符为坏字符
            int x = j - bc[mainStr.charAt(i + j)];
            int y = 0;

            // 主串和模式串存在公共后缀
            if (j < m - 1) {
                // 按照好后缀规则计算出后移长度
                y = moveByGS(patternStr, j, suffix, prefix);
            }

            // 取坏字符规则和好后缀规则两者间的最大值
            i = i + Math.max(x, y);
        }

        return -1;
    }

    /**
     * 根据好后缀规则计算出向后移动的距离
     *
     * @param patternStr 模式串
     * @param j          坏字符对应的模式串中的下标
     * @param suffix     好后缀索引数组
     * @param prefix     是否存在好前缀数组
     */
    private int moveByGS(String patternStr, int j, int[] suffix, boolean[] prefix) {
        int m = patternStr.length();
        // 好后缀长度
        int k = m - j - 1;
        // 满足2.1.存在好后缀
        if (suffix[k] != -1) return j - suffix[k] + 1;

        // 没有好后缀，判断有没有好前缀
        while (k > 0) {
            if (prefix[k]) {
                return m - k;
            }
            k--;
        }
        return m;
    }

    /**
     * 模式串生成好后缀和好前缀数组
     *
     * @param patternStr 模式串
     * @param suffix     好后缀索引数组
     * @param prefix     是否存在好前缀数组
     */
    private void generateGS(String patternStr, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < suffix.length; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        int m = patternStr.length();

        for (int i = 0; i < m - 1; i++) {
            // 公共好后缀长度
            int k = 0;
            // 开始比较公共后缀的其实位置
            int j = i;

            // 每次从j位置开始向后比较
            while (j >= 0 && patternStr.charAt(j) == patternStr.charAt(m - k - 1)) {
                j--;
                k++;
                suffix[k] = i;
            }

            // 小于0时，表示后缀与前缀匹配成功
            if (j < 0) prefix[k] = true;
        }
    }

    /**
     * 模式串生成坏字符索引数组
     *
     * @param patternStr 模式串
     * @param bc         坏字符索引数组
     */
    private void generateBC(String patternStr, int[] bc) {
        for (int i = 0; i < bc.length; i++) {
            bc[i] = -1;
        }

        for (int i = 0; i < patternStr.length(); i++) {
            // 字符转化成ascii码
            int ascii = patternStr.charAt(i);
            // 存放每个字符最大的索引位置
            bc[ascii] = i;
        }
    }


}
