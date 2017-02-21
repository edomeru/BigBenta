package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.StoreTabFragment;
import ph.dreambig.bigbenta.marketplacev2.LANDINGPAGE.LANDINGPAGE;
import ph.dreambig.bigbenta.marketplacev2.R;

public class MyCart2 extends AppCompatActivity {
    TextView toolbar_title,textFloating;
    ProgressDialog progressDialog;
    static WebView webView;
    FloatingActionButton fab;
    //Button continuetoShop;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        textFloating = (TextView)findViewById(R.id.textFloating);
        this.setTitle("My Cart");
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "Bariol_Regular.otf");
        toolbar_title.setTypeface(tf);
        textFloating.setTypeface(tf);
        realm = Realm.getDefaultInstance();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.arrow);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(24,53,68));
        }

        fab = (FloatingActionButton) findViewById(R.id.arrow);

       // continuetoShop = (Button)findViewById(R.id.continueshop);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.clearView();
//        webView.measure(10, 10);
        RealmResults<ShoppingCartData> events = realm.where(ShoppingCartData.class).findAll();
        Log.d("RealmResults_DATA",events.toString());
        RealmList<ShoppingCartData> results = new RealmList<ShoppingCartData>();

        results.addAll(events.subList(0, events.size()));

        if(results.size()!=0) {
            String var = "";
            for (int i = 0; i < results.size(); i++) {
                var += String.valueOf(results.get(i).getVariantId()) + "-";
                var += String.valueOf(results.get(i).getQuantity()) + "-";
                var += String.valueOf(results.get(i).getStoreId());
                if (!(i == results.size() - 1)) {
                    var += ",";
                }

            }
            Log.d("LAMAN", "http://marketplacetest.bigbenta.com/mobile-view/" + var.toString());
            startWebView("https://bigbenta.com/mobile-view/" + var.toString());
        }else{

            startWebView("https://bigbenta.com/mobile-view/" + "0-0-0");
            fab.setVisibility(View.GONE);
        }


//        continuetoShop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(MyCart2.this, MainActivity.class);
//                i.putExtra("pos","store");
//                MyCart2.this.finish();
//                startActivity(i);
//            }
//        });




    }

    private void startWebView(String url) {

        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(MyCart2.this);
                     progressDialog.setMessage(getString(R.string.loading));
                     progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.loadUrl(url);

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            progressDialog.dismiss();
            super.onBackPressed();

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("pos","store");
            this.finish();
            startActivity(i);

         }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            Intent intentforBackButton = NavUtils.getParentActivityIntent(this);
//            intentforBackButton.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            NavUtils.navigateUpTo(this, intentforBackButton);

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("pos","store");
            this.finish();
            startActivity(i);


            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
