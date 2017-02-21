
package ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings.RealmFolder;

/**
 * Created by jenzoned on 4/12/16.
 */

import android.content.Context;

import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings.BookingListData;

import io.realm.RealmResults;


public class RealmDataAdapter extends RealmModelAdapter<BookingListData> {
    public RealmDataAdapter(Context context, RealmResults<BookingListData> realmResults) {
        super(context, realmResults);
    }
}

