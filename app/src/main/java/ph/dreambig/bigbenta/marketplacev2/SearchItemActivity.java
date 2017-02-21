package ph.dreambig.bigbenta.marketplacev2;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchItemActivity extends AppCompatActivity {

    private Toolbar toolbar;
API_Retrofit service;
    ProgressDialog progressDialog;

    AlertDialog okDialog;

    GridView gridViewItems;

    ExploreAdapter exploreAdapter;
    List<SearchStoreResponse> exploreItems;

    String userTokenPrefs;
    String searchText;

    int pageCount;

   // private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        okDialog = new AlertDialog.Builder(this).create();

        Bundle bundleText = getIntent().getExtras();
        searchText = bundleText.getString(Constants.KEY_SEARCH);

//        getSupportActionBar().setTitle("Results for" + " " + searchText);

        gridViewItems = (GridView) findViewById(R.id.gridViewItems);
        gridViewItems.setEmptyView(findViewById(R.id.textNoRecord));
        exploreItems = new ArrayList<SearchStoreResponse>();
        exploreAdapter = new ExploreAdapter(this, exploreItems);
        gridViewItems.setAdapter(exploreAdapter);

        getUserToken();

        pageCount = 0;
        serviceSearchItems(pageCount);

        gridViewItems.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                serviceSearchItems(pageCount);
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void serviceSearchItems(int page) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        progressDialog.show();
        service = retrofit.create(API_Retrofit.class);
        SearchStoreRequest serviceRequest = new SearchStoreRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory("stores");
        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(page));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setSearch(searchText);

        // Log.d("CAT ID111111", catid);
        Call<SearchStoreResponse> serviceResponseCall =  service.getStoreSearchInfo(serviceRequest);

        serviceResponseCall.enqueue(new Callback<SearchStoreResponse>() {
            @Override
            public void onResponse(Call<SearchStoreResponse> call, retrofit2.Response<SearchStoreResponse> response) {
                int statusCode = response.code();


                SearchStoreResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                //Log.d("COUNT_ITEMDETAIL",String.valueOf(latestClass.size()) );
                //latestClass.clear();
                for(int i=0;i< serviceResponsee.getData().getStores().getStores().size();i++) {

                    exploreItems.add(serviceResponsee);

                    if (serviceResponsee.getData().getStores().getStores().size() != 0) {
                pageCount++;
            }
                    exploreAdapter.notifyDataSetChanged();
                }
                progressDialog.hide();

            }

            @Override
            public void onFailure(Call<SearchStoreResponse> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(SearchItemActivity.this, "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });


//        String API_SEARCH_ITEMS = Constants.API_BIGBENTA + Constants.API_SEARCH_ITEMS +
//                "?" + Constants.USER_TOKEN + "=" + userTokenPrefs +
//                "&" + Constants.PAGE + "=" + page +
//                "&" + Constants.COUNT + "=" + Constants.COUNT_VALUE +
//                "&" + Constants.SEARCH_TEXT + "=" + searchText;
//
//        Cache cache = AppController.getInstance().getRequestQueue().getCache();
//        Cache.Entry entry = cache.get(API_SEARCH_ITEMS);
//
//            try {
//                String data = new String(entry.data, "UTF-8");
//                try {
//                    parseSearchItemsResponse(new JSONObject(data));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//
//            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
//                    API_SEARCH_ITEMS, null, new Response.Listener<JSONObject>() {
//
//                @Override
//                public void onResponse(JSONObject response) {
//                    VolleyLog.d(TAG, "Response: " + response.toString());
//                    if (response != null) {
//                        parseSearchItemsResponse(response);
//                    }
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    VolleyLog.d(TAG, "Error: " + error.getMessage());
//                    okDialog.setMessage(getString(R.string.try_again));
//                    okDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            progressDialog.dismiss();
//                            okDialog.cancel();
//                            finish();
//                        }
//                    });
//                    okDialog.show();
//                    progressDialog.dismiss();
//                }
//            }) {
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put(Constants.HEADER_TAG, Base64.encodeToString(Constants.HEADER_VALUE.getBytes(), Base64.DEFAULT));
//                    return params;
//                }
//            };
//
//            AppController.getInstance().addToRequestQueue(jsonReq);

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setCancelable(false);
            progressDialog.show();

    }

//    private void parseSearchItemsResponse(JSONObject response) {
//        try {
//            JSONArray dataArray = response.getJSONArray("data");
//
//            for (int i = 0; i < dataArray.length(); i++) {
//                JSONObject dataObject = (JSONObject) dataArray.get(i);
//                JSONArray imageArray = new JSONArray(dataArray.getJSONObject(i).optString("images", "-"));
//                JSONArray categoriesArray = new JSONArray(dataArray.getJSONObject(i).optString("categories", "-"));
//
//                JSONObject userObject = dataObject.getJSONObject("user");
//                JSONObject pricesObject = dataObject.getJSONObject("prices");
//                JSONObject locationObject = dataObject.getJSONObject("location");
//
//                Explore explore = new Explore();
//                explore.setUserId(userObject.optString("user_id", "-"));
//                explore.setProfilePicUrl(userObject.optString("sm_url", "-"));
//                explore.setProfilePicUrlMd(userObject.optString("md_url", "-"));
//                explore.setFirstName(userObject.optString("first_name", "-"));
//                explore.setLastName(userObject.optString("last_name", "-"));
//                explore.setItemName(dataObject.optString("title", "-"));
//                explore.setPrice(dataObject.optString("currency_abbr", "-") + " " + pricesObject.getJSONObject("actual").optString("formatted", "-"));
//                explore.setLocation(locationObject.optString("city_name", "-"));
//                explore.setMemberSince(userObject.getJSONObject("created_date").optString("formatted", "-"));
//                explore.setDateCreated(dataObject.getJSONObject("created_date").optString("formatted", "-"));
//                explore.setDescription(dataObject.optString("description", "-"));
//                explore.setItemId(dataObject.optString("item_id", "-"));
//                explore.setMobile(userObject.optString("mobile", "-"));
//                explore.setCondition(dataObject.optString("condition_name", "-"));
//                explore.setViewCount(dataObject.optString("view_count", "0"));
//                explore.setHasFavorited(dataObject.optString("has_favorited", "0"));
//
//                for (int j = 0; j < imageArray.length(); j++) {
//                    if (imageArray.getJSONObject(j).has("is_primary")) {
//                        String smdImageUrl = imageArray.getJSONObject(j).optString("smd_url", "-");
//                        explore.setImageUrl(smdImageUrl);
//                    }
//
//                    String smdImageUrlArr = imageArray.getJSONObject(j).optString("smd_url", "-");
//                    ArrayList<String> images = new ArrayList<String>();
//
//                    images.add(smdImageUrlArr);
//                }
//
//                StringBuilder builder = new StringBuilder();
//                for (int k = 0; k < categoriesArray.length(); k++) {
//                    String categoriesArr = categoriesArray.getJSONObject(k).optString("name", "-");
//                    ArrayList<String> images = new ArrayList<String>();
//
//                    images.add(categoriesArr);
//
//                    builder.append(categoriesArr + "\n");
//
//                }
////                explore.setCategories(builder.toString().substring(0, builder.toString().length() - 2));
//                explore.setCategories(builder.toString());
//
//                exploreItems.add(explore);
//            }
//
//            if (dataArray.length() != 0) {
//                pageCount++;
//            }
//
//            exploreAdapter.notifyDataSetChanged();
//            progressDialog.dismiss();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
        private int visibleThreshold = 12;
        private int currentPage = 0;
        private int previousTotalItemCount = 0;
        private boolean loading = true;
        private int startingPageIndex = 0;

        public EndlessScrollListener() {
        }

        public EndlessScrollListener(int visibleThreshold) {
            this.visibleThreshold = visibleThreshold;
        }

        public EndlessScrollListener(int visibleThreshold, int startPage) {
            this.visibleThreshold = visibleThreshold;
            this.startingPageIndex = startPage;
            this.currentPage = startPage;
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.loading = true;
                }
            }
            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
                currentPage++;
            }

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                onLoadMore(currentPage + 1, totalItemCount);
                loading = true;
            }
        }

        public abstract void onLoadMore(int page, int totalItemsCount);

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }
    }

    public void getUserToken() {
        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(this);
        userTokenPrefs = preferences.getString(Constants.PREFS_USER_TOKEN, "");

    }
}
