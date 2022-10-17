package com.teraninja.mohamy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

import com.teraninja.mohamy.DataClient.OnClickgetDays;
import com.teraninja.mohamy.DataClient.OnClickgetInfoCall;
import com.teraninja.mohamy.Model.DataCall;
import com.teraninja.mohamy.Model.SendDatacall;
import com.teraninja.mohamy.UI.Main.Adapter_Days;
import com.teraninja.mohamy.UI.Main.Adapter_call;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentCallFilteringBinding;

import java.util.ArrayList;


public class CallFiltering extends Fragment implements OnClickgetDays , OnClickgetInfoCall {
FragmentCallFilteringBinding  binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int valopen=0;
    ArrayList<String> listcasemonth = new ArrayList<>();
    ArrayList<Integer> listid = new ArrayList<>();
    int numberMonth;
    ArrayList<Integer> dayss = new ArrayList<>();

    public CallFiltering() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding= DataBindingUtil.inflate(inflater,R.layout.fragment_call_filtering, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        binding.editfilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                valopen=0;

                binding.rec.setVisibility(View.VISIBLE);

                binding.l1.setVisibility(View.GONE);

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
        listcasemonth.add("يناير");
        listcasemonth.add("فبراير");
        listcasemonth.add("مارس");
        listcasemonth.add("ابريل");
        listcasemonth.add("مايو");
        listcasemonth.add("يونيه");
        listcasemonth.add("يوليه");
        listcasemonth.add("اغسطس");
        listcasemonth.add("سبتمبر");
        listcasemonth.add("اكتوبر");
        listcasemonth.add("نوفبر");
        listcasemonth.add("ديسمبر");
        listid.add(1);
        listid.add(2);
        listid.add(3);
        listid.add(4);
        listid.add(5);
        listid.add(6);
        listid.add(7);
        listid.add(8);
        listid.add(9);
        listid.add(10);
        listid.add(11);
        listid.add(12);

        ArrayAdapter<String> filtercaseOne = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,listcasemonth);
        binding.spinner.setAdapter(filtercaseOne);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 numberMonth=listid.get(i);
                SendDatacall  datacall = new SendDatacall();
                datacall.setMonth(String.valueOf(numberMonth));
                model.callfiltring("Bearer"+Token,datacall);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Adapter_Days days = new Adapter_Days(this);
        binding.recdays.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.recdays.setAdapter(days);
        Adapter_call call = new Adapter_call(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(call);
        for (int i=0;i<32;i++){
            dayss.add(i);
        }
        days.getList(dayss);





        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
      return binding.getRoot();
    }

    @Override
    public void getdays(int days) {
        int d=days;
        d++;
        SendDatacall  datacall = new SendDatacall();
        datacall.setMonth(String.valueOf(numberMonth));
        datacall.setDay(String.valueOf(d));
        model.callfiltring("Bearer"+Token,datacall);


    }

    @Override
    public void getinfo(String name, String number, String wath) {
        DilogeshowDeitlseinfo(name,number,wath);
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

}