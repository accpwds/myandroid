package com.example.test1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */

public class FruitAdapter2 extends RecyclerView.Adapter<FruitAdapter2.ViewHolder> {

    private List<Fruit> mFruitList;

    public FruitAdapter2(List<Fruit> fruitList) {

        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = viewHolder.getAdapterPosition();
                Fruit fruit = mFruitList.get(postion);
                Toast.makeText(v.getContext(),"you clicked view " + fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = viewHolder.getAdapterPosition();
                Fruit fruit = mFruitList.get(postion);
                Toast.makeText(v.getContext(),"you clicked image " + fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitText.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView fruitImage;
        TextView fruitText;
        View fruitView;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitText = (TextView) view.findViewById(R.id.fruit_text);
        }
    }
}
