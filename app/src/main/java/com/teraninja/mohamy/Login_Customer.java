package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.mohamy.Model.DataSendClientLogin;
import com.teraninja.mohamy.Model.ModelDataClientLogin;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentLoginCustomerBinding;


public class Login_Customer extends Fragment {
FragmentLoginCustomerBinding binding;
    MoveViewModel model;
    String number;
    SharedPreferences preferences;
    public Login_Customer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_login__customer, container, false);
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
        preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = binding.numberPhone.getText().toString().trim();
                String password=binding.password.getText().toString().trim();
                if (number.isEmpty()){
                    binding.numberPhone.setError("");
                    return;
                }
                if (password.isEmpty()){
                    binding.password.setError("");
                    return;
                }
                else{
                    DataSendClientLogin login = new DataSendClientLogin();
                    login.setMobile(number);
                    login.setPassword(password);
                    model.ClientLogin(login);

                }
            }
        });

        model.ClientLogin.observe(getViewLifecycleOwner(), new Observer<ModelDataClientLogin>() {
            @Override
            public void onChanged(ModelDataClientLogin modelDataClientLogin) {
                if (modelDataClientLogin.getStatus()==1){
                    Toast.makeText(getContext(), ""+modelDataClientLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    savedata("token",modelDataClientLogin.getData().getToken().getAccess_token());
                    savedata("case","log");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_login_Customer_to_homeClient);

                }else if(modelDataClientLogin.getStatus()==2) {
                    Login_CustomerDirections.ActionLoginCustomerToVervfiyCode code = Login_CustomerDirections.actionLoginCustomerToVervfiyCode();
                    code.setCode(modelDataClientLogin.getData().getMobile_code());
                    code.setNumber(number);
                    Navigation.findNavController(binding.getRoot()).navigate(code);
                    Toast.makeText(getContext(), ""+modelDataClientLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), ""+modelDataClientLogin.getData().getMobile_code(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getContext(), ""+modelDataClientLogin.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_login_Customer_to_frogetPassword);
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