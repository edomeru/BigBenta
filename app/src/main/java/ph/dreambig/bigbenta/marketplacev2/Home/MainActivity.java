package ph.dreambig.bigbenta.marketplacev2.Home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.AboutFragment;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Help_CenterFragment;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.AdsTabFragment;

import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.Home2TabFragment;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.ServicesTabFragment;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.StoreTabFragment;
import ph.dreambig.bigbenta.marketplacev2.LANDINGPAGE.LANDINGPAGE;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Frag1;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Frag2;
import ph.dreambig.bigbenta.marketplacev2.LogIn.SignUp;
import ph.dreambig.bigbenta.marketplacev2.Profile.ProfileEditFragment;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SampleWebView;
import ph.dreambig.bigbenta.marketplacev2.Search.SearchADSFragment;
import ph.dreambig.bigbenta.marketplacev2.SearchItemFragment;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.UserInformation;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String pos;
    Typeface font;
    String[] bannerr = new String[3];
    Menu nav_Menu;
    TextView navName, emailNav;
    View headerView;
    API_Retrofit service, services;
    private Menu menu = null;
    ImageView slide;
    ViewFlipper viewFlipper;

    CircleImageView circleImage;
    SessionManager sessionManager;
    NavigationView navigationView;
    TextView toolbar_title1, toolbar_title2, toolbar_subtitle1, tvHeaderName;
    String bannerUrl[] = {Constants.API_BIGBENTA_DOMAIN + "/bb/banner1.jpg",
            Constants.API_BIGBENTA_DOMAIN + "/bb/banner2.jpg",
            Constants.API_BIGBENTA_DOMAIN + "/bb/banner3.jpg",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ph.dreambig.bigbenta.marketplacev2.R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(24,53,68));
        }
        Toolbar toolbar = (Toolbar) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.toolbar);

        setSupportActionBar(toolbar);

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        FloatingActionButton fab = (FloatingActionButton) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post = new Intent(MainActivity.this, LANDINGPAGE.class);
                startActivity(post);
                MainActivity.this.finish();

            }
        });

        sessionManager = new SessionManager(getApplicationContext());

        toolbar_subtitle1 = (TextView) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.toolbar_subtitle1);
       // toolbar_title1 = (TextView) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.toolbar_title);
       // toolbar_title2 = (TextView) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.toolbar_right_subtitle);
        DrawerLayout drawer = (DrawerLayout) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, ph.dreambig.bigbenta.marketplacev2.R.string.navigation_drawer_open, ph.dreambig.bigbenta.marketplacev2.R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle(mDrawerTitle);

                if (!sessionManager.isLoggedIn()) {
                    nav_Menu = navigationView.getMenu();
                    nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.logout).setVisible(false);
                    nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.myprofile).setVisible(false);
                    headerView = navigationView.getHeaderView(0);
                    navName = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.navName);
                    emailNav = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.emailNav);
                    navName.setText("Welcome, Guest");
                    emailNav.setVisibility(View.GONE);

                    Glide.with(MainActivity.this)
                            .load("http://edmer.x10host.com/bb/profpic.png")
                            .centerCrop()
                            .dontAnimate()
                            .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.ic_launcher)
                            .into(circleImage);
                }else {


                    sessionManager = new SessionManager(getApplicationContext());
                    UserInformation user = new UserInformation(MainActivity.this);
                    nav_Menu = navigationView.getMenu();
                    nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.logout).setVisible(true);
                    nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.myprofile).setVisible(true);
                    headerView = navigationView.getHeaderView(0);
                    circleImage = (CircleImageView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.imageView);
                    navName = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.navName);
                    emailNav = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.emailNav);
                    emailNav.setText(user.getEmail().toString());
                    navName.setText(user.getFirstName().toString() + " " + user.getLastName().toString());
                    Glide.with(MainActivity.this)
                            .load(user.getProfilePicture().toString())
                            .centerCrop()
                            .dontAnimate()
                            .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.ic_launcher)
                            .into(circleImage);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
            }
        };
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        navigationView = (NavigationView) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //   View navHeaderView= navigationView.inflateHeaderView(R.layout.nav_header_main);


        viewFlipper = (ViewFlipper) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.ViewFlipper01);
        viewFlipper.setMeasureAllChildren(false);

        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
        viewFlipper.showNext();
        View headerView = navigationView.getHeaderView(0);
        circleImage = (CircleImageView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.imageView);
        slide = (ImageView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.slide);


        Log.d("IS_LOGGED_IN", String.valueOf(sessionManager.isLoggedIn()));
        //mFragmentTransaction.replace(R.id.containerView,new HomeTabFragment()).commit();
        if (!sessionManager.isLoggedIn()) {
            nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.logout).setVisible(false);
            nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.myprofile).setVisible(false);
            headerView = navigationView.getHeaderView(0);
            navName = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.navName);
            emailNav = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.emailNav);
            navName.setText("Welcome, Guest");
            emailNav.setVisibility(View.GONE);
            Glide.with(this)
                    .load("http://edmer.x10host.com/bb/profpic.png")
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.ic_launcher)
                    .into(circleImage);
        } else {
            UserInformation user = new UserInformation(this);
            Log.d("NAV_PROF_PIC", user.getProfilePicture().toString());
            Glide.with(this)
                    .load(user.getProfilePicture().toString())
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.ic_launcher)
                    .into(circleImage);

            circleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toolbar_subtitle1.setTextColor(Color.WHITE);
                    toolbar_subtitle1.setText("My Profile");
