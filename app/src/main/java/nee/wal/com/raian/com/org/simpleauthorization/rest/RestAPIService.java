package nee.wal.com.raian.com.org.simpleauthorization.rest;

import nee.wal.com.raian.com.org.simpleauthorization.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RestAPIService {

    @GET("basic")
    Call<Result>getResponse(@Header("Authorization")String authorizationHeader);

}
