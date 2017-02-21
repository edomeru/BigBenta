package ph.dreambig.bigbenta.marketplacev2;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutFragment extends Fragment {
    View x;
    private Toolbar toolbar;
TextView whyBB,whatisBB,whatisBBparagraph,whyBBparagraph;
    ProgressDialog progressDialog;
    //WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         x = inflater.inflate(R.layout.about, container, false);
        whyBB= (TextView)x.findViewById(R.id.whyBB);
        whyBBparagraph= (TextView)x.findViewById(R.id.whyBBparagraph);
        whatisBB = (TextView)x.findViewById(R.id.whatisBB);
        whatisBBparagraph = (TextView)x.findViewById(R.id.whatisBBparagraph);
        Typeface bariol = Typeface.createFromAsset(getContext().getAssets(), "Bariol_Regular.otf");
        whatisBBparagraph.setTypeface(bariol);
        whatisBB.setTypeface(bariol);
        whyBB.setTypeface(bariol);
        whyBBparagraph.setTypeface(bariol);
        return x;
    }


}
