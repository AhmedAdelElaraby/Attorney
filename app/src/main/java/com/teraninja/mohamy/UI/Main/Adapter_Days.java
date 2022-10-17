package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickIdClientMalya;
import com.teraninja.mohamy.DataClient.OnClickgetDays;
import com.teraninja.mohamy.Model.ELMalyaD;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;
import com.teraninja.mohamy.databinding.ItemDaysBinding;

import java.util.ArrayList;


public class Adapter_Days extends RecyclerView.Adapter<Adapter_Days.ViewHolder>{
    ArrayList<Integer> list = new ArrayList<>();
OnClickgetDays days;
    int selectPosition= 0;

    public Adapter_Days(OnClickgetDays days) {
        this.days = days;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemDaysBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_days,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.days.setText(""+list.get(position));
        int pos=holder.getAdapterPosition();
        selectPosition=pos;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               days.getdays(list.get(position));
                int pos=holder.getAdapterPosition();
                selectPosition=pos;
                notifyDataSetChanged();

            }
        });
        if (selectPosition==position){
            holder.recyclerBinding.days.setBackground(holder.itemView.getResources().getDrawable(R.drawable.item_btn_register));


        }else {
            holder.recyclerBinding.days.setBackground(holder.itemView.getResources().getDrawable(R.drawable.item_filter));


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Integer> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemDaysBinding recyclerBinding;


        public ViewHolder(ItemDaysBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
