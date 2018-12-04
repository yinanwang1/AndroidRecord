package com.example.arthurwang.helloworld.may;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.arthurwang.helloworld.R;

public class GeocodeSearchActivity extends Activity implements View.OnClickListener
{
    Button parseBn, reverseBn;
    EditText etLng, etLat, etAddress, etResult;
    GeocodeSearch search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocode_search);

        parseBn = (Button) findViewById(R.id.parse);
        reverseBn = (Button) findViewById(R.id.reverse);
        etLng = (EditText) findViewById(R.id.lng);
        etLat = (EditText) findViewById(R.id.lat);
        etAddress = (EditText) findViewById(R.id.address);
        etResult = (EditText) findViewById(R.id.result);

        parseBn.setOnClickListener(this);
        reverseBn.setOnClickListener(this);

        search = new GeocodeSearch(this);

        search.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                etResult.setText("经度：" + etLng.getText() + "、经度" + etLat.getText() + "的地址为：\n" + addr.getFormatAddress());
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                GeocodeAddress addr = geocodeResult.getGeocodeAddressList().get(0);
                LatLonPoint latlng = addr.getLatLonPoint();
                etResult.setText(etAddress.getText() + "的经度是：" + latlng.getLongitude() + "、纬度是：" + latlng.getLatitude());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.parse:
            {
                String address = etAddress.getText().toString().trim();

                if (address.equals(""))
                {
                    Toast.makeText(this, "请输入有效的地址", Toast.LENGTH_LONG).show();
                }
                else
                {
                    GeocodeQuery query = new GeocodeQuery(address, "杭州");

                    search.getFromLocationNameAsyn(query);
                }
            }
            break;

            case R.id.reverse:
            {
                String lng = etLng.getText().toString().trim();
                String lat = etLat.getText().toString().trim();

                if (lng.equals("")
                        || lat.equals(""))
                {
                    Toast.makeText(this, "请输入有效的经度、纬度!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    search.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(Double.parseDouble(lat),
                            Double.parseDouble(lng)),
                            20,
                            GeocodeSearch.GPS));
                }
            }
            break;
        }
    }
}
