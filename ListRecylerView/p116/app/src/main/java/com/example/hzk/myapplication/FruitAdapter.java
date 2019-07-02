package com.example.hzk.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 新建适配器继承自ArrayAdapter，将泛型指定为Fruit类。
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    public FruitAdapter(@NonNull Context context, @LayoutRes int resource,List<Fruit> objects) {
        super(context, resource,objects);
        this.resourceId = resource;
    }
    //在每个子项被滚动到屏幕内的时候被调用
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);//获取当前项的Fruit实例
        //使用LayoutInflater来为这个子项加载我们的布局，第三个为false意思是只让我们在父布局中声明的layout属性生效，但不为View添加父布局。
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitname = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        //设置显示的图片和文字
        viewHolder.fruitImage.setImageResource(fruit.getImageid());
        viewHolder.fruitname.setText(fruit.getName());
        return view;
    }

    private class ViewHolder {
        ImageView fruitImage;
        TextView fruitname;
    }
}
