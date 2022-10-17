package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.Model.FinancialDetail;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;
import com.teraninja.mohamy.databinding.ItemDetilseMalayaBinding;
import com.teraninja.mohamy.databinding.ItemMalyaTooBinding;

import java.util.ArrayList;


public class Adapter_DetilseMaliyaToo extends RecyclerView.Adapter<Adapter_DetilseMaliyaToo.ViewHolder>{
    ArrayList<FinancialDetail> list = new ArrayList<>();




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemMalyaTooBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_malya_too,parent,false);
        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.Data.setText(""+list.get(position).getCreditor());
        holder.recyclerBinding.typefile.setText(""+list.get(position).getDebtor());
        holder.recyclerBinding.case2.setText(""+list.get(position).getNet_wege());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<FinancialDetail> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemMalyaTooBinding recyclerBinding;


        public ViewHolder(ItemMalyaTooBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
