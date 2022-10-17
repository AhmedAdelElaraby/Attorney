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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.mohamy.Model.AdminRequste;
import com.teraninja.mohamy.Model.DataShowDetilslssues;
import com.teraninja.mohamy.Model.DetilseLssues;
import com.teraninja.mohamy.UI.Main.AdapterViewCustomerIssues;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentViewCustomerIssuesBinding;

import java.util.ArrayList;


public class ViewCustomerIssues extends Fragment {
FragmentViewCustomerIssuesBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int page=1;
    ArrayList<DetilseLssues> list = new ArrayList<>();
    int id;
    public ViewCustomerIssues() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_view_customer_issues, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        if (getArguments()!=null){
            ViewCustomerIssuesArgs args =ViewCustomerIssuesArgs.fromBundle(getArguments());
            id=args.getClientId();
            model.DetilsLsses("Bearer"+Token,id,page,20);
        }
        AdapterViewCustomerIssues issues = new AdapterViewCustomerIssues();
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(issues);

        binding.nes.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                    page++;
                    binding.progress.setVisibility(View.VISIBLE);
                    model.DetilsLsses("Bearer"+Token,id,page,20);
                }
            }
        });

        model.DetilsLsses.observe(getViewLifecycleOwner(), new Observer<DataShowDetilslssues>() {
            @Override
            public void onChanged(DataShowDetilslssues dataShowDetilslssues) {

                for (int i=0;i<dataShowDetilslssues.getData().size();i++){
                    list.add(dataShowDetilslssues.getData().get(i));

                }
                Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
                binding.progress.setVisibility(View.GONE);
                issues.getList(list);
            }
        });
        return binding.getRoot();
    }
}