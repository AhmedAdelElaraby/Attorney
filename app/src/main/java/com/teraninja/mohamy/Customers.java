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
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.teraninja.mohamy.DataClient.OnClickItemClinet;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.Model.DataChengePassword;
import com.teraninja.mohamy.Model.DataCustmers;
import com.teraninja.mohamy.Model.SendDataCastmerfilter;
import com.teraninja.mohamy.Model.SendDataChengePassword;
import com.teraninja.mohamy.UI.Main.AdapterCustomers;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentCustomersBinding;

import java.util.ArrayList;




public class Customers extends Fragment implements OnClickItemClinet {
FragmentCustomersBinding binding;
 MoveViewModel model;
 String Token;
 SharedPreferences preferences;
    int page=1;
    ArrayList<Custmers> list = new ArrayList<>();
    ArrayList<String> listcase= new ArrayList<>();
    int valopen=0;
    boolean cheeck=true;
    String searchitemcase;
    public Customers() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_customers, container, false);
     preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
     Token = preferences.getString("token","no");
     model= ViewModelProviders.of(this).get(MoveViewModel.class);
     model.Custmers("Bearer"+Token,page,20);
        AdapterCustomers customers = new AdapterCustomers(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.rec.setAdapter(customers);

        listcase.add("VIP");
        listcase.add("شخص");
        listcase.add("مؤسسه");
        listcase.add("شركه");
        listcase.add("وسيط");
        listcase.add("فرصه");

        ArrayAdapter<String> filtercase = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,listcase);
        binding.spinner.setAdapter(filtercase);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cheeck=false;
               String item= binding.spinner.getSelectedItem().toString();
               switch (item){
                   case "شخص":
                       searchitemcase="person";
                       break;
                       case "شركه":
                       searchitemcase="company";
                       break;
                       case "مؤسسه":
                       searchitemcase="Establishment";
                       break;
                       case "فرصه":
                       searchitemcase="Chance";
                       break;
                       case "VIP":
                       searchitemcase="vip";
                       break;
                       case "وسيط":
                       searchitemcase="mediator";
                       break;
               }
                SendDataCastmerfilter castmerfilter = new SendDataCastmerfilter();
                castmerfilter.setType(searchitemcase);
                model.Custmersfilter("Bearer"+Token,castmerfilter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        model.Custmersfilter.observe(getViewLifecycleOwner(), new Observer<DataCustmers>() {
            @Override
            public void onChanged(DataCustmers dataCustmers) {
                customers.getList(dataCustmers.getData());
            }
        });
        binding.nes.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (cheeck){
                if (scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                    page++;
                    binding.progress.setVisibility(View.VISIBLE);
                    model.Custmers("Bearer"+Token,page,20);
                }}
                else {

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
                model.Custmers("Bearer"+Token,page,20);
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
        binding.editfilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

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
                SendDataCastmerfilter castmerfilter = new SendDataCastmerfilter();
                castmerfilter.setFilter(String.valueOf(s));
                model.Custmersfilter("Bearer"+Token,castmerfilter);


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
                    SendDataCastmerfilter castmerfilter = new SendDataCastmerfilter();
                    castmerfilter.setStart_date(fromday+"/"+frommins+"/"+fromyears);
                    castmerfilter.setEnd_date(today+"/"+tomanth+"/"+toyears);
                    model.Custmersfilter("Bearer"+Token,castmerfilter);

                }
            }
        });
        binding.navcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        model.Custmers.observe(getViewLifecycleOwner(), new Observer<DataCustmers>() {
    @Override
    public void onChanged(DataCustmers dataCustmers) {
        for (int i=0;i<dataCustmers.getData().size();i++){
            list.add(dataCustmers.getData().get(i));
        }
        binding.progress.setVisibility(View.GONE);
         customers.getList(list);
    }
   });

       return binding.getRoot();
    }
    public void DilogeshowDeitlseinfo(int ClientId,String getname,String getphone,String getemail){
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
        name.setText(getname);
        number.setText(getphone);
        email.setText(getemail);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomersDirections.ActionCustomers2ToViewCustomerIssues issues = CustomersDirections.actionCustomers2ToViewCustomerIssues();
                issues.setClientId(ClientId);
                Navigation.findNavController(binding.getRoot()).navigate(issues);
                myDialog.dismiss();
            }
        });
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
    public void OnClickInfo(int ClientId,String name, String number, String email) {
        DilogeshowDeitlseinfo(ClientId,name,number,email);
    }
}