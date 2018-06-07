package com.moon.moonhome;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndPermission
                .with(this)
                .runtime()
                .permission(Manifest.permission.CAMERA)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Log.d(TAG, "onGranted");
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Log.d(TAG, "onDenied");
                        if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, Manifest.permission.CAMERA)) {
                            AndPermission
                                    .with(MainActivity.this)
                                    .runtime()
                                    .permission(Manifest.permission.CAMERA)
                                    .rationale(new RuntimeRationale())
                                    .onGranted(new Action<List<String>>() {
                                        @Override
                                        public void onAction(List<String> data) {
                                            Log.d(TAG, "onGranted111");
                                        }
                                    })
                                    .onDenied(new Action<List<String>>() {
                                        @Override
                                        public void onAction(List<String> data) {
                                            Log.d(TAG, "onDenied111");
                                        }
                                    })
                                    .start();
                        }
                    }
                })
                .start();
    }
}