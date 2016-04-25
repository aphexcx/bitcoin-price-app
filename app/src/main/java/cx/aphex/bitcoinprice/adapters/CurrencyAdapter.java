package cx.aphex.bitcoinprice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.bitcoinprice.R;
import cx.aphex.bitcoinprice.models.CurrencyPrice;


/**
 * Created by aphex on 4/25/16.
 */
public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
    ArrayList<CurrencyPrice> mItems = new ArrayList<>();
    private String TAG = this.getClass().getSimpleName();
    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_currency, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        final CurrencyPrice currencyPrice = mItems.get(position);

        vh.price.setText(String.valueOf(currencyPrice.getAsk()));

        //TODO:
        vh.currencyCode.setText(currencyPrice.getCode());
//        vh.icon.setImageURI(Uri.parse(currencyPrice.getIcon()));
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(Collection<CurrencyPrice> items) {
        Log.i(TAG, items.size() + " items received.");
        if (items.equals(mItems)) {
            Log.i(TAG, "New list is the same as the current list.");
            return;
        }
        clearItems();
        fj.data.List.list(items)
                .foreachDoEffect(this::addItem);
        Log.i(TAG, items.size() + " items added.");
    }

    public void addItem(CurrencyPrice item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    public void removeItem(CurrencyPrice item) {
        remove(mItems.indexOf(item));
    }

    public void remove(int index) {
        mItems.remove(index);
        notifyItemRemoved(index);
    }

    public void clearItems() {
        int oldSize = mItems.size();
        mItems.clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.row)
        LinearLayout row;
        @Bind(R.id.icon)
        SimpleDraweeView icon;
        @Bind(R.id.price)
        TextView price;
        @Bind(R.id.currencyCode)
        TextView currencyCode;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