//                    Typeface tf = Typeface.createFromAsset(MainActivity.getAssets(),
//                            "atwriter.ttf");
                    toolbar_subtitle1.setTypeface(font);
                    ProfileEditFragment fragment = new ProfileEditFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.containerView, fragment).addToBackStack(null);
                    ft.commit();


                }
            });
            Log.d("PIC_LORI", user.getProfilePicture().toString());
            Log.d("FIRST_LORI", user.getFirstName().toString());
            Log.d("LAST_LORI", user.getLastName().toString());
            Log.d("EMAIL_LORI", user.getEmail().toString());

            nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.logout).setVisible(true);
            nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.myprofile).setVisible(true);
            headerView = navigationView.getHeaderView(0);
            navName = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.navName);
            emailNav = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.emailNav);
            emailNav.setText(user.getEmail().toString());
            navName.setText(user.getFirstName().toString() + " " + user.getLastName().toString());
            navName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProfileEditFragment fragment = new ProfileEditFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.containerView, fragment).addToBackStack(null);
                    ft.commit();


                }
            });
            emailNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProfileEditFragment fragment = new ProfileEditFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.containerView, fragment).addToBackStack(null);
                    ft.commit();
                }
            });
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        services = retrofit.create(API_Retrofit.class);
        BannerRequest serviceRequest = new BannerRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setIs_mobile("1");


        Call<BannerResponse> serviceResponseCall = services.getBanner(serviceRequest);


        serviceResponseCall.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, retrofit2.Response<BannerResponse> response) {
                int statusCode = response.code();


                BannerResponse serviceResponsee = response.body();

                Log.d("STATUS CODE_BANNER", String.valueOf(statusCode));

//                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE", serviceResponsee.toString());

                for (int i = 0; i < serviceResponsee.getData().size(); i++) {

                    bannerr[i] = serviceResponsee.getData().get(i).getBannerUrl().toString();

                    Log.d("BANNER_PO_ITO", bannerr[i]);

                    for (int c = 0; c < serviceResponsee.getData().size(); c++) {
                        ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        Glide.with(MainActivity.this)
                                .load(bannerr[i])
                                .centerCrop()
                                .dontAnimate()
                                .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.dialog_background)
                                .into(imageView);
                        viewFlipper.addView(imageView);
                        //Log.d("BANNER_PICS",  bannerr[c]);
                    }

                }


            }


            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {

            }
        });

        ////-----TOOLBAR TITLE------////
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "arialbold.ttf");

//        for (int c = 0; c < bannerr.length; c++) {
//            ImageView imageView = new ImageView(MainActivity.this);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            Glide.with(MainActivity.this)
//                    .load(bannerr[c])
//                    .centerCrop()
//                    .dontAnimate()
//                    .placeholder(R.drawable.dialog_background)
//                    .into(imageView);
//            viewFlipper.addView(imageView);
//        }
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);


//        toolbar_title1.setTextColor(Color.rgb(252, 204, 0));
//        toolbar_title1.setText("Big");
//        toolbar_title1.setTypeface(tf);
//        toolbar_title2.setTextColor(Color.BLACK);
//        toolbar_title2.setText("Benta");
//        toolbar_title2.setTypeface(tf);
        toolbar_subtitle1.setTextColor(Color.WHITE);
        font = Typeface.createFromAsset(this.getAssets(),
                "Bariol_Regular.otf");
        toolbar_subtitle1.setTypeface(font);

//        toolbar_subtitle1.setTextColor(Color.WHITE);
//        toolbar_subtitle1.setText("Home");

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
       // Window w = getWindow(); // in Activity's onCreate() for instance

//            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //}

        drawer.setDrawerShadow(ph.dreambig.bigbenta.marketplacev2.R.drawable.listviewbg,
                GravityCompat.START);
