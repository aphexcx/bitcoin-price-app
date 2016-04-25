package cx.aphex.bitcoinprice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.bitcoinprice.helpers.JsonHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.loadingAnimation) SimpleDraweeView loadingAnimation;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupLoadingAnimation();

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


}
