package ph.dreambig.bigbenta.marketplacev2;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;



/**
 * Created by jenzoned on 4/22/16.
 */
public class ShippingMethod extends Fragment implements DatePickerDialog.OnDateSetListener {

    EditText DeliveryDate;
    Spinner DeliveryDateSpinner;
    ImageButton orderReview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipping_methodstore,container,false);

        DeliveryDate = (EditText)view.findViewById(R.id.deliverydate);
        DeliveryDateSpinner = (Spinner)view.findViewById(R.id.deliverydatespinner);
        orderReview = (ImageButton)view.findViewById(R.id.imageButton);
        ArrayAdapter deliveryadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.delivery_date, android.R.layout.simple_spinner_dropdown_item);
        DeliveryDateSpinner.setAdapter(deliveryadapter);

        DeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        ShippingMethod.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.mediumBlue));
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");

            }
        });

        orderReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // MainActivity.setFragment("Order Review");

            }
        });


        return view;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
       DeliveryDate.setText(date);
    }
}
