package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.mohamy.Model.CurrentConsulting;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.Model.DataConsultingsModel;
import com.teraninja.mohamy.Model.lastConsulting;
import com.teraninja.mohamy.UI.Main.AdapterConsultingsCurrent;
import com.teraninja.mohamy.UI.Main.AdapterConsultingslast;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentConsultingsBinding;
import com.teraninja.mohamy.databinding.FragmentCustomersBinding;

import java.util.ArrayList;


public class Consultings extends Fragment {
FragmentConsultingsBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    ArrayList<lastConsulting> lastConsulting = new ArrayList<>();
    ArrayList<CurrentConsulting> CurrentConsulting= new ArrayList<>();
    public Consultings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_consultings, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        model.getallConsulings("Bearer"+Token);
        AdapterConsultingslast consultingslast = new AdapterConsultingslast();
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(consultingslast);
        AdapterConsultingsCurrent current = new AdapterConsultingsCurrent();
        binding.rec2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec2.setAdapter(current);

       binding.DataType.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
               binding.case3.setBackground(getResources().getDrawable(R.drawable.item_filter));
               binding.rec2.setVisibility(View.VISIBLE);
               binding.rec.setVisibility(View.GONE);

               current.getList(CurrentConsulting);
           }
       });
        binding.case3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rec.setVisibility(View.VISIBLE);
                binding.rec2.setVisibility(View.GONE);
                binding.case3.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_filter));
                consultingslast.getList(lastConsulting);
            }
        });
model.getallConsulings.observe(getViewLifecycleOwner(), new Observer<DataConsultingsModel>() {
    @Override
    public void onChanged(DataConsultingsModel dataConsultingsModel) {
        if (dataConsultingsModel.getStatus()==1){
            current.getList(dataConsultingsModel.getData().current_consultings);

            for (int i=0;i<dataConsultingsModel.getData().getLast_consultings().size();i++) {
           lastConsulting.add(dataConsultingsModel.getData().getLast_consultings().get(i));
            }
            for (int i=0;i<dataConsultingsModel.getData().getCurrent_consultings().size();i++) {
                CurrentConsulting.add(dataConsultingsModel.getData().getCurrent_consultings().get(i));
            }

        }
    }
});
             return binding.getRoot();
    }
}