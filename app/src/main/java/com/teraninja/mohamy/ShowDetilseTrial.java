package com.teraninja.mohamy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teraninja.mohamy.Model.DataShowTrial;
import com.teraninja.mohamy.UI.FragmentElmodayeAlay;
import com.teraninja.mohamy.UI.FragmentSessionFromTrial;
import com.teraninja.mohamy.UI.Main.FragmentModaey;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentShowDetilseTrialBinding;


public class ShowDetilseTrial extends Fragment {
FragmentShowDetilseTrialBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
   int id;
    public ShowDetilseTrial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_detilse_trial, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
       if (getArguments()!=null){
           ShowDetilseTrialArgs args =ShowDetilseTrialArgs.fromBundle(getArguments());
            id=args.getId();
           model.ShowTrial("Bearer"+Token,id);
           savedata("id", String.valueOf(id));
           binding.user.setBackground(getResources().getDrawable(R.drawable.item_filter));
           binding.elkhsem.setBackground(null);
       }
        NavHostFragment navHostFragment =(NavHostFragment) getChildFragmentManager().findFragmentById(binding.frem.getId());
        NavController navController = navHostFragment.getNavController();
        binding.user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.user.setBackground(getResources().getDrawable(R.drawable.item_filter));
                binding.elkhsem.setBackground(null);
                binding.session.setBackground(null);
                binding.malya.setBackground(null);


                NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.fragmentModaey, false).build();

                navController.navigate(R.id.fragmentModaey,savedInstanceState,builder1);
//                getChildFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.frem, FragmentModaey.class,null)
//                        .commit();

            }
        });

       binding.elkhsem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               binding.elkhsem.setBackground(getResources().getDrawable(R.drawable.item_filter));
               binding.user.setBackground(null);
               binding.malya.setBackground(null);
               binding.session.setBackground(null);

               NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.fragmentElmodayeAlay, true).build();

               navController.navigate(R.id.fragmentElmodayeAlay,savedInstanceState,builder1);
//               getChildFragmentManager().beginTransaction().setReorderingAllowed(true)
//                       .replace(R.id.frem,  FragmentElmodayeAlay.class,null).commit();
           }
       });
       binding.session.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               binding.session.setBackground(getResources().getDrawable(R.drawable.item_filter));
               binding.user.setBackground(null);
               binding.elkhsem.setBackground(null);
               binding.malya.setBackground(null);

               NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.fragmentSessionFromTrial, true).build();
               navController.navigate(R.id.fragmentSessionFromTrial,savedInstanceState,builder1);

           }
       });
binding.malya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.malya.setBackground(getResources().getDrawable(R.drawable.item_filter));
                binding.user.setBackground(null);
                binding.elkhsem.setBackground(null);
                binding.session.setBackground(null);

                NavOptions builder1 = new NavOptions.Builder().setPopUpTo(R.id.fragmentMalyaFromTrial, true).build();
                navController.navigate(R.id.fragmentMalyaFromTrial,savedInstanceState,builder1);

            }
        });
       model.ShowTrial.observe(getViewLifecycleOwner(), new Observer<DataShowTrial>() {
           @Override
           public void onChanged(DataShowTrial dataShowTrial) {
                if(dataShowTrial.getStatus()==1){
                 binding.textissname.setText(""+dataShowTrial.getData().getTitle());
                 binding.textcase.setText(""+dataShowTrial.getData().getType().getName());
                 binding.descrption.setText(""+dataShowTrial.getData().getDescription());
                 binding.numberfile.setText("رقم الملف : "+dataShowTrial.getData().getTrial_code());
                 binding.numbertrila.setText("رقم القضيه : "+dataShowTrial.getData().getNo_of_trial());
                 binding.typehirea.setText(""+dataShowTrial.getData().getHijri());
                 binding.typemeladey.setText(""+dataShowTrial.getData().getYear());
                }
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