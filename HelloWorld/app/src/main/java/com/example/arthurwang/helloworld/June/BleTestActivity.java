package com.example.arthurwang.helloworld.June;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthurwang.helloworld.June.ble.AESCipher;
import com.example.arthurwang.helloworld.June.ble.BluetoothInterface;
import com.example.arthurwang.helloworld.June.ble.BluetoothRequestType;
import com.example.arthurwang.helloworld.June.ble.BluetoothResponseType;
import com.example.arthurwang.helloworld.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeClearMileage;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeClosePowerLock;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeFind;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeLight;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeLock;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeOpenPowerLock;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypePowerOff;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypePowerOn;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeUSB;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeUnlock;
import static com.example.arthurwang.helloworld.June.ble.BluetoothRequestType.kBleRequestTypeUnlockCollect;

public class BleTestActivity extends Activity {

    protected LinearLayout mLlBaseView;
    protected EditText mEtBikeNumber;
    protected EditText mEtKey;
    protected Button mBtConnect;
    protected TextView mTvStatus;
    protected ListView mLvOperation;

    public ProgressDialog mProgressDialog;
    private boolean hasBeenConnection;
    private static byte[] object = new byte[0];
    private static Toast toast = null;

    private final int kBleCellFuctionUnlock = 0;
    private final int kBleCellFuctionPowerOnElectricity = 1;
    private final int kBleCellFuctionPowerOnAssistant = 2;
    private final int kBleCellFuctionPowerOnManPower = 3;
    private final int kBleCellFuctionPowerOnStrongAssistant = 4;
    private final int kBleCellFuctionPowerOnMiddleAssistant = 5;
    private final int kBleCellFuctionPowerOnWeakAssistant = 6;
    private final int kBleCellFuctionPowerOff = 7;
    private final int kBleCellFuctionClearMiles = 8;
    private final int kBleCellFuctionLock = 9;
    private final int kBleCellFuctionLightOn = 10;
    private final int kBleCellFuctionLightOff = 11;
    private final int kBleCellFuctionUSBOn = 12;
    private final int kBleCellFuctionUSBOff = 13;
    private final int kBleCellFuctionBatteryUnlock = 14;
    private final int kBleCellFuctionBatteryLock = 15;
    private final int kBleCellFuctionFind = 16;
    private final int kBleCellFuctionStartTravel = 17;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_test);

        mProgressDialog = new ProgressDialog(this);

        assignViews();

        addListener();

        initialListView();
    }


    private void assignViews() {
        mLlBaseView = (LinearLayout) findViewById(R.id.ll_base_view);
        mEtBikeNumber = (EditText) findViewById(R.id.et_bike_number);
        mEtKey = (EditText) findViewById(R.id.et_key);
        mBtConnect = (Button) findViewById(R.id.bt_connect);
        mTvStatus = (TextView) findViewById(R.id.tv_status);
        mLvOperation = (ListView) findViewById(R.id.lv_operation);
    }


    private void addListener() {
        mBtConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInputSoftware(mLlBaseView);

                showMessage("开始连接蓝牙设备");

                if (0 >= mEtBikeNumber.getText().toString().length())
                {
                    showLoading("请输入单车编号");

                    return;
                }

                connect();
            }
        });
    }

    private void initialListView() {
        mLvOperation.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        mLvOperation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!hasBeenConnection)
                {
                    showMessage("请先连接蓝牙");

                    return;
                }

                if (0 >= mEtKey.getText().toString().length())
                {
                    showMessage("请输入key");

                    return;
                }

                BluetoothRequestType requestType = kBleRequestTypeUnlock;
                String content = "0";

                switch (position)
                {
                    case kBleCellFuctionUnlock:
                    {
                        requestType = kBleRequestTypeUnlock;
                        content = "0";
                    }
                    break;

                    case kBleCellFuctionPowerOnElectricity:
                    {
                        requestType = kBleRequestTypePowerOn;
                        content = "1";
                    }
                    break;

                    case kBleCellFuctionPowerOnAssistant:
                    {
                        requestType = kBleRequestTypePowerOn;
                        content = "2";
                    }
                    break;

                    case kBleCellFuctionPowerOnManPower:
                    {
                        requestType = kBleRequestTypePowerOn;
                        content = "3";
                    }
                    break;

                    case kBleCellFuctionPowerOnStrongAssistant:
                    {
                        requestType = kBleRequestTypePowerOn;
                        content = "4";
                    }
                    break;

                    case kBleCellFuctionPowerOnMiddleAssistant:
                    {
                        requestType = kBleRequestTypePowerOn;
                        content = "5";
                    }
                    break;

                    case kBleCellFuctionPowerOnWeakAssistant:
                    {
                        requestType = kBleRequestTypePowerOn;
                        content = "6";
                    }
                    break;

                    case kBleCellFuctionPowerOff:
                    {
                        requestType = kBleRequestTypePowerOff;
                        content = "0";
                    }
                    break;

                    case kBleCellFuctionClearMiles:
                    {
                        requestType = kBleRequestTypeClearMileage;
                        content = "0";
                    }
                    break;

                    case kBleCellFuctionLock:
                    {
                        requestType = kBleRequestTypeLock;
                        content = "0";
                    }
                    break;

                    case kBleCellFuctionLightOn:
                    {
                        requestType = kBleRequestTypeLight;
                        content = "1";
                    }
                    break;

                    case kBleCellFuctionLightOff:
                    {
                        requestType = kBleRequestTypeLight;
                        content = "0";
                    }
                    break;

                    case kBleCellFuctionUSBOn:
                    {
                        requestType = kBleRequestTypeUSB;
                        content = "1";
                    }
                    break;

                    case kBleCellFuctionUSBOff:
                    {
                        requestType = kBleRequestTypeUSB;
                        content = "0";
                    }
                    break;

                    case kBleCellFuctionBatteryUnlock:
                    {
                        requestType = kBleRequestTypeOpenPowerLock;
                        content = "0";
                    }
                    break;

                    case kBleCellFuctionBatteryLock:
                    {
                        requestType = kBleRequestTypeClosePowerLock;
                        content = "0";
                    }
                    break;

                    case kBleCellFuctionFind:
                    {
                        requestType = kBleRequestTypeFind;
                        content = "0";
                    }

                    case kBleCellFuctionStartTravel:
                    {
                        requestType = kBleRequestTypeUnlockCollect;
                        content = "0";
                    }

                    default:
                        break;
                }

                String keyAES = "vPXo76sGwXg9uqIR";
                String keyEncode = "";
                try
                {
                    keyEncode = AESCipher.aesEncryptString(mEtKey.getText().toString(), keyAES);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }


                sendType(keyEncode, requestType, content);

                showLoading("等待单车返回");
            }
        });
    }




    private void connect()
    {
        QeebikeBleManager.getManager().connectBle(BleTestActivity.this, mEtBikeNumber.getText().toString(), new BluetoothInterface(){

            @Override
            public void bluetoothResponse(BluetoothResponseType type, String content) {
                hideLoading();

                String responseType = "";

                switch (type)
                {
                    case kBleResponseTypeError:
                    {
                        responseType = "蓝牙响应超时 ";
                    }
                    break;

                    case kBleResponseTypeLock:
                    {
                        responseType = "关锁 ";
                    }
                    break;

                    case kBleResponseTypeUnlock:
                    {
                        responseType = "解锁 ";
                    }
                    break;

                    case kBleResponseTypePowerOn:
                    {
                        responseType = "通电 ";
                    }
                    break;

                    case kBleResponseTypePowerOff:
                    {
                        responseType = "断电 ";
                    }
                    break;

                    case kBleResponseTypeFind:
                    {
                        responseType = "寻车提示 ";
                    }
                    break;

                    case kBleResponseTypeOpenPowerLock:
                    {
                        responseType = "电池锁打开 ";
                    }
                    break;

                    case kBleResponseTypeClosePowerLock:
                    {
                        responseType = "电池锁关闭 ";
                    }
                    break;

                    case kBleResponseTypeClearMileage:
                    {
                        responseType = "里程清零 ";
                    }
                    break;

                    case kBleResponseTypeLight:
                    {
                        responseType = "灯光控制 ";
                    }
                    break;

                    case kBleResponseTypeUSB:
                    {
                        responseType = "USB控制 ";
                    }
                    break;

                    case kBleResponseTypeUnlockCollect:
                    {
                        responseType = "解锁、通电、里程清零 ";
                    }
                    break;

                    case kBleResponseTypeUnlockCollectWithoutClearMileage:
                    {
                        responseType = "解锁、通电 ";
                    }
                    break;
                }

                String statusStr = "";
                if (0 == Integer.parseInt(content))
                {
                    statusStr = "成功";
                }
                else {
                    statusStr = "失败";
                }

                mTvStatus.setText(responseType + statusStr);
            }

            @Override
            public void bluetoothConnectSuccess(boolean success) {
                if (success)
                {
                    hasBeenConnection = true;

                    mBtConnect.setText("已连接");
                    mTvStatus.setText("连接成功");
                }
                else
                {
                    hasBeenConnection = false;

                    mBtConnect.setText("断开连接");
                    mTvStatus.setText("连接失败");

                    connect();
                }
            }
        });
    }

    private void sendType(String key, BluetoothRequestType type, String content)
    {
        hideInputSoftware(mLlBaseView);

        QeebikeBleManager.getManager().sendMessage(key, type, content);
    }


    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("解锁");
        data.add("通电，电动");
        data.add("通电，助力");
        data.add("通电，人力");
        data.add("通电，强助力");
        data.add("通电，中助力");
        data.add("通电，弱助力");
        data.add("断电");
        data.add("里程清零");
        data.add("关锁");
        data.add("灯光控制，开灯");
        data.add("灯光控制，关灯");
        data.add("USB控制，打开USB充电");
        data.add("USB控制，关闭USB充电");
        data.add("电池锁打开");
        data.add("电池锁关闭");
        data.add("寻车");
        data.add("解锁、通电、里程清零");

        return data;
    }



    /**
     * 显示输入法
     *
     * @param view
     */
    protected void showInputSoftware(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏输入法
     */
    protected void hideInputSoftware(View view) {
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示loading加载框
     */
    public void showLoading(String message) {
        if (message == null || TextUtils.isEmpty(message)) {
            mProgressDialog.setMessage("正在加载...");
        } else {
            mProgressDialog.setMessage(message);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 隐藏loading框
     */
    public void hideLoading() {
        mProgressDialog.dismiss();
    }

    /**
     * toast 消息
     *
     * @param msg
     */
    public void showMessage(final String msg) {
        showMessage(msg, Toast.LENGTH_SHORT);
    }

    public void showMessage(String msg, final int length) {
        if (TextUtils.isEmpty(msg)) return;
        Observable.just(msg).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        synchronized (object) {
                            if (toast != null) {
                                toast.cancel();
                            }
                            toast = Toast.makeText(getApplicationContext(), s, length);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                });

    }

}
