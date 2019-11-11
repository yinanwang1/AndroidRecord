package com.example.arthurwang.helloworld.aug;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arthurwang.helloworld.R;
import com.socks.library.KLog;

import java.io.ByteArrayOutputStream;

public class AugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aug);

        int tst =  Integer.parseInt("FE", 16);

        KLog.e("wyn", "tst is " + tst);

        test();
    }

    private void test() {
        String string = "wyn";
        byte str[] =  string.getBytes();
        byte output[] = new byte[string.length() * 3 + 1];

        byteToHex(str, output, str.length);

        KLog.e("wyn", "output is " + output.toString());

        byte otherOutPut[] = new byte[string.length()];

        hexToByte(output, otherOutPut, output.length);

        KLog.e("wyn", "otherOutPut is " + otherOutPut.toString());
    }

    private String toHex(String str) {
        String hexString = "0123456789ABCDEF";
        byte[] bytes = str.getBytes();

        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for(int i = 0; i < bytes.length; i++)
        {
            hex.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            hex.append(hexString.charAt(bytes[i] & 0x0f));
            hex.append(' ');
        }

        return hex.toString();
    }

    public static String decode(String bytes) {
        String hexString = "0123456789ABCDEF";
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        for (int i = 0; i < bytes.length(); i+=3)
        {
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        }

        return new String(baos.toByteArray());
    }

    // byte 转为16机制
    public void byteToHex(byte inPut[], byte outPut[], int len)
    {
        int i = len, j = 0, tmp;
        for (i = 0; i < len; i++)
        {
            tmp = inPut[i];
            switch ((tmp >> 4) & 0x0f)
            {
                case 0: outPut[j++] = '0';break;
                case 1: outPut[j++] = '1';break;
                case 2: outPut[j++] = '2';break;
                case 3: outPut[j++] = '3';break;
                case 4: outPut[j++] = '4';break;
                case 5: outPut[j++] = '5';break;
                case 6: outPut[j++] = '6';break;
                case 7: outPut[j++] = '7';break;
                case 8: outPut[j++] = '8';break;
                case 9: outPut[j++] = '9';break;
                case 10: outPut[j++] = 'A';break;
                case 11: outPut[j++] = 'B';break;
                case 12: outPut[j++] = 'C';break;
                case 13: outPut[j++] = 'D';break;
                case 14: outPut[j++] = 'E';break;
                case 15: outPut[j++] = 'F';break;
            }

            switch (tmp & 0x0f)
            {
                case 0: outPut[j++] = '0';break;
                case 1: outPut[j++] = '1';break;
                case 2: outPut[j++] = '2';break;
                case 3: outPut[j++] = '3';break;
                case 4: outPut[j++] = '4';break;
                case 5: outPut[j++] = '5';break;
                case 6: outPut[j++] = '6';break;
                case 7: outPut[j++] = '7';break;
                case 8: outPut[j++] = '8';break;
                case 9: outPut[j++] = '9';break;
                case 10: outPut[j++] = 'A';break;
                case 11: outPut[j++] = 'B';break;
                case 12: outPut[j++] = 'C';break;
                case 13: outPut[j++] = 'D';break;
                case 14: outPut[j++] = 'E';break;
                case 15: outPut[j++] = 'F';break;
            }

            outPut[j++] = ' ';
        }

        outPut[j] = '\0';

    }

    public static void hexToByte(byte input[], byte outPut[], int len)
    {
        int i, j =0;
        for (i = 0; i < len; i++)
        {
            if (input[i] == '\0')
            {
                break;
            }

            if (input[i] != ' ')
            {
                outPut[j] = (byte)(input[i] * 16 + input[++i]);
            } else {
                j++;
            }
        }

    }

}
