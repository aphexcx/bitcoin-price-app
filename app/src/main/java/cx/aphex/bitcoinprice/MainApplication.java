package cx.aphex.bitcoinprice;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cx.aphex.bitcoinprice.interfaces.BitcoinAverageInterface;
import cx.aphex.bitcoinprice.services.ApiService;

/**
 * Created by aphex on 4/23/16.
 */
public class MainApplication extends Application {
    private static BitcoinAverageInterface sApiService;

    public static BitcoinAverageInterface getApiService() {
        if (sApiService == null) {
            sApiService = new ApiService();
        }
        return sApiService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
