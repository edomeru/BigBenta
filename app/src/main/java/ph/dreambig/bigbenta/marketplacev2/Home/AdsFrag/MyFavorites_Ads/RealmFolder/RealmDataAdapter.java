
package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyFavorites_Ads.RealmFolder;

/**
 * Created by jenzoned on 4/12/16.
 */

import android.content.Context;

import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyFavorites_Ads.AdsFavData;


import io.realm.RealmResults;


public class RealmDataAdapter extends RealmModelAdapter<AdsFavData> {
    public RealmDataAdapter(Context context, RealmResults<AdsFavData> realmResults) {
        super(context, realmResults);
    }
}

