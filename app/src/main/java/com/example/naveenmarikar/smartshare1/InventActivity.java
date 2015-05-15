package com.example.naveenmarikar.smartshare1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;


public class InventActivity extends Activity {

    private ExpListAdapter ExpAdapter;
    private ArrayList<InventGroup> ExpListItems;
    private ExpandableListView ExpList;
    private int inventorySize = 0 ;
    private int lastExpandedGroupPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventactivity);

        ExpList = (ExpandableListView) findViewById(R.id.exp_list);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpListAdapter(InventActivity.this, ExpListItems);
        ExpList.setAdapter(ExpAdapter);

        final Button button = (Button)findViewById(R.id.addItem);


        button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        callAddItemPage(button);
                    }
                }
        );

    }

    public void callAddItemPage(Button button){
        button.setText("It works!");
    }

    public ArrayList<InventGroup> SetStandardGroups() {

        inventorySize =3;

        Inventory[] Summary = new Inventory[inventorySize];

        //for(int i=0;i<Summary.length;i++) {
        int a=R.drawable.cycle;
        //  System.out.print("tester tester "+a);

            Summary[0] = new Inventory(R.drawable.portable_generator, "Generator", "Shared");
            Summary[1] = new Inventory(R.drawable.grass_cutter, "Grass Cutter", "Shared");
            Summary[2] = new Inventory(R.drawable.hardware, "Hardware-Kit", "Out");
        //}



        InventoryDetail[] Detail = new InventoryDetail[inventorySize];
        //for(int i=0;i<Detail.length;i++) {
            Detail[1] = new InventoryDetail("Grass Cutter", "Hardware", 14, R.drawable.grass_cutter, 4, R.drawable.rating4_2, 12, "I bought it 4 months back, really in a condition as good as a new one. But me not having much of use, I can share if you can use it in a handy way. ");
            Detail[0] = new InventoryDetail("Generator", "Hardware", 18, R.drawable.portable_generator, 5, R.drawable.rating4_9, 12, "This is very important for me, but most of the time I don't use it, please use it with care. ");
        Detail[2] = new InventoryDetail("Generator", "Hardware", 18, R.drawable.hardware, 5, R.drawable.rating4_9, 12, "This is very important for me, but most of the time I don't use it, please use it with care. ");
        //}


        ArrayList<InventGroup> list = new ArrayList<InventGroup>();

        int size = 1;
        int j = 0;

        for (Inventory group_name : Summary) {
            InventChild ch = new InventChild();
            InventGroup gru = new InventGroup();
            gru.setItemSummary(group_name);

//            ch_list = new ArrayList<Child>();
            for (; j < size; j++) {

               ch.setItemDetail(Detail[j]);

            }
            gru.setItemDetail(ch);
            list.add(gru);

            size = size + 1;
        }


        return list;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int lastExpandedPosition = -1;
    private ExpandableListView expList;


    public void onGroupExpand(int groupPosition) {
        if (lastExpandedPosition != -1
                && groupPosition != lastExpandedPosition) {
            expList.collapseGroup(lastExpandedPosition);
        }
        lastExpandedPosition = groupPosition;
    }





}