//            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
//            systemBarTintManager.setStatusBarTintEnabled(true);
//            systemBarTintManager.setStatusBarTintColor(Color.parseColor("#ff3600"));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Intent intent = getIntent();

            pos = intent.getStringExtra("pos");

            if (pos.equals("store")) {

                StoreTabFragment fragment = new StoreTabFragment();
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fragment);

                fragmentTransaction.commit();
            } else if (pos.equals("ads")) {
                AdsTabFragment fragment = new AdsTabFragment();
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fragment);

                fragmentTransaction.commit();
            } else if (pos.equals("service")) {
                ServicesTabFragment fragment = new ServicesTabFragment();
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fragment);
                //  fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
    }

    public void setTextName(String text) {
        toolbar_subtitle1.setTextColor(Color.WHITE);
        toolbar_subtitle1.setText(text);
    }

    public void setTextTitle(String text) {
        toolbar_subtitle1.setTextColor(Color.WHITE);
        toolbar_subtitle1.setText(text);


    }

    public void setFont(String text) {
        if (text == "store" || text == "My Store") {
            Typeface tf = Typeface.createFromAsset(this.getAssets(),
                    "Bariol_Regular.otf");
            toolbar_subtitle1.setTypeface(tf);
        }
        if (text == "Classified Ads" || text == "Ads") {
            Typeface tf = Typeface.createFromAsset(this.getAssets(),
                    "Bariol_Regular.otf");
            toolbar_subtitle1.setTypeface(tf);
        }
        if (text == "Services" || text == "Service") {
            Typeface tf = Typeface.createFromAsset(this.getAssets(),
                    "Bariol_Regular.otf");
            toolbar_subtitle1.setTypeface(tf);
        }

    }

    public void setTextColor(int color) {
        toolbar_subtitle1.setTextColor(color);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
//            if(SampleWebView.canGoBack()){
//                SampleWebView.goBack();
//            }else{
//                super.onBackPressed();
//            }
            super.onBackPressed();
        }
    }

//    public void changeTitle(String title) {
//        toolbar_title1 = (TextView) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.toolbar_title);
//        toolbar_title1.setText(title);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(ph.dreambig.bigbenta.marketplacev2.R.menu.main, menu);
        if (sessionManager.isLoggedIn()) {
            menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.login).setVisible(false);
        } else {
            menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.login).setVisible(true);
        }
//        MenuItem item = menu.findItem(R.id.action_cart);
//        LayerDrawable icon = (LayerDrawable) item.getIcon();
//        Utility.setBadgeCount(this, icon, BaseApplication.CartItemsCount);
//
//
//        MenuItem service = menu.findItem(R.id.action_service);
//        LayerDrawable serviceicon = (LayerDrawable) service.getIcon();
//        Utility.setServiceBadgeCount(this, serviceicon, BaseApplication.ServiceItemsCount);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the com.example.edmeralarte.bigbenta2.Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == ph.dreambig.bigbenta.marketplacev2.R.id.login) {
            Frag1 fr = new Frag1();
            FragmentManager fm = this.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Bundle args = new Bundle();
            args.putString("position", "1");
            args.putString("from", "MainActivity");
            fr.setArguments(args);
            ft.addToBackStack(null);
            ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
            ft.commit();

            return true;
        }

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

        if (id == ph.dreambig.bigbenta.marketplacev2.R.id.search) {
            searchItem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == ph.dreambig.bigbenta.marketplacev2.R.id.home) {
            toolbar_subtitle1.setTextColor(Color.WHITE);
            toolbar_subtitle1.setText("Store Categories");
            setTextName("Store Categories");
            Home2TabFragment fragment = new Home2TabFragment();
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fragment);
            fragmentTransaction.commit();
            toolbar_subtitle1.setTextColor(Color.WHITE);
            toolbar_subtitle1.setText("Store Categories");

        }
