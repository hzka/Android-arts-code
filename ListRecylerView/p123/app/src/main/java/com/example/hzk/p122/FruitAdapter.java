package com.example.hzk.p122;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * 新建适配器FruitAdapter类继承自RecyclerView.Adapter，并将泛型指定为FruitAdapter.ViewHolder，其中ViewHolder为内部类。
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    //由于继承自RecyclerView.Adapter，会重写三个方法。
    private List<Fruit> mFruitList;
    //定义内部类ViewHolder，继承自RecyclerView.ViewHolder，传入的参数是RecyclerView子项的最外层布局
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitimage;
        TextView fruitname;
        View fruitview;
        public ViewHolder(View itemView) {
            super(itemView);
            fruitview = itemView;
            //获取布局中TextView和ImageView的实例
            fruitname = (TextView) itemView.findViewById(R.id.fruit_name);
            fruitimage = (ImageView) itemView.findViewById(R.id.fruit_image);
        }
    }
    //将要展示的数据传递给全局变量mFruitList，后序操作都在此的基础之上。
    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }
    //创建ViewHolder实例，并将加载出来的布局fruit_item传递到构造方法中获取实例，最后返回的是ViewHolder实例。
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.fruitview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                Fruit fruit = mFruitList.get(pos);
                Toast.makeText(v.getContext(),"you clicked the view"+fruit.getName(),Toast.LENGTH_LONG).show();
            }
        });
        viewHolder.fruitimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                Fruit fruit = mFruitList.get(pos);
                Toast.makeText(v.getContext(),"you clicked the image"+fruit.getName(),Toast.LENGTH_LONG).show();
            }
        });
        return viewHolder;
    }
    //用于对RecyclerView子项的数据进行赋值，通过get获取position得到当前项的Fruit实例，然后设置到holder的imageview和textview中。
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitimage.setImageResource(fruit.getImageid());
        holder.fruitname.setText(fruit.getName());
    }
    //告诉recylerview有多少子项，直接返回数据源的长度。
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
