package com.teraninja.mohamy;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teraninja.mohamy.DataClient.OnClickId;
import com.teraninja.mohamy.Model.Custmers;
import com.teraninja.mohamy.Model.DataChengePassword;
import com.teraninja.mohamy.Model.DataCustmers;
import com.teraninja.mohamy.Model.DataSessions;
import com.teraninja.mohamy.Model.DataShowSessionDetilse;
import com.teraninja.mohamy.Model.SendDataCastmerfilter;
import com.teraninja.mohamy.Model.SendDataChengePassword;
import com.teraninja.mohamy.Model.SendDataSessions;
import com.teraninja.mohamy.Model.Sessionsdata;
import com.teraninja.mohamy.UI.Main.AdapterSessions;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentSessionsBinding;

import java.util.ArrayList;


public class Sessions extends Fragment implements OnClickId {
FragmentSessionsBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int page=1;
    Dialog myDialog;
    ArrayList<Sessionsdata> list = new ArrayList<>();
    ArrayList<String> listcase= new ArrayList<>();
    int valopen=0;
    boolean cheeck=true;
    String searchitemcase;
    public Sessions() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_sessions, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        String branchId =preferences.getString("branchId","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        model.Sessions("Bearer"+Token,page,20, Integer.parseInt(branchId));
        AdapterSessions sessions = new AdapterSessions(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(sessions);
        binding.nes.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (cheeck){
                    if (scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                        page++;
                        binding.progress.setVisibility(View.VISIBLE);
                        model.Sessions("Bearer"+Token,page,20, Integer.parseInt(branchId));
                    }}
                    else {

                }
            }
        });
        binding.navcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valopen==0){
                    valopen=1;
                    binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                    binding.case3.setBackground(getResources().getDrawable(R.drawable.item_filter));

                    binding.DataType.setVisibility(View.VISIBLE);
                    binding.case3.setVisibility(View.VISIBLE);
                    binding.cc.setVisibility(View.VISIBLE);
                    binding.nes.setVisibility(View.GONE);
                    binding.Data.setVisibility(View.GONE);
                    binding.space.setVisibility(View.GONE);
                    binding.typefile.setVisibility(View.GONE);
                    binding.space2.setVisibility(View.GONE);
                    binding.case2.setVisibility(View.GONE);
                    binding.view.setVisibility(View.GONE);

                }else {
                    valopen=0;
                    cheeck=true;
                    model.Sessions("Bearer"+Token,page,20, Integer.parseInt(branchId));
                    binding.DataType.setVisibility(View.GONE);
                    binding.case3.setVisibility(View.GONE);
                    binding.cc.setVisibility(View.GONE);
                    binding.nes.setVisibility(View.VISIBLE);
                    binding.Data.setVisibility(View.VISIBLE);
                    binding.space.setVisibility(View.VISIBLE);
                    binding.typefile.setVisibility(View.VISIBLE);
                    binding.space2.setVisibility(View.VISIBLE);
                    binding.case2.setVisibility(View.VISIBLE);
                    binding.view.setVisibility(View.VISIBLE);
                    binding.l1.setVisibility(View.GONE);
                }
            }
        });
        binding.editfilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cheeck=false;
                valopen=0;
                binding.DataType.setVisibility(View.GONE);
                binding.case3.setVisibility(View.GONE);
                binding.cc.setVisibility(View.GONE);
                binding.nes.setVisibility(View.VISIBLE);
                binding.Data.setVisibility(View.VISIBLE);
                binding.space.setVisibility(View.VISIBLE);
                binding.typefile.setVisibility(View.VISIBLE);
                binding.space2.setVisibility(View.VISIBLE);
                binding.case2.setVisibility(View.VISIBLE);
                binding.view.setVisibility(View.VISIBLE);
                binding.l1.setVisibility(View.GONE);
                String text= String.valueOf(s).trim();
                if (text.isEmpty()){
                    model.Sessions("Bearer"+Token,page,20, Integer.parseInt(branchId));

                }
                if (text.equals("")){
                    model.Sessions("Bearer"+Token,page,20, Integer.parseInt(branchId));

                }
                else {
                    SendDataSessions castmerfilter = new SendDataSessions();
                    castmerfilter.setFilter(text);
                    model.Sessionsfilter("Bearer" + Token, Integer.parseInt(branchId), castmerfilter);
                }

            }
        });
        model.Sessionsfilter.observe(getViewLifecycleOwner(), new Observer<DataSessions>() {
            @Override
            public void onChanged(DataSessions dataCustmers) {
                sessions.getList(dataCustmers.getData());
            }
        });
        binding.DataType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                binding.case3.setBackground(getResources().getDrawable(R.drawable.item_filter));
                binding.l1.setVisibility(View.GONE);
                binding.cc.setVisibility(View.VISIBLE);
                binding.nes.setVisibility(View.GONE);
                binding.Data.setVisibility(View.GONE);
                binding.space.setVisibility(View.GONE);
                binding.typefile.setVisibility(View.GONE);
                binding.space2.setVisibility(View.GONE);
                binding.case2.setVisibility(View.GONE);
                binding.view.setVisibility(View.GONE);
            }
        });
        binding.case3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.case3.setBackground(getResources().getDrawable(R.drawable.item_btn_register));
                binding.DataType.setBackground(getResources().getDrawable(R.drawable.item_filter));
                binding.l1.setVisibility(View.VISIBLE);
                binding.cc.setVisibility(View.GONE);
                binding.nes.setVisibility(View.VISIBLE);
                binding.Data.setVisibility(View.VISIBLE);
                binding.space.setVisibility(View.VISIBLE);
                binding.typefile.setVisibility(View.VISIBLE);
                binding.space2.setVisibility(View.VISIBLE);
                binding.case2.setVisibility(View.VISIBLE);
                binding.view.setVisibility(View.VISIBLE);
            }
        });
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromday=binding.fromday.getText().toString().trim();
                String frommins=binding.frommanth.getText().toString().trim();
                String fromyears= binding.fromyears.getText().toString().trim();
                String today=binding.today.getText().toString().trim();
                String tomanth=binding.tomanth.getText().toString().trim();
                String toyears = binding.toyears.getText().toString().trim();
                if (fromday.isEmpty()){
                    binding.fromday.setError("");
                    return;
                }
                if (frommins.isEmpty()){
                    binding.frommanth.setError("");
                    return;
                }
                if (fromyears.isEmpty()){
                    binding.fromyears.setError("");
                    return;
                }
                if (today.isEmpty()){
                    binding.today.setError("");
                    return;
                }
                if (tomanth.isEmpty()){
                    binding.tomanth.setError("");
                    return;
                }
                if (toyears.isEmpty()){
                    binding.toyears.setError("");
                    return;
                }
                else{
                    cheeck=false;
                    valopen=0;
                    binding.DataType.setVisibility(View.GONE);
                    binding.case3.setVisibility(View.GONE);
                    binding.cc.setVisibility(View.GONE);
                    binding.nes.setVisibility(View.VISIBLE);
                    binding.Data.setVisibility(View.VISIBLE);
                    binding.space.setVisibility(View.VISIBLE);
                    binding.typefile.setVisibility(View.VISIBLE);
                    binding.space2.setVisibility(View.VISIBLE);
                    binding.case2.setVisibility(View.VISIBLE);
                    binding.view.setVisibility(View.VISIBLE);
                    binding.l1.setVisibility(View.GONE);
                    Toast.makeText(getContext(), ""+fromday+"/"+frommins+"/"+fromyears, Toast.LENGTH_SHORT).show();
                    SendDataSessions castmerfilter = new SendDataSessions();
                    castmerfilter.setStart_date(fromday+"/"+frommins+"/"+fromyears);
                    castmerfilter.setEnd_date(today+"/"+tomanth+"/"+toyears);
                    model.Sessionsfilter("Bearer"+Token, Integer.parseInt(branchId),castmerfilter);

                }
            }
        });
        listcase.clear();
        listcase.add("منتهي");
        listcase.add("غير منتهي");
        ArrayAdapter<String> filtercase = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,listcase);
        binding.spinner.setAdapter(filtercase);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cheeck=false;
                String item= binding.spinner.getSelectedItem().toString();
                switch (item){
                    case "منتهي":
                        searchitemcase="1";
                        break;
                    case "غير منتهي":
                        searchitemcase="0";
                        break;
                }
                SendDataSessions castmerfilter = new SendDataSessions();
                castmerfilter.setIs_done(searchitemcase);
                model.Sessionsfilter("Bearer"+Token, Integer.parseInt(branchId),castmerfilter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        model.Sessions.observe(getViewLifecycleOwner(), new Observer<DataSessions>() {
           @Override
           public void onChanged(DataSessions dataSessions) {
               list.clear();
               for (int i=0;i<dataSessions.getData().size();i++){
                   list.add(dataSessions.getData().get(i));
               }
               binding.progress.setVisibility(View.GONE);
               sessions.getList(list);
           }
       });
       return binding.getRoot();
    }

    @Override
    public void getId(int id) {
        DilogeShow(id);
    }


    public void DilogeShow(int id){
        model.SessionsDetilseShow("Bearer"+Token,id);

        TextView currantname,textcase,textnameoffes,textnuber,textnamepacker,textnuberlocation
                ,textnameqadey,textdaeara,typehajra,typemeladey,times,descrotion;
        myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.item_detilse_session);
        currantname=myDialog.findViewById(R.id.textissname);
        textcase=myDialog.findViewById(R.id.textcase);
        textnameoffes=myDialog.findViewById(R.id.textnameoffes);
        textnuber=myDialog.findViewById(R.id.textnuber);
        textnamepacker=myDialog.findViewById(R.id.textnamepacker);
        textnuberlocation=myDialog.findViewById(R.id.textnuberlocation);
        textnameqadey=myDialog.findViewById(R.id.textnameqadey);
        textdaeara=myDialog.findViewById(R.id.textdaeara);
        typehajra=myDialog.findViewById(R.id.typehajra);
        typemeladey=myDialog.findViewById(R.id.typemeladey);
        times=myDialog.findViewById(R.id.time);
        descrotion=myDialog.findViewById(R.id.descrotion);
        model.DetilseSession.observe(getViewLifecycleOwner(), new Observer<DataShowSessionDetilse>() {
            @Override
            public void onChanged(DataShowSessionDetilse dataShowSessionDetilse) {
                if (dataShowSessionDetilse.getStatus()==1){
                    currantname.setText(""+dataShowSessionDetilse.getData().getTrial().getTitle());
                    textcase.setText(""+dataShowSessionDetilse.getData().getTrial().getType().getName());
                    textnameoffes.setText("اسم المكلف"+":"+dataShowSessionDetilse.getData().getEmployee().getName());
                    textnuber.setText("رقم الجلسه"+":"+dataShowSessionDetilse.getData().getSitting_no());
                    textnamepacker.setText(""+dataShowSessionDetilse.getData().getCourt_name());
                    textnuberlocation.setText(""+dataShowSessionDetilse.getData().getCity());
                    textnameqadey.setText("اسم القاضي"+":"+dataShowSessionDetilse.getData().getJudge_name());
                    textdaeara.setText(""+dataShowSessionDetilse.getData().getCircle_name());
                    typehajra.setText(""+dataShowSessionDetilse.getData().getHijri());
                    typemeladey.setText(""+dataShowSessionDetilse.getData().getDate());
                    times.setText(""+dataShowSessionDetilse.getData().getTime());

                }

                Toast.makeText(getContext(), ""+dataShowSessionDetilse.getData().circle_name, Toast.LENGTH_SHORT).show();
            }
        });


        descrotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                SessionsDirections.ActionSessionsToShowDetilseSession sessions =SessionsDirections.actionSessionsToShowDetilseSession();
                sessions.setId(id);
                Navigation.findNavController(binding.getRoot()).navigate(sessions);
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