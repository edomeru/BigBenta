package ph.dreambig.bigbenta.marketplacev2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import ph.dreambig.bigbenta.marketplacev2.LogIn.FirstPage;


public class TermsFragment extends Fragment {

    TextView adstitle,adsbody;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_terms, container, false);
        setHasOptionsMenu(true);
        ((FirstPage) getActivity()).setTextTitle("Terms & Condition");
        Typeface bariol = Typeface.createFromAsset(getContext().getAssets(), "Bariol_Regular.otf");
        adstitle = (TextView)view.findViewById(R.id.adstitle);
        adsbody= (TextView)view.findViewById(R.id.adstitle);
        adsbody.setTypeface(bariol);
        adstitle.setTypeface(bariol);
        return view;
    }
//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//
//        menu.findItem(R.id.action_cart).setVisible(false);
//        menu.findItem(R.id.search).setVisible(true);
////        menu.findItem(R.id.action_chat).setVisible(true);
//        super.onPrepareOptionsMenu(menu);
//    }

}
