package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickId;
import com.teraninja.mohamy.DataClient.OnClickItemClinet;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.Model.DataSessions;
import com.teraninja.mohamy.Model.Sessionsdata;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;

import java.util.ArrayList;


public class AdapterSessions extends RecyclerView.Adapter<AdapterSessions.ViewHolder>{
    ArrayList<Sessionsdata> list = new ArrayList<>();

OnClickId onClickId;

    public AdapterSessions(OnClickId onClickId) {
        this.onClickId = onClickId;
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
        holder.recyclerBinding.Data.setText(""+list.get(position).getDate()+""+list.get(position).getTime()+""+list.get(position).getHijri());
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

    public void getList(ArrayList<Sessionsdata> list) {
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
