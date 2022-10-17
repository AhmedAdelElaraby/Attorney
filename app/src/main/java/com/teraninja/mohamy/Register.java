package com.teraninja.mohamy;

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

import com.teraninja.mohamy.Model.DataSendRegister;
import com.teraninja.mohamy.Model.ModelDataRegister;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentRegisterBinding;


public class Register extends Fragment {
FragmentRegisterBinding  binding;
    MoveViewModel model;
    String number;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_register, container, false);
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.username.getText().toString().trim();
                 number=binding.numberPhone.getText().toString().trim();
                String Email=binding.email.getText().toString().trim();
                String password=binding.password.getText().toString().trim();
                String confirmpassword=binding.confirmpassword.getText().toString().trim();
                boolean isCheck = binding.cheeck.isChecked();
                if (name.isEmpty()){
                    binding.username.setError("");
                    return;
                }
                if (number.isEmpty()){
                    binding.numberPhone.setError("");
                    return;
                }
                if (Email.isEmpty()){
                    binding.email.setError("");
                    return;
                }
                if (password.isEmpty()){
                    binding.password.setError("");
                    return;
                }
                if (confirmpassword.isEmpty()){
                    binding.confirmpassword.setError("");
                    return;
                }
                if (!isCheck){
                    //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                    binding.cheeck.setError("");
                }
                else{
                    DataSendRegister register = new DataSendRegister();
                    register.setName(name);
                    register.setMobile(number);
                    register.setEmail(Email);
                    register.setPassword(password);
                    register.setPassword_confirmation(confirmpassword);
                    model.Register(register);
                }
            }
        });
        model.Register.observe(getViewLifecycleOwner(), new Observer<ModelDataRegister>() {
            @Override
            public void onChanged(ModelDataRegister modelDataRegister) {
                if (modelDataRegister.getStatus()==1){
                    Toast.makeText(getContext(), "Success"+"\t"+modelDataRegister.getData().getMobile_code(), Toast.LENGTH_SHORT).show();
                    RegisterDirections.ActionRegisterToVervfiyCode register =RegisterDirections.actionRegisterToVervfiyCode();
                    register.setNumber(number);

                    register.setCode(String.valueOf(modelDataRegister.getData().getMobile_code()));
                    Navigation.findNavController(binding.getRoot()).navigate(register);
                }else {
                    Toast.makeText(getContext(), ""+modelDataRegister.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    return binding.getRoot();

    }
}