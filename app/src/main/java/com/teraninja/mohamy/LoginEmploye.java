package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.mohamy.Model.DataLoginEmploye;
import com.teraninja.mohamy.Model.SendDataLogin;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentLoginEmployeBinding;


public class LoginEmploye extends Fragment {
FragmentLoginEmployeBinding binding;
MoveViewModel model;
    SharedPreferences preferences;
    public LoginEmploye() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_login_employe, container, false);
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginEmploye_to_frogetPassword);
            }
        });

        model.LoginEmoloye.observe(getViewLifecycleOwner(), new Observer<DataLoginEmploye>() {
            @Override
            public void onChanged(DataLoginEmploye dataLoginEmploye) {
                if (dataLoginEmploye.getStatus() == 1) {
                    Toast.makeText(getContext(), "" + dataLoginEmploye.getMessage(), Toast.LENGTH_SHORT).show();
                    savedata("token",dataLoginEmploye.getData().getToken().getAccess_token());
                    savedata("branchId",""+dataLoginEmploye.getData().getUser().getBranch_id());
                    dataLoginEmploye.setStatus(3);
                    NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.loginEmploye, true).build();

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginEmploye_to_homeEmployee
                    ,savedInstanceState,builder1);
                } else {
                    Toast.makeText(getContext(), "" + dataLoginEmploye.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = binding.numberPhone.getText().toString().trim();
                String password = binding.password.getText().toString().trim();
                if (number.isEmpty()) {
                    binding.numberPhone.setError(getString(R.string.number_phone));
                    return;
                }
                if (password.isEmpty()) {
                    binding.password.setError(getString(R.string.password));
                    return;
                } else {
                    SendDataLogin login = new SendDataLogin();
                    login.setMobile(number);
                    login.setPassword(password);
                    model.login(login);
                }
            }
        });
       return binding.getRoot();
    }


    public  void savedata(String key,String val){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,val);
        editor.commit();

    }
}