package com.teraninja.mohamy.UI;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.teraninja.mohamy.DataClient.RetrofitAPI;
import com.teraninja.mohamy.Model.DataAddConslout;
import com.teraninja.mohamy.Model.DataAdminRequste;
import com.teraninja.mohamy.Model.DataAllTask;
import com.teraninja.mohamy.Model.DataCall;
import com.teraninja.mohamy.Model.DataCheeckCode;
import com.teraninja.mohamy.Model.DataChengePassword;
import com.teraninja.mohamy.Model.DataCity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MoveViewModel extends ViewModel {

    public MutableLiveData<DataLoginEmploye> LoginEmoloye = new MutableLiveData();
    public MutableLiveData<DataHome> Home = new MutableLiveData();
    public MutableLiveData<DataHome> HomeClient= new MutableLiveData();

    public MutableLiveData<DataSalary> salary = new MutableLiveData<>();
    public MutableLiveData<DataCustmers> Custmers = new MutableLiveData<>();
    public MutableLiveData<DataChengePassword> chengePassword = new MutableLiveData<>();
    public MutableLiveData<DataAdminRequste> dataAdminRequste= new MutableLiveData<>();
    public MutableLiveData<DataCustmers> Custmersfilter = new MutableLiveData<>();
    public MutableLiveData<DataShowDetilslssues> DetilsLsses= new MutableLiveData<>();
    public MutableLiveData<DataSessions> Sessions= new MutableLiveData<>();
    public MutableLiveData<DataSessions> Sessionsfilter= new MutableLiveData<>();
    public MutableLiveData<DataShowSessionDetilse> DetilseSession=new MutableLiveData<>();
    public MutableLiveData<DataCity> city= new MutableLiveData<>();
    public MutableLiveData<DataTrial> allTeial= new MutableLiveData<>();
    public MutableLiveData<DataTrial> filterTeial= new MutableLiveData<>();
    public MutableLiveData<DataShowTrial> ShowTrial= new MutableLiveData<>();
    public MutableLiveData<DataOppenents> Oppenents = new MutableLiveData<>();
    public MutableLiveData<DataSittingsOfTrial>  SittingsOfTrial = new MutableLiveData<>();
    public MutableLiveData<DataAllTask> AllTask = new MutableLiveData<>();
    public MutableLiveData<DataMalyaFromTrial> MalyaFromTrial = new MutableLiveData<>();
    public MutableLiveData<DataELMalaya> Elmalya = new MutableLiveData<>();
    public MutableLiveData<DataDetilseMaliaya> DetilseElmalya = new MutableLiveData<>();
    public MutableLiveData<ModelDataRegister> Register = new MutableLiveData<>();
    public MutableLiveData<DataCheeckCode> Cheeckcode = new MutableLiveData<>();
    public MutableLiveData<ModelDataClientLogin> ClientLogin = new MutableLiveData<>();
    public MutableLiveData<DataModelCode> SendCode = new MutableLiveData<>();
    public MutableLiveData<DataModelCode> Frogetpassword = new MutableLiveData<>();
    public MutableLiveData<DataAddConslout> Add = new MutableLiveData<>();
    public MutableLiveData<DataConsultingsModel> getallConsulings = new MutableLiveData<>();
    public MutableLiveData<DataDetilseTaskes> DeilseTases = new MutableLiveData<>();
    public MutableLiveData<DataCall> call = new MutableLiveData<>();
    public MutableLiveData<DataCall> callfiltring = new MutableLiveData<>();


    public void login(SendDataLogin dataLogin){
        Observable observable = RetrofitAPI.getInstans().LoginEmploye(dataLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                LoginEmoloye.setValue((DataLoginEmploye) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataLoginEmploye data = new DataLoginEmploye(object.getInt("status"), object.getString("message"));
                        LoginEmoloye.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Home(String Token){
        Observable observable = RetrofitAPI.getInstans().Home(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Home.setValue((DataHome) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataHome data = new DataHome(object.getInt("status"), object.getString("message"));
                        Home.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void HomeClient(String Token){
        Observable observable = RetrofitAPI.getInstans().HomeClient(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                HomeClient.setValue((DataHome) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataHome data = new DataHome(object.getInt("status"), object.getString("message"));
                        HomeClient.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void Salary(String Token){
        Observable observable = RetrofitAPI.getInstans().Salary(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                salary.setValue((DataSalary) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataSalary data = new DataSalary(object.getInt("status"), object.getString("message"));
                        salary.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Custmers(String Token,int page,int limit){
        Observable observable = RetrofitAPI.getInstans().Custemers(Token,page,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Custmers.setValue((DataCustmers) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCustmers data = new DataCustmers(object.getInt("status"), object.getString("message"));
                        Custmers.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Chengepassword(String Token, SendDataChengePassword sendchengePassword){
        Observable observable = RetrofitAPI.getInstans().ChangePasswordemploye(Token,sendchengePassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                chengePassword.setValue((DataChengePassword) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataChengePassword data = new DataChengePassword(object.getInt("status"), object.getString("message"));
                        chengePassword.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void AdminRequste(String Token, int page,int limit,int brinch_id){
        Observable observable = RetrofitAPI.getInstans().AdminRequste(Token,page,limit,brinch_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                dataAdminRequste.setValue((DataAdminRequste) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataAdminRequste data = new DataAdminRequste(object.getInt("status"), object.getString("message"));
                        dataAdminRequste.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Custmersfilter(String Token, SendDataCastmerfilter castmerfilter){
        Observable observable = RetrofitAPI.getInstans().Custemersfilter(Token,castmerfilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Custmersfilter.setValue((DataCustmers) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCustmers data = new DataCustmers(object.getInt("status"), object.getString("message"));
                        Custmersfilter.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void DetilsLsses(String Token,int ClientId, int page,int limit){
        Observable observable = RetrofitAPI.getInstans().DetilseLsses(Token,ClientId,page,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                DetilsLsses.setValue((DataShowDetilslssues) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataShowDetilslssues data = new DataShowDetilslssues(object.getInt("status"), object.getString("message"));
                        DetilsLsses.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Sessions(String Token, int page,int limit,int brinch_id){
        Observable observable = RetrofitAPI.getInstans().Sessions(Token,page,limit,brinch_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Sessions.setValue((DataSessions) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataSessions data = new DataSessions(object.getInt("status"), object.getString("message"));
                        Sessions.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Sessionsfilter(String Token, int brinch_id, SendDataSessions sessions){
        Observable observable = RetrofitAPI.getInstans().Sessionsfilter(Token,brinch_id,sessions)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Sessionsfilter.setValue((DataSessions) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataSessions data = new DataSessions(object.getInt("status"), object.getString("message"));
                        Sessionsfilter.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void SessionsDetilseShow(String Token, int brinch_id){
        Observable observable = RetrofitAPI.getInstans().DetilseSessionshow(Token,brinch_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                DetilseSession.setValue((DataShowSessionDetilse) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataShowSessionDetilse data = new DataShowSessionDetilse(object.getInt("status"), object.getString("message"));
                        DetilseSession.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void ollcity(String Token){
        Observable observable = RetrofitAPI.getInstans().ollcity(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                city.setValue((DataCity) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCity data = new DataCity(object.getInt("status"), object.getString("message"));
                        city.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void allTrial(String Token, int page,int limit,int brinch_id){
        Observable observable = RetrofitAPI.getInstans().allTrial(Token,page,limit,brinch_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                allTeial.setValue((DataTrial) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataTrial data = new DataTrial(object.getInt("status"), object.getString("message"));
                        allTeial.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void filterTrial(String Token, int brinch_id, SendDataTrial dataTrial){
        Observable observable = RetrofitAPI.getInstans().filterTrial(Token,brinch_id,dataTrial)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                filterTeial.setValue((DataTrial) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataTrial data = new DataTrial(object.getInt("status"), object.getString("message"));
                        filterTeial.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void ShowTrial(String Token, int id){
        Observable observable = RetrofitAPI.getInstans().ShowTrial(Token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                ShowTrial.setValue((DataShowTrial) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataShowTrial data = new DataShowTrial(object.getInt("status"), object.getString("message"));
                        ShowTrial.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Oppenents(String Token, int id){
        Observable observable = RetrofitAPI.getInstans().Oppenents(Token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Oppenents.setValue((DataOppenents) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataOppenents data = new DataOppenents(object.getInt("status"), object.getString("message"));
                        Oppenents.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void SittingsOfTrial(String Token, int id){
        Observable observable = RetrofitAPI.getInstans().sittings_of_trial(Token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                SittingsOfTrial.setValue((DataSittingsOfTrial) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataSittingsOfTrial data = new DataSittingsOfTrial(object.getInt("status"), object.getString("message"));
                        SittingsOfTrial.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void AllTask(String Token, int page,int limit,int brinch_id){
        Observable observable = RetrofitAPI.getInstans().AllTask(Token,page,limit,brinch_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                AllTask.setValue((DataAllTask) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataAllTask data = new DataAllTask(object.getInt("status"), object.getString("message"));
                        AllTask.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void MalyaFromTrial(String Token,int id){
        Observable observable = RetrofitAPI.getInstans().MalyaFromTrail(Token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                MalyaFromTrial.setValue((DataMalyaFromTrial) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataMalyaFromTrial data = new DataMalyaFromTrial(object.getInt("status"), object.getString("message"));
                        MalyaFromTrial.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Elmalya(String Token, int page,int limit){
        Observable observable = RetrofitAPI.getInstans().Elmalya(Token,page,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Elmalya.setValue((DataELMalaya) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataELMalaya data = new DataELMalaya(object.getInt("status"), object.getString("message"));
                        Elmalya.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }


    public void DetilseElmalya(String Token,int ClientId){
        Observable observable = RetrofitAPI.getInstans().DetilseElmalya(Token,ClientId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                DetilseElmalya.setValue((DataDetilseMaliaya) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataDetilseMaliaya data = new DataDetilseMaliaya(object.getInt("status"), object.getString("message"));
                        DetilseElmalya.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void Register(DataSendRegister  register){
        Observable observable = RetrofitAPI.getInstans().Register(register)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Register.setValue((ModelDataRegister) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        ModelDataRegister data = new ModelDataRegister(object.getInt("status"), object.getString("message"));
                        Register.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void Cheekcode(DataSendnumberandCode code){
        Observable observable = RetrofitAPI.getInstans().CheeckCode(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Cheeckcode.setValue((DataCheeckCode) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCheeckCode data = new DataCheeckCode(object.getInt("status"), object.getString("message"));
                        Cheeckcode.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void ClientLogin(DataSendClientLogin clientLogin){
        Observable observable = RetrofitAPI.getInstans().ClientLogin(clientLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                ClientLogin.setValue((ModelDataClientLogin) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        ModelDataClientLogin data = new ModelDataClientLogin(object.getInt("status"), object.getString("message"));
                        ClientLogin.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }


    public void SendCode(SendCode clientLogin){
        Observable observable = RetrofitAPI.getInstans().SendCode(clientLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                SendCode.setValue((DataModelCode) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelCode data = new DataModelCode(object.getInt("status"), object.getString("message"));
                        SendCode.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void Forgetpassword(SendDataForRseedPassword clientLogin){
        Observable observable = RetrofitAPI.getInstans().forgetpassword(clientLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Frogetpassword.setValue((DataModelCode) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataModelCode data = new DataModelCode(object.getInt("status"), object.getString("message"));
                        Frogetpassword.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void Add(String Token, SendConslout conslout){
        Observable observable = RetrofitAPI.getInstans().Add(Token,conslout)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Add.setValue((DataAddConslout) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataAddConslout data = new DataAddConslout(object.getInt("status"), object.getString("message"));
                        Add.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void getallConsulings(String Token){
        Observable observable = RetrofitAPI.getInstans().getallConsulings(Token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                getallConsulings.setValue((DataConsultingsModel) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataConsultingsModel data = new DataConsultingsModel(object.getInt("status"), object.getString("message"));
                        getallConsulings.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }




    public void DeilseTaskes(String Token, int id, SendDetilseTaskes taskes){
        Observable observable = RetrofitAPI.getInstans().DetilseTaskes(Token,id,taskes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                DeilseTases.setValue((DataDetilseTaskes) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataDetilseTaskes data = new DataDetilseTaskes(object.getInt("status"), object.getString("message"));
                        DeilseTases.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void call(String Token, int page,int limit){
        Observable observable = RetrofitAPI.getInstans().call(Token,page,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                call.setValue((DataCall) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCall data = new DataCall(object.getInt("status"), object.getString("message"));
                        call.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public void callfiltring(String Token, SendDatacall datacall){
        Observable observable = RetrofitAPI.getInstans().callfiltering(Token,datacall)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                callfiltring.setValue((DataCall) o);
//                Log.i("ahmed", ""+((DataNotifications) o).getMessage());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("ahmed", ""+e);
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    ResponseBody responseBody = httpException
                            .response().errorBody();
                    JSONObject object = null;
                    try {
                        object = new JSONObject(responseBody.string());
                        DataCall data = new DataCall(object.getInt("status"), object.getString("message"));
                        callfiltring.setValue(data);

                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    //Log.d("ahmed", " " + object);
                }else {
                    Log.d("ahmed", "onError: "+e.getMessage().toString());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

}