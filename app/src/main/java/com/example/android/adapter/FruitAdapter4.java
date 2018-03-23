package com.example.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.R;
import com.example.android.activity.FruitActivity5;
import com.example.android.object.Fruit;

import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */

public class FruitAdapter4 extends RecyclerView.Adapter<FruitAdapter4.ViewHolder> {

    private Context mContext;

    private List<Fruit> mFruitList;

    public FruitAdapter4(List<Fruit> fruitList) {

        mFruitList = fruitList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView fruitImage;
        TextView fruitText;
        //View fruitView;

        public ViewHolder(View view) {
            super(view);
            //fruitView = view;
            cardView = (CardView) view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitText = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item3, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = viewHolder.getAdapterPosition();
                Fruit fruit = mFruitList.get(postion);
                Intent intent = new Intent(mContext, FruitActivity5.class);
                intent.putExtra(FruitActivity5.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity5.FRUIT_IMAGE_ID,fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitText.setText(fruit.getName());
        holder.fruitImage.setImageResource(fruit.getImageId());
        //holder.fruitText.setText(fruit.getContent());
        //加载图片后，将图片设置到对应的图片位置
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {

        return mFruitList.size();
    }
}
