package ph.dreambig.bigbenta.marketplacev2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.widget.ShareDialog;

import java.util.List;

/**
 * Created by pol_oribello on 6/21/2015.
 */
public class ExploreAdapter extends BaseAdapter {

    ProgressDialog progressDialog;

    AlertDialog okDialog;

    private FragmentActivity exploreFragment;
    private List<SearchStoreResponse> exploreItems;
    private LayoutInflater inflater;

    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    String userTokenPrefs;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    public ExploreAdapter(FragmentActivity exploreFragment, List<SearchStoreResponse> exploreItems) {
        FacebookSdk.sdkInitialize(exploreFragment.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        this.exploreFragment = exploreFragment;
        this.exploreItems = exploreItems;
        getUserToken();
        okDialog = new AlertDialog.Builder(this.exploreFragment).create();
        shareDialog = new ShareDialog(this.exploreFragment);
    }

    @Override
    public int getCount() {
        return exploreItems.size();
    }

    @Override
    public Object getItem(int location) {
        return exploreItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        RelativeLayout relativeLayoutUser;
        ImageView imageFavorite;
        ImageView imageIsFavorite;
        ImageView imageMessage;
        ImageView imageShare;
        NetworkImageView imageProfilePic;
        TextView textUsername;
        TextView textItemName;
        TextView textPrice;
        TextView textLocation;
        //ItemImageView imageItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Holder holder = new Holder();

        if (inflater == null)
            inflater = (LayoutInflater) exploreFragment.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        if (convertView == null)
//            convertView = inflater.inflate(R.layout.explore_item, null);

//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();

       holder.relativeLayoutUser = (RelativeLayout) convertView.findViewById(R.id.relativeLayoutUser);
        holder.imageFavorite = (ImageView) convertView.findViewById(R.id.imageFavorite);
        holder.imageIsFavorite = (ImageView) convertView.findViewById(R.id.imageIsFavorite);
        holder.imageMessage = (ImageView) convertView.findViewById(R.id.imageMessage);
        holder.imageShare = (ImageView) convertView.findViewById(R.id.imageShare);
        holder.imageProfilePic = (NetworkImageView) convertView.findViewById(R.id.imageProfilePic);
        holder.textUsername = (TextView) convertView.findViewById(R.id.textUsername);
        holder.textItemName = (TextView) convertView.findViewById(R.id.textItemName);
        holder.textPrice = (TextView) convertView.findViewById(R.id.textPrice);
        holder.textLocation = (TextView) convertView.findViewById(R.id.textLocation);
       // holder.imageItem = (ItemImageView) convertView.findViewById(R.id.imageItem);

        final SearchStoreResponse explore = exploreItems.get(position);

//        if (explore.getHasFavorited() != "0") {
//            holder.imageIsFavorite.setVisibility(View.VISIBLE);
//            holder.imageFavorite.setVisibility(View.GONE);
//        } else {
//            holder.imageIsFavorite.setVisibility(View.GONE);
//            holder.imageFavorite.setVisibility(View.VISIBLE);
//        }

        holder.textUsername.setText(explore.getData().getStores().getStores().get(position).getTitle().toString() );
        holder.textItemName.setText(explore.getData().getStores().getStores().get(position).getTitle().toString());
        holder.textPrice.setText(explore.getData().getStores().getStores().get(position).getPrice().toString());
        holder.textLocation.setText(explore.getData().getStores().getStores().get(position).getLocation().toString());

       // holder.imageProfilePic.setImageUrl(explore.getData().getStores().getStores().get(position).getImage().get(position).getImageMd().toString(), imageLoader);
        Glide.with(exploreFragment)
                .load(explore.getData().getStores().getStores().get(position).getImage().get(position).getImageMd().toString())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.bbpaula)
                .into( holder.imageProfilePic);

//        if (explore.getImageUrl() != null) {
//            holder.imageItem.setImageUrl(explore.getImageUrl(), imageLoader);
//            holder.imageItem.setVisibility(View.VISIBLE);
//            holder.imageItem.setResponseObserver(new ItemImageView.ResponseObserver() {
//                @Override
//                public void onError() {
//                }
//
//                @Override
//                public void onSuccess() {
//                }
//            });
//        } else {
//            holder.imageItem.setVisibility(View.INVISIBLE);
//        }

//        holder.relativeLayoutUser.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(exploreFragment, AboutSellerActivity.class);
//                Bundle bundleText = new Bundle();
//                bundleText.putString(Constants.KEY_USER_ID, explore.getUserId());
//                bundleText.putString(Constants.KEY_PROFILE_MD, explore.getProfilePicUrlMd());
//                bundleText.putString(Constants.KEY_FIRST_NAME, explore.getFirstName());
//                bundleText.putString(Constants.KEY_LAST_NAME, explore.getLastName());
//                bundleText.putString(Constants.KEY_MOBILE, explore.getMobile());
//                bundleText.putString(Constants.KEY_MEMBER_SINCE, explore.getMemberSince());
//                intent.putExtras(bundleText);
//                exploreFragment.startActivity(intent);
//            }
//        });

//        holder.imageItem.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(exploreFragment, ItemDetailsActivity.class);
//                Bundle bundleText = new Bundle();
//                bundleText.putString(Constants.KEY_ITEM_NAME, explore.getItemName());
//                bundleText.putString(Constants.KEY_PRICE, explore.getPrice());
//                bundleText.putString(Constants.KEY_VIEWS, explore.getViewCount());
//                bundleText.putString(Constants.KEY_LOCATION, explore.getLocation());
//                bundleText.putString(Constants.KEY_CATEGORIES, explore.getCategories());
//                bundleText.putString(Constants.KEY_CONDITION, explore.getCondition());
//                bundleText.putString(Constants.KEY_DATE_CREATED, explore.getDateCreated());
//                bundleText.putString(Constants.KEY_DESCRIPTION, explore.getDescription());
//                bundleText.putString(Constants.KEY_FIRST_NAME, explore.getFirstName());
//                bundleText.putString(Constants.KEY_LAST_NAME, explore.getLastName());
//                bundleText.putString(Constants.KEY_MOBILE, explore.getMobile());
//                bundleText.putString(Constants.KEY_MEMBER_SINCE, explore.getMemberSince());
//                bundleText.putString(Constants.KEY_PROFILE_MD, explore.getProfilePicUrlMd());
//                bundleText.putString(Constants.KEY_ITEM_MD, explore.getImageUrl());
//                bundleText.putString(Constants.KEY_ITEM_ID, explore.getItemId());
//                bundleText.putString(Constants.KEY_FAVORITE, explore.getHasFavorited());
//
//                intent.putExtras(bundleText);
//                exploreFragment.startActivity(intent);
//            }
//        });

//        holder.imageFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (userTokenPrefs != "") {
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                            Constants.API_BIGBENTA + Constants.API_FAVORITE,
//                            new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//                                    try {
//                                        JSONObject jsonString = new JSONObject(response);
//
//                                        if (jsonString.getString("success") == Constants.TRUE) {
//                                            explore.setHasFavorited("1");
//                                            holder.imageIsFavorite.setVisibility(View.VISIBLE);
//                                            holder.imageFavorite.setVisibility(View.GONE);
//                                            progressDialog.dismiss();
//                                        }
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            okDialog.setMessage(exploreFragment.getString(R.string.try_again));
//                            okDialog.setButton(AlertDialog.BUTTON_POSITIVE, exploreFragment.getString(R.string.ok), new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    progressDialog.dismiss();
//                                    okDialog.cancel();
//                                    exploreFragment.finish();
//                                }
//                            });
//                            okDialog.show();
//                            progressDialog.dismiss();
//                            progressDialog.dismiss();
//                        }
//                    }) {
//                        @Override
//                        protected Map<String, String> getParams() {
//                            Map<String, String> params = new HashMap<String, String>();
//                            params.put(Constants.USER_TOKEN, userTokenPrefs);
//                            params.put(Constants.ITEM_ID, explore.getItemId());
//
//                            return params;
//                        }
//
//                        @Override
//                        public Map<String, String> getHeaders() throws AuthFailureError {
//                            Map<String, String> params = new HashMap<String, String>();
//                            params.put(Constants.HEADER_TAG, Base64.encodeToString(Constants.HEADER_VALUE.getBytes(), Base64.DEFAULT));
//                            return params;
//                        }
//                    };
//
//                    AppController.getInstance().addToRequestQueue(stringRequest);
//
//                    progressDialog = new ProgressDialog(exploreFragment);
//                    progressDialog.setMessage(exploreFragment.getString(R.string.loading));
//                    progressDialog.setCancelable(false);
//                    progressDialog.show();
//                } else {
//                    okDialog.setMessage(exploreFragment.getString(R.string.need_to_login_favorite));
//                    okDialog.setButton(AlertDialog.BUTTON_POSITIVE, exploreFragment.getString(R.string.ok), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            okDialog.cancel();
//                        }
//                    });
//                    okDialog.show();
//                }
//            }
//        });

//        holder.imageIsFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                        Constants.API_BIGBENTA + Constants.API_UNFAVORITE,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                try {
//                                    JSONObject jsonString = new JSONObject(response);
//
//                                    if (jsonString.getString("success") == Constants.TRUE) {
//                                        explore.setHasFavorited("0");
//                                        holder.imageIsFavorite.setVisibility(View.GONE);
//                                        holder.imageFavorite.setVisibility(View.VISIBLE);
//                                        progressDialog.dismiss();
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        okDialog.setMessage(exploreFragment.getString(R.string.try_again));
//                        okDialog.setButton(AlertDialog.BUTTON_POSITIVE, exploreFragment.getString(R.string.ok), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                progressDialog.dismiss();
//                                okDialog.cancel();
//                                exploreFragment.finish();
//                            }
//                        });
//                        okDialog.show();
//                        progressDialog.dismiss();
//                        progressDialog.dismiss();
//                    }
//                }) {
////                    @Override
////                    protected Map<String, String> getParams() {
////                        Map<String, String> params = new HashMap<String, String>();
////                        params.put(Constants.USER_TOKEN, userTokenPrefs);
////                        params.put(Constants.ITEM_ID, explore.getItemId());
////
////                        return params;
////                    }
//
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put(Constants.HEADER_TAG, Base64.encodeToString(Constants.HEADER_VALUE.getBytes(), Base64.DEFAULT));
//                        return params;
//                    }
//                };
//
//                AppController.getInstance().addToRequestQueue(stringRequest);
//
//                progressDialog = new ProgressDialog(exploreFragment);
//                progressDialog.setMessage(exploreFragment.getString(R.string.loading));
//                progressDialog.setCancelable(false);
//                progressDialog.show();
//            }
//        });

                holder.imageMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                Intent intent = new Intent(exploreFragment, SendMessageActivity.class);
//                Bundle bundleText = new Bundle();
//                bundleText.putString(Constants.KEY_ITEM_ID, explore.getItemId());
//
//                intent.putExtras(bundleText);
//                exploreFragment.startActivity(intent);
                    }
                });

                holder.imageShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                        .setContentTitle(explore.getItemName())
//                        .setContentDescription(explore.getDescription())
//                        .setContentUrl(Uri.parse(Constants.BIGBENTA_ITEM_LINK +
//                                explore.getItemId() + "/" + explore.getItemName()))
//                        .setImageUrl(Uri.parse(explore.getImageUrl()))
//                        .build();
//
//                shareDialog.show(linkContent);
                    }
                });

                return convertView;
            }

            public void getUserToken() {
                SharedPreferences preferences = PreferenceManager.
                        getDefaultSharedPreferences(exploreFragment);
                userTokenPrefs = preferences.getString(Constants.PREFS_USER_TOKEN, "");
            }

        }
