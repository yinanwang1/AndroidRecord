package com.example.arthurwang.helloworld.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.arthurwang.helloworld.R
import java.io.BufferedReader
import java.io.StringReader

class KotlinActivity : AppCompatActivity() {

    private val study = Study()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

//        val persons = listOf(Person("Alice"),
//                Person("Bob", 29))
//
//        val oldest = persons.maxBy { it.age ?: 0 }
//
//        println("The oldest is: $oldest")
//
//        var x = 1


//        study.testStudy()
//
//        testOutOfClass();

//        study.testWhile()

//        study.testMap()

//        study.testIndex()

        var reader = BufferedReader(StringReader("123"))
        study.testReader(reader)

         reader = BufferedReader(StringReader("not a number"))
        study.testReader(reader)













    }

    data class Person(val name: String, val age: Int? = null)


}



