package cl.duoc.pichangaspiscolas.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import cl.duoc.pichangaspiscolas.BuildConfig;
import cl.duoc.pichangaspiscolas.interfaces.ApiEndPointInterface;
import cl.duoc.pichangaspiscolas.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiConnection {
    private static ApiEndPointInterface apiEndPointInterface;
    private static Context mContext;
    private static Retrofit retrofit;

    public static ApiEndPointInterface getApi(Context context) {
        mContext = context;
        if (apiEndPointInterface == null) {
            initialize();
        }
        return apiEndPointInterface;
    }

    private static void initialize() {
        Gson gson = new GsonBuilder()
                .setDateFormat(Constants.ISO_DATE_FORMAT)
                .create();

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));
        builder.readTimeout(Constants.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(Constants.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        final OkHttpClient okHttpClient = builder.build();



        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/bins/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        apiEndPointInterface = retrofit.create(ApiEndPointInterface.class);
    }
}
