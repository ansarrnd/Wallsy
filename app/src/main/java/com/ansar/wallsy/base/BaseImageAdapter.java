package com.ansar.wallsy.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ansar.wallsy.R;
import com.ansar.wallsy.data.Image;
import com.ansar.wallsy.data.glide.GlideApp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseImageAdapter extends RecyclerView.Adapter<BaseImageAdapter.ViewHolder> {

    private List<Image> images;
    private int itemSize;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public BaseImageAdapter(List<Image> images, int itemSize){
        this.images = images;
        this.itemSize = itemSize;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Image image = images.get(position);
        GlideApp
                .with(holder.image.getContext())
                .load(image.thumbURL())
                .centerCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return itemSize;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        View view;

        ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.image);
            view = itemView.findViewById(R.id.view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
