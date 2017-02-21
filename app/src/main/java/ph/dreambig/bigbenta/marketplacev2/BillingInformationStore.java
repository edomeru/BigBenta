package ph.dreambig.bigbenta.marketplacev2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import ph.dreambig.bigbenta.marketplacev2.OrderReview.OrderReview;


public class BillingInformationStore extends Fragment {

    Spinner Country;
    ImageButton ToShippingMethod;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.billing_information_layout,container,false);

        ArrayAdapter sortbyAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.country_array, android.R.layout.simple_spinner_dropdown_item);
        Country = (Spinner) view.findViewById(R.id.country);
        Country.setAdapter(sortbyAdapter);
        ToShippingMethod = (ImageButton)view.findViewById(R.id.toshippingmethod);

        Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {

                } else if(position == 1){


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ToShippingMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //MainActivity.setFragment("Shipping Method");
                OrderReview fragment = new OrderReview();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView, fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
