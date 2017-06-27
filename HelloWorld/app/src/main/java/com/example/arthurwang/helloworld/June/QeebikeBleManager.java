package com.example.arthurwang.helloworld.June;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.clj.fastble.BleManager;
import com.clj.fastble.conn.BleCharacterCallback;
import com.clj.fastble.conn.BleGattCallback;
import com.clj.fastble.data.ScanResult;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;
import com.example.arthurwang.helloworld.June.ble.AESCipher;
import com.example.arthurwang.helloworld.June.ble.BluetoothInterface;
import com.example.arthurwang.helloworld.June.ble.BluetoothRequestType;
import com.example.arthurwang.helloworld.June.ble.BluetoothResponseType;
import com.socks.library.KLog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luoliuqing on 17/6/14.
 */

public class QeebikeBleManager {
    private static QeebikeBleManager manager;

    private static final String ASE_KEY ="vPXo76sGwXg9uqIR";

    // 下面的所有UUID及指令请根据实际设备替换
    private static final String UUID_SERVICE = "00001802-0000-1000-8000-00805f9b34fb";
    private static final String UUID_NOTIFY = "00002ABC-0000-1000-8000-00805f9b34fb";
    private static final String UUID_WRITE = "00002A06-0000-1000-8000-00805f9b34fb";
    private static final String UUID_READ = "00002ABC-0000-1000-8000-00805f9b34fb";

    private static final long TIME_OUT = 10000;                                          // 扫描超时时间
    private static final String TAG = "ble_manager";
    private static final String PREFIX = "qeebike_";                                    // 蓝牙名称的前缀

    private BleManager bleManager;                                                      // Ble核心管理类
    private ScanResult scanResult;
    private String mDeviceName;
    private BluetoothInterface bluetoothInterface;
    private Handler threadHandler = new Handler(Looper.getMainLooper());

    private boolean hasConnected = false;
    private ArrayList<String> writeDataArr = new ArrayList<>();
    private Handler handler;
    private volatile Thread thread;

    public static QeebikeBleManager getManager() {
        if (manager == null){
            manager = new QeebikeBleManager();
        }
        return manager;
    }

    public boolean isOpenBlueTooth() {
        BluetoothAdapter blueadapter = BluetoothAdapter.getDefaultAdapter();
        return blueadapter.isEnabled();
    }

    public void connectBle(Context context, String bikeNo, BluetoothInterface bluetoothInterface)
    {
        bleManager = new BleManager(context);

        if (!isSupportBle())
        {
            bluetoothInterface.bluetoothResponse(BluetoothResponseType.kBleResponseTypeError, "不支持蓝牙4.0");

            stopThread();

            return;
        }

        enableBlue();

        this.bluetoothInterface = bluetoothInterface;
        this.mDeviceName = PREFIX + bikeNo;

        if (hasConnected)
        {
            return;
        }

        scanAndConnect1();
    }

    public void disconnectBle()
    {
        if (bleManager == null) return;
        disableBlue();

        cancelScan();

        stop_notify();

        close();

        writeDataArr.clear();

        hasConnected = false;
    }

