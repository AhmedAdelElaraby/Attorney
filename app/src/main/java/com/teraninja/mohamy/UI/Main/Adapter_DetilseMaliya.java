package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickIdClientMalya;
import com.teraninja.mohamy.Model.DetilseElmalya;
import com.teraninja.mohamy.Model.ELMalyaD;
import com.teraninja.mohamy.Model.TrialDetails;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;
import com.teraninja.mohamy.databinding.ItemDetilseMalayaBinding;

import java.util.ArrayList;


public class Adapter_DetilseMaliya extends RecyclerView.Adapter<Adapter_DetilseMaliya.ViewHolder>{
    ArrayList<DetilseElmalya> list = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemDetilseMalayaBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_detilse_malaya,parent,false);
        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.text.setText(list.get(position).getTrial_details().getTitle());
        holder.recyclerBinding.text2.setText(list.get(position).getTrial_details().getNo_of_trial());
        Adapter_DetilseMaliyaToo maliyaToo = new Adapter_DetilseMaliyaToo();
        LinearLayoutManager manager = new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.VERTICAL,false);
        manager.setReverseLayout(true);
        holder.recyclerBinding.rec.setLayoutManager(manager);
        holder.recyclerBinding.rec.setAdapter(maliyaToo);
        maliyaToo.getList(list.get(position).getFinancial_details());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<DetilseElmalya> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemDetilseMalayaBinding recyclerBinding;


        public ViewHolder(ItemDetilseMalayaBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
