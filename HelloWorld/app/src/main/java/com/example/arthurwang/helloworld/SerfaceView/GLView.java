package com.example.arthurwang.helloworld.SerfaceView;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by arthurwang on 2021/9/15
 */
public class GLView extends GLSurfaceView {
    private GLRenderer mRenderer;

    public GLView(Context context) {
        this(context, null);
    }

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        mRenderer = new GLRenderer();
        setRenderer(mRenderer);

    }
}