    public void sendMessage(String key, BluetoothRequestType type, String content) {
        String decodeKey = "";
        try {
            decodeKey = AESCipher.aesDecryptString(key, ASE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String writeContent = decodeKey + "," + type.getCode() + "," + content;

        if (!hasConnected) {
            scanAndConnect1();

            writeDataArr.add(writeContent);
        } else {

            write(writeContent);
        }

        if (null == handler) {
            handler = new Handler() {
                public void handleMessage(Message msg) {

                    bluetoothInterface.bluetoothResponse(BluetoothResponseType.kBleResponseTypeError, "1");

                    stopThread();

                    super.handleMessage(msg);
                }
            };
        }

        if (null == thread)
        {
            thread = new Thread(new MyThread());
        }

        thread.start();
    }


        /**************************************user's operate****************************************/

    /**
     * 判断是否支持ble
     */
    private boolean isSupportBle() {
        return bleManager.isSupportBle();
    }

    /**
     * 手动开启蓝牙
     */
    private void enableBlue() {
        bleManager.enableBluetooth();
    }

    /**
     * 手动关闭蓝牙
     */
    private void disableBlue() {
        bleManager.disableBluetooth();
    }

    /**
     * 刷新缓存操作
     */
    private void refersh() {
        bleManager.refreshDeviceCache();
    }

    /**
     * 关闭操作
     */
    private void close() {
        bleManager.closeBluetoothGatt();
    }

    /**
     * 扫描指定广播名的设备，并连接（唯一广播名）
     */
    private void scanAndConnect1() {
        bleManager.scanNameAndConnect(
                mDeviceName,
                TIME_OUT,
                false,
                new BleGattCallback() {
                    @Override
                    public void onNotFoundDevice() {
                        Log.i(TAG, "未发现设备");

                        hasConnected = false;
                    }

                    @Override
                    public void onFoundDevice(ScanResult scanResult) {
                        Log.i(TAG, "发现设备: " + scanResult.getDevice().getAddress());
                    }

                    @Override
                    public void onConnectSuccess(BluetoothGatt gatt, int status) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            gatt.discoverServices();
                        }
                        Log.i(TAG, "连接成功");
                    }

                    @Override
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                        Log.i(TAG, "发现服务");

                        hasConnected = true;

                        bluetoothInterface.bluetoothConnectSuccess(true);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            BluetoothGattService service = gatt.getService(UUID.fromString(UUID_SERVICE));

                            BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString(UUID_NOTIFY));

                            if (null != characteristic)
                            {
                                KLog.e("wyn characteristic is " + characteristic.getUuid());
                            }

                            if (null != service)
                            {
                                runOnMainThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        listen_notify();
                                    }
                                });

                                hasConnected = true;

