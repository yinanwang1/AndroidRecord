package com.example.arthurwang.helloworld.may;


import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.example.arthurwang.helloworld.R;

import java.nio.charset.Charset;

public class NFCActivity extends Activity implements NfcAdapter.CreateNdefMessageCallback
{
    NfcAdapter nfcAdapter;
    TextView promt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        promt = findViewById(R.id.promt);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (null == nfcAdapter)
        {
            promt.setText("设置不支持NFC！");
//            finish();
            return;
        }

        if (!nfcAdapter.isEnabled())
        {
            promt.setText("请在系统设置中启用NFC功能！");
//            finish();
            return;
        }

        nfcAdapter.setNdefPushMessageCallback(this, this);

        processIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction()))
        {
            processIntent(getIntent());
        }
    }


    private void processIntent(Intent intent)
    {
        if (null == intent) {
            return;
        }

        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        if (null == tagFromIntent)
        {
            return;
        }

        for (String tech: tagFromIntent.getTechList())
        {
            System.out.println(tech);
        }

        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        NdefMessage msg = (NdefMessage) rawMsgs[0];

        promt.setText(new String(msg.getRecords()[0].getPayload()));

    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String text = ("Beam me up, Android!\n\n" + "Beam Time: " + System.currentTimeMillis());

        NdefMessage msg = new NdefMessage(new NdefRecord[]{
                createMimeRecord("application/com.example.arthurwang.helloworld", text.getBytes())
        });

        return msg;
    }

    public NdefRecord createMimeRecord(String mimeType, byte[] payload)
    {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));

        NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);

        return mimeRecord;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);

        processIntent(getIntent());
    }
}
