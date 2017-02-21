package ph.dreambig.bigbenta.marketplacev2;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.StoreTabFragment;


public class SampleWebView extends Fragment {
    ProgressDialog progressDialog;
  static  WebView webView;
    FloatingActionButton fab;
    //Button continuetoShop;
    private Realm realm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Shopping Cart");
        realm = Realm.getDefaultInstance();


    }
    public SampleWebView() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View x= inflater.inflate(R.layout.fragment_sample_web_view, container, false);
         fab = (FloatingActionButton)x. findViewById(R.id.arrow);

         //continuetoShop = (Button)x.findViewById(R.id.continueshop);
        webView = (WebView) x.findViewById(R.id.webView);
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
//                StoreTabFragment fr=new StoreTabFragment();
//                FragmentManager fm= getActivity().getSupportFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                Bundle args = new Bundle();
//                args.putString("position", "0");
//                fr.setArguments(args);
//                ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
//                ft.commit();
//            }
//        });
        return x;
    }

    private void startWebView(String url) {

        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(getActivity());
//                    progressDialog.setMessage(getString(R.string.loading));
//                    progressDialog.show();
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
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        webView.setScrollbarFadingEnabled(false);
//        webView.getSettings().setBuiltInZoomControls(true);

        webView.loadUrl(url);

    }

//    @Override
//    public void onBackPressed() {
//        if(webView.canGoBack()) {
//            webView.goBack();
//        } else {
//            progressDialog.dismiss();
//            super.onBackPressed();
//        }
//    }
//
//    public boolean onSupportNavigateUp() {
//        progressDialog.dismiss();
//        onBackPressed();
//        return true;
//    }

//    public static boolean canGoBack(){
//        return webView.canGoBack();
//    }

    public static void goBack(){
        webView.goBack();
    }

}
