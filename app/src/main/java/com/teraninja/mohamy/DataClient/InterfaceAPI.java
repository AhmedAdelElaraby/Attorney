package com.teraninja.mohamy.DataClient;

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
import com.teraninja.mohamy.Model.DataOppenents;
import com.teraninja.mohamy.Model.DataSalary;
import com.teraninja.mohamy.Model.DataSendClientLogin;
import com.teraninja.mohamy.Model.DataModelCode;
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
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfaceAPI {
    @POST("api/login")
    Observable<DataLoginEmploye> LoginEmploye(@Body SendDataLogin dataLogin);

    @POST("api/home")
    Observable<DataHome> Home(@Header("Authorization") String Token);

    @POST("api/salary")
    Observable<DataSalary> Salary(@Header("Authorization") String Token);

    @POST("api/employee/admin_requests/{page}/{limit}")
    Observable<DataAdminRequste> Adminrequste(@Header("Authorization") String Token,
                                              @Path("page") int page, @Path("limit")
                                                      int limit, @Query("branch_id") int branch_id);

    @POST("api/employee/change/password")
    Observable<DataChengePassword> chengepasswordemoloye(@Header("Authorization") String Token, @Body SendDataChengePassword chengePassword);

    @POST("api/employee/get/clients/{page}/{limit}")
    Observable<DataCustmers> Castmers(@Header("Authorization") String Token, @Path("page") int page, @Path("limit") int limit);

    @POST("api/employee/get/clients")
    Observable<DataCustmers> Castmersfilter(@Header("Authorization") String Token, @Body SendDataCastmerfilter castmerfilter);

    @POST("api/trials/byclient/{Client_id}/{PageNo}/{no-of-items}")
    Observable<DataShowDetilslssues> DetilseLsses(@Header("Authorization") String Token, @Path("Client_id") int Client_id, @Path("PageNo") int PageNo, @Path("no-of-items") int limit);

    @POST("api/sittings/all/{page}/{limit}")
    Observable<DataSessions> Sessions(@Header("Authorization") String Token,
                                      @Path("page") int page, @Path("limit")
                                              int limit, @Query("branch_id") int branch_id);

    @POST("api/sittings/all")
    Observable<DataSessions> Sessionsfilter(@Header("Authorization") String Token, @Query("branch_id") int branch_id, @Body SendDataSessions filtersessions);

    @POST("api/sittings/{branch_id}/showsitting")
    Observable<DataShowSessionDetilse> SessionsshowDetilse(@Header("Authorization") String Token, @Path("branch_id") int branch_id);

    @POST("api/branches")
    Observable<DataCity> ollcity(@Header("Authorization") String Token);

    @POST("api/trials/list/{page}/{limit}")
    Observable<DataTrial> allTrial(@Header("Authorization") String Token, @Path("page") int page, @Path("limit") int limit
            , @Query("branch_id") int branch_id);

    @POST("api/trials/list")
    Observable<DataTrial> filterTrial(@Header("Authorization") String Token, @Query("branch_id") int branch_id, @Body SendDataTrial sendDataTrial);

    @POST("api/trial/{trial_id}/show")
    Observable<DataShowTrial> ShowTrial(@Header("Authorization") String Token, @Path("trial_id") int id);

    @POST("api/trial/{trial_id}/oppenents")
    Observable<DataOppenents> Oppenents(@Header("Authorization") String Token, @Path("trial_id") int id);

    @POST("api/trial/{trial_id}/sittings")
    Observable<DataSittingsOfTrial> sittings_of_trial(@Header("Authorization") String Token, @Path("trial_id") int id);

    @POST("api/tasks/{page}/{limit}")
    Observable<DataAllTask> AllTask (@Header("Authorization") String Token,
                                     @Path("page") int page, @Path("limit")
                                              int limit, @Query("branch_id") int branch_id);

    @POST("api/trial/{trial_id}/financial")
    Observable<DataMalyaFromTrial> MalyaFromTrail(@Header("Authorization") String Token, @Path("trial_id") int id);

    @POST("api/financial/clients/{page}/{limit}")
    Observable<DataELMalaya> Elmalya(@Header("Authorization") String Token,@Path("page") int page, @Path("limit") int limit );

    @POST("api/financial/clients/{client_id}/trials")
    Observable<DataDetilseMaliaya> DetilseElmalya(@Header("Authorization") String Token, @Path("client_id") int client_id );
    @POST("api/client/register")
    Observable<ModelDataRegister> Register(@Body DataSendRegister register );
    @POST("api/client/checkCode")
    Observable<DataCheeckCode> checkCode(@Body DataSendnumberandCode sendnumberandCode);

    @POST("api/client/login")
    Observable<ModelDataClientLogin> ClientLogin(@Body DataSendClientLogin clientLogin);
    @POST("api/client/sendCode")
    Observable<DataModelCode> SendCod(@Body SendCode sendCod);
    @POST("api/client/forgetPassword")
    Observable<DataModelCode> forgetpassword(@Body SendDataForRseedPassword forRseedPassword );
    @POST("api/client/home")
    Observable<DataHome> HomeClient(@Header("Authorization") String Token);
    @POST("api/client/consultings/store")
    Observable<DataAddConslout> Add(@Header("Authorization") String Token,@Body SendConslout conslout);
    @POST("api/client/consultings/all")
    Observable<DataConsultingsModel>getallConsulings (@Header("Authorization") String Token);

    @POST("api/trial/{id}/trialTasks")
    Observable<DataDetilseTaskes>DetilseTaskes (@Header("Authorization") String Token, @Path("id") int id, @Body SendDetilseTaskes taskes);

//    @POST("api/client/trials/all/{page}/{limit}")
//    Observable<>alltrial (@Header("Authorization") String Token,@Path("page") int page,@Path("limit") int limit);
//

    @POST("api/receptions/{page}/{limit}")
    Observable<DataCall> call(@Header("Authorization") String Token, @Path("page") int page, @Path("limit") int limit );
    @POST("api/receptions")
    Observable<DataCall> callfiltering(@Header("Authorization") String Token, @Body SendDatacall datacall);

}
