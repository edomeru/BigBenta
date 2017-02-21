package ph.dreambig.bigbenta.marketplacev2;

import ph.dreambig.bigbenta.marketplacev2.AdsItemDetail.AdsDetailRequest;
import ph.dreambig.bigbenta.marketplacev2.AdsItemDetail.AdsDetailRetrofitResponse;
import ph.dreambig.bigbenta.marketplacev2.AdsItemDetail.SendMsg.SendMsgResponse;
import ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag.AdsItemRequest;
import ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag.AdsItemRetrofitResponse;
import ph.dreambig.bigbenta.marketplacev2.AdsSubCat.AdsSubCatRequest;
import ph.dreambig.bigbenta.marketplacev2.AdsSubCat.AdsSubCatRetrofitResponse;
import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds.MyAdsRequest;
import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds.MyAdsResponse;
import ph.dreambig.bigbenta.marketplacev2.Home.BannerRequest;
import ph.dreambig.bigbenta.marketplacev2.Home.BannerResponse;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyOrderHistory.OrderHistRequest;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyOrderHistory.OrderHistResponse;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.PromoDeals.DealsRequest;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.PromoDeals.DealsResponse;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Facebook.FacebookRequest;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Facebook.FacebookResponse;
import ph.dreambig.bigbenta.marketplacev2.Profile.ProfileRequest;
import ph.dreambig.bigbenta.marketplacev2.Profile.ProfileResponse;
import ph.dreambig.bigbenta.marketplacev2.Profile.UpdateProfileResponse;
import ph.dreambig.bigbenta.marketplacev2.Search.SearchADSRequest;
import ph.dreambig.bigbenta.marketplacev2.Search.SearchADSResponse;
import ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL.SearchALL;
import ph.dreambig.bigbenta.marketplacev2.ServiceItems2.ServiceRequest;
import ph.dreambig.bigbenta.marketplacev2.ServiceItems2.ServiceResponse;
import ph.dreambig.bigbenta.marketplacev2.StoreItemDetail.StoreDetailRequest;
import ph.dreambig.bigbenta.marketplacev2.StoreItemDetail.StoreDetailRetrofitResponse;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemRequest;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemRetrofitResponse;
import ph.dreambig.bigbenta.marketplacev2.Store_SubCat.StoreSubCatRequest;
import ph.dreambig.bigbenta.marketplacev2.Store_SubCat.StoreSubRetrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Edmer on 07/11/2016.
 */

public interface API_Retrofit {

@POST(Constants.API_SERVICESEARCH)
    Call<ServiceResponse> getSubCat(@Body ServiceRequest servicerequest);

    @POST(Constants.API_StoreSub)
    Call<StoreSubRetrofit> getStoreSubCat(@Body StoreSubCatRequest storesubrequest);

    @POST(Constants.API_ClassifiedSub)
    Call<AdsSubCatRetrofitResponse> getAdsSubCat(@Body AdsSubCatRequest storesubrequest);

    @POST(Constants.API_search)
    Call<AdsDetailRetrofitResponse> getAdsDetail(@Body AdsDetailRequest adsdetailrequest);

    @POST(Constants.API_search)
    Call<StoreItemRetrofitResponse> getStoreItems(@Body StoreItemRequest adsdetailrequest);

    @POST(Constants.API_search)
    Call<StoreDetailRetrofitResponse> getStoreDetail(@Body StoreDetailRequest storedetailrequest);

    @POST(Constants.API_search)
    Call<AdsItemRetrofitResponse> getAdsItems(@Body AdsItemRequest adsitemrequest);

    @POST(Constants.API_USER_PROFILE)
    Call<ProfileResponse> getProfileInfo(@Body ProfileRequest profilerequest);

    @FormUrlEncoded
    @POST(Constants.API_USER_PROFILE_UPDATE)
    Call<UpdateProfileResponse> getUpdatedProfileInfo(@Field("app_key") String app_key, @Field("is_mobile") String is_mobile ,
                                                      @Field("token") String token , @Field("user_id") String user_id , @Field("first_name") String name, @Field("last_name") String lnme,
                                                      @Field("phone") String phone, @Field("mobile") String mobile, @Field("email") String email );

    @POST(Constants.API_Search)
    Call<SearchStoreResponse> getStoreSearchInfo(@Body SearchStoreRequest searchstorerequest);

    @POST(Constants.API_Search)
    Call<SearchADSResponse> getStoreSearchInfo(@Body SearchADSRequest searchstorerequest);

    @POST(Constants.API_Search)
    Call<ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL.SearchALLResponse> getALLSearch(@Body SearchALL searchALLrequest);

    @FormUrlEncoded
    @POST(Constants.API_SEND_MESSEGE)
    Call<SendMsgResponse> getsendMSG(@Field("app_key") String app_key, @Field("is_mobile") String is_mobile ,
                                     @Field("email_sender") String email_sender , @Field("contact_number") String contact_number , @Field("message") String message, @Field("item_id") String item_id);

    @POST(Constants.API_Search)
    Call<DealsResponse> getDeals(@Body DealsRequest dealsrequest);

    @POST(Constants.API_Search)
    Call<MyAdsResponse> getMyAds(@Body MyAdsRequest myadsrequest);

    @POST(Constants.API_ORDER_HIST)
    Call<OrderHistResponse> getOrderHist(@Body OrderHistRequest orderHistrequest);

    @POST(Constants.BANNER)
    Call<BannerResponse> getBanner(@Body BannerRequest bennerrequest);

    @POST(Constants.API_LOGIN)
    Call<FacebookResponse> getFBLogin(@Body FacebookRequest fbloginrequest);

}
