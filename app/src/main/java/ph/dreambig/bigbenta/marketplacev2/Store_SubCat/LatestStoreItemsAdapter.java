package ph.dreambig.bigbenta.marketplacev2.Store_SubCat;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;


import java.util.ArrayList;
import java.util.List;



public class LatestStoreItemsAdapter extends RecyclerView.Adapter<LatestStoreItemsAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;
    SessionManager sessionManager;
    //List of superHeroes
    List<StoreSubRetrofit> superHeroes = new ArrayList<>();


    public LatestStoreItemsAdapter(List<StoreSubRetrofit> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_categories_cardview, parent, false);


        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StoreSubRetrofit    latestitems =  superHeroes.get(position);

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(latestitems.getimage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
//
//        holder.imageView.setImageUrl(latestitems.getimage(), imageLoader);
//if(sessionManager.getsubcatid().equals("subcatId")){

        if(latestitems.getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().get(position).getName()!=null){
            holder.product_name.setText(latestitems.getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().get(position).getName().toString());

        }



//    holder.product_name.setText(latestitems.getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().get(Integer.valueOf(position)).getName().toString());

//        holder.product_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment fr=new StoreItems_Frag();
//                FragmentManager fm= ((MainActivity) context).getSupportFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                Bundle args = new Bundle();
//                //Log.d("STORESUBCAT", String.valueOf(superHeroes.get(position).getData().get(position).getCategoryId()));
//                args.putString("catid", latestitems.getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().get(position).getCategoryId().toString());
//
//                fr.setArguments(args);
//                ft.replace(R.id.containerView, fr);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//        holder.pprice.setText(latestitems.getprod_name());
//        holder.store_name.setText(latestitems.getstore_name());

    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public NetworkImageView imageView;

        public TextView product_name;
        public TextView pprice;
        public TextView store_name;


        public ViewHolder(View itemView) {
            super(itemView);
          //  imageView = (NetworkImageView) itemView.findViewById(R.id.product_img);

            product_name= (TextView) itemView.findViewById(R.id.product_name);
//            pprice= (TextView) itemView.findViewById(R.id.price);
//            store_name= (TextView) itemView.findViewById(R.id.store_name);
            Typeface Caviarr = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.product_name.setTypeface(Caviarr);

//            this.pprice.setTypeface(Caviarr);
//            this.store_name.setTypeface(Caviarr);

        }
    }
}