package ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service;



import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.Categories_Store.StoreAdapter;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.ServiceItems2.ServiceItems22_Frag;

import ph.dreambig.bigbenta.marketplacev2.ServiceSubCat.Service_SubCat_Frag;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Category_Service extends Fragment {

    private List<ServiceData> serviceData = new ArrayList<>();
    private ServiceAdapter mAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gaggeredGridLayoutManager;

    String categoryId[] ={
            "1",
            "2",
            "3",
            "4",
            "6",
            "7",
            "8",


};


    String mProductName[] ={"Home & repair Maintenance","Health & Beauty",
            "Lessons & Tutorial", "Events",
            "Equipment & Devices","Technical Support",
             "Bussines Support"};

    String mImageUrl[]= {"http://edmer.x10host.com/bb/repair.jpeg","http://edmer.x10host.com/bb/beauty.jpg",
            "http://edmer.x10host.com/bb/tutorial.jpg", "http://edmer.x10host.com/bb/events.jpeg",
           "http://edmer.x10host.com/bb/electricalequip.jpg",
            "http://edmer.x10host.com/bb/electroniccompo.jpg","http://edmer.x10host.com/bb/eyewear.jpeg"};

    Category_Service newInstance(String mProductName, String mProductImage, String categoryId) {
        Category_Service ViewProductFragment = new Category_Service();

        Bundle args = new Bundle();
        args.putString("Name", mProductName);
        args.putString("Url", mProductImage);
        args.putString("categoryId",categoryId);
                ViewProductFragment.setArguments(args);

        return ViewProductFragment;
    }
    public Category_Service() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ServiceAdapter(serviceData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
       // ((MainActivity) getActivity()).setTextName("SERVICE");
        recyclerView = (RecyclerView)view.findViewById(R.id.service_recycler_view);
        recyclerView.setHasFixedSize(true);
       // int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());
        gaggeredGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        mAdapter =  new ServiceAdapter(serviceData);

        if(mAdapter.getItemCount()==0) {
            prepareVisitData();
            mAdapter =  new ServiceAdapter(serviceData);
            recyclerView.setAdapter(mAdapter);

        }else {

            recyclerView.setAdapter(mAdapter);

        }


        recyclerView.addOnItemTouchListener(new Service_SubCat_Frag.RecyclerTouchListener(getActivity(), recyclerView, new Service_SubCat_Frag.ClickListener() {
            @Override
            public void onClick(View view, int position) {


                ServiceItems22_Frag fr=new ServiceItems22_Frag();
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("criteria_id", serviceData.get(position).getCategoryId());
                args.putString("category_pic", serviceData.get(position).getImgUrl());
                args.putString("category_name", serviceData.get(position).getName());
                fr.setArguments(args);
                ft.replace(R.id.containerView, fr);
                ft.addToBackStack(null);
                ft.commit();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



       // ((MainActivity) getActivity()).setTextName("Services");
        return view;
    }
    private void prepareVisitData() {

        for(int i=0; i<mProductName.length;i++) {
            ServiceData newsAndPromosData = new ServiceData(mImageUrl[i],mProductName[i],categoryId[i]);
            serviceData.add(newsAndPromosData);
        }
        mAdapter.notifyDataSetChanged();
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
