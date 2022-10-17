package com.teraninja.mohamy.UI.Main;

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

import com.teraninja.mohamy.Model.DataShowTrial;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.ShowDetilseTrialArgs;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentModaeyBinding;


public class FragmentModaey extends Fragment {
FragmentModaeyBinding binding ;
String id;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
String phone;
    public FragmentModaey() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding= DataBindingUtil.inflate(inflater,R.layout.fragment_modaey, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        id=preferences.getString("id","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        if (id!="no"){
            model.ShowTrial("Bearer"+Token, Integer.parseInt(id));
            Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
        }
        else
        {

        }
        model.ShowTrial.observe(getViewLifecycleOwner(), new Observer<DataShowTrial>() {
            @Override
            public void onChanged(DataShowTrial dataShowTrial) {
                if(dataShowTrial.getStatus()==1){
                    binding.text.setText(""+dataShowTrial.getData().getClient().getName());
                    binding.textposation.setText(""+dataShowTrial.getData().getClient().getType_of_job());
                  phone= dataShowTrial.getData().getClient().getMobile();
                  binding.username.setText(""+dataShowTrial.getData().getClient().getMobile());
                    binding.numberPhone.setText(""+dataShowTrial.getData().getClient().getEmail());
                    binding.textAddrass.setText(""+dataShowTrial.getData().getClient().getCity()
                    +""+dataShowTrial.getData().getClient().getDistrict()+""+dataShowTrial.getData().getClient().getStreet_name()
                    +""+dataShowTrial.getData().getClient().getBuilding_number());
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