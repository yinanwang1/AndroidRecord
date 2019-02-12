package com.example.arthurwang.helloworld.kotlin

import java.io.BufferedReader
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by arthurwang on 2019/1/31
 */


fun testOutOfClass() {
    println("I am a fun out of class")
}


class Study {

    var test = 1

    fun testStudy() {
        println("I am here!")

        val message: String

        if (test == 1) {
            message = "Success"
        } else {
            message = "Failed"
        }

        val languages = arrayListOf("Java")

        languages.add("test")



    }

    fun mix(c1: Color, c2: Color) =
            when (setOf(c1, c2)) {
                setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
                setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
                setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
                else -> throw Exception("Dirty color")
            }


    fun testWhile() {
        val max = 100
        var value = 0

        while (max > value) {
            value++

            println("value is $value")
        }
    }

    fun testMap() {
        val binaryReps = TreeMap<Char, String>()

        for (c in 'A'..'F') {
            val binary = Integer.toHexString(c.toInt())
            binaryReps[c] = binary
        }

        for ((letter, binary) in binaryReps) {
            println("$letter = $binary")
        }
    }


    fun testIndex() {
        val list = arrayListOf("10", "11", "1001")

        for ((index, element) in list.withIndex()) {
            println("$index = $element")
        }
    }

    fun testReader(reader: BufferedReader) {
        val number = try {
            Integer.parseInt(reader.readLine())
        } catch (e: NumberFormatException) {
            null
        }

        println("wyn is $number")
    }

    fun testString() {
        val list = listOf(1, 2, 3)
        println("list is ${joinString(list, ",", "(", ")")}")
    }

    fun <T>joinString(collection: Collection<T>,
                      separator: String,
                      prefix: String,
                      postfix: String) : String {
        val result = StringBuilder(prefix)

        for ((index, element) in collection.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(element)
        }

        result.append(postfix)

        return result.toString()
    }

    fun testRegex() {
        val string = "12.345-6.A"

        println("string is ${string.split("\\.|-".toRegex())}")
    }

    fun parsePath(path: String) {
        val directory = path.substringBeforeLast("/")
        val fullName = path.substringAfterLast("/")
        val fileName = fullName.substringBeforeLast(".")
        val extension = fullName.substringAfterLast(".")

        println("Dir: $directory, name: $fileName, ext: $extension")
    }


}


enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET,
}


interface Clickable {
    fun click()

    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")

    fun showOff() = println("I'm focusable!")
}

class Button: Clickable, Focusable {
    override fun click() {
        println("click")
    }

    override fun showOff() {
        println("Hello")
    }
}

interface User {
    val nickName: String
}

class DelegatingCollection<T>(innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList
{

}
