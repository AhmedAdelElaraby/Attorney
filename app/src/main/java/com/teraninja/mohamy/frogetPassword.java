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

import com.teraninja.mohamy.Model.DataModelCode;
import com.teraninja.mohamy.Model.SendCode;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentFrogetPasswordBinding;


public class frogetPassword extends Fragment {
   FragmentFrogetPasswordBinding binding;
    MoveViewModel model;
    String number;

    public frogetPassword() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_froget_password, container, false);
        model = ViewModelProviders.of(this).get(MoveViewModel.class);

        binding.Register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             number=binding.numberPhone.getText().toString().trim();
            if (number.isEmpty()){
                binding.numberPhone.setError("");

            }
            else{
                SendCode code = new SendCode();
                code.setMobile(number);
                model.SendCode(code);
            }
           }
       });
        model.SendCode.observe(getViewLifecycleOwner(), new Observer<DataModelCode>() {
            @Override
            public void onChanged(DataModelCode dataModelCode) {
                if (dataModelCode.getStatus()==1){
                    Toast.makeText(getContext(), ""+dataModelCode.getData().getMobile_code(), Toast.LENGTH_SHORT).show();
            frogetPasswordDirections.ActionFrogetPasswordToFragmentVerfiyNumberByPassword numberByPassword =frogetPasswordDirections.actionFrogetPasswordToFragmentVerfiyNumberByPassword();
            numberByPassword.setNumber(dataModelCode.getData().getMobile());
            numberByPassword.setCode(dataModelCode.getData().getMobile_code());
            Navigation.findNavController(binding.getRoot()).navigate(numberByPassword);
                }else{
                    Toast.makeText(getContext(), ""+dataModelCode.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
       return binding.getRoot();
    }
}