//        else if (id == ph.dreambig.bigbenta.marketplacev2.R.id.feedback) {
//
//        }
        else if (id == ph.dreambig.bigbenta.marketplacev2.R.id.register) {


            toolbar_subtitle1.setTextColor(Color.WHITE);
            toolbar_subtitle1.setText("Register");
            Typeface tf = Typeface.createFromAsset(this.getAssets(),
                    "Bariol_Regular.otf");
            toolbar_subtitle1.setTypeface(tf);
            Frag2 fragment = new Frag2();
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle args = new Bundle();
            args.putString("from", "MainActivity");
            fragment.setArguments(args);
            fragmentTransaction.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fragment);
            fragmentTransaction.commit();

        } else if (id == ph.dreambig.bigbenta.marketplacev2.R.id.about) {

            toolbar_subtitle1.setTextColor(Color.WHITE);
            toolbar_subtitle1.setText("About Us");
            Typeface tf = Typeface.createFromAsset(this.getAssets(),
                    "Bariol_Regular.otf");
            toolbar_subtitle1.setTypeface(tf);
            AboutFragment fragment = new AboutFragment();
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fragment);
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
//        else if (id == ph.dreambig.bigbenta.marketplace.R.id.sampleWebview) {
//
//            toolbar_subtitle1.setTextColor(Color.WHITE);
//            toolbar_subtitle1.setText("Sample WebView");
//            Typeface tf = Typeface.createFromAsset(this.getAssets(),
//                    "atwriter.ttf");
//            toolbar_subtitle1.setTypeface(tf);
//            SampleWebView fragment = new SampleWebView();
//            FragmentManager fragmentManager = this.getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(ph.dreambig.bigbenta.marketplace.R.id.containerView, fragment);
////            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//
//        }
        else if (id == ph.dreambig.bigbenta.marketplacev2.R.id.logout) {
            if (sessionManager.isLoggedIn() == true) {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Logout?")
                        .setContentText("Are you sure you want to logout?!")
                        .setConfirmText("Logout")
                        .setCancelText("Don't logout")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                Intent i = new Intent(MainActivity.this, LANDINGPAGE.class);
                                startActivity(i);
                                sessionManager.logoutUser();
                                finish();
                            }
                        })
                        .show();


            }


        } else if (id == ph.dreambig.bigbenta.marketplacev2.R.id.myprofile) {

            toolbar_subtitle1.setTextColor(Color.WHITE);
            toolbar_subtitle1.setText("My Profile");
            Typeface tf = Typeface.createFromAsset(this.getAssets(),
                    "Bariol_Regular.otf");
            toolbar_subtitle1.setTypeface(tf);
            ProfileEditFragment fragment = new ProfileEditFragment();
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fragment);
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == ph.dreambig.bigbenta.marketplacev2.R.id.help) {

            toolbar_subtitle1.setTextColor(Color.WHITE);
            toolbar_subtitle1.setText("Help Center");
            Typeface tf = Typeface.createFromAsset(this.getAssets(),
                    "Bariol_Regular.otf");
            toolbar_subtitle1.setTypeface(tf);
            Help_CenterFragment fragment = new Help_CenterFragment();
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fragment);
//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        //onDrawerOpened(drawer);

        return true;
    }
//    public void onDrawerOpened(View drawerView) {
//        //super.onDrawerOpened(drawerView);
//      UserInformation user = new UserInformation(this);
//
//        Log.d("STATUS CODE", "WALKAHJAGDADJHADJAVDJADJDGJAGDJD");
//        nav_Menu = navigationView.getMenu();
//        nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.logout).setVisible(true);
//        nav_Menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.myprofile).setVisible(true);
//        headerView = navigationView.getHeaderView(0);
//        navName = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.navName);
//        emailNav = (TextView) headerView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.emailNav);
//        emailNav.setText(user.getEmail().toString());
//        Log.d("NAV_EMAIL", user.getEmail().toString());
//        navName.setText(user.getFirstName().toString() + " " + user.getLastName().toString());
//        Log.d("NAV_NAME", user.getFirstName().toString() + " " + user.getLastName().toString());
//        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//    }
    public void searchItem() {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();

        dialog.setContentView(ph.dreambig.bigbenta.marketplacev2.R.layout.search_dialog);

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setTitle("BigBenta");

        final EditText editTextSearch = (EditText) dialog.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.editTextSearch);

        dialog.show();

        Button buttonSearch = (Button) dialog.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
                if (editTextSearch.getText().toString().trim().length() == 0) {
                    dialog.dismiss();
                } else {
//                    Intent intent = new Intent(MainActivity.this, SearchItemActivity.class);
//                    Bundle bundleText = new Bundle();
//                    bundleText.putString(Constants.KEY_SEARCH, editTextSearch.getText().toString());
//
//                    intent.putExtras(bundleText);
//                    startActivity(intent);
//                    dialog.dismiss();
                    Log.d("EDITTEXTSEARCH", editTextSearch.getText().toString());
                    if (pos.equals("store")) {
                        SearchItemFragment fr = new SearchItemFragment();
                        FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Bundle args = new Bundle();
                        args.putString(Constants.KEY_SEARCH, editTextSearch.getText().toString());
                        fr.setArguments(args);
                        ft.addToBackStack(null);
                        ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
                        ft.commit();
                        dialog.dismiss();
                    } else if (pos.equals("ads")) {
                        SearchADSFragment fr = new SearchADSFragment();
                        FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Bundle args = new Bundle();
                        args.putString(Constants.KEY_SEARCH, editTextSearch.getText().toString());
                        fr.setArguments(args);
                        ft.addToBackStack(null);
                        ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
                        ft.commit();
                        dialog.dismiss();

                    }

                }
            }
        });


    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }

    }



}

