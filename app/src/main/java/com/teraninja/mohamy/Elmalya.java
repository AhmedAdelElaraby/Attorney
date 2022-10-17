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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.mohamy.DataClient.OnClickIdClientMalya;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.Model.DataCustmers;
import com.teraninja.mohamy.Model.DataELMalaya;
import com.teraninja.mohamy.Model.ELMalyaD;
import com.teraninja.mohamy.UI.Main.Adapter_All_Malya;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentElmalyaBinding;

import java.util.ArrayList;


public class Elmalya extends Fragment implements OnClickIdClientMalya {
    FragmentElmalyaBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int page = 1;
    ArrayList<ELMalyaD> list = new ArrayList<>();

    public Elmalya() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_elmalya, container, false);
        preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token", "no");
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
        model.Elmalya("Bearer" + Token, page, 20);
        Adapter_All_Malya Elmalya = new Adapter_All_Malya(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rec.setAdapter(Elmalya);

        binding.nes.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    page++;
                    binding.progress.setVisibility(View.VISIBLE);
                    model.Elmalya("Bearer" + Token, page, 20);
                } else {

                }
            }
        });


        model.Elmalya.observe(getViewLifecycleOwner(), new Observer<DataELMalaya>() {
            @Override
            public void onChanged(DataELMalaya dataELMalaya) {
                for (int i = 0; i < dataELMalaya.getData().size(); i++) {
                    list.add(dataELMalaya.getData().get(i));
                }
                binding.progress.setVisibility(View.GONE);
                Elmalya.getList(list);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void getid(int id) {
        ElmalyaDirections.ActionElmalyaToDetilseMaliya elmalya = ElmalyaDirections.actionElmalyaToDetilseMaliya();
        elmalya.setId(id);
        Navigation.findNavController(binding.getRoot()).navigate(elmalya);
    }
}