                                for (int i = 0; i < writeDataArr.size(); i++)
                                {
                                    final String writeConnent = writeDataArr.get(i);

                                    runOnMainThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            write(writeConnent);
                                        }
                                    });
                                }

                                writeDataArr.clear();
                            }
                        }
                    }

                    @Override
                    public void onConnectFailure(BleException exception) {
                        Log.i(TAG, "连接中断：" + exception.toString());

                        hasConnected = false;

                        bluetoothInterface.bluetoothConnectSuccess(false);

                        bluetoothInterface.bluetoothResponse(BluetoothResponseType.kBleResponseTypeError, exception.getDescription());

                        stopThread();
                    }





                });
    }

    /**
     * 取消搜索
     */
    private void cancelScan() {
        bleManager.cancelScan();
    }

    /**
     * notify
     */
    private void listen_notify() {
        KLog.e("listen_notify 开始监听");

        bleManager.notify(
                UUID_SERVICE,
                UUID_NOTIFY,
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {
                        KLog.e("listen_notify onSuccess.");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            dealWithResponse(characteristic.getValue());
                        }
                    }

                    @Override
                    public void onFailure(BleException exception) {
                        KLog.e("listen_notify exception" + exception.getDescription());

                        bluetoothInterface.bluetoothResponse(BluetoothResponseType.kBleResponseTypeError, exception.getDescription());

                        stopThread();
                    }
                });
    }

    /**
     * stop notify
     */
    private boolean stop_notify() {
        return bleManager.stopNotify(UUID_SERVICE, UUID_NOTIFY);
    }


    /**
     * write 要写入设备某一个character的指令
     */
    private void write(String writeValue) {
        String data = convertStringToHex(writeValue);

        bleManager.writeDevice(
                UUID_SERVICE,
                UUID_WRITE,
                HexUtil.hexStringToBytes(data),
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {
                        KLog.e("写指令成功");

                        KLog.e("characteristic.getWriteType() is " + characteristic.getWriteType());
                    }

                    @Override
                    public void onFailure(BleException exception) {
                        KLog.e(exception.getDescription());

                        bluetoothInterface.bluetoothResponse(BluetoothResponseType.kBleResponseTypeError, exception.getDescription());

                        stopThread();
                    }
                });
    }

    /**
     * read
     */
    private void read() {
        bleManager.readDevice(
                UUID_SERVICE,
                UUID_READ,
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {
                        KLog.e("read onSuccess");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            dealWithResponse(characteristic.getValue());
                        }
                    }

                    @Override
                    public void onFailure(BleException exception) {
                        KLog.e(exception.getDescription());

                        bluetoothInterface.bluetoothResponse(BluetoothResponseType.kBleResponseTypeError, exception.getDescription());

                        stopThread();
                    }
                });
    }


    // 处理repose ___start___

    private void dealWithResponse(byte[] data)
    {
        KLog.e("data is " + HexUtil.encodeHexStr(data));

        if (0 >= data.length) {
            bluetoothInterface.bluetoothResponse(BluetoothResponseType.kBleResponseTypeError, "数据为空");

            stopThread();

            return;
        }

        String hexStr = HexUtil.encodeHexStr(data);
        String charStr = convertHexToString(hexStr);
        String dataStr = charStr.replace(" ", "");

        KLog.e("dataStr is " + dataStr);

        List<String> dataList = Arrays.asList(dataStr.split(","));

        if (2 != dataList.size())
        {
            bluetoothInterface.bluetoothResponse(BluetoothResponseType.kBleResponseTypeError, "数据格式不正确");

            stopThread();

            return;
        }

        String typeStr = dataList.get(0);
        String contentStr = dataList.get(1);

        BluetoothResponseType type = BluetoothResponseType.kBleResponseTypeError;
        int typeInt = Integer.parseInt(typeStr);
        if (typeInt == BluetoothResponseType.kBleResponseTypeLock.getCode()) {
            type = BluetoothResponseType.kBleResponseTypeLock;
        } else if (typeInt == BluetoothResponseType.kBleResponseTypeUnlock.getCode()) {
            type = BluetoothResponseType.kBleResponseTypeUnlock;
        } else if (typeInt == BluetoothResponseType.kBleResponseTypePowerOn.getCode()) {
            type = BluetoothResponseType.kBleResponseTypePowerOn;
        } else if (typeInt == BluetoothResponseType.kBleResponseTypePowerOff.getCode()) {
            type = BluetoothResponseType.kBleResponseTypePowerOff;
        } else if (typeInt == BluetoothResponseType.kBleResponseTypeOpenPowerLock.getCode()) {
            type = BluetoothResponseType.kBleResponseTypeOpenPowerLock;
        } else if (typeInt == BluetoothResponseType.kBleResponseTypeClosePowerLock.getCode()) {
            type = BluetoothResponseType.kBleResponseTypeClosePowerLock;
        } else if (typeInt == BluetoothResponseType.kBleResponseTypeClearMileage.getCode()) {
            type = BluetoothResponseType.kBleResponseTypeClearMileage;
        } else if (typeInt == BluetoothResponseType.kBleResponseTypeLight.getCode()) {
            type = BluetoothResponseType.kBleResponseTypeLight;
        } else if (typeInt == BluetoothResponseType.kBleResponseTypeUSB.getCode()) {
            type = BluetoothResponseType.kBleResponseTypeUSB;
        }

        bluetoothInterface.bluetoothResponse(type, contentStr);

        stopThread();
    }

    // 处理repose ___end___

    private void runOnMainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            threadHandler.post(runnable);
        }
    }

    /* 将字符串转化16进制数字的字符串 */
    public String convertStringToHex(String str){
        //把字符串转换成char数组
        char[] chars = str.toCharArray();
        //新建一个字符串缓存类
        StringBuffer hex = new StringBuffer();
        //循环每一个char
        for(int i = 0; i < chars.length; i++){
            //把每一个char都转换成16进制的，然后添加到字符串缓存对象中
            hex.append(Integer.toHexString((int)chars[i]));
        }

        //最后返回字符串就是16进制的字符串
        return hex.toString();
    }

    /*
    * 将16进制数字解码成字符串,适用于所有字符（包括中文）
    */
    public static String convertHexToString(String bytes) {
        String hexString = "0123456789abcdef";
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        //将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }


    public class MyThread implements Runnable {

        @Override
        public void run() {
            Thread thisThread = Thread.currentThread();
            while (thread == thisThread) {
                try {
                    thisThread.sleep(TIME_OUT); // 单位毫秒
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);// 发送消息
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private void stopThread()
    {
        KLog.e("停止线程");

        if (null != thread) {
            thread.interrupt();
            thread = null;
        }
    }
}

