package com.fromth.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fromth.R;
import com.fromth.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<Item> list;
    private ItemListener itemListener;



    public RecycleViewAdapter() {
        list = new ArrayList<>();

    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Item getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = list.get(position);
        holder.name.setText(item.getName());
        holder.item1.setText(item.getItem1());
        holder.item2.setText(item.getItem2());
        String a="";
        if((item.getItem3()).contains("1"))
        {
            if(!a.isEmpty())
                a+=",";
           a+="CNTT";
        }
        if((item.getItem3()).contains("2"))
        {
            a+="VT";
        }
        if((item.getItem3()).contains("3"))
        {if(!a.isEmpty())
                a+=",";
            a+="DT";}
        holder.item3.setText(a);
        holder.item4.setText(item.getItem4() );
        //== 1? "Yeu thich":"Khong yeu thich");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name,item1, item2, item3, item4;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            name= view.findViewById(R.id.tvname);
            item1= view.findViewById(R.id.tvItem1);
            item2= view.findViewById(R.id.tvItem2);
            item3= view.findViewById(R.id.tvItem3);
            item4= view.findViewById(R.id.tvItem4);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (itemListener != null)
                itemListener.onItemClick(view, getAdapterPosition() );
        }
    }
    public interface  ItemListener{
        void onItemClick(View view,int position);
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
}


