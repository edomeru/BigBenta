package ph.dreambig.bigbenta.marketplacev2;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings.BookingListData;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class AppController  extends MultiDexApplication {

    public static final String TAG = AppController.class.getSimpleName();
    public static int CartItemsCount = 0;
    public static int ServiceItemsCount = 0;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    LruBitmapCache mLruBitmapCache;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        MultiDex.install(this);
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        SharedPreferences prefs = getSharedPreferences("NumberofCartItems",  MODE_PRIVATE);
        Realm realm;
        realm = Realm.getDefaultInstance();

//        CartItemsCount = prefs.getInt("TotalNumberofCartitems", 0);
        CartItemsCount = realm.where(ShoppingCartData.class).sum("Quantity").intValue();
        ServiceItemsCount = realm.where(BookingListData.class).sum("id").intValue();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            getLruBitmapCache();
            mImageLoader = new ImageLoader(this.mRequestQueue, mLruBitmapCache);
        }

        return this.mImageLoader;
    }

    public LruBitmapCache getLruBitmapCache() {
        if (mLruBitmapCache == null)
            mLruBitmapCache = new LruBitmapCache();
        return this.mLruBitmapCache;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
