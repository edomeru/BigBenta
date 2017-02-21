package ph.dreambig.bigbenta.marketplacev2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;


public class TermsFragmentMain extends Fragment {

    View view;
TextView adstitle,adsbody;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.activity_terms, container, false);
        setHasOptionsMenu(true);
        adstitle = (TextView)view.findViewById(R.id.adstitle);
        adsbody= (TextView)view.findViewById(R.id.adsbody);
        Typeface bariol = Typeface.createFromAsset(getContext().getAssets(), "Bariol_Regular.otf");
        ((MainActivity) getActivity()).setTextTitle("Terms & Condition");
        adsbody.setTypeface(bariol);
        adstitle.setTypeface(bariol);
        return view;
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.action_service).setVisible(false);
//        menu.findItem(R.id.action_cads).setVisible(false);
        menu.findItem(R.id.search).setVisible(true);
       // menu.findItem(R.id.action_chat).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }


}
