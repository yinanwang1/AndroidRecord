package com.example.arthurwang.helloworld.April2020

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.arthurwang.helloworld.R
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_broadcast.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.reflect.KProperty

class BroadcastActivity : AppCompatActivity() {

    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

//        val mySet = MySet<Int>(HashSet())
//
//        mySet.helloworld()

//        val intentFilter = IntentFilter()
//
//        intentFilter.addAction("android.intent.action.TIME_TICK")
//        timeChangeReceiver = TimeChangeReceiver()
//        registerReceiver(timeChangeReceiver, intentFilter)
//
//        val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
//        val result = StringBuilder().build {
//            append("Start eating fruits.\n")
//            for (fruit in list) {
//                append(fruit).append("\n")
//            }
//            append("Ate all fruits.")
//        }
//
//        KLog.e("wyn", result.toString())

//        val trans = object : Transformer<Person> {
//            override fun transform(t: Person): String {
//                return "${t.name} ${t.age}"
//            }
//        }
//
//        handleTransformer(trans)

        sendRequestWithHttpURlConnection()


    }



    private fun sendRequestWithHttpURlConnection() {
        thread {
//            var connection: HttpURLConnection? = null
            try {
//                val response = StringBuilder()

                val client = OkHttpClient()
                val request = Request.Builder()
                        .url("https://www.baidu.com")
                        .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (null != responseData) {
                    showResponse(responseData)
                }

//                val url = URL("https://www.baidu.com")
//                connection = url.openConnection() as HttpURLConnection
//                connection.requestMethod = "GET"
//                connection.connectTimeout = 8000
//                connection.readTimeout = 8000
//                val input = connection.inputStream
//                val reader = BufferedReader(InputStreamReader(input))
//                reader.use {
//                    reader.forEachLine {
//                        response.append(it)
//                    }
//                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
//                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            responseText.text = response
        }
    }

    fun handleTransformer(trans: Transformer<Student>) {
        val student = Student("Tom", 19)

        val result = trans.transform(student)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(this@BroadcastActivity, "Time is changed", Toast.LENGTH_LONG).show()
        }
    }

    fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
        block()

        val map = mapOf("Apple" with 1, "Banana" with 2, "orange" with 3, 1 with "123")

        return this
    }

    infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)
}

class MySet<T>(private val helperSet: HashSet<T>) : Set<T> by helperSet {
    fun helloworld() = KLog.e("wyn", "test mySet")
}

fun <T> later(block: () -> T) = Later(block)

class Later<T>(val block: () -> T) {
    var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (null == value) {
            value = block()
        }

        return value as T
    }
}

class MyThread: Thread() {
    override fun run() {
        super.run()
    }


}
class MyRunnable: Runnable {
    override fun run() {
        TODO("Not yet implemented")
    }
}

interface Transformer<in T> {
    fun transform(t: T): String
}

open class Person(open val name: String = "", open val age: Int = 0) {

}

class Student(override val name: String, override val age: Int): Person(name, age) {
    val school: String = ""
}
