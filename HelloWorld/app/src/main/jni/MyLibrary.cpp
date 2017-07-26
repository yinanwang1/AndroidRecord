//
// Created by ArthurWang on 2017/7/26.
//

#include "com_example_arthurwang_helloworld_July_MyNdk.h"

JNIEXPORT jstring JNICALL Java_com_example_arthurwang_helloworld_July_MyNdk_getString(JNIEnv *env, jobject obj)
{
    return (*env).NewStringUTF("This is 哇哈哈");
}

