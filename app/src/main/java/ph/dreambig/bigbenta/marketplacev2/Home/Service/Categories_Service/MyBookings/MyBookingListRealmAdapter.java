
package ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ph.dreambig.bigbenta.marketplacev2.BaseApplication;
import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings.RealmFolder.RealmRecyclerViewAdapter;

import ph.dreambig.bigbenta.marketplacev2.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;


public class MyBookingListRealmAdapter extends RealmRecyclerViewAdapter<BookingListData> {
    Context context;
    Realm realm;

    private class EventViewHolderr extends RecyclerView.ViewHolder {
        TextView SPName,address,Amountt,pay_status,SPStatus,Datee,Duration,Refno,Datebooked,checkout;
        CircleImageView SPImg;
        View Devider;
        Button bQuantity,DeleteSP;
        public EventViewHolderr(View itemView) {
            super(itemView);

            SPName = (TextView)itemView.findViewById(R.id.sp_name);
            SPStatus = (TextView)itemView.findViewById(R.id.sp_status);
            SPImg = (CircleImageView)itemView.findViewById(R.id.sp_img);
            Amountt = (TextView) itemView.findViewById(R.id.amt);
            pay_status = (TextView)itemView.findViewById(R.id.pay_status);
            address = (TextView) itemView.findViewById(R.id.address);
            Datee = (TextView)itemView.findViewById(R.id.datee);
                    Datebooked = (TextView)itemView.findViewById(R.id.datebooked);
            checkout = (Button)itemView.findViewById(R.id.checkout);
            Duration = (TextView)itemView.findViewById(R.id.duration);
            Refno = (TextView)itemView.findViewById(R.id.refno);
            Devider = itemView.findViewById(R.id.devider);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybookinglist_cardview, parent, false);
        context = parent.getContext();
        return new EventViewHolderr(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int k) {
        final EventViewHolderr evh = (EventViewHolderr) viewHolder;
        BookingListData SPInfo = getItem(k);

        realm = Realm.getDefaultInstance();
        realm.setAutoRefresh(true);
        evh.checkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


            }
        });



        RealmResults<BookingListData> QueryResults = realm.where(BookingListData.class).findAll();



        evh.SPName.setText(SPInfo.getSPName());
        evh.SPStatus.setText(SPInfo.getSPStatus());
        evh.Datee.setText(SPInfo.getSPDate());
        evh.Datebooked.setText(SPInfo.getDateBooked());
        evh.Duration.setText(SPInfo.getDuration());
        evh.Refno.setText(SPInfo.getRefNo());

        evh.Amountt.setText("₱"+SPInfo.getAmount());
        evh.address.setText(SPInfo.getSPAddress());
       evh.pay_status.setText(SPInfo.getPaymentStatus());
        Glide.with(context)
                .load(SPInfo.getImageUrl())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.bbpaula)
                .into(evh.SPImg);
        if(QueryResults.size()==1){
            evh.Devider.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public class FetchCountTask extends AsyncTask<Void, Integer, Integer> {

        int CartCount;
        public FetchCountTask(int i) {
            super();
            CartCount = i;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            return CartCount;
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }

    private void updateNotificationsBadge(int count) {
        BaseApplication.ServiceItemsCount = count;
        if(count == 0 ){

            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Empty Booking List")
                    .setContentText("You have nothing in your Booklist")
                    .setConfirmText("Book Now")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {


                        }
                    })
                    .show();

        }
        ((Activity) context).invalidateOptionsMenu();
    }
}

