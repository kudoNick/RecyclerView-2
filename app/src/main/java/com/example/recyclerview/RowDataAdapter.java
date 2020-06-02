package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RowDataAdapter extends RecyclerView.Adapter<RowDataAdapter.ViewHolder> {

    Context context;
    List<Img> imgList;

    public RowDataAdapter(Context context, List<Img> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_img,parent,false);
        System.out.println("tvName 1");
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Img img = imgList.get(position);

        Picasso.get().load(img.getImg()).into(holder.imageView);
        System.out.println("img 3");
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgImage);
        }
    }
}
