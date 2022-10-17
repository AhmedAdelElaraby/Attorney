package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.teraninja.mohamy.DataClient.OnClickgetDetilse;
import com.teraninja.mohamy.Model.DataDetilseTaskes;
import com.teraninja.mohamy.Model.SendDataTrial;
import com.teraninja.mohamy.Model.SendDetilseTaskes;
import com.teraninja.mohamy.UI.Main.Adapter_DetilseTaskes;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentFilterDetilseTaskesBinding;

import java.util.ArrayList;


public class FilterDetilseTaskes extends Fragment implements OnClickgetDetilse {
FragmentFilterDetilseTaskesBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    String searchitemcase2;
    String searchitemcase1;
    String searchitemcase3;
    int valopen=1;
    int id;
    ArrayList<String> listcaseOne= new ArrayList<>();
    ArrayList<String> listcasetow= new ArrayList<>();
    ArrayList<String> listcasethree= new ArrayList<>();

    public FilterDetilseTaskes() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_filter_detilse_taskes, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        if (getArguments()!=null){
            FilterDetilseTaskesArgs args =FilterDetilseTaskesArgs.fromBundle(getArguments());
             id= args.getId();
        }else{

        }
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
                    binding.rec.setVisibility(View.GONE);
                }else {
                    valopen=0;
                    binding.DataType.setVisibility(View.GONE);
                    binding.case3.setVisibility(View.GONE);
                    binding.cc.setVisibility(View.GONE);
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

            }
        });
        binding.case3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.case3.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_filter));
                binding.l1.setVisibility(View.VISIBLE);
                binding.cc.setVisibility(View.GONE);

            }
        });
        Adapter_DetilseTaskes taskes = new Adapter_DetilseTaskes(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(taskes);
////
//
        listcaseOne.add("الاداره");
        listcaseOne.add("قانوني");
        listcaseOne.add("مالي");

        ArrayAdapter<String> filtercaseOne = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,listcaseOne);
        binding.spinner.setAdapter(filtercaseOne);
//
//
//
            listcasetow.add("منجزه");
            listcasetow.add("غير منجزه");
        ArrayAdapter<String> filtercasetwo = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,listcasetow);
        binding.spinner2.setAdapter(filtercasetwo);
////
        listcasethree.add("خاص");
        listcasethree.add("عام");
        listcasethree.add("موقت");
        ArrayAdapter<String> filtercasethree = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,listcasethree);
        binding.spinner3.setAdapter(filtercasethree);



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

                    valopen=0;
                    binding.DataType.setVisibility(View.GONE);
                    binding.case3.setVisibility(View.GONE);
                    binding.cc.setVisibility(View.GONE);
                    binding.rec.setVisibility(View.VISIBLE);

                    binding.l1.setVisibility(View.GONE);
                    Toast.makeText(getContext(), ""+fromday+"/"+frommins+"/"+fromyears, Toast.LENGTH_SHORT).show();
                    SendDetilseTaskes Trialfilter = new SendDetilseTaskes();
                    Trialfilter.setStart_date(fromday+"/"+frommins+"/"+fromyears);
                    Trialfilter.setEnd_date(today+"/"+tomanth+"/"+toyears);
                    model.DeilseTaskes("Bearer"+Token,id,Trialfilter);

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

                valopen=0;
                binding.DataType.setVisibility(View.GONE);
                binding.case3.setVisibility(View.GONE);
                binding.cc.setVisibility(View.GONE);
                binding.rec.setVisibility(View.VISIBLE);

                binding.l1.setVisibility(View.GONE);
                String text= String.valueOf(s).trim();
                if (text.isEmpty()){
                    binding.rec.setVisibility(View.GONE);

                    SendDetilseTaskes Trialfilter = new SendDetilseTaskes();

                    model.DeilseTaskes("Bearer"+Token, id,Trialfilter);

                }
                if (text.equals("")){
                    binding.rec.setVisibility(View.GONE);

                    SendDetilseTaskes Trialfilter = new SendDetilseTaskes();

                    model.DeilseTaskes("Bearer"+Token, id,Trialfilter);

                }
                else {
                    SendDetilseTaskes Trialfilter = new SendDetilseTaskes();
                    Trialfilter.setFilter(text);
                    model.DeilseTaskes("Bearer" + Token,id, Trialfilter);
                }

            }
        });
//
        binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item= binding.spinner2.getSelectedItem().toString();
                switch (item){
                    case "منجزه":
                        searchitemcase2="1";
                        break;
                    case "غير منجزه":
                        searchitemcase2="0";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item= binding.spinner.getSelectedItem().toString();
                switch (item){
                    case "الاداره":
                        searchitemcase1="1";
                        break;
                    case "قانوني":
                        searchitemcase1="1";
                    case"مالي":
                        searchitemcase1="1";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item= binding.spinner3.getSelectedItem().toString();
                switch (item){
                    case "خاص":
                        searchitemcase3="private";
                        break;
                    case "عام":
                        searchitemcase3="public";
                    case "موقت":
                        searchitemcase3="0";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//
        binding.searchspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valopen=0;
                SendDetilseTaskes Trialfilter = new SendDetilseTaskes();
                Trialfilter.setDepartment_id(searchitemcase2);
                Trialfilter.setView_type(searchitemcase3);
                Trialfilter.setIs_done(searchitemcase1);
                model.DeilseTaskes("Bearer"+Token, id,Trialfilter);
                binding.DataType.setVisibility(View.GONE);
                binding.case3.setVisibility(View.GONE);
                binding.cc.setVisibility(View.GONE);
                binding.rec.setVisibility(View.VISIBLE);
                binding.l1.setVisibility(View.GONE);

            }
        });
        model.DeilseTases.observe(getViewLifecycleOwner(), new Observer<DataDetilseTaskes>() {
            @Override
            public void onChanged(DataDetilseTaskes dataDetilseTaskes) {
                taskes.getList(dataDetilseTaskes.getData());
            }
        });
//


        return binding.getRoot();

    }


    @Override
    public void getDetils(int id) {
        
    }
}