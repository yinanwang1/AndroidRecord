package com.example.arthurwang.helloworld.kotlin;

import android.os.RemoteException;

/**
 * Created by arthurwang on 2019-12-16
 */
public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
