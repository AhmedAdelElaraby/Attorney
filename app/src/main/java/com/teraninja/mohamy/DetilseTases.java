package com.teraninja.mohamy;

import android.app.Dialog;
import android.content.Context;
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

import com.teraninja.mohamy.DataClient.OnClickgetDetilse;
import com.teraninja.mohamy.Model.DataDetilseTaskes;
import com.teraninja.mohamy.Model.DataShowSessionDetilse;
import com.teraninja.mohamy.Model.SendDetilseTaskes;
import com.teraninja.mohamy.UI.Main.Adapter_DetilseTaskes;
import com.teraninja.mohamy.UI.MoveViewModel;
import com.teraninja.mohamy.databinding.FragmentDetilseTasesBinding;


public class DetilseTases extends Fragment implements OnClickgetDetilse {
FragmentDetilseTasesBinding binding;
    MoveViewModel model;
    String Token;
    SharedPreferences preferences;
    int id;
    public DetilseTases() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_detilse_tases, container, false);
        preferences=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Token = preferences.getString("token","no");
        model= ViewModelProviders.of(this).get(MoveViewModel.class);
        if (getArguments()!=null){
            DetilseTasesArgs args =DetilseTasesArgs.fromBundle(getArguments());
            id= args.getId();
            SendDetilseTaskes Trialfilter = new SendDetilseTaskes();

            model.DeilseTaskes("Bearer"+Token,id,Trialfilter);
        }

        Adapter_DetilseTaskes taskes = new Adapter_DetilseTaskes(this);
        binding.rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rec.setAdapter(taskes);

        model.DeilseTases.observe(getViewLifecycleOwner(), new Observer<DataDetilseTaskes>() {
            @Override
            public void onChanged(DataDetilseTaskes dataDetilseTaskes) {
                taskes.getList(dataDetilseTaskes.getData());
            }
        });
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetilseTasesDirections.ActionDetilseTasesToFilterDetilseTaskes tases=DetilseTasesDirections.actionDetilseTasesToFilterDetilseTaskes();
                tases.setId(id);
                Navigation.findNavController(binding.getRoot()).navigate(tases);
            }
        });
        return binding.getRoot();
    }
    public void DilogeShow(int id){
        model.SessionsDetilseShow("Bearer"+Token,id);
        Dialog myDialog;
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
                DetilseTasesDirections.ActionDetilseTasesToShowDetilseSession sessions =DetilseTasesDirections.actionDetilseTasesToShowDetilseSession();
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

    @Override
    public void getDetils(int id) {
        DilogeShow(id);
    }
}