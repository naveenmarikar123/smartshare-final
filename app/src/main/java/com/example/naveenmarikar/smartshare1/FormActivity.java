package com.example.naveenmarikar.smartshare1;

import android.app.Activity;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by naveenmarikar on 15/04/15.
 */
public class FormActivity extends Activity {

    LocationManager mLocationManager;
    String Number;
    String Address;
    Double Long;
    Double Lat;
    String ID = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_form);


        TextView tx = (TextView)findViewById(R.id.intro);
        TextView tx1 = (TextView)findViewById(R.id.signup);
        TextView tx2 = (TextView)findViewById(R.id.number);
        TextView tx3 = (TextView)findViewById(R.id.address);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"Futura Light BT.ttf");
        tx.setTypeface(custom_font);
        tx1.setTypeface(custom_font);
        tx2.setTypeface(custom_font);
        tx3.setTypeface(custom_font);


    }

    public void sendDetails(View view) {

        EditText number  = (EditText) findViewById(R.id.number);
        EditText address = (EditText) findViewById(R.id.address);
        GPSTracker gps = new GPSTracker(FormActivity.this);;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ID = extras.getString("FacebookID").toString();
        }

        Number = number.getText().toString();
        Address = address.getText().toString();
        Long = gps.getLongitude();
        Lat = gps.getLatitude();

        Toast.makeText(FormActivity.this,Number+""+Address+""+Long+""+Lat+""+ID,Toast.LENGTH_LONG).show();


    }

    class SendData extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            //ENTER SERVER CODE HERE.
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
