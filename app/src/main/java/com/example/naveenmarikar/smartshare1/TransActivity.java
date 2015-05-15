package com.example.naveenmarikar.smartshare1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class TransActivity extends Activity {

    private TransactionAdapter ExpAdapter;
    private ArrayList<Group> ExpListItems;
    private ExpandableListView ExpList;

    private int transactionSize = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactivity);


        ExpList = (ExpandableListView) findViewById(R.id.trans_detail);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new TransactionAdapter(TransActivity.this, ExpListItems);
        ExpList.setAdapter(ExpAdapter);



    }


    public ArrayList<Group> SetStandardGroups() {

        transactionSize = 3;
        Transactions[] transactionsData = new Transactions[transactionSize];

        transactionsData[0] = new Transactions(R.drawable.portable_generator, "Generator", "Declined", R.drawable.call);
        transactionsData[1] = new Transactions(R.drawable.ladder, "Ladder", "Complete", R.drawable.call);
        transactionsData[2] = new Transactions(R.drawable.hardware, "Hardware-Kit", "Shared", R.drawable.call);
//                new Transactions(R.drawable.portable_generator, "Chairs", "Waiting ", R.drawable.call),
//                new Transactions(R.drawable.portable_generator, "Bicycle", "Declined", R.drawable.call),

//        new Transactions(R.drawable.portable_generator, "Grass Cutter", "Declined", R.drawable.search),
//                new Transactions(R.drawable.portable_generator, "Generator", "Approved ", R.drawable.call),
//                new Transactions(R.drawable.portable_generator, "Hardware tools", "Declined", R.drawable.search),
//                new Transactions(R.drawable.portable_generator, "Umbrella", "Shared", R.drawable.call),


        TransactionDetail[] Detail = new TransactionDetail[transactionSize];
        //for(int i=0;i<Detail.length;i++) {
        Detail[0] = new TransactionDetail(1,2,3,12888834879L, 124464896626L ,2466872546L,"Request Pending", true,false,4.5,4.1);
        Detail[1] = new TransactionDetail(1,2,3,432265565879L, 77456626L ,299762546L,"Completed", true,false,4.5,4.1);
        Detail[2] = new TransactionDetail(1,2,3,121225565879L, 774843256626L ,299762532246L,"Shared", true,false,4.1,4.1);
        //Detail[1] = new InventoryDetail("Generator", "Hardware", 18, R.drawable.portable_generator, 5, R.drawable.rating4_9, 12, "This is very important for me, but most of the time I don't use it, please use it with care. ");
        //}


        ArrayList<Group> list = new ArrayList<Group>();

        int size = 1;
        int j = 0;

        for (Transactions group_name : transactionsData) {
            Child ch = new Child();
            Group gru = new Group();
            gru.setTransactionSummary(group_name);


//            ch_list = new ArrayList<Child>();
            for (; j < size; j++) {

                ch.setTransactionDetail(Detail[j]);

            }
            gru.setTransactionDetail(ch);
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
}



