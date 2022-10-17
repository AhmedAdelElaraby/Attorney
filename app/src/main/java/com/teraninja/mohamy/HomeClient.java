package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teraninja.mohamy.Model.DataAddConslout;
import com.teraninja.mohamy.Model.DataHome;
import com.teraninja.mohamy.Model.SendConslout;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentHomeClientBinding;


public class HomeClient extends Fragment {
FragmentHomeClientBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
String caselog;

    public HomeClient() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home_client, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        caselog = preferences.getString("case","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        if (caselog.equals("log")){
            model.HomeClient("Bearer"+Token);
        }
    binding.out.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.homeClient, true).build();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeClient_to_login_Customer,savedInstanceState
                    ,builder1);
        }
    });
        //Toast.makeText(getContext(), ""+Token, Toast.LENGTH_SHORT).show();
        model.HomeClient.observe(getViewLifecycleOwner(), new Observer<DataHome>() {
            @Override
            public void onChanged(DataHome dataHome) {
                if (dataHome.getStatus()==1) {
                    Toast.makeText(getContext(), "" + dataHome.getMessage(), Toast.LENGTH_SHORT).show();
                    Picasso.get().load(dataHome.getData().getPhoto()).fit().into(binding.imageuser);
                    binding.username.setText(dataHome.getData().getName());
                    binding.namejob.setText(""+dataHome.getData().getType_of_job());
                    binding.numberphone.setText("" + dataHome.getData().getMobile());
                    binding.Email.setText("" + dataHome.getData().getEmail());
                    binding.Addriss.setText("" + dataHome.getData().getCity() + "-" + dataHome.getData().getStreet_name()
                            + "-" + dataHome.getData().getDistrict());
                     }
                else
                    {
                    Toast.makeText(getContext(), ""+dataHome.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });
        binding.customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caselog.equals("log")) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeClient_to_trialClient);
                }else {
                    Toast.makeText(getContext(), "يجب تسجيل الدخول الاول", Toast.LENGTH_SHORT).show();
                }
            }
        });
    binding.Add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (caselog.equals("log")){
                String body=binding.text.getText().toString();
                if (body.isEmpty()){
                    binding.text.setError("");
                }else {
                    SendConslout conslout = new SendConslout();
                    conslout.setConsulting_id(String.valueOf(1));
                    conslout.setBody(body);
                    model.Add("Bearer" + Token,conslout);
                }
            }else {
                Toast.makeText(getContext(), "يجب تسجيل الدخول الاول", Toast.LENGTH_SHORT).show();

            }

        }
    });
    model.Add.observe(getViewLifecycleOwner(), new Observer<DataAddConslout>() {
        @Override
        public void onChanged(DataAddConslout dataAddConslout) {
            Toast.makeText(getContext(), ""+dataAddConslout.getMessage(), Toast.LENGTH_SHORT).show();
            binding.text.setText(null);
        }
    });
binding.users3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (caselog.equals("log")) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeClient_to_consultings);
        }
        else {
            Toast.makeText(getContext(), "يجب تسجيل الدخول الاول", Toast.LENGTH_SHORT).show();

        }
    }
});
        return binding.getRoot();
    }
}