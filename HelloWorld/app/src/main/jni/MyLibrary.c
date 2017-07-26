//
// Created by ArthurWang on 2017/7/26.
//

#include "com_example_arthurwang_helloworld_July_MyNdk.h"
#include "static.h"

JNIEXPORT jstring JNICALL Java_com_example_arthurwang_helloworld_July_MyNdk_getString(JNIEnv *env, jobject obj)
{
    int result = add(3, 3);
    char test[100] = "结果                ";

    if (3 < result)
    {
        test[10] = '5';
        test[11] = '5';
    } else {
        test[10] = '6';
        test[11] = '6';
    }


    return (*env)->NewStringUTF(env, test);
}

