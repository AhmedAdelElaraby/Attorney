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

import com.teraninja.mohamy.Model.DataDetilseMaliaya;
import com.teraninja.mohamy.UI.Main.Adapter_DetilseMaliya;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentDetilseMaliyaBinding;


public class DetilseMaliya extends Fragment {
FragmentDetilseMaliyaBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;

    public DetilseMaliya() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_detilse_maliya, container, false);
        preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token", "no");
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
       if (getArguments()!=null){
           DetilseMaliyaArgs args =DetilseMaliyaArgs.fromBundle(getArguments());
           int id=args.getId();
           model.DetilseElmalya("Bearer" + Token,id);
       }
        Adapter_DetilseMaliya maliya = new Adapter_DetilseMaliya();
       binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
       binding.rec.setAdapter(maliya);

      model.DetilseElmalya.observe(getViewLifecycleOwner(), new Observer<DataDetilseMaliaya>() {
          @Override
          public void onChanged(DataDetilseMaliaya dataDetilseMaliaya) {
              maliya.getList(dataDetilseMaliaya.getData());
          }
      });




        return binding.getRoot();

    }
}