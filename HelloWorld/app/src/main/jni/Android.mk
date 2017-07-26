LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := static_add
LOCAL_SRC_FILES := $(TARGET_ARCH_ABI)/libstatic_add.a
include $(PREBUILT_STATIC_LIBRARY)


include $(CLEAR_VARS)

LOCAL_MODULE := MyLibrary
LOCAL_SRC_FILES := MyLibrary.c
LOCAL_STATIC_LIBRARIES += static_add
include $(BUILD_SHARED_LIBRARY)