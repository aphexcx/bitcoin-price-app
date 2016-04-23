package cx.aphex.bitcoinprice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fj.data.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
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
                .map(this::jsonStringToJsonObject)
                .map(JSONObject::names)
                .map(this::jsonArrayToFjList)
                .subscribe(codes -> {
                    Log.d(TAG, codes.toString());

                });
    }

    private JSONObject jsonStringToJsonObject(String s) {
        try {
            return new JSONObject(s);
        } catch (JSONException e) {
            throw Exceptions.propagate(e);
        }
    }

    private List<String> jsonArrayToFjList(@NonNull JSONArray jsonArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                arrayList.add(jsonArray.get(i).toString());
            } catch (JSONException e) {
                throw Exceptions.propagate(e);
            }
        }
        return List.list(arrayList);
    }
}
