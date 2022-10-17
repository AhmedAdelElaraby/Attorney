package com.teraninja.mohamy;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.mohamy.DataClient.OnClickgetIdTasksHome;
import com.teraninja.mohamy.Model.DataChengePassword;
import com.teraninja.mohamy.Model.DataCity;
import com.teraninja.mohamy.Model.DataHome;
import com.teraninja.mohamy.Model.SendDataCastmerfilter;
import com.teraninja.mohamy.Model.SendDataChengePassword;
import com.teraninja.mohamy.UI.Main.AdapterTaskes;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentHomeEmployeeBinding;

import java.util.ArrayList;


public class HomeEmployee extends Fragment implements OnClickgetIdTasksHome {
FragmentHomeEmployeeBinding binding;
MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    ArrayList<String> listcity= new ArrayList();
    ArrayList<Integer> listcityid= new ArrayList();

    public HomeEmployee() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home_employee, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
       model= ViewModelProviders.of(this).get(MoveViewModel.class);
       model.Home("Bearer"+Token);
       model.ollcity("Bearer"+Token);
        AdapterTaskes taskes = new AdapterTaskes(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(taskes);
        model.city.observe(getViewLifecycleOwner(), new Observer<DataCity>() {
            @Override
            public void onChanged(DataCity dataCity) {
                listcity.clear();
                listcityid.clear();
                listcity.add("اختر الفرع");
                listcityid.add(0);
                for (int i=0;i<dataCity.getData().size();i++){
                    listcity.add(dataCity.getData().get(i).getName());
                    listcityid.add(dataCity.getData().get(i).getId());
                }
            }
        });
        ArrayAdapter<String> filtercase = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,listcity);
        binding.spiner.setAdapter(filtercase);
        binding.spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int s = listcityid.get(i);
                Toast.makeText(getContext(), "id city"+s, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.users2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_allTask);
            }
        });
        binding.users33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_elmalya);
            }
        });
        binding.users3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_trial);
            }
        });

        model.Home.observe(getViewLifecycleOwner(), new Observer<DataHome>() {
           @Override
           public void onChanged(DataHome dataHome) {
               Toast.makeText(getContext(), ""+dataHome.getMessage(), Toast.LENGTH_SHORT).show();
               Picasso.get().load(dataHome.getData().getPhoto()).fit().into(binding.imageuser);
                binding.username.setText(dataHome.getData().getName());
                binding.namejob.setText(dataHome.getData().getRole());
                taskes.getList(dataHome.getData().getTasks());

           }
       });
       binding.users.setOnClickListener(view -> {
           Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_sessions);
       });
       binding.maney.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_salary);
           }
       });
       binding.note.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_admin_requests);
           }
       });
       binding.customers.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_customers2);
           }
       });
       binding.changepassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               DilogeUpdatePassword();

           }
       });
       binding.customerService.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_call_Medie);
           }
       });
       binding.out.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.homeEmployee, true).build();
               Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeEmployee_to_loginEmploye,savedInstanceState
               ,builder1);
           }
       });
      return binding.getRoot();
    }
    public void DilogeUpdatePassword(){
        Dialog myDialog;
        EditText old_password;
        EditText new_password;
        EditText confarn_password;
        Button button;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.itemchangepassword);
        old_password=myDialog.findViewById(R.id.old_password);
        new_password=myDialog.findViewById(R.id.new_password);
        confarn_password=myDialog.findViewById(R.id.confarm_password);
        button=myDialog.findViewById(R.id.Save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old=old_password.getText().toString().trim();
                String newpass=new_password.getText().toString().trim();
                String comfarm=confarn_password.getText().toString().trim();
                if (old.isEmpty()){
                    old_password.setError("");
                    return;
                }
                if (newpass.isEmpty()){
                    new_password.setError("");
                    return;
                }
                if (comfarm.isEmpty()){
                    confarn_password.setError("");
                    return;
                }
                else {
                    SendDataChengePassword chengePassword = new SendDataChengePassword();
                    chengePassword.setOld_password(old);
                    chengePassword.setPassword(newpass);
                    chengePassword.setPassword_confirmation(comfarm);
                    model.Chengepassword("Bearer"+Token,chengePassword);
                }


            }
        });
        model.chengePassword.observe(getViewLifecycleOwner(), new Observer<DataChengePassword>() {
            @Override
            public void onChanged(DataChengePassword dataUpdatePhone) {
                if (dataUpdatePhone.getStatus()==1){
                    Toast.makeText(getContext(), ""+dataUpdatePhone.getData(), Toast.LENGTH_SHORT).show();
                    myDialog.dismiss();
                }else{
                    Toast.makeText(getContext(), ""+dataUpdatePhone.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);




        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }

    @Override
    public void getId(int id) {
        HomeEmployeeDirections.ActionHomeEmployeeToDetilseTases tases=HomeEmployeeDirections.actionHomeEmployeeToDetilseTases();
        Navigation.findNavController(binding.getRoot()).navigate(tases);
    }
}