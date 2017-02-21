package ph.dreambig.bigbenta.marketplacev2.ServiceSubCat;



import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;


public class Service_SubCat_Frag extends Fragment {
    private List<ServiceSubData> storeSubData = new ArrayList<>();
    private ServiceSubAdapter mAdapter;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private ImageView subcatImg;
    String mProductName[] ={"Hair","Face",
            "Eyes", "Nail & Toe Nails", "Body",
            "Skin", "Health Care"};
    String mImageUrl[]= {"http://mdemulher.abril.com.br/sites/mdemulher/files/styles/retangular_horizontal_2/public/core/tingir-cabelo-em-casa.jpg?itok=Vv9sXozg",
            "http://hdwallfusion.com/wallpapers/angelina-jolie/angelina-jolie-face-hd-wallpaper-free.jpg",
            "http://hd.wallpaperswide.com/thumbs/eyes_3-t2.jpg",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSJNtAQbeHOyxfCo-hv0j9nae4YdCr9--26xiSCR9mclf9i6rN9Sw",
            "https://beastsports.com/wp-content/uploads/2014/08/10530653_781842521848016_3433668545348223625_o-200x300.jpg",
            "https://s-media-cache-ak0.pinimg.com/736x/ba/f2/14/baf2148524ff3dd95be46b8c383f5564.jpg",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSDyw8xVy-iRlmW06S2IGFK6hp97cR8M_gvp8IfMt2kTbhiYMud"
          };
    Service_SubCat_Frag newInstance(String mProductName, String mProductImage) {
        Service_SubCat_Frag ViewProductFragment = new Service_SubCat_Frag();

        Bundle args = new Bundle();
        args.putString("Name", mProductName);
        args.putString("Url", mProductImage);
        ViewProductFragment.setArguments(args);

        return ViewProductFragment;
    }

    public Service_SubCat_Frag() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ServiceSubAdapter(storeSubData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.fragment_service_subcat, container, false);

        recyclerView = (RecyclerView)view.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.recycler_service_subcat);
        subcatImg = (ImageView) view.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.sub_cat_img);
        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new Service_SubCat_Frag.RecyclerTouchListener(getActivity(), recyclerView, new Service_SubCat_Frag.ClickListener() {
            @Override
            public void onClick(View view, int position) {

//                ServiceItems1_Frag fragment = new ServiceItems1_Frag();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.containerView, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        prepareVisitData();
        return view;
    }
    private void prepareVisitData() {

        for(int i=0; i<mProductName.length;i++) {
            ServiceSubData newsAndPromosData = new ServiceSubData(mImageUrl[i],mProductName[i]);
            storeSubData.add(newsAndPromosData);
        }
        mAdapter.notifyDataSetChanged();
        Glide.with(getActivity())
                .load("http://nebula.wsimg.com/caaa95514f3a4b6efcf168c1bc831610?AccessKeyId=0937245AA8279835B871&disposition=0&alloworigin=1")
                .centerCrop()
                .into(subcatImg);

    }
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    public static  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Service_SubCat_Frag.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Service_SubCat_Frag.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }
}
