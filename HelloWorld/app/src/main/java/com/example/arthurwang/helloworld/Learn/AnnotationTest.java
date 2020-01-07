package com.example.arthurwang.helloworld.Learn;

import com.socks.library.KLog;

/**
 * Created by arthurwang on 2020-01-06
 */
public class AnnotationTest {

    @MyMessage(num = 10, desc = "参数a")
    private static int a;

    @MyMessage(name = "Sam test", desc = "测试方法test")
    public void test() {
        KLog.e("wyn", "wyn test");
    }
}
