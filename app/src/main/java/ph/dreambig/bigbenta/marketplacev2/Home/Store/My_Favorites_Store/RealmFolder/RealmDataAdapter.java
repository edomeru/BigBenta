
package ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.RealmFolder;



import android.content.Context;

import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.FavoritesCartData;

import io.realm.RealmResults;


public class RealmDataAdapter extends RealmModelAdapter<FavoritesCartData> {
    public RealmDataAdapter(Context context, RealmResults<FavoritesCartData> realmResults) {
        super(context, realmResults);
    }
}

