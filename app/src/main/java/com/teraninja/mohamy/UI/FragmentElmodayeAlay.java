package com.teraninja.mohamy.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teraninja.mohamy.Model.DataOppenents;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.databinding.FragmentElmodayeAlayBinding;


public class FragmentElmodayeAlay extends Fragment {
FragmentElmodayeAlayBinding binding;
    String phone;
    String id;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    public FragmentElmodayeAlay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_elmodaye_alay, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        id=preferences.getString("id","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        if (id!="no"){
            model.Oppenents("Bearer"+Token, Integer.parseInt(id));
            Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
        }
        else
        {

        }

        model.Oppenents.observe(getViewLifecycleOwner(), new Observer<DataOppenents>() {
            @Override
            public void onChanged(DataOppenents dataOppenents) {
                if (dataOppenents.getStatus()==1){
                    binding.text.setText(""+dataOppenents.getData().getOppenents().get(0).getOppenent_name());
                    binding.textposation.setText(""+dataOppenents.getData().getOppenents().get(0).getType_of_job_of_the_opponent());
                    phone= dataOppenents.getData().getOppenents().get(0).getPhone_of_the_opponent();
                    binding.username.setText(""+dataOppenents.getData().getOppenents().get(0).getPhone_of_the_opponent());
                    binding.numberPhone.setText(""+dataOppenents.getData().getOppenents().get(0).getHome_address_of_the_opponent());


                }
            }
        });

        binding.telphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);

            }
        });
        binding.waths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone="+phone;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

    return binding.getRoot();
    }
}