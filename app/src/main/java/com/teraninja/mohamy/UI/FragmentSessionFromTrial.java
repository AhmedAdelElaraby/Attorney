package com.teraninja.mohamy.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.mohamy.DataClient.OnClickId;
import com.teraninja.mohamy.Model.DataShowSessionDetilse;
import com.teraninja.mohamy.Model.DataSittingsOfTrial;
import com.teraninja.mohamy.R;
import com.teraninja.mohamy.SessionsDirections;
import com.teraninja.mohamy.Show;
import com.teraninja.mohamy.ShowDetilseTrialDirections;
import com.teraninja.mohamy.UI.Main.AdapterSittingLastandNext;
import com.teraninja.mohamy.UI.Main.AdapterSittingNextandLast;
import com.teraninja.mohamy.databinding.FragmentSessionFromTrialBinding;


public class FragmentSessionFromTrial extends Fragment implements OnClickId {
    FragmentSessionFromTrialBinding binding;
    String id;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int cheeck = 0;

    public FragmentSessionFromTrial() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_session_from_trial, container, false);
        preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token", "no");
        id = preferences.getString("id", "no");
        model = ViewModelProviders.of(this).get(MoveViewModel.class);
        if (id != "no") {
            model.SittingsOfTrial("Bearer" + Token, Integer.parseInt(id));
            Toast.makeText(getContext(), "" + id, Toast.LENGTH_SHORT).show();
            binding.rec2.setVisibility(View.VISIBLE);
            binding.rec.setVisibility(View.GONE);
        } else {

        }
        binding.DataType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheeck = 0;
                binding.rec2.setVisibility(View.VISIBLE);
                binding.rec.setVisibility(View.GONE);
                binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                binding.case3.setBackground(getResources().getDrawable(R.drawable.item_filter));
                model.SittingsOfTrial("Bearer" + Token, Integer.parseInt(id));
            }
        });
        binding.case3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheeck = 1;
                binding.rec.setVisibility(View.VISIBLE);
                binding.rec2.setVisibility(View.GONE);

                binding.case3.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_filter));
                model.SittingsOfTrial("Bearer" + Token, Integer.parseInt(id));
            }
        });
        AdapterSittingLastandNext sittingLastandNext = new AdapterSittingLastandNext(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rec.setAdapter(sittingLastandNext);
        AdapterSittingNextandLast nextandLast = new AdapterSittingNextandLast(this);
        binding.rec2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rec2.setAdapter(nextandLast);
        model.SittingsOfTrial.observe(getViewLifecycleOwner(), new Observer<DataSittingsOfTrial>() {
            @Override
            public void onChanged(DataSittingsOfTrial dataSittingsOfTrial) {
                if (dataSittingsOfTrial.getStatus() == 1) {
                    if (cheeck == 0) {
                        Toast.makeText(getContext(), "next" + dataSittingsOfTrial.getData().getNext_sitings().size(), Toast.LENGTH_SHORT).show();
                        nextandLast.getList(dataSittingsOfTrial.getData().getNext_sitings());

                    } else {
                        Toast.makeText(getContext(), "last" + dataSittingsOfTrial.getData().getLast_sitings().size(), Toast.LENGTH_SHORT).show();

                        sittingLastandNext.getList(dataSittingsOfTrial.getData().getLast_sitings());
                    }

                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void getId(int id) {
        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();
        DilogeShow(id);
    }

    public void DilogeShow(int id) {
        model.SessionsDetilseShow("Bearer" + Token, id);
        Dialog myDialog;
        TextView currantname, textcase, textnameoffes, textnuber, textnamepacker, textnuberlocation, textnameqadey, textdaeara, typehajra, typemeladey, times, descrotion;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_detilse_session);
        currantname = myDialog.findViewById(R.id.textissname);
        textcase = myDialog.findViewById(R.id.textcase);
        textnameoffes = myDialog.findViewById(R.id.textnameoffes);
        textnuber = myDialog.findViewById(R.id.textnuber);
        textnamepacker = myDialog.findViewById(R.id.textnamepacker);
        textnuberlocation = myDialog.findViewById(R.id.textnuberlocation);
        textnameqadey = myDialog.findViewById(R.id.textnameqadey);
        textdaeara = myDialog.findViewById(R.id.textdaeara);
        typehajra = myDialog.findViewById(R.id.typehajra);
        typemeladey = myDialog.findViewById(R.id.typemeladey);
        times = myDialog.findViewById(R.id.time);
        descrotion = myDialog.findViewById(R.id.descrotion);
        model.DetilseSession.observe(getViewLifecycleOwner(), new Observer<DataShowSessionDetilse>() {
            @Override
            public void onChanged(DataShowSessionDetilse dataShowSessionDetilse) {
                if (dataShowSessionDetilse.getStatus() == 1) {
                    currantname.setText("" + dataShowSessionDetilse.getData().getTrial().getTitle());
                    textcase.setText("" + dataShowSessionDetilse.getData().getTrial().getType().getName());
                    textnameoffes.setText("اسم المكلف" + ":" + dataShowSessionDetilse.getData().getEmployee().getName());
                    textnuber.setText("رقم الجلسه" + ":" + dataShowSessionDetilse.getData().getSitting_no());
                    textnamepacker.setText("" + dataShowSessionDetilse.getData().getCourt_name());
                    textnuberlocation.setText("" + dataShowSessionDetilse.getData().getCity());
                    textnameqadey.setText("اسم القاضي" + ":" + dataShowSessionDetilse.getData().getJudge_name());
                    textdaeara.setText("" + dataShowSessionDetilse.getData().getCircle_name());
                    typehajra.setText("" + dataShowSessionDetilse.getData().getHijri());
                    typemeladey.setText("" + dataShowSessionDetilse.getData().getDate());
                    times.setText("" + dataShowSessionDetilse.getData().getTime());

                }

                Toast.makeText(getContext(), "" + dataShowSessionDetilse.getData().circle_name, Toast.LENGTH_SHORT).show();
            }
        });


        descrotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                Intent intent = new Intent(getContext(), Show.class);
               intent.putExtra("id",id);
                startActivity(intent);

            }
        });
        Window window = myDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);


        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.create();
        myDialog.show();
    }
}