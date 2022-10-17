package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.mohamy.databinding.FragmentChoseLoginOrRegisterBinding;


public class ChoseLoginOrRegister extends Fragment {
FragmentChoseLoginOrRegisterBinding binding;
    SharedPreferences preferences;

    public ChoseLoginOrRegister() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_chose_login_or_register, container, false);
        preferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        binding.loginemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_choseLoginOrRegister_to_loginEmploye);
            }
        });
        binding.loginClint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_choseLoginOrRegister_to_login_Customer);
            }
        });
        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_choseLoginOrRegister_to_register);
            }
        });
        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata("case","skip");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_choseLoginOrRegister_to_homeClient);
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