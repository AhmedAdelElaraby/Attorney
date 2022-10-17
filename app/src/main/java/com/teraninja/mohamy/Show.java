package com.teraninja.mohamy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.teraninja.mohamy.Model.DataShowSessionDetilse;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.ActivityShowBinding;

public class Show extends AppCompatActivity {
ActivityShowBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_show);
        preferences=getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
       int id =getIntent().getIntExtra("id",0);
        if (id!=0) {
            model.SessionsDetilseShow("Bearer" + Token, id);
        }else {

            Toast.makeText(this, "this is id null", Toast.LENGTH_SHORT).show();
        }

        binding.navcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        model.DetilseSession.observe(this, new Observer<DataShowSessionDetilse>() {
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
                Toast.makeText(Show.this, ""+dataShowSessionDetilse.getData().circle_name, Toast.LENGTH_SHORT).show();
            }
        });


    }
}