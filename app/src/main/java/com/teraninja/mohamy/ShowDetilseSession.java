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

import com.teraninja.mohamy.Model.DataShowSessionDetilse;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentShowDetilseSessionBinding;

public class ShowDetilseSession extends Fragment {
FragmentShowDetilseSessionBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;

    public ShowDetilseSession() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_detilse_session, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        if (getArguments()!=null){
        ShowDetilseSessionArgs  args = ShowDetilseSessionArgs.fromBundle(getArguments());
            model.SessionsDetilseShow("Bearer"+Token,args.getId());

        }
        binding.navcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showDetilseSession_to_homeEmployee);
            }
        });
        model.DetilseSession.observe(getViewLifecycleOwner(), new Observer<DataShowSessionDetilse>() {
            @Override
            public void onChanged(DataShowSessionDetilse dataShowSessionDetilse) {
                if (dataShowSessionDetilse.getStatus()==1){
                    binding.textissname.setText("رقم الملف"+dataShowSessionDetilse.getData().getTrial().getTrial_code());
                    binding.textcase.setText("رقم القضيه"+dataShowSessionDetilse.getData().getTrial().getNo_of_trial());
                    binding.wqaeasession.setText("وقائع الجلسة");
                    binding.descrptionsession.setText(""+dataShowSessionDetilse.getData().getCourt_ruling());
                    binding.descrptionnext.setText(""+dataShowSessionDetilse.getData().getNext_discussion());
                    binding.descrptionnextqadema.setText(""+dataShowSessionDetilse.getData().getNext_sitting_recommendations());
                }

                Toast.makeText(getContext(), ""+dataShowSessionDetilse.getData().circle_name, Toast.LENGTH_SHORT).show();
            }
        });


        return binding.getRoot();

    }
}