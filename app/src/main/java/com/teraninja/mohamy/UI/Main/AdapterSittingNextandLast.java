package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickId;
import com.teraninja.mohamy.Model.LastSiting;
import com.teraninja.mohamy.Model.nextsitings;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;
import com.teraninja.mohamy.databinding.ItemlastandnextBinding;

import java.util.ArrayList;


public class AdapterSittingNextandLast extends RecyclerView.Adapter<AdapterSittingNextandLast.ViewHolder>{
    ArrayList<nextsitings> list = new ArrayList<>();

    OnClickId onClickId;

    public AdapterSittingNextandLast(OnClickId onClickId) {
        this.onClickId = onClickId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemlastandnextBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemlastandnext,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.Data.setText(""+list.get(position).getDate()+""+list.get(position).getHijri()+""+list.get(position).getTime());
        holder.recyclerBinding.typefile.setText(list.get(position).getCircle_name());
        holder.recyclerBinding.case2.setText(""+list.get(position).getCourt_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickId.getId(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<nextsitings> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemlastandnextBinding recyclerBinding;


        public ViewHolder(ItemlastandnextBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
