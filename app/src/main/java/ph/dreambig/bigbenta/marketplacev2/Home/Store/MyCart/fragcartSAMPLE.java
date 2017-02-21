package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragcartSAMPLE extends Fragment {

    View x;
    public fragcartSAMPLE() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         x = inflater.inflate(R.layout.fragment_fragcart_sample, container, false);

        Intent i = new Intent(getActivity(), MyCart2.class);
        getActivity().finish();
        startActivity(i);
        return x;
    }

}
