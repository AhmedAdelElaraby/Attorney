package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.Model.AdminRequste;
import com.teraninja.mohamy.Model.DetilseLssues;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemOrdersBinding;

import java.util.ArrayList;


public class AdapterViewCustomerIssues extends RecyclerView.Adapter<AdapterViewCustomerIssues.ViewHolder>{
    ArrayList<DetilseLssues> list = new ArrayList<>();



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemOrdersBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_orders,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.recyclerBinding.Data.setText(""+list.get(position).getTrial_code());
    holder.recyclerBinding.typefile.setText(""+list.get(position).getClient().getName());
    holder.recyclerBinding.case2.setText(""+list.get(position).getTitle());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DetilseLssues> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemOrdersBinding recyclerBinding;


        public ViewHolder(ItemOrdersBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
