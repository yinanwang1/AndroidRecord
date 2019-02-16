package com.example.arthurwang.helloworld.kotlin

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.arthurwang.helloworld.R
import java.io.BufferedReader

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

//        var reader = BufferedReader(StringReader("123"))
//        study.testReader(reader)
//
//         reader = BufferedReader(StringReader("not a number"))
//        study.testReader(reader)

//        study.testString()

//        study.testRegex()

//        study.parsePath("/User/yole/kotlin-book/chapter.adoc")
//
//
//        btn_click.setOnClickListener{
//            println("点我啊！！！")
//        }

//        val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Wang", 31))
//        println(people.maxBy { it.age })

//        val sum = {x: Int, y: Int -> x + y}
//        println("sum is ${sum(1, 2)}")
//
//        kotlin.run { println(4444123123123) }
//        val names = people.joinToString(separator = " ", transform = {p: Person -> p.name})

//        val names = people.joinToString(" ") {p: Person -> p.name}
//        println("names is $names")
//
//
//        val errors = listOf("403 Forbidden", "404 Not Found")
//        printMessagesWithPrefix(errors, "Error:")

//        val createPerson = ::Person
//        val p = createPerson("Alice", 33)
//
//        println(p)

//        val list = listOf(1, 2, 3, 4)
//        val result = list.map { it * it }
//
//        println("result is $result")

//        val canBeInClub27 = {p: Person -> p.age <= 30}
//
//        println(people.groupBy{it.age})

//        val books = listOf(Book("123", listOf("wang", "yi")), Book("456", listOf("li", "qianqian")))
//
//        println(books.map { it.authors }.flatten())

//        val naturalNumbers = generateSequence(0) { it + 1 }
//        val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
//        println(numbersTo100.sum())

//        println("alphabet is ${alphabet()}")

//        println("createViewWithCustomerAttributes is ${createViewWithCustomerAttributes(this)}")
//
//        ll_test.addView(createViewWithCustomerAttributes(this))

//        var email: String? = "yole@example.com"
//        email?.let { sendEmailTo(it) }
//
//        email = null
//        email?.let { sendEmailTo(it) }

//        val p1 = Point(10, 20)
//        val p2 = Point(30, 40)
//        println("wyn ${p1 + p2}")

//        println('a' * 3)

//        var point = Point(1, 2)
//        point += Point(3, 4)
//        println("point is $point")

//        val list = arrayListOf(1, 2)
//        list += 3
//        val newList = list + listOf(4, 5)
//        println(list)
//        println(newList)

//        twoAndThree{a, b -> a + b}
//        twoAndThree{a, b -> a * b}

//        println("ab1c".filter { it in 'a'..'z' })

        val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
        println("Shipping costs ${calculator(Order(3))}")

    }

    annotation class JsonName(val name: String)

    enum class Delivery {STANDARD, EXPEDITED}

    class Order(val itemCount: Int)

    fun getShippingCostCalculator(delivery: Delivery) : (Order) -> Double {
        if (delivery == Delivery.EXPEDITED) {
            return {order -> 6 + 2.1 * order.itemCount }
        }

        return {order -> 1.2 * order.itemCount }
    }

    fun twoAndThree(operation: (Int, Int) -> Int) {
        val result = operation(2, 3)
        println("The result is $result")
    }



    operator fun Char.times(count: Int): String {
        return toString().repeat(count)
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point): Point {
            return Point(x + other.x, y + other.y)
        }
    }

    fun readNumbers(reader: BufferedReader) : List<Int?> {
        val result = ArrayList<Int?>()
        for (line in reader.lineSequence()) {
            val number = line.toIntOrNull()
            result.add(number)
        }

        return result
    }


    fun sendEmailTo(email: String) {
        println("Sending email to $email")
    }

    data class Person(val name: String, val age: Int = 0)

    class Book(val title: String, val authors: List<String>)


    fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
        messages.forEach {
            println("prefix is $prefix, message is $it")
        }
    }

    fun alphabet() : String {
        val result = StringBuilder()
        for (letter in 'A'..'Z') {
            result.append(letter)
        }

        result.append("\nNow I know the alphabet!")

        return result.toString()
    }

    fun createViewWithCustomerAttributes(context: Context) = TextView(context).apply {
        text = "Sample Text"
        textSize = 20.0f
        setPadding(10, 0, 0, 0)
    }


}



