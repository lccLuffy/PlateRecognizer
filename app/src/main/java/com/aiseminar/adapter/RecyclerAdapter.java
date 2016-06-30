package com.aiseminar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aiseminar.platerecognizer.R;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcc_luffy on 2016/6/30.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    private List<String> pictures;
    private Map<Integer, String> plates;

    public RecyclerAdapter(List<String> data) {
        this.pictures = data;
        plates = new HashMap<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recognizer, parent, false));
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(position);
    }

    public void putPlate(int position, String plate) {
        plates.put(position, plate);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        ProgressBar progressBar;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_pic);
            textView = (TextView) itemView.findViewById(R.id.tv_plate);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
        }

        void bind(int pos) {
            Glide.with(itemView.getContext())
                    .load(pictures.get(pos))
                    .into(imageView);
            if (plates.containsKey(pos)) {
                progressBar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                String plate = plates.get(pos);
                if (plate != null && !"0".equals(plate)) {
                    textView.setText(plates.get(pos));
                } else {
                    textView.setText("None");
                }
            } else {
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }

        }
    }
}
