package com.teraninja.mohamy.UI;

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
import android.widget.Toast;

import com.teraninja.mohamy.Model.DataMalyaFromTrial;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.UI.Main.AdapterMalyaFromTrial;
import com.teraninja.mohamy.databinding.FragmentMalyaFromTrialBinding;


public class FragmentMalyaFromTrial extends Fragment {
FragmentMalyaFromTrialBinding binding;
    String id;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;

    public FragmentMalyaFromTrial() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_malya_from_trial, container, false);
        preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token", "no");
        id = preferences.getString("id", "no");
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
        if (id != "no") {
            model.MalyaFromTrial("Bearer" + Token, Integer.parseInt(id));
            Toast.makeText(getContext(), "" + id, Toast.LENGTH_SHORT).show();

        } else {

        }
        AdapterMalyaFromTrial trial = new AdapterMalyaFromTrial();
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(trial);
        model.MalyaFromTrial.observe(getViewLifecycleOwner(), new Observer<DataMalyaFromTrial>() {
            @Override
            public void onChanged(DataMalyaFromTrial dataMalyaFromTrial) {
                trial.getList(dataMalyaFromTrial.getData());
            }
        });
              return binding.getRoot();

    }
}