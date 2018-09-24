package com.example.yahor.fitnessclub.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yahor.fitnessclub.Interface.ItemClickListener;
import com.example.yahor.fitnessclub.R;

public class TrainerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView trainer_name;
    public ImageView trainer_image;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public TrainerViewHolder(View itemView) {
        super(itemView);

        trainer_name = (TextView) itemView.findViewById(R.id.trainer_name);
        trainer_image = (ImageView) itemView.findViewById(R.id.trainer_image);


        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
