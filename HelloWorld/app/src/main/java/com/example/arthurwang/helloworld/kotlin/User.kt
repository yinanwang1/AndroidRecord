package com.example.arthurwang.helloworld.kotlin

import android.os.Parcel
import android.os.Parcelable
import com.example.arthurwang.helloworld.nov.Book

/**
 * Created by arthurwang on 2019-12-11
 */
class User: Parcelable {
    private var userId: Int? = null
    private var userName: String? = null
    private var isMale: Boolean? = null
    private var book: Book? = null

    constructor(userId: Int, userName: String, isMale: Boolean) {
        this.userId = userId
        this.userName = userName
        this.isMale = isMale
    }

    constructor(parcelable: Parcel?) {
        userId = parcelable?.readInt()
        userName = parcelable?.readString() ?: ""
        isMale = parcelable?.readInt() == 1
        book = parcelable?.readParcelable(Thread.currentThread().contextClassLoader)
     }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(userId ?: 0)
        dest?.writeString(userName)
        val result = isMale ?: false
        dest?.writeInt(if (result) 1 else 0)
        dest?.writeParcelable(book, 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object :Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel?): User {
                return User(source)
            }

            override fun newArray(size: Int): Array<User> {
                return arrayOf()
            }
        }
    }


}