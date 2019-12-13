package com.example.arthurwang.helloworld.kotlin

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.ServiceConnection
import android.os.*
import com.example.arthurwang.helloworld.R
import com.example.arthurwang.helloworld.nov.User
import com.example.arthurwang.helloworld.nov.utils.MyUtils
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_one2.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.ObjectInputStream

class OneActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one2)

        mBtnJumpToTwo.setOnClickListener {
            val intent = Intent(this, TwoActivity::class.java)
            intent.setFlags(FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            this.startActivity(intent)
        }

        mBtnPushOne.setOnClickListener {
//            val intent = Intent(this, OneActivity::class.java)
//            this.startActivity(intent)
            val intent = Intent()
            intent.setClassName(this, "com.example.arthurwang.helloworld.kotlin.OneActivity")
            this.startActivity(intent)
        }

//        recoverFromFile()

        val intent = Intent(this, MessagerService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    private fun recoverFromFile() {
        Thread(object : Runnable {
            override fun run() {
                var user: User?
                var cachedFile = File((externalCacheDir?.absolutePath ?: "") + "test")
                if (cachedFile.exists()) {
                    var objectInputStream: ObjectInputStream? = null
                    try {
                        objectInputStream = ObjectInputStream(FileInputStream(cachedFile))
                        user = objectInputStream.readObject() as User
                        KLog.e("wyn", "user is $user")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: ClassNotFoundException) {
                        e.printStackTrace()
                    } finally {
                        MyUtils.close(objectInputStream)
                    }
                }
            }
        }).start()
    }

    private val TAG = "MessengerActivity"
    private var mService: Messenger? = null

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mService = Messenger(service)
            val msg = Message.obtain(null, 1000)
            val data = Bundle()
            data.putString("msg", "Hello, this is client.")
            msg.data = data
            try {
                mService!!.send(msg)
                KLog.e("wyn", "send")
            } catch (e: RemoteException) {
                e.printStackTrace()
            }

            KLog.e("wyn", "111")
        }

        override fun onServiceDisconnected(name: ComponentName) {}
    }

    override fun onDestroy() {
        unbindService(mConnection)

        super.onDestroy()
    }
}
