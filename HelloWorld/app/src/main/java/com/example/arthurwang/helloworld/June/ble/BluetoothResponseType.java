package com.example.arthurwang.helloworld.june.ble;

/**
 * Created by arthurwang on 2017/6/19.
 */

public enum BluetoothResponseType {

    kBleResponseTypeError(0, "连接失败，断开连接或超时等异常，content中返回错误信息"),

    kBleResponseTypeLock(103, "关锁           0-成功   1-失败"),
    kBleResponseTypeUnlock(104, "解锁           0-成功   1-失败"),
    kBleResponseTypePowerOn(105, "通电           0-成功   1-失败"),
    kBleResponseTypePowerOff(106, "断电           0-成功   1-失败"),
    kBleResponseTypeFind(107, "寻车提示           0-成功   1-失败"),
    kBleResponseTypeOpenPowerLock(110, "电池锁打开      0-成功   1-失败"),
    kBleResponseTypeClosePowerLock(111, "电池锁关闭      0-成功   1-失败"),
    kBleResponseTypeClearMileage(113, "里程清零        0-成功   1-失败"),
    kBleResponseTypeLight(114, "灯光控制        0-成功   1-失败"),
    kBleResponseTypeUSB(116, "USB控制        0-成功   1-失败"),
    kBleResponseTypeUnlockCollect(120, "解锁、通电、里程清零反馈        0-成功   1-失败"),
    kBleResponseTypeUnlockCollectWithoutClearMileage(121, "解锁、通电        0-成功   1-失败"),
            ;


    private int code;
    private String description;

    BluetoothResponseType(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
