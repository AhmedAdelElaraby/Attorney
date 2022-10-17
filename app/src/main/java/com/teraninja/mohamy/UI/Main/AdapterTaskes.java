package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.teraninja.mohamy.DataClient.OnClickgetIdTasksHome;
import com.teraninja.mohamy.Model.Tasks;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemtaskesBinding;

import java.util.ArrayList;


public class AdapterTaskes extends RecyclerView.Adapter<AdapterTaskes.ViewHolder>{
    ArrayList<Tasks> list = new ArrayList<>();
    OnClickgetIdTasksHome home;

    public AdapterTaskes(OnClickgetIdTasksHome home) {
        this.home = home;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemtaskesBinding bining1 = DataBindingUtil.inflate(inflater, R.layout.itemtaskes,parent,false);

        return new ViewHolder(bining1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerBinding.data.setText(list.get(position).getStart_date());
        holder.recyclerBinding.name.setText(list.get(position).getTag().getName());
        holder.recyclerBinding.image.setImageResource(R.drawable.backlog);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.getId(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Tasks> list) {
        this.list=list;
        notifyDataSetChanged();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ItemtaskesBinding recyclerBinding;


        public ViewHolder(ItemtaskesBinding binding) {
            super(binding.getRoot());
            this.recyclerBinding=binding;
        }
    }
}
