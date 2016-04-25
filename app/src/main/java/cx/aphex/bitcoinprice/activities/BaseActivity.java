package cx.aphex.bitcoinprice.activities;

import android.support.v7.app.AppCompatActivity;

import cx.aphex.bitcoinprice.MainApplication;
import cx.aphex.bitcoinprice.interfaces.BitcoinAverageInterface;

/**
 * Created by aphex on 4/25/16.
 */
public class BaseActivity extends AppCompatActivity {
    protected BitcoinAverageInterface getApiService() {
        return MainApplication.getApiService();
    }
}
