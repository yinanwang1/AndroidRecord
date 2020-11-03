package com.example.arthurwang.helloworld.April2020

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import android.os.*
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewGroup
import android.view.textservice.SpellCheckerInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.arthurwang.helloworld.R
import com.mylhyl.acp.Acp
import com.mylhyl.acp.AcpListener
import com.mylhyl.acp.AcpOptions
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_file.*
import kotlinx.android.synthetic.main.content_list_item.*
import org.jetbrains.anko.commit
import java.io.*
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class FileActivity : AppCompatActivity() {

    val TAG = "File Activity"
    var mHandlerThread: MyHandlerThread? = null
    var dialogFragment = MyDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        /*
        *
        * //
        *
        * */

//        checkAppPermissions()

        supportFragmentManager.beginTransaction().replace(R.id.fg_settings, PrefFragment()).commit()



    }

    /**
     * 检查app权限
     */
    private fun checkAppPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val acpOptions: AcpOptions
            acpOptions = if (Build.VERSION_CODES.Q <= Build.VERSION.SDK_INT) {
                AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_APN_SETTINGS,
                                Manifest.permission.CAMERA,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                        .build()
            } else {
                AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_APN_SETTINGS,
                                Manifest.permission.CAMERA,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION)
                        .build()
            }
            Acp.getInstance(this).request(acpOptions,
                    object : AcpListener {
                        override fun onGranted() {
                            KLog.e("wyn", "222")

                            val filePath = externalCacheDir
                            KLog.e("wyn", "filePath is $filePath")

                            if (null != filePath) {
                                saveToExternal("$filePath/test.txt")
                            }
                        }

                        override fun onDenied(permissions: MutableList<String>?) {
                            KLog.e("wyn", "333")
                        }
                    })
        } else {
            KLog.e("wyn", "111")
        }
    }

    /**
     * 获得当前应用的目录
     *
     * @return SDCard存在则返回当前应用的路径，否则返回null
     */
    fun getLocalProductPath(): String? {
        val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        if (!isSDPresent) {
            KLog.e("getLocalProductPath", "SDCard disappered!")
            return null
        }
        val path = Environment.getExternalStorageDirectory().absolutePath + "/Hello"
        val dir = File(path)
        if (!dir.isDirectory) {
            dir.mkdirs()
        }

        val file = File(path)
        if (!file.exists()) {
            try {
                val f = FileWriter(file.absolutePath)
                f.close()
            } catch (e: IOException) {
                e.printStackTrace()
                KLog.e("getLocalProductPath", "IO Exception in file: $path.nomedia")
            }
        }
        return path
    }

    fun saveToExternal(filePath: String) {
        try {
            val file = File(filePath)
            if (!file.exists()) {
                file.createNewFile()
//                if (null != file.parent) {
//                    val dir = File(file.parent)
//                    dir.mkdir()
//                    file.createNewFile()
//                }
            }

            val outStream = FileOutputStream(file)
            outStream.write("尝试下新的地铁哈".toByteArray())
            outStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun save(inputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            KLog.e("wyn", "${filesDir}")
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun load() {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    KLog.e("wyn", it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}


class MyHandlerThread(name: String): Thread(name) {
    val TAG = "MyHT"
    var mhandler: Handler? = null

    override fun run() {
        KLog.e(TAG, "进入Thread的run")
        Looper.prepare()
        val looper = Looper.myLooper()

        if (null != looper) {
            mhandler = object : Handler(looper) {
                override fun handleMessage(msg: Message) {
                    KLog.e(TAG, "获得了message")
                    KLog.e(TAG, "message is ${msg.what}")
                    super.handleMessage(msg)

                    KLog.e(TAG, "当前线程 ${currentThread()}")
                }
            }
        }

        Looper.loop()
    }
}

