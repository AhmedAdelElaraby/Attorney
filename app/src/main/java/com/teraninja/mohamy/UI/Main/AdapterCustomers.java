package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickItemClinet;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;
import com.teraninja.mohamy.databinding.ItemOrdersBinding;
import com.teraninja.mohamy.databinding.ItemSalaryBinding;

import java.util.ArrayList;


public class AdapterCustomers extends RecyclerView.Adapter<AdapterCustomers.ViewHolder>{
    ArrayList<Custmers> list = new ArrayList<>();
    OnClickItemClinet onClickInfo;

    public AdapterCustomers(OnClickItemClinet onClickInfo) {
        this.onClickInfo = onClickInfo;
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
        holder.recyclerBinding.typefile.setText(list.get(position).getName());
        holder.recyclerBinding.case2.setText(""+list.get(position).getTrials_count());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickInfo.OnClickInfo(list.get(position).getId(),list.get(position).getName(),
                        list.get(position).getMobile(),
                        list.get(position).getEmail());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<Custmers> list) {
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
