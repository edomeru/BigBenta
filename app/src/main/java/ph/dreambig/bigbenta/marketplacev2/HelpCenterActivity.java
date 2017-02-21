package ph.dreambig.bigbenta.marketplacev2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.LANDINGPAGE.LANDINGPAGE;


public class HelpCenterActivity extends AppCompatActivity {

    private Toolbar toolbar;
TextView toolbar_title;
ScrollView myScrollingContent;
    TextView hc_body,hc_body2,hc_body3,hc_Title;
    ProgressDialog progressDialog;
    //WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpcenter);

       final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        toolbar_title =(TextView)findViewById(R.id.toolbar_title);
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "Bariol_Regular.otf");
        toolbar_title.setTypeface(tf);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hc_body = (TextView)findViewById(R.id.hc_body);
        hc_body2= (TextView)findViewById(R.id.hc_body2);
        hc_body3= (TextView)findViewById(R.id.hc_body3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(24,53,68));
        }
        Typeface bariol = Typeface.createFromAsset(this.getAssets(), "Bariol_Regular.otf");
        hc_body.setTypeface(bariol);
        hc_body2.setTypeface(bariol);
        hc_body3.setTypeface(bariol);

        hc_body.setText(Html.fromHtml(" "));

        hc_body2.setText(Html.fromHtml(" "+
                "\n" +
                "</ul>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"col-md-8\">\n" +
                "\n" +
                "<h2>About the company</h2>\n" +
                "<strong>\n" +
                "What is BigBenta?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta Corporation is an Internet Software Company that aims to develop applications for the use of micro, small, and medium enterprises (MSMEs). It is a locally owned corporation established last March 2015. \n" +
                "</p>\n" +
                "<strong>\n" +
                "Is BigBenta a local company or foreign owned?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta is 100% Filipino owned, and intends to keep the same ownership structure in the future.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How many years has it been in business?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta has been in business for almost a year having been established in March 2015, it has already launched 2 platforms in the market, i.e., Online Classified Ads and Ecommerce Platform.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Who owns BigBenta?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta is owned by its four (4) founding members and local investors.\n" +
                "</p>\n" +
                "\n" +
                "<h2>About BigBenta Store solution</h2>\n" +
                "<strong>\n" +
                "What is the BigBenta Store solution?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta store solution is called BigBenta Store which enables MSMEs to set up their online store complete with payment and courier facilities. The platform also offers services that will help storeowners manage their stores more efficiently through its Sales Dashboard, Case Inquiry Management, Chat Service, among others.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Why do I need my store/business to be online?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Being online will allow you to reach customers located in different parts of the Philippines which will result to increase in sales revenues.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How is BigBenta Store different from its competitors?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta Store has thoroughly analyzed the needs of its target market prior to developing its ecommerce platform and has come up with unique features and solutions that will benefit store owners. It will also deploy a more effective sales and distribution strategy to successfully reach its target customers. \n" +
                "</p>\n" +
                "<strong>\n" +
                "Why do I need to host and build my store in BigBenta when I have my own website?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Most websites are static which means that purchases and other transactions cannot be made online. With BigBenta Store, customers will be able to see a full display of your merchandise, order, purchase, and request for delivery.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How can BigBenta promote my Store, products and services?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta will help you promote your store through Search Engine Optimization (SEO), Online Marketing, Bigbenta Market place, and other traditional channels.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How much does it cost to become a BigBenta Store Owner?\n" +
                "</strong>\n" +
                "<p>\n" +
                "There are three (3) subscription plans to choose from, i.e. Basic at P999, Professional at P1, 999 and Enterprise at P2, 999. You can set up and operate an online store for as low as P999 per month.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I use my current Store domain instead of using BigBenta Store?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Initially, a separate domain name under BigBenta Store will be created for your store. \n" +
                "</p>\n" +
                "<strong>\n" +
                "Will my Online Store be accessible in mobile either in iOS and Android?\n" +
                "</strong>\n" +
                "<p>\n" +
                "All platforms of BigBenta will be accessible on your PC, android and IOS devices.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I register two or more Stores on my account?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Yes, you can. BigBenta has this unique feature to cater to entrepreneurs or businessmen who own multiple businesses.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Will having a BigBenta Store just move my existing customers from being a walk-in client to online? \n" +
                "</strong>\n" +
                "<p>\n" +
                "The goal of BigBenta is to expand your customer base. Its platform will connect you to customers that you are not able to serve due to geographic limitations.\n" +
                "</p>\n" +
                "<strong>\n" +
                "What are the advantages I get if I subscribe to BigBenta Store?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Your subscription will allow you to design your own website with ordering, payment, and delivery facilities. As a BigBenta Store owner, you will also receive a full range of services and support to maximize your business potential.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How will I know if having a BigBenta Store will increase my business?\n" +
                "</strong>\n" +
                "<p>\n" +
                "As a  BigBenta storeowner, you will have access to a Sales Dashboard that will allow you to see your sales statistics and customer analytics.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Who can I talk to in case I have a problem with my BigBenta Store?  \n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta has a customer support team to attend to your concerns and problems. They are accessible through email, chat, and phone.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How long will it take me to launch my own BigBenta Store?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Applying for a subscription is fast and easy. Your store can be up and running two (2) weeks after you pay for your subscription fees.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How much does it cost to set up a BigBenta Store? How are the subscription plans different?\n" +
                "</strong>\n" +
                "<p>\n"));

        hc_body3.setText(Html.fromHtml("<div class=\"col-md-12\">\n" +

                "\n" +
                "</p>\n" +
                "<strong>\n" +
                "How will I get paid for the products that I sell?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Buyers are given several payment options to choose from: Credit card, over-the-counter, bank deposit, and cash-on-delivery. Merchants can set up the payment option that is most appropriate for their business.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I trust your company?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta is a legitimate corporation, run and managed by professionals. It has an office located in Paranaque City.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Do I have the option to cancel my subscription?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Store owners have the flexibility to cancel their subscription any time, but refund will only be given for un-used months during the subscription period.\n" +
                "</p>\n" +
                "<h2>About BigBenta Sales Partners</h2>\n" +
                "<strong>\n" +
                "Why does BigBenta employ sales representative if its Store offering is based online?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta believes in personal or face-to-face selling as a more effective means to reach its target customers. Filipinos in general trust a person that they see and can personally talk to for questions and concerns. \n" +
                "</p>\n" +
                "<strong>\n" +
                "Does BigBenta have authorized Sales representatives?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta employs its own full-time sales team and works with company-trained sales partners to ensure adequate coverage of the market. \n" +
                "</p>\n" +
                "<strong>\n" +
                "Who is the BigBenta sales representative in my area?\n" +
                "</strong>\n" +
                "<p>\n" +
                "If a BigBenta sales executive or sales partner has not visited you yet, you may call our customer hotline at (02) 8693001- 02 and 8107910 and we will assign someone to contact and see you immediately. \n" +
                "</p>\n" +
                "<strong>\n" +
                "How can I get in touch with BigBenta sales representatives in my area?\n" +
                "</strong>\n" +
                "<p>\n" +
                "You may call our customer hotline at (02) 8693001-02 and 8107910 and we will assign someone to contact and see you immediately.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How will I know if the sales person approaching me is an authorized representative from BigBenta?\n" +
                "</strong>\n" +
                "<p>\n" +
                "All our Sales Executives and Sales Partners will be provided IDs and if you would like to check further, visit our company website at sales.bigbenta.com where the photos and profiles of all our sales personnel will be posted for your reference.\n" +
                "</p>\n" +
                "<p>\n" +
                "Please note that our Sales personnel are NOT allowed to collect and accept payment from customers. All payments for subscriptions can only be paid via credit card, bank deposit, and over-the-counter.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Do I need to get in touch or coordinate with BigBenta sales representatives to process by application for BigBenta Store?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Yes, because the Sales Executive or Sales Partner will manage your account and will help address your queries and guide you in setting up your online store. \n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I directly register online at BigBenta to apply for my store subscription without dealing with BigBenta’s sales representatives?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Yes, you may register directly online if this is more convenient for you. You may also contact our customer support at (02) 8693001-02 and 8107910 for queries. Ideally, BigBenta recommends that you go through a Sales Executive or Sales Partner to enjoy a full servicing of your account. \n" +
                "</p>\n" +
                "<strong>\n" +
                "Is BigBenta allowing sales representatives to accept payments for its Store subscriptions?\n" +
                "</strong>\n" +
                "<p>\n" +
                "No, Sales Executive or Sales Partners are NOT allowed to collect or accept payment from customers.\n" +
                "</p>\n" +
                "\n" +
                "<h2>About BigBenta Authentication and Verification (A&V) policy</h2>\n" +
                "<strong>\n" +
                "What is Authentication & Verification (A&V)?\n" +
                "</strong>\n" +
                "<p>\n" +
                "A&V is BigBenta’s unique approach to ensure that all stores registered on its platform are legitimate and can be trusted by customers. It uses a third party partner to evaluate businesses before they can set up a store and post products in the BigBenta store.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Why should I undergo A&V prior to becoming a BigBenta Store Owner?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta wants to promote an ecommerce environment where buyers and sellers can safely and confidently transact their business. This is the thrust of our company. \n" +
                "</p>\n" +
                "<strong>\n" +
                "What if I refuse to undergo A&V process of BigBenta?\n" +
                "</strong>\n" +
                "<p>\n" +
                "We encourage you to undergo this simple A&V requirement to set up your online store.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How long does it take to complete this A&V process?\n" +
                "</strong>\n" +
                "<p>\n" +
                "It only takes a maximum of three (3) days for businesses located in Metro Manila and seven (7) days for provincial areas. Please ensure that your company documents are prepared for the scheduled visit.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Is this for free?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Yes, the A&V assessment is free. However, if there is a need to reschedule a visit due to incomplete requirements, there will be minimal charges that will be applied.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Who will conduct the A&V process?\n" +
                "</strong>\n" +
                "<p>\n" +
                "The A&V process will be conducted by an accredited and credible third party partner who has been in the business for over six (6) years.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I assign my representative to coordinate during the A&V process?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta understands how busy you are, hence we are giving you the flexibility to designate a contact person from your company to transact with the A&V partner.\n" +
                "</p>\n" +
                "<strong>\n" +
                "How will I be assured that the information and documents I submit will be safe and not be distributed to other parties outside BigBenta?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta has very strict Information Security Policy in place to prevent the un-authorized distribution of confidential information of all our store owners. Data security is a priority for Bigbenta. \n" +
                "</p>\n" +
                "\n" +
                "<h2>About BigBenta Payment and Transaction policy</h2>\n" +
                "<strong>\n" +
                "What forms of payment are accepted by BigBenta for Store subscription?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Storeowners can pay via credit card, over-the-counter payments (OTC), and bank deposit.\n" +
                "</p>\n" +
                "<strong>\n" +
                "What payment is accepted by BigBenta for Store item purchase?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Buyers can pay for items via credit card, over-the-counter (OTC), bank deposit, and cash-on-delivery (COD). COD as a payment option will be the prerogative of the Store Owner if he wants to activate it in the store settings.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I cancel my ordered item?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Purchased items can be cancelled 24 hours before the scheduled delivery, conveniently through the BigBenta Store website.\n" +
                "</p>\n" +
                "<strong>\n" +
                "As a buyer of products/items or services, how will I be assured that I receive the item I purchased/ordered from BigBenta merchants?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta requires all store owners to sign a Store Agreement Policy which contain the following provision:\n" +
                "</p>\n" +
                "<p>\n" +
                "a.  Quality – Storeowner warrants, represents and agrees that all shipments of the LISTED PRODUCTS sold or shipped under this AGREEMENT shall be of great quality (free from defects, unexpired, usable and consumable by the consumer) to the full extent of the value indicated on the Product.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I cancel my Store subscription?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Store owners can cancel their subscription at any time, but only fees for the unused months will be refunded. The mobilization fee of P2, 500 will not be returned.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I get a refund if my ordered item was not delivered?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Refund will be given by BigBenta for undelivered items. Our partner courier’s committed delivery time is five (5) days for Metro Manila and seven (7) days for provincial areas. \n" +
                "</p>\n" +
                "<strong>\n" +
                "What do I need to do to cancel my Store subscriptions?\n" +
                "</strong>\n" +
                "<p>\n" +
                "You may email your request to customersupport@bigbenta.com and expect us to respond within 48 working hours.\n" +
                "</p>\n" +
                "<strong>\n" +
                "As a storeowner, how long does it take for BigBenta to pay/transfer the amount due me from the time the ordered item was picked up for delivery? \n" +
                "</strong>\n" +
                "<p>\n" +
                "You will be paid for all items sold 20 days after delivery, regardless of the payment mode used by the buyer.\n" +
                "</p>\n" +
                "\n" +
                "<h2>About BigBenta Delivery Policy</h2>\n" +
                "<strong>\n" +
                "When will I receive the item from the time I made the order and paid the transaction?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Delivery time is five (5) days for Metro Manila and seven (7) days for provincial areas. \n" +
                "</p>\n" +
                "<strong>\n" +
                "How will BigBenta know the freight/delivery cost of any product/item I purchased or ordered?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Delivery fees will be displayed when you purchase an item from the BigBenta store.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Does BigBenta provide delivery from or to provincial locations?\n" +
                "</strong>\n" +
                "<p>\n" +
                "Our Courier partner delivers items in Metro Manila and in select provincial areas.\n" +
                "</p>\n" +
                "<strong>\n" +
                "Can I choose which delivery provider will handle my order?\n" +
                "</strong>\n" +
                "<p>\n" +
                "BigBenta has so far accredited one (1) service provider. We will let you know if there will be other courier partners in the future.\n" +
                "</p>\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "</div>\n"));
//        webView = (WebView) findViewById(R.id.webView);
//
//        startWebView(Constants.API_STATIC_TERMS);
    }

//    private void startWebView(String url) {
//
//        webView.setWebViewClient(new WebViewClient() {
//
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            public void onLoadResource(WebView view, String url) {
//                if (progressDialog == null) {
//                    progressDialog = new ProgressDialog(TermsFragment.this);
//                    progressDialog.setMessage(getString(R.string.loading));
//                    progressDialog.show();
//                }
//            }
//
//            public void onPageFinished(WebView view, String url) {
//                try {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.hide();
//                    }
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            }
//
//        });
//
//        webView.getSettings().setJavaScriptEnabled(true);
////        webView.getSettings().setLoadWithOverviewMode(true);
////        webView.getSettings().setUseWideViewPort(true);
////        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
////        webView.setScrollbarFadingEnabled(false);
////        webView.getSettings().setBuiltInZoomControls(true);
//
//        webView.loadUrl(url);
//
//
//    }

    @Override
    public void onBackPressed() {
//        if(webView.canGoBack()) {
//            webView.goBack();
//        } else {
//            progressDialog.dismiss();
//            super.onBackPressed();
            Intent login = new Intent(this,LANDINGPAGE.class);
            startActivity(login);
            this.finish();

       // }
    }

//    public boolean onSupportNavigateUp() {
//        progressDialog.dismiss();
//        onBackPressed();
//        return true;
//    }

}
