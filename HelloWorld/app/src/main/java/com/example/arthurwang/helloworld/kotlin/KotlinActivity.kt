package com.example.arthurwang.helloworld.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.arthurwang.helloworld.R

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

        println("alphabet is ${alphabet()}")


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


}



