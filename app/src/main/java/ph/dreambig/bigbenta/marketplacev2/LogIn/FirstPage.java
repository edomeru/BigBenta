package ph.dreambig.bigbenta.marketplacev2.LogIn;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.LogInTabFragment;
import ph.dreambig.bigbenta.marketplacev2.R;


/**
 * Created by Acer on 31/03/2016.
 */
public class FirstPage extends AppCompatActivity {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    TextView toolbar_subtitle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.firstpage);
        toolbar_subtitle1 = (TextView)findViewById(R.id.toolbar_subtitle1);
        Typeface bariol = Typeface.createFromAsset(getAssets(), "Bariol_Regular.otf");
        toolbar_subtitle1.setTypeface(bariol);
//        FirstPageLogin firstPageLogin = new FirstPageLogin();
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.add(R.id.firstpage, firstPageLogin, "");
//        transaction.commit();
//        FirstPageLogin fragment = new FirstPageLogin();
//        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.firstpage, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(24,53,68));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.firstpage,new LogInTabFragment()).commit();
    }



//    @Override
//    public void onBackPressed() {
//       android.support.v4.app. FragmentManager fm = getFragmentManager();
//        if (fm.getBackStackEntryCount() >= 1) {
//            fm.popBackStack();
//        } else {
//            finish();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loginmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the com.example.edmeralarte.bigbenta2.Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

//        if (id == R.id.action_chat) {
//            return true;
//        }
//        if (id == R.id.action_cart) {
//            StoreTabFragment fr=new StoreTabFragment();
//            FragmentManager fm= this.getSupportFragmentManager();
//            FragmentTransaction ft=fm.beginTransaction();
//            Bundle args = new Bundle();
//            args.putString("position", "1");
//            fr.setArguments(args);
//            ft.replace(R.id.containerView, fr);
//            ft.commit();
//            return true;
//        }
//
//        if (id == R.id.action_service) {
//            ServicesTabFragment fr=new ServicesTabFragment();
//            FragmentManager fm= this.getSupportFragmentManager();
//            FragmentTransaction ft=fm.beginTransaction();
//            Bundle args = new Bundle();
//            args.putString("position", "1");
//            fr.setArguments(args);
//            ft.replace(R.id.containerView, fr);
//            ft.commit();
//            return true;
//        }

        if (id == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTextTitle(String text){
        toolbar_subtitle1.setTextColor(Color.WHITE);
        toolbar_subtitle1.setText(text);
        //toolbar_title2.setVisibility(View.GONE);
        //toolbar_subtitle1.setVisibility(View.GONE);

    }

}


