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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teraninja.mohamy.DataClient.OnClickgetInfoCall;
import com.teraninja.mohamy.Model.Callsed;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.Model.DataCall;
import com.teraninja.mohamy.Model.SendDataCastmerfilter;
import com.teraninja.mohamy.Model.SendDatacall;
import com.teraninja.mohamy.UI.Main.Adapter_call;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentCallMedieBinding;

import java.util.ArrayList;


public class Call_Medie extends Fragment implements OnClickgetInfoCall {
FragmentCallMedieBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int page=1;
    ArrayList<Callsed> list = new ArrayList<>();
    public Call_Medie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_call__medie, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        model.call("Bearer"+Token,page,20);


        Adapter_call call = new Adapter_call(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(call);
        binding.nes.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                        page++;
                        binding.progress.setVisibility(View.VISIBLE);
                        model.call("Bearer"+Token,page,20);
                    }
                else {

                }
            }
        });

        binding.editfilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                binding.nes.setVisibility(View.VISIBLE);
                binding.Data.setVisibility(View.VISIBLE);
                binding.space.setVisibility(View.VISIBLE);
                binding.typefile.setVisibility(View.VISIBLE);
                binding.space2.setVisibility(View.VISIBLE);
                binding.case2.setVisibility(View.VISIBLE);
                binding.view.setVisibility(View.VISIBLE);

                SendDatacall castmerfilter = new SendDatacall();
                castmerfilter.setFilter(String.valueOf(s));
                model.callfiltring("Bearer"+Token,castmerfilter);


            }
        });
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_call_Medie_to_callFiltering);
            }
        });
        binding.navcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    model.callfiltring.observe(getViewLifecycleOwner(), new Observer<DataCall>() {
        @Override
        public void onChanged(DataCall dataCall) {
            call.getList(dataCall.getData());
        }
    });
    model.call.observe(getViewLifecycleOwner(), new Observer<DataCall>() {
        @Override
        public void onChanged(DataCall dataCall) {
            for (int i=0;i<dataCall.getData().size();i++){
                list.add(dataCall.getData().get(i));
            }
            binding.progress.setVisibility(View.GONE);
            call.getList(list);
        }
    });



        return binding.getRoot();
    }

    public void DilogeshowDeitlseinfo(String getname,String getphone,String type){
        Dialog myDialog;
        TextView name;
        TextView number;
        TextView email;
        ImageView phone;
        ImageView waths;
        Button show;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.itemdetilsephone);
        name = myDialog.findViewById(R.id.text);
        number = myDialog.findViewById(R.id.username);
        email = myDialog.findViewById(R.id.numberPhone);
        phone = myDialog.findViewById(R.id.telphone);
        waths = myDialog.findViewById(R.id.waths);
        show = myDialog.findViewById(R.id.show);
        show.setVisibility(View.GONE);
        name.setText(getname);
        number.setText(getphone);
        email.setText(type);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+getphone));
                startActivity(intent);
                myDialog.dismiss();
            }
        });
        waths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone="+getphone;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                myDialog.dismiss();
            }
        });
        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);




        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

    @Override
    public void getinfo(String name, String number, String wath) {
        DilogeshowDeitlseinfo(name,number,wath);
    }
}