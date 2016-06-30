package com.aiseminar.platerecognizer.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.aiseminar.adapter.RecyclerAdapter;
import com.aiseminar.platerecognizer.R;
import com.aiseminar.platerecognizer.base.BaseActivity;
import com.aiseminar.util.EasyPr;

import java.util.ArrayList;

import butterknife.Bind;

public class RecognizerActivity extends BaseActivity {

    @Bind(R.id.viewPager)
    RecyclerView viewPager;

    RecyclerAdapter adapter;
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("识别结果");


        data = (ArrayList<String>) getIntent().getSerializableExtra("data");
        adapter = new RecyclerAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        viewPager.setLayoutManager(layoutManager);
        viewPager.setAdapter(adapter);
        recognize();
    }

    private void recognize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < data.size(); i++) {
                    final String plate = EasyPr.get().recognize(data.get(i));
                    final int finalI = i;
                    viewPager.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.putPlate(finalI, plate);
                        }
                    });
                }
            }
        }).start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recognizer;
    }
}
