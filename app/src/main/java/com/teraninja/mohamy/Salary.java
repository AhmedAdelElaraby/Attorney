package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.mohamy.Model.DataHome;
import com.teraninja.mohamy.Model.DataSalary;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentSalaryBinding;

public class Salary extends Fragment {
FragmentSalaryBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    public Salary() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_salary, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        model.Salary("Bearer"+Token);
        model.salary.observe(getViewLifecycleOwner(), new Observer<DataSalary>() {
            @Override
            public void onChanged(DataSalary salary) {
                Toast.makeText(getContext(), ""+salary.getMessage(), Toast.LENGTH_SHORT).show();
               binding.basicslaray.setText(""+salary.getData().getSalary());
                binding.totalallw.setText(""+salary.getData().getAllowances());
                binding.tatalben.setText(""+salary.getData().getBenefits());
                binding.tatoldeduc.setText(""+salary.getData().getDeductions());
                binding.netwage.setText(""+salary.getData().getNet_salary());
            }
        });
        binding.navcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return binding.getRoot();
    }
}