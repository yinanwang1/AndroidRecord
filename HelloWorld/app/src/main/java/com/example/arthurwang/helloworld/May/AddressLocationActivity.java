package com.example.arthurwang.helloworld.may;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.arthurwang.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class AddressLocationActivity extends Activity implements View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener, RouteSearch.OnRouteSearchListener
{
    private MapView mapView;
    private AMap aMap;
    private LocationManager locMgr;
    GeocodeSearch search;
    RouteSearch routeSearch;
    private EditText targetAddressEt;
    private Button navBn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_location);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        init();

        navBn = (Button) findViewById(R.id.loc);
        targetAddressEt = (EditText) findViewById(R.id.address);

        navBn.setOnClickListener(this);

        search = new GeocodeSearch(this);
        search.setOnGeocodeSearchListener(this);

        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);

        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try
        {
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    300,
                    8,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            updatePosition(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                            try
                            {
                                updatePosition(locMgr.getLastKnownLocation(provider));
                            }
                            catch (SecurityException e)
                            {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    });
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    private void init()
    {
        if (null == aMap)
        {
            aMap = mapView.getMap();

            CameraUpdate cu = CameraUpdateFactory.zoomTo(16);

            aMap.moveCamera(cu);
        }
    }

    private void updatePosition(Location location)
    {
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cu = CameraUpdateFactory.changeLatLng(pos);
        aMap.moveCamera(cu);

        aMap.clear();

        MarkerOptions markerOptions = new MarkerOptions()
                .position(pos)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car))
                .draggable(true);

        aMap.addMarker(markerOptions);
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i)
    {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i)
    {
        Log.e("test", "search returned result.");

        GeocodeAddress addr = geocodeResult.getGeocodeAddressList().get(0);
        LatLonPoint latlng = addr.getLatLonPoint();
        try {
            Location loc = locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            RouteSearch.FromAndTo ft = new RouteSearch.FromAndTo(new LatLonPoint(loc.getLatitude(), loc.getLongitude()), latlng);

            RouteSearch.DriveRouteQuery driveRouteQuery = new RouteSearch
                    .DriveRouteQuery(ft,
                    RouteSearch.DrivingDefault,
                    null,
                    null,
                    null);
            routeSearch.calculateDriveRouteAsyn(driveRouteQuery);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }



    }

    @Override
    public void onClick(View v)
    {
        String address = targetAddressEt.getText().toString().trim();

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

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i)
    {
        DrivePath drivePath = driveRouteResult.getPaths().get(0);

        List<DriveStep> steps = drivePath.getSteps();

        for (DriveStep step: steps)
        {
            List<LatLonPoint> points = step.getPolyline();
            List<LatLng> latLngs = new ArrayList<>();
            for (LatLonPoint point: points)
            {
                latLngs.add(new LatLng(point.getLatitude(), point.getLongitude()));
            }

            PolylineOptions ployOptions = new PolylineOptions()
                    .addAll(latLngs)
                    .color(0xffff0000)
                    .width(8);

            aMap.addPolyline(ployOptions);
        }

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
