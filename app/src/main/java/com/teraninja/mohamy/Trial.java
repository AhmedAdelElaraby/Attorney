package com.teraninja.mohamy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.mohamy.DataClient.OnClick_get_Trial_Id;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.Model.DataTrial;
import com.teraninja.mohamy.Model.SendDataSessions;
import com.teraninja.mohamy.Model.SendDataTrial;
import com.teraninja.mohamy.Model.TrialData;
import com.teraninja.mohamy.UI.Main.AdapterTrial;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentTrialBinding;

import java.util.ArrayList;


public class Trial extends Fragment implements OnClick_get_Trial_Id {
FragmentTrialBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int page=1;
    ArrayList<TrialData> list = new ArrayList<>();
    int valopen=0;
    boolean cheeck=true;
    String searchitemcase;
    ArrayList<String> listcase= new ArrayList<>();

    public Trial() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_trial, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        String branchId =preferences.getString("branchId","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);

        model.allTrial("Bearer"+Token,page,20, Integer.parseInt(branchId));
        AdapterTrial trial = new AdapterTrial(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(trial);
        binding.nes.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (cheeck){
                    if (scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                        page++;
                        binding.progress.setVisibility(View.VISIBLE);
                        model.allTrial("Bearer"+Token,page,20, Integer.parseInt(branchId));
                    }}
                else {

                }
            }
        });
        binding.editfilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cheeck=false;
                valopen=0;
                binding.DataType.setVisibility(View.GONE);
                binding.case3.setVisibility(View.GONE);
                binding.cc.setVisibility(View.GONE);
                binding.nes.setVisibility(View.VISIBLE);
                binding.Data.setVisibility(View.VISIBLE);
                binding.space.setVisibility(View.VISIBLE);
                binding.typefile.setVisibility(View.VISIBLE);
                binding.space2.setVisibility(View.VISIBLE);
                binding.case2.setVisibility(View.VISIBLE);
                binding.view.setVisibility(View.VISIBLE);
                binding.l1.setVisibility(View.GONE);
                String text= String.valueOf(s).trim();
                if (text.isEmpty()){
                    model.allTrial("Bearer"+Token,page,20, Integer.parseInt(branchId));

                }
                if (text.equals("")){
                    model.allTrial("Bearer"+Token,page,20, Integer.parseInt(branchId));

                }
                else {
                    SendDataTrial Trialfilter = new SendDataTrial();
                    Trialfilter.setFilter(text);
                    model.filterTrial("Bearer" + Token, Integer.parseInt(branchId), Trialfilter);
                }

            }
        });
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valopen==0){
                    valopen=1;
                    binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                    binding.case3.setBackground(getResources().getDrawable(R.drawable.item_filter));

                    binding.DataType.setVisibility(View.VISIBLE);
                    binding.case3.setVisibility(View.VISIBLE);
                    binding.cc.setVisibility(View.VISIBLE);
                    binding.nes.setVisibility(View.GONE);
                    binding.Data.setVisibility(View.GONE);
                    binding.space.setVisibility(View.GONE);
                    binding.typefile.setVisibility(View.GONE);
                    binding.space2.setVisibility(View.GONE);
                    binding.case2.setVisibility(View.GONE);
                    binding.view.setVisibility(View.GONE);

                }else {
                    valopen=0;
                    cheeck=true;
                    model.allTrial("Bearer"+Token,page,20, Integer.parseInt(branchId));
                    binding.DataType.setVisibility(View.GONE);
                    binding.case3.setVisibility(View.GONE);
                    binding.cc.setVisibility(View.GONE);
                    binding.nes.setVisibility(View.VISIBLE);
                    binding.Data.setVisibility(View.VISIBLE);
                    binding.space.setVisibility(View.VISIBLE);
                    binding.typefile.setVisibility(View.VISIBLE);
                    binding.space2.setVisibility(View.VISIBLE);
                    binding.case2.setVisibility(View.VISIBLE);
                    binding.view.setVisibility(View.VISIBLE);
                    binding.l1.setVisibility(View.GONE);
                }
            }
        });
        binding.DataType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                binding.case3.setBackground(getResources().getDrawable(R.drawable.item_filter));
                binding.l1.setVisibility(View.GONE);
                binding.cc.setVisibility(View.VISIBLE);
                binding.nes.setVisibility(View.GONE);
                binding.Data.setVisibility(View.GONE);
                binding.space.setVisibility(View.GONE);
                binding.typefile.setVisibility(View.GONE);
                binding.space2.setVisibility(View.GONE);
                binding.case2.setVisibility(View.GONE);
                binding.view.setVisibility(View.GONE);
            }
        });
        binding.case3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.case3.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_filter));
                binding.l1.setVisibility(View.VISIBLE);
                binding.cc.setVisibility(View.GONE);
                binding.nes.setVisibility(View.VISIBLE);
                binding.Data.setVisibility(View.VISIBLE);
                binding.space.setVisibility(View.VISIBLE);
                binding.typefile.setVisibility(View.VISIBLE);
                binding.space2.setVisibility(View.VISIBLE);
                binding.case2.setVisibility(View.VISIBLE);
                binding.view.setVisibility(View.VISIBLE);
            }
        });
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromday=binding.fromday.getText().toString().trim();
                String frommins=binding.frommanth.getText().toString().trim();
                String fromyears= binding.fromyears.getText().toString().trim();
                String today=binding.today.getText().toString().trim();
                String tomanth=binding.tomanth.getText().toString().trim();
                String toyears = binding.toyears.getText().toString().trim();
                if (fromday.isEmpty()){
                    binding.fromday.setError("");
                    return;
                }
                if (frommins.isEmpty()){
                    binding.frommanth.setError("");
                    return;
                }
                if (fromyears.isEmpty()){
                    binding.fromyears.setError("");
                    return;
                }
                if (today.isEmpty()){
                    binding.today.setError("");
                    return;
                }
                if (tomanth.isEmpty()){
                    binding.tomanth.setError("");
                    return;
                }
                if (toyears.isEmpty()){
                    binding.toyears.setError("");
                    return;
                }
                else{
                    cheeck=false;
                    valopen=0;
                    binding.DataType.setVisibility(View.GONE);
                    binding.case3.setVisibility(View.GONE);
                    binding.cc.setVisibility(View.GONE);
                    binding.nes.setVisibility(View.VISIBLE);
                    binding.Data.setVisibility(View.VISIBLE);
                    binding.space.setVisibility(View.VISIBLE);
                    binding.typefile.setVisibility(View.VISIBLE);
                    binding.space2.setVisibility(View.VISIBLE);
                    binding.case2.setVisibility(View.VISIBLE);
                    binding.view.setVisibility(View.VISIBLE);
                    binding.l1.setVisibility(View.GONE);
                    Toast.makeText(getContext(), ""+fromday+"/"+frommins+"/"+fromyears, Toast.LENGTH_SHORT).show();
                    SendDataTrial filterteail = new SendDataTrial();
                    filterteail.setStart_date(fromday+"/"+frommins+"/"+fromyears);
                    filterteail.setEnd_date(today+"/"+tomanth+"/"+toyears);
                    model.filterTrial("Bearer"+Token, Integer.parseInt(branchId),filterteail);

                }
            }
        });
        listcase.clear();
        listcase.add("مدعي");
        listcase.add("مدعي عليه");
        ArrayAdapter<String> filtercase = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,listcase);
        binding.spinner.setAdapter(filtercase);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cheeck=false;
                String item= binding.spinner.getSelectedItem().toString();
                switch (item){
                    case "مدعي":
                        searchitemcase="1";
                        break;
                    case "مدعي عليه":
                        searchitemcase="0";
                        break;
                }
                SendDataTrial filterteail = new SendDataTrial();
                filterteail.setIs_prosecutor(searchitemcase);
                model.filterTrial("Bearer"+Token, Integer.parseInt(branchId),filterteail);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        model.allTeial.observe(getViewLifecycleOwner(), new Observer<DataTrial>() {
            @Override
            public void onChanged(DataTrial dataTrial) {

                for (int i=0;i<dataTrial.getData().size();i++){
                    list.add(dataTrial.getData().get(i));
                }
                binding.progress.setVisibility(View.GONE);
                trial.getList(list);
            }
        });
        model.filterTeial.observe(getViewLifecycleOwner(), new Observer<DataTrial>() {
            @Override
            public void onChanged(DataTrial dataTrial) {
                trial.getList(dataTrial.getData());
            }
        });
        return binding.getRoot();
    }

    @Override
    public void getTrialId(int Id) {
        TrialDirections.ActionTrialToShowDetilseTrial trial = TrialDirections.actionTrialToShowDetilseTrial();
        trial.setId(Id);
    Navigation.findNavController(binding.getRoot()).navigate(trial);

    }




}