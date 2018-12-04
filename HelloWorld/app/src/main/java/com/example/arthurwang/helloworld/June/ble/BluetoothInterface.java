package com.example.arthurwang.helloworld.june.ble;

/**
 * Created by arthurwang on 2017/6/19.
 */

public interface BluetoothInterface {
    /**
     * 请求指令的反馈
     * @param type
     * @param content
     */
    public void bluetoothResponse(BluetoothResponseType type, String content);

    /**
     * 连接状态更新
     */
    public void bluetoothConnectSuccess(boolean success);
}
