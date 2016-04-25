package cx.aphex.bitcoinprice.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.bitcoinprice.R;
import cx.aphex.bitcoinprice.adapters.CurrencyAdapter;
import cx.aphex.bitcoinprice.models.CurrencyPrice;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.loadingAnimation) SimpleDraweeView loadingAnimation;
    @Bind(R.id.rvCurrencyCards) RecyclerView rvCurrencyCards;
    private String TAG = this.getClass().getSimpleName();
    private CurrencyAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupLoadingAnimation();

        setupUserRecyclerView();

        //load currency cards
        //circular reveal and show them one by one
        loadPricesFromApi();

        //manually refreshing them should clear the list, circular hide, and then show them agian
        //autorefreshing them should bounce them in place

        //have a fab to add currencies (displays a sheet of possible currencies to check off
        // sheet should be stored in sharedprefs


    }

    private void setupLoadingAnimation() {
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithResourceId(R.drawable.nyan100)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageRequest.getSourceUri())
                .setAutoPlayAnimations(true)
                .build();
        loadingAnimation.setController(controller);
    }

    private void setupUserRecyclerView() {
        currencyAdapter = new CurrencyAdapter();

        //Grab the Recycler View and list all conversation objects in a vertical list
        rvCurrencyCards.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvCurrencyCards.setItemAnimator(new SlideInUpAnimator());
        rvCurrencyCards.setAdapter(currencyAdapter);

        //TODO: swipe to refresh
    }

    private void loadPricesFromApi() {
//        hideCurrencyCards();
        //get selected currency codes
        // if this is the first time, populate with USD, KES, TZS and UGX

        // Whoa, if we request these too fast, we run into an HTTP 429 Too Many Requests response!
        // TODO: implement backpressure to avoid running into this rate-limiting.

        Observable.from(new String[]{"USD", "KES", "TZS", "UGX"})
                .flatMap(code -> getApiService().getCurrencyPrice(code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onPriceReceived,
                        this::onConnectionError,
                        this::onLoadComplete);

    }

    private void onPriceReceived(CurrencyPrice currencyPrice) {
        currencyAdapter.addItem(currencyPrice);
    }

    private void onConnectionError(Throwable throwable) {
        Log.e(TAG, "Connection Error:");
        throwable.printStackTrace();

        //TODO: Snackbar
    }

    private void onLoadComplete() {
//        showCurrencyCards();
    }
}
