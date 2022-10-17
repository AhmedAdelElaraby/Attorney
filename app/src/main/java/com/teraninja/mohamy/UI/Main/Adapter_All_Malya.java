package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickIdClientMalya;
import com.teraninja.mohamy.Model.AllTasks;
import com.teraninja.mohamy.Model.ELMalyaD;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;

import java.util.ArrayList;


public class Adapter_All_Malya extends RecyclerView.Adapter<Adapter_All_Malya.ViewHolder>{
    ArrayList<ELMalyaD> list = new ArrayList<>();
    OnClickIdClientMalya idClientMalya;

    public Adapter_All_Malya(OnClickIdClientMalya idClientMalya) {
        this.idClientMalya = idClientMalya;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemCusomersBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.item_cusomers,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.Data.setText(""+list.get(position).getId());
        holder.recyclerBinding.typefile.setText(""+list.get(position).getName());
        holder.recyclerBinding.case2.setText(""+list.get(position).getTrials_count());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idClientMalya.getid(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<ELMalyaD> list) {
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
