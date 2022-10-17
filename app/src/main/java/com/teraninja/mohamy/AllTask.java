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

import com.teraninja.mohamy.DataClient.OnClickgetIdTaseks;
import com.teraninja.mohamy.Model.AllTasks;
import com.teraninja.mohamy.Model.DataAllTask;
import com.teraninja.mohamy.Model.DataSessions;
import com.teraninja.mohamy.Model.Sessionsdata;
import com.teraninja.mohamy.UI.Main.AdapterAllTask;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentAllTaskBinding;

import java.util.ArrayList;


public class AllTask extends Fragment implements OnClickgetIdTaseks {
FragmentAllTaskBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int page=1;
    ArrayList<AllTasks> list = new ArrayList<>();

    public AllTask() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_all_task, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        String branchId =preferences.getString("branchId","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        model.AllTask("Bearer"+Token,page,20, Integer.parseInt(branchId));

        AdapterAllTask allTasks = new AdapterAllTask(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(allTasks);
        binding.nes.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                        page++;
                        binding.progress.setVisibility(View.VISIBLE);
                        model.AllTask("Bearer"+Token,page,20, Integer.parseInt(branchId));
                    }
                else {

                }
            }
        });



        model.AllTask.observe(getViewLifecycleOwner(), new Observer<DataAllTask>() {
            @Override
            public void onChanged(DataAllTask allTask) {
                list.clear();
                for (int i=0;i<allTask.getData().size();i++){
                    list.add(allTask.getData().get(i));
                }
                binding.progress.setVisibility(View.GONE);
                allTasks.getList(list);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void getid(int id) {
        AllTaskDirections.ActionAllTaskToDetilseTases2 task =AllTaskDirections.actionAllTaskToDetilseTases2();
        task.setId(id);
        Navigation.findNavController(binding.getRoot()).navigate(task);

    }
}