package com.example.naveenmarikar.smartshare1;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by naveenmarikar on 10/05/15.
 */
public class AddItemsActivity extends Activity implements AdapterView.OnItemSelectedListener{

    public String itemName;
    public String itemType;
    public String itemDescription;

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        parent.setSelection(position);
        itemType= parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.additems);

        TextView tx = (TextView) findViewById(R.id.text1);
        TextView tx1 = (TextView) findViewById(R.id.text2);
        TextView tx2 = (TextView) findViewById(R.id.text3);
        TextView tx3 = (TextView) findViewById(R.id.text4);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"Futura Light BT.ttf");
        tx.setTypeface(custom_font);
        tx1.setTypeface(custom_font);
        tx2.setTypeface(custom_font);
        tx3.setTypeface(custom_font);


        Spinner spinner = (Spinner) findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }



    public void sendInfo(View view) {
        EditText iname = (EditText) findViewById(R.id.edittext1);
        itemName = iname.getText().toString();
        EditText idet = (EditText) findViewById(R.id.edittext12);
        itemDescription = idet.getText().toString();
        Toast.makeText(getApplicationContext(),itemDescription,Toast.LENGTH_LONG).show();
    }

}
