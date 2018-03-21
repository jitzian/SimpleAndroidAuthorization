package nee.wal.com.raian.com.org.simpleauthorization.providers;

import com.google.gson.GsonBuilder;

import java.util.concurrent.Executors;

import nee.wal.com.raian.com.org.simpleauthorization.constants.GlobalConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProviders {
    private static RetrofitProviders instance;
    private RetrofitProviders(){}

    public static RetrofitProviders getInstance(){
        if(instance == null){
            synchronized (RetrofitProviders.class){
                if(instance == null){
                    instance = new RetrofitProviders();
                }
            }
        }
        return instance;
    }

    public Retrofit providesRetrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .callbackExecutor(Executors.newSingleThreadScheduledExecutor())
                .baseUrl(GlobalConstants.baseURL)
                .build();
    }

}
