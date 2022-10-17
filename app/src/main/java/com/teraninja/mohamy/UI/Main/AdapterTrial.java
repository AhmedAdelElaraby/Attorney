package com.teraninja.mohamy.UI.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.teraninja.mohamy.DataClient.OnClickItemClinet;
import com.teraninja.mohamy.DataClient.OnClick_get_Trial_Id;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.Model.TrialData;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.ItemCusomersBinding;

import java.util.ArrayList;


public class AdapterTrial extends RecyclerView.Adapter<AdapterTrial.ViewHolder>{
    ArrayList<TrialData> list = new ArrayList<>();
    OnClick_get_Trial_Id trial_id;

    public AdapterTrial(OnClick_get_Trial_Id trial_id) {
        this.trial_id = trial_id;
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
        holder.recyclerBinding.case2.setText(list.get(position).getTitle());
        holder.recyclerBinding.typefile.setText(""+list.get(position).getClient().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trial_id.getTrialId(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getList(ArrayList<TrialData> list) {
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
