package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.Model.AllTasks;
import com.teraninja.mohamy.Model.MalyaFromTrial;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;

import java.util.ArrayList;


public class AdapterMalyaFromTrial extends RecyclerView.Adapter<AdapterMalyaFromTrial.ViewHolder>{
    ArrayList<MalyaFromTrial> list = new ArrayList<>();




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemCusomersBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_cusomers,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.Data.setText(""+list.get(position).getCreditor());
        holder.recyclerBinding.typefile.setText(""+list.get(position).getDebtor());
        holder.recyclerBinding.case2.setText(""+list.get(position).getNet_wege());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<MalyaFromTrial> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemCusomersBinding recyclerBinding;


        public ViewHolder(ItemCusomersBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
