package com.example.arthurwang.helloworld.june.ble;

/**
 * Created by arthurwang on 2017/6/19.
 */

public enum BluetoothRequestType {

    kBleRequestTypeLock(3, "关锁       0-成功   1-失败"),
    kBleRequestTypeUnlock(4,"解锁       0-成功   1-失败"),
    kBleRequestTypePowerOn(5, "通电       1-电动 2-混动 3-人力"),
    kBleRequestTypePowerOff (6, "断电       0-成功   1-失败"),
    kBleRequestTypeFind (7, "寻车提示       0-成功   1-失败"),
    kBleRequestTypeOpenPowerLock(10, "电池锁打开    0-成功   1-失败"),
    kBleRequestTypeClosePowerLock(11, "电池锁关闭    0-成功   1-失败"),
    kBleRequestTypeClearMileage(13, "里程清零     0-成功   1-失败"),
    kBleRequestTypeLight(14, "灯光控制     0-关灯   1-开灯"),
    kBleRequestTypeUSB(16, "USB控制     0-关闭usb充电   1-打开usb充电"),
    kBleRequestTypeUnlockCollect(20, "驱动模式    1-电动 2-混动 3-人力 4-强助力 5-中助力 6-弱助力"),
    kBleRequestTypeUnlockCollectWithoutClearMileage(21, "驱动模式    1-电动 2-混动 3-人力 4-强助力 5-中助力 6-弱助力"),
    ;


    private int code;
    private String description;

    BluetoothRequestType(int code, String description)
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
