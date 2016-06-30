package com.aiseminar.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aiseminar.platerecognizer.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lcc_luffy on 2016/6/30.
 */

public class PicturesAdapter extends PagerAdapter {
    List<String> data;

    public PicturesAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_recognizer, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_pic);
        Glide.with(container.getContext()).load(data.get(position)).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
