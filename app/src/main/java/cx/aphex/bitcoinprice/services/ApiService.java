package cx.aphex.bitcoinprice.services;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import cx.aphex.bitcoinprice.helpers.JsonHelper;
import cx.aphex.bitcoinprice.interfaces.BitcoinAverageInterface;
import cx.aphex.bitcoinprice.models.CurrencyPrice;
import fj.data.List;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by aphex on 4/23/16.
 */
public class ApiService implements BitcoinAverageInterface {

    // Trailing slash is needed
    private static final String BASE_URL = "https://api.bitcoinaverage.com/ticker/";
    private final BitcoinAverageInterface service;

    public ApiService() {
        service = create();
    }

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

    @Override
    public Observable<CurrencyPrice> getCurrencyPrice(@Path("currency") String currencyCode) {
        return service.getCurrencyPrice(currencyCode)
                .map(c -> {
                    c.setCode(currencyCode);
                    return c;
                });
    }

    @Override
    public Observable<String> getCurrencyCodes() {
        return service.getCurrencyCodes();
    }

    public Observable<List<String>> getCurrencyCodesList() {
        return service.getCurrencyCodes()
                .map(JsonHelper::stringToJsonObject)
                .map(JSONObject::names)
                .map(JsonHelper::arrayToFjList);
    }

}

