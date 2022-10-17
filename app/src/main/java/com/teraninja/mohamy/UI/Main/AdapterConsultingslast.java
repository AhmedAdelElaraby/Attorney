package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickId;
import com.teraninja.mohamy.Model.LastSiting;
import com.teraninja.mohamy.Model.lastConsulting;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemConsultingsBinding;
import com.teraninja.mohamy.databinding.ItemlastandnextBinding;

import java.util.ArrayList;


public class AdapterConsultingslast extends RecyclerView.Adapter<AdapterConsultingslast.ViewHolder>{
    ArrayList<lastConsulting> list = new ArrayList<>();




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemConsultingsBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_consultings,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.data.setText(""+list.get(position).getCreated_at());
        holder.recyclerBinding.tiltil.setText(""+list.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<lastConsulting> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemConsultingsBinding recyclerBinding;


        public ViewHolder(ItemConsultingsBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
