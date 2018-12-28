package com.example.arthurwang.helloworld.one.bean;

import com.example.arthurwang.helloworld.one.view.Display;
import com.socks.library.KLog;

/**
 * Created by arthurwang on 2018/12/26
 */
public class Cartoon<E, F> implements Display<E, F> {
    public void role(E sheep, F wolf) {
        KLog.e(sheep.toString());
        KLog.e(wolf.toString());
    }
}
