package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test1.object.Fruit;

import java.util.List;

/**
 * Created by Administrator on 2018/2/26.
 *
 *  自定义适配器 FruitAdapter
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resurceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context,textViewResourceId,objects);
        resurceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        //ListView加载显示性能优化
        View view;
        //对控件的实例进行缓存
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resurceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitText = (TextView) view.findViewById(R.id.fruit_text);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitText.setText(fruit.getName());
        return view;
    }

    //内部类 viewHolder
    class ViewHolder {

        ImageView fruitImage;

        TextView fruitText;
    }
}
