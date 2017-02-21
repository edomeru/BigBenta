package ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.My_Favorites_Service;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class My_Favorites_Service extends Fragment {
TextView favServices;
View x;
    public My_Favorites_Service() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        x= inflater.inflate(R.layout.my__favorites, container, false);
        favServices =(TextView)x.findViewById(R.id.favServices);
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        favServices.setTypeface(bariol);

                return x;
    }

}
