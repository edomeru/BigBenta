package ph.dreambig.bigbenta.marketplacev2;

import android.app.Application;
import android.content.SharedPreferences;

import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings.BookingListData;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;

import io.realm.Realm;
import io.realm.RealmConfiguration;

//import io.realm.Realm;
//import io.realm.RealmConfiguration;

/**
 * Created by edmeralarte on 15/09/2016.
 */

public class BaseApplication extends Application {

    public static int CartItemsCount = 0;
    public static int ServiceItemsCount = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        SharedPreferences prefs = getSharedPreferences("NumberofCartItems",  MODE_PRIVATE);
        Realm realm;
        realm = Realm.getDefaultInstance();

//        CartItemsCount = prefs.getInt("TotalNumberofCartitems", 0);
        CartItemsCount = realm.where(ShoppingCartData.class).sum("Quantity").intValue();
        ServiceItemsCount = realm.where(BookingListData.class).sum("id").intValue();
    }
}
