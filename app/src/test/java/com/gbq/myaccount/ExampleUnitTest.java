package com.gbq.myaccount;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void length() throws Exception {
        byte[] s = "啊1啊啊是1啊2".getBytes("UTF-8");
        byte[] string = new byte[s.length-2];
        for (int i = 0; i < s.length-2; i++) {
            string[i] = s[i];
            i++;
        }
        System.err.print(new String(string,"UTF-8"));
    }

    /**
     * 判断传进来的字符串，是否
     * 大于指定的字节，如果大于递归调用
     * 直到小于指定字节数 ，一定要指定字符编码，因为各个系统字符编码都不一样，字节数也不一样
     * @param s
     *      原始字符串
     *      传进来指定字节数
     * @return String 截取后的字符串

     */
    public static String idgui(String s,int length)throws Exception{
        int changdu = s.getBytes("UTF-8").length;
        if(changdu > length){
            s = s.substring(0, s.length() - 1);
            s = idgui(s,length);
        }
        return s;
    }

}