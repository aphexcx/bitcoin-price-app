package cx.aphex.bitcoinprice.interfaces;

import cx.aphex.bitcoinprice.models.CurrencyPrice;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by aphex on 4/23/16.
 */
public interface BitcoinAverageInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("global/{currency}")
    Observable<CurrencyPrice> getCurrencyPrice(@Path("currency") String currencyCode);

    @GET("global")
    Observable<String> getCurrencyCodes();
}

