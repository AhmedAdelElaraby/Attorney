package com.teraninja.mohamy.DataClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teraninja.mohamy.Model.DataAddConslout;
import com.teraninja.mohamy.Model.DataAdminRequste;
import com.teraninja.mohamy.Model.DataAllTask;
import com.teraninja.mohamy.Model.DataCall;
import com.teraninja.mohamy.Model.DataCheeckCode;
import com.teraninja.mohamy.Model.DataChengePassword;
import com.teraninja.mohamy.Model.DataCity;
import com.teraninja.mohamy.Model.DataConsolut;
import com.teraninja.mohamy.Model.DataConsultingsModel;
import com.teraninja.mohamy.Model.DataCustmers;
import com.teraninja.mohamy.Model.DataDetilseMaliaya;
import com.teraninja.mohamy.Model.DataDetilseTaskes;
import com.teraninja.mohamy.Model.DataELMalaya;
import com.teraninja.mohamy.Model.DataHome;
import com.teraninja.mohamy.Model.DataLoginEmploye;
import com.teraninja.mohamy.Model.DataMalyaFromTrial;
import com.teraninja.mohamy.Model.DataModelCode;
import com.teraninja.mohamy.Model.DataOppenents;
import com.teraninja.mohamy.Model.DataSalary;
import com.teraninja.mohamy.Model.DataSendClientLogin;
import com.teraninja.mohamy.Model.DataSendRegister;
import com.teraninja.mohamy.Model.DataSendnumberandCode;
import com.teraninja.mohamy.Model.DataSessions;
import com.teraninja.mohamy.Model.DataShowDetilslssues;
import com.teraninja.mohamy.Model.DataShowSessionDetilse;
import com.teraninja.mohamy.Model.DataShowTrial;
import com.teraninja.mohamy.Model.DataSittingsOfTrial;
import com.teraninja.mohamy.Model.DataTrial;
import com.teraninja.mohamy.Model.ModelDataClientLogin;
import com.teraninja.mohamy.Model.ModelDataRegister;
import com.teraninja.mohamy.Model.SendCode;
import com.teraninja.mohamy.Model.SendConslout;
import com.teraninja.mohamy.Model.SendDataCastmerfilter;
import com.teraninja.mohamy.Model.SendDataChengePassword;
import com.teraninja.mohamy.Model.SendDataForRseedPassword;
import com.teraninja.mohamy.Model.SendDataLogin;
import com.teraninja.mohamy.Model.SendDataSessions;
import com.teraninja.mohamy.Model.SendDataTrial;
import com.teraninja.mohamy.Model.SendDatacall;
import com.teraninja.mohamy.Model.SendDetilseTaskes;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    InterfaceAPI interFaceAPI;
    private static final String BASE_URL="https://order.teraninjadev.com/";
    public static RetrofitAPI instans;

    public RetrofitAPI() {

        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        interFaceAPI=retrofit.create(InterfaceAPI.class);

    }


    public static RetrofitAPI getInstans() {

        if (null==instans){
            instans=new RetrofitAPI();}
        return instans;
    }
    public Observable<DataLoginEmploye> LoginEmploye(SendDataLogin dataLogin){

        return interFaceAPI.LoginEmploye(dataLogin);
    }
    public Observable<DataHome> Home(String Token){

        return interFaceAPI.Home(Token);
    }
    public Observable<DataSalary> Salary(String Token){

        return interFaceAPI.Salary(Token);
    }
    public Observable<DataCustmers> Custemers(String Token, int page, int limit){

        return interFaceAPI.Castmers(Token,page,limit);
    }

    public Observable<DataChengePassword> ChangePasswordemploye(String Token , SendDataChengePassword  chengePassword){

        return interFaceAPI.chengepasswordemoloye(Token,chengePassword);
    }
    public Observable<DataAdminRequste> AdminRequste(String Token ,int page,int limit,int brinch_id){

        return interFaceAPI.Adminrequste(Token,page,limit,brinch_id);
    }
    public Observable<DataCustmers> Custemersfilter(String Token, SendDataCastmerfilter castmerfilter){

        return interFaceAPI.Castmersfilter(Token,castmerfilter);
    }

    public Observable<DataShowDetilslssues> DetilseLsses(String Token ,int ClientId, int page, int limit){

        return interFaceAPI.DetilseLsses(Token,ClientId,page,limit);
    }



    public Observable<DataSessions> Sessions(String Token , int page, int limit, int brinch_id){

        return interFaceAPI.Sessions(Token,page,limit,brinch_id);
    }
    public Observable<DataSessions> Sessionsfilter(String Token , int brinch_id, SendDataSessions sessions){

        return interFaceAPI.Sessionsfilter(Token,brinch_id,sessions);
    }

    public Observable<DataShowSessionDetilse> DetilseSessionshow(String Token , int brinch_id){

        return interFaceAPI.SessionsshowDetilse(Token,brinch_id);
    }

    public Observable<DataCity> ollcity(String Token ){

        return interFaceAPI.ollcity(Token);
    }

    public Observable<DataTrial> allTrial(String Token , int page, int limit, int brinch_id){

        return interFaceAPI.allTrial(Token,page,limit,brinch_id);
    }
    public Observable<DataTrial> filterTrial(String Token , int brinch_id, SendDataTrial dataTrial){

        return interFaceAPI.filterTrial(Token,brinch_id,dataTrial);
    }


    public Observable<DataShowTrial> ShowTrial(String Token , int id){

        return interFaceAPI.ShowTrial(Token,id);
    }
    public  Observable<DataOppenents> Oppenents(String Token , int id){

        return interFaceAPI.Oppenents(Token,id);
    }

    public Observable<DataSittingsOfTrial> sittings_of_trial(String Token , int id){

        return interFaceAPI.sittings_of_trial(Token,id);
    }
    public Observable<DataAllTask> AllTask (String Token , int page, int limit, int brinch_id){

        return interFaceAPI.AllTask(Token,page,limit,brinch_id);
    }
    public Observable<DataMalyaFromTrial> MalyaFromTrail (String Token , int id){

        return interFaceAPI.MalyaFromTrail(Token,id);
    }

    public Observable<DataELMalaya> Elmalya (String Token , int page, int limit){

        return interFaceAPI.Elmalya(Token,page,limit);
    }
    public Observable<DataDetilseMaliaya> DetilseElmalya (String Token , int clinetId){

        return interFaceAPI.DetilseElmalya(Token,clinetId);
    }

    public Observable<ModelDataRegister> Register (DataSendRegister register){

        return interFaceAPI.Register(register);
    }

    public Observable<DataCheeckCode> CheeckCode (DataSendnumberandCode code){

        return interFaceAPI.checkCode(code);
    }
    public Observable<ModelDataClientLogin> ClientLogin (DataSendClientLogin clientLogin){

        return interFaceAPI.ClientLogin(clientLogin);
    }
    public Observable<DataModelCode> SendCode (SendCode clientLogin){

        return interFaceAPI.SendCod(clientLogin);
    }
    public Observable<DataModelCode> forgetpassword (SendDataForRseedPassword clientLogin){

        return interFaceAPI.forgetpassword(clientLogin);
    }
    public Observable<DataHome> HomeClient(String Token){

        return interFaceAPI.HomeClient(Token);
    }
    public Observable<DataAddConslout> Add(String Token, SendConslout conslout){

        return interFaceAPI.Add(Token,conslout);
    }

    public Observable<DataConsultingsModel> getallConsulings(String Token){

        return interFaceAPI.getallConsulings(Token);
    }

    public Observable<DataDetilseTaskes> DetilseTaskes(String Token, int id, SendDetilseTaskes taskes){

        return interFaceAPI.DetilseTaskes(Token,id,taskes);
    }
    public Observable<DataCall> call (String Token , int page, int limit){

        return interFaceAPI.call(Token,page,limit);
    }
    public Observable<DataCall> callfiltering (String Token , SendDatacall datacall){

        return interFaceAPI.callfiltering(Token,datacall);
    }
}
