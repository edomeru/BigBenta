package ph.dreambig.bigbenta.marketplacev2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;


/**
 * Created by edmeralarte on 26/09/2016.
 */
public class PostAds extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    TextView toolbar_title;
    Spinner ConditionSpinner;
    Object spinnerValue;
    Button submitad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postads);

         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        toolbar_title =(TextView)findViewById(R.id.toolbar_title);
        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "arialbold.ttf");
        toolbar_title.setTypeface(tf);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ConditionSpinner = (Spinner)findViewById(R.id.condition);
        ArrayAdapter deliveryadapter = ArrayAdapter.createFromResource(this,
                R.array.condition, android.R.layout.simple_spinner_dropdown_item);
        ConditionSpinner.setAdapter(deliveryadapter);
        ConditionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerValue = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        submitad= (Button) findViewById(R.id.submitad);
        submitad.setOnClickListener(this);

    }



    @Override
    public void onBackPressed() {

        Intent login = new Intent(this,MainActivity.class);
        startActivity(login);
        this.finish();


    }


    @Override
    public void onClick(View view) {
        Intent login = new Intent(this,MainActivity.class);
        startActivity(login);
        this.finish();
    }
}