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

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.teraninja.mohamy.Model.DataCheeckCode;
import com.teraninja.mohamy.Model.DataSendnumberandCode;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentVerfiyNumberByPasswordBinding;


public class FragmentVerfiyNumberByPassword extends Fragment {
    FragmentVerfiyNumberByPasswordBinding binding;
    MoveViewModel model;
    String code;
    String number;

    public FragmentVerfiyNumberByPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_verfiy_number_by_password, container, false);
        model = ViewModelProviders.of(this).get(MoveViewModel.class);

        if (getArguments()!=null){
            FragmentVerfiyNumberByPasswordArgs args = FragmentVerfiyNumberByPasswordArgs.fromBundle(getArguments());
            number =args.getNumber();
            code=args.getCode();
        }

        binding.txtPinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {

                if (str.toString().equals(code)) {
                    DataSendnumberandCode code1 = new DataSendnumberandCode();
                    code1.setMobile(number);
                    code1.setMobile_code(code);
                    model.Cheekcode(code1);

                } else {
                    Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                    binding.txtPinEntry.setText(null);
                }
            }
        });

        model.Cheeckcode.observe(getViewLifecycleOwner(), new Observer<DataCheeckCode>() {
            @Override
            public void onChanged(DataCheeckCode dataCheeckCode) {
                if (dataCheeckCode.getStatus()==1){
                    FragmentVerfiyNumberByPasswordDirections.ActionFragmentVerfiyNumberByPasswordToNewPassword numbersend = FragmentVerfiyNumberByPasswordDirections.actionFragmentVerfiyNumberByPasswordToNewPassword();
                    numbersend.setNumber(number);
                    numbersend.setCode(code);
                    Navigation.findNavController(binding.getRoot()).navigate(numbersend);
                }else{
                    Toast.makeText(getContext(), ""+dataCheeckCode.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        return binding.getRoot();
    }
}