package cx.aphex.bitcoinprice;

import android.app.Application;

import cx.aphex.bitcoinprice.interfaces.BitcoinAverageInterface;
import cx.aphex.bitcoinprice.services.ApiService;

/**
 * Created by aphex on 4/23/16.
 */
public class MainApplication extends Application {
    private static BitcoinAverageInterface sApiService;

    public static BitcoinAverageInterface getApiService() {
        if (sApiService == null) {
            sApiService = ApiService.create();
        }
        return sApiService;
    }

}
