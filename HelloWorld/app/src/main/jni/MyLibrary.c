//
// Created by ArthurWang on 2017/7/26.
//

#include "com_example_arthurwang_helloworld_July_MyNdk.h"
#include "i7565H1H2.h"

JNIEXPORT jstring JNICALL Java_com_example_arthurwang_helloworld_July_MyNdk_getString(JNIEnv *env, jobject obj)
{
    BYTE a = 43;
    BYTE b = 43;
    int result = VCI_Clr_TxSentCnt(a, b);
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

