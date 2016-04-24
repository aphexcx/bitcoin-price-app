package cx.aphex.bitcoinprice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import cx.aphex.bitcoinprice.helpers.JsonHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainApplication.getApiService().getCurrencyPrice("AFN")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(r -> {
                    Log.d(TAG, r.toString());
                });

        MainApplication.getApiService().getCurrencyCodes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(JsonHelper::stringToJsonObject)
                .map(JSONObject::names)
                .map(JsonHelper::arrayToFjList)
                .subscribe(codes -> {
                    Log.d(TAG, codes.toString());

                });
    }


}
