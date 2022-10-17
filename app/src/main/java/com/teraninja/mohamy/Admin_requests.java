package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.mohamy.Model.AdminRequste;
import com.teraninja.mohamy.Model.DataAdminRequste;
import com.teraninja.mohamy.UI.Main.AdapterOrders;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentAdminRequestsBinding;

import java.util.ArrayList;


public class Admin_requests extends Fragment {
FragmentAdminRequestsBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int page=1;
 ArrayList<AdminRequste> list = new ArrayList<>();

    public Admin_requests() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_admin_requests, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        String branchId =preferences.getString("branchId","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        Toast.makeText(getContext(), ""+branchId, Toast.LENGTH_SHORT).show();
        model.AdminRequste("Bearer"+Token,page,21, Integer.parseInt(branchId));
        AdapterOrders orders = new AdapterOrders();
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.rec.setAdapter(orders);
        binding.nes.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                    page++;
                    binding.progress.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), ""+branchId+page, Toast.LENGTH_SHORT).show();
                    model.AdminRequste("Bearer"+Token,page,23, Integer.parseInt(branchId));
                }
            }
        });



        binding.navcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });



    model.dataAdminRequste.observe(getViewLifecycleOwner(), new Observer<DataAdminRequste>() {
        @Override
        public void onChanged(DataAdminRequste dataAdminRequste) {
            Toast.makeText(getContext(), ""+dataAdminRequste.getMessage(), Toast.LENGTH_SHORT).show();
            for (int i=0;i<dataAdminRequste.getData().size();i++){
                list.add(dataAdminRequste.getData().get(i));
            }
            Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
            binding.progress.setVisibility(View.GONE);
            orders.getList(list);
        }
    });


       return binding.getRoot();
    }
}