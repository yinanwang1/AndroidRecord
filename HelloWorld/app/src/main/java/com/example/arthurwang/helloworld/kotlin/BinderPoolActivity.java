package com.example.arthurwang.helloworld.kotlin;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

public class BinderPoolActivity extends Activity {

    private Button mBtnDoWork;
    private ISecurityCenter mSecurityCenter;
    private ICompute mCompute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);

        mBtnDoWork = (Button) findViewById(R.id.btn_do_work);

        mBtnDoWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        doWork();
                    }
                }.start();
            }
        });
    }


    private void doWork() {
        BinderPool binderPool = BinderPool.getInstance(BinderPoolActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = (ISecurityCenter) SecurityCenterImpl.asInterface(securityBinder);
        KLog.e("wyn", "visit ISecurityCenter");
        String msg = "hellowork-安卓";
        KLog.e("cotent: " + msg);

        try {
            String password = mSecurityCenter.encrypt(msg);
            KLog.e("wyn", "encrypt: " + password);
            KLog.e("wyn", "decrypt: " + mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        KLog.e("wyn", "visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ComputeImpl.asInterface(computeBinder);

        try
        {
            KLog.e("wyn", "3+5=" + mCompute.add(3, 5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
