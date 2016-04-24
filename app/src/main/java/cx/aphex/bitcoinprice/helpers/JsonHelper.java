package cx.aphex.bitcoinprice.helpers;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fj.data.List;
import rx.exceptions.Exceptions;

/**
 * JSON helper methods, with checked exceptions adapted to play nice with Observables.
 */
public class JsonHelper {

    public static JSONObject stringToJsonObject(String s) {
        try {
            return new JSONObject(s);
        } catch (JSONException e) {
            throw Exceptions.propagate(e);
        }
    }

    public static List<String> arrayToFjList(@NonNull JSONArray jsonArray) {
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
