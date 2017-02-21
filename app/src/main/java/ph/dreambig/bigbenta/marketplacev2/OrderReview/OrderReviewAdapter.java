package ph.dreambig.bigbenta.marketplacev2.OrderReview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.RealmFolder.RealmRecyclerViewAdapter;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;
import ph.dreambig.bigbenta.marketplacev2.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jenzoned on 5/4/16.
 */
public class OrderReviewAdapter extends RealmRecyclerViewAdapter<ShoppingCartData> {
    Context context;
    String title;
    Realm realm;
    int toQuantity;
    private class EventViewHolder extends RecyclerView.ViewHolder {
        TextView ProductName,ProductWeight,ProductPrice,ProductAvailability,Quantity,UnitPrice;
        View Devider;
        public EventViewHolder(View itemView) {
            super(itemView);

            ProductName = (TextView)itemView.findViewById(R.id.product_name);
            ProductPrice = (TextView) itemView.findViewById(R.id.product_price);
            ProductAvailability = (TextView) itemView.findViewById(R.id.product_availability);
            Quantity = (TextView)itemView.findViewById(R.id.product_quantity);
            ProductWeight = (TextView) itemView.findViewById(R.id.product_weight);
            UnitPrice = (TextView)itemView.findViewById(R.id.unit_price);
            Devider = itemView.findViewById(R.id.devider);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_layout_cardview, parent, false);
        context = parent.getContext();
        return new EventViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        final EventViewHolder evh = (EventViewHolder) viewHolder;
        ShoppingCartData productInfo = getItem(i);

        realm = Realm.getDefaultInstance();
        realm.setAutoRefresh(true);

        RealmResults<ShoppingCartData> QueryResults = realm.where(ShoppingCartData.class).findAll();
        evh.ProductName.setText(productInfo.getProductName());
        evh.Quantity.setText(""+productInfo.getQuantity());
        evh.ProductPrice.setText("₱"+productInfo.getPrice() * productInfo.getQuantity());
        evh.UnitPrice.setText("₱"+productInfo.getPrice());
        if(QueryResults.size()==1){
            evh.Devider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }
}
