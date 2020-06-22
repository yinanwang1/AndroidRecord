package com.example.arthurwang.helloworld.April2020

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by arthurwang on 2020/5/21
 */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}