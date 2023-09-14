package com.demo.demo_volley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Responseobject> responseobjects;
    MainActivity activity;

    public Adapter(List<Responseobject> responseobjects, MainActivity activity) {
        this.responseobjects = responseobjects;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater iflater =LayoutInflater.from(activity);
        View view =iflater.inflate(R.layout.iteam_main,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.txt.setText(responseobjects.get(position).getTitle());
        Glide.with(activity)
                .load(responseobjects.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.view);
    }

    @Override
    public int getItemCount() {
        return responseobjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt;
        ImageView view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txtid);
            view = itemView.findViewById(R.id.imgid);

        }
    }
}
