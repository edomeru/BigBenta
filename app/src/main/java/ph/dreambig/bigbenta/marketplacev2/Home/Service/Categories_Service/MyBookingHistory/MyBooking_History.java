package ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookingHistory;


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
public class MyBooking_History extends Fragment {
View x;
    TextView labelbook;

    public MyBooking_History() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         x =inflater.inflate(R.layout.my_booking__history, container, false);
        labelbook = (TextView)x.findViewById(R.id.labelbook);
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        labelbook.setTypeface(bariol);

        return x;
    }

}
