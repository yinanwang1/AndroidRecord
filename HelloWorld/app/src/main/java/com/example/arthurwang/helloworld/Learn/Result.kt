package com.example.arthurwang.helloworld.Learn

import java.lang.Exception

/**
 * Created by arthurwang on 2020/4/15
 */

sealed class Result

class Success(val msg: String): Result()
class Failure(val error: Exception): Result()