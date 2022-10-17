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
import com.teraninja.mohamy.Model.SendDataForRseedPassword;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentNewPasswordBinding;


public class NewPassword extends Fragment {
FragmentNewPasswordBinding binding;
    MoveViewModel model;
    String number;
    String code;
    public NewPassword() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_new_password, container, false);
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
        if (getArguments()!=null){
            NewPasswordArgs args = NewPasswordArgs.fromBundle(getArguments());
            number=args.getNumber();
            code=args.getCode();
        }
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password=binding.numberPhone.getText().toString().trim();
                String confirmpassword=binding.confarmpass.getText().toString().trim();
                if (password.isEmpty()){
                    binding.numberPhone.setError("");
                    return;
                }
                if (confirmpassword.isEmpty()){
                    binding.confarmpass.setError("");
                    return;
                }
                else {
                    SendDataForRseedPassword forRseedPassword= new SendDataForRseedPassword();
                    forRseedPassword.setPassword(password);
                    forRseedPassword.setPassword_confirmation(confirmpassword);
                    forRseedPassword.setMobile(number);
                    forRseedPassword.setMobile_code(code);
                    model.Forgetpassword(forRseedPassword);
                }
            }
        });
        model.Frogetpassword.observe(getViewLifecycleOwner(), new Observer<DataModelCode>() {
            @Override
            public void onChanged(DataModelCode dataModelCode) {
                if (dataModelCode.getStatus()==1){
                    Toast.makeText(getContext(), ""+dataModelCode.getMessage(), Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_newPassword_to_login_Customer);

                }else{
                    Toast.makeText(getContext(), ""+dataModelCode.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();

    }
}