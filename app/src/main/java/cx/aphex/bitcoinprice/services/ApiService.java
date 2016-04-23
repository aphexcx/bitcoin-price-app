package cx.aphex.bitcoinprice.services;

import android.support.annotation.NonNull;

import cx.aphex.bitcoinprice.interfaces.BitcoinAverageInterface;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by aphex on 4/23/16.
 */
public class ApiService {

    // Trailing slash is needed
    private static final String BASE_URL = "https://api.bitcoinaverage.com/ticker/";

    public static BitcoinAverageInterface create() {
        return getRetrofit(getHttpClient())
                .create(BitcoinAverageInterface.class);
    }

    @NonNull
    private static OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @NonNull
    private static Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

//    @NonNull
//    private static Moshi getMoshi() {
//        return new Moshi.Builder()
//                .build();
//    }
}

