
package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.RealmFolder;



import android.content.Context;

import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;

import io.realm.RealmResults;


public class RealmDataAdapter extends RealmModelAdapter<ShoppingCartData> {
    public RealmDataAdapter(Context context, RealmResults<ShoppingCartData> realmResults) {
        super(context, realmResults);
    }
}

