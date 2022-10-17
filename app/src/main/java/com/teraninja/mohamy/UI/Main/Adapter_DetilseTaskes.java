package com.teraninja.mohamy.UI.Main;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickIdClientMalya;
import com.teraninja.mohamy.DataClient.OnClickgetDetilse;
import com.teraninja.mohamy.Model.DetilseTaskes;
import com.teraninja.mohamy.Model.ELMalyaD;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;
import com.teraninja.mohamy.databinding.ItemDetilseTaskesBinding;

import java.util.ArrayList;


public class Adapter_DetilseTaskes extends RecyclerView.Adapter<Adapter_DetilseTaskes.ViewHolder>{
    ArrayList<DetilseTaskes> list = new ArrayList<>();
    OnClickgetDetilse detilse;

    public Adapter_DetilseTaskes(OnClickgetDetilse detilse) {
        this.detilse = detilse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemDetilseTaskesBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_detilse_taskes,parent,false);
        return new ViewHolder(bining1);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.draset.setText(""+list.get(position).getType().getName());
        holder.recyclerBinding.detilse.setText(""+list.get(position).getDepartment().getName());
        holder.recyclerBinding.decrpshion.setText(""+list.get(position).getDescritpion());
        holder.recyclerBinding.hjray.setText(""+list.get(position).getHijri_end());
        holder.recyclerBinding.mladey.setText(""+list.get(position).getEnd_date());
        holder.recyclerBinding.cases.setText(""+list.get(position).getView_type());
        holder.recyclerBinding.Admin.setText(""+list.get(position).getEmployee().getName());
        holder.recyclerBinding.posation.setText(list.get(position).getAssigned().getName());
        holder.recyclerBinding.card.setCardBackgroundColor(Color.parseColor(list.get(position).
                department.getColor_code()));
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String i= String.valueOf(list.get(position).getType().getName());
        if (i.equals("جلسة")){
            int id=list.get(position).getSitting().getId();

            detilse.getDetils(id);
        }else {

        }

    }
});

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DetilseTaskes> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemDetilseTaskesBinding recyclerBinding;


        public ViewHolder(ItemDetilseTaskesBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
