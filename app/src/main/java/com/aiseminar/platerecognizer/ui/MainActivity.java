package com.aiseminar.platerecognizer.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.aiseminar.platerecognizer.R;
import com.aiseminar.platerecognizer.base.BaseActivity;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.define.Define;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.fab_camera)
    FloatingActionButton fab_camera;

    @Bind(R.id.btn_pictures)
    Button btn_pictures;

    @Bind(R.id.old_camera)
    Button old_camera;

    @Bind(R.id.shimmer_tv)
    ShimmerTextView shimmer_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void setListeners() {
        old_camera.setVisibility(View.GONE);
        fab_camera.setOnClickListener(this);
        btn_pictures.setOnClickListener(this);
        old_camera.setOnClickListener(this);

        Shimmer shimmer = new Shimmer();
        shimmer.start(shimmer_tv);
    }


    private static final int RequestCodeOfCamera = 8660;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_camera:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    toast("没有相机权限");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, RequestCodeOfCamera);
                    return;
                }
                camera();
                break;
            case R.id.btn_pictures:
                FishBun.with(this)
                        .setPickerCount(66)
                        .setRequestCode(Define.ALBUM_REQUEST_CODE)
                        .startAlbum();

                break;
            case R.id.old_camera:
                startActivity(new Intent(this, CameraActivity.class));
                break;
        }
    }

    private void camera() {
        startActivity(new Intent(this, CarCameraActivity.class));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Define.ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    ArrayList<String> path = data.getStringArrayListExtra(Define.INTENT_PATH);
                    Intent intent = new Intent(this, RecognizerActivity.class);
                    intent.putExtra("data", path);
                    startActivity(intent);
                }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RequestCodeOfCamera) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                camera();
            } else {
                toast("竟然拒绝权限");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
