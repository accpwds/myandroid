package com.example.android.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.android.R;
import com.example.android.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/30.
 */

public class BaiduLBSActivity extends BaseActivity {

    private LocationClient mLocationClient;

    private TextView positionText;

    private MapView mapView;

    private BaiduMap baiduMap;

    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MylocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidulbs);
        positionText = (TextView) findViewById(R.id.position_text_view);
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        List<String> pressmionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(BaiduLBSActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            pressmionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(BaiduLBSActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            pressmionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(BaiduLBSActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            pressmionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!pressmionList.isEmpty()){
            String[] permission = pressmionList.toArray(new String[pressmionList.size()]);
            ActivityCompat.requestPermissions(BaiduLBSActivity.this,permission,1);
        } else {
            requestLocation();
        }
    }

    private void requestLocation(){
        initLoaction();
        mLocationClient.start();
    }

    private void initLoaction(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(30000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(BaiduLBSActivity.this,"必须同意所有权限才能使用此程序",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                }
                break;
            default:
        }
    }

    public class MylocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            /*StringBuilder sb = new StringBuilder();
            sb.append("纬度：").append(bdLocation.getLatitude()).append("\n");
            sb.append("经度：").append(bdLocation.getLongitude()).append("\n");
            sb.append("国家：").append(bdLocation.getCountry()).append("\n");
            sb.append("省：").append(bdLocation.getProvince()).append("\n");
            sb.append("市：").append(bdLocation.getCity()).append("\n");
            sb.append("区：").append(bdLocation.getDistrict()).append("\n");
            sb.append("街道：").append(bdLocation.getStreet()).append("\n");
            sb.append("定位方式：");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                sb.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                sb.append("网络");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation){
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError){
                sb.append("服务端网络定位失败");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException){
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException){
                sb.append("无法获取有效定位依据导致定位失败");
            } else {
                sb.append(location.getLocType());    //这里打印出来location.getLocType()是162
                sb.append("老子也不知道是啥原因！！！");
            }
            positionText.setText(sb);*/

            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation ||
                    bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
                LogUtil.d("BaiduLBS","come here");
                navigateTo(bdLocation);
            }
        }
    }

    public void navigateTo(BDLocation location){

        if (isFirstLocate){
            LogUtil.d("BaiduLBS","come here1");
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            LogUtil.d("BaiduLBS"," " + location.getLatitude() + "  " + location.getLongitude() + " "
            + location.getCity());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
}
