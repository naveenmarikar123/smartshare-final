package com.example.naveenmarikar.smartshare1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by shiv on 22/3/15.
 */

public class TransactionAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Group> groups;

    String[] names = {"Rachel", "Mira", "Cindrela", "Ross", "Mac", "Chandler", "VJ", "Joey"};

    public String getName(int id){
        String name=names[id%8] ;
        return name;
    }

    public String getIsBroken(boolean isBroken){
        return (isBroken)? "Has been broken":"Unbroken and in Fresh condition";
    }


    public String getIsEnhanced(boolean isEnhanced){
        return (isEnhanced)? "Has been Enhanced":"No upgrades.";
    }


    public String getDate(long epoch){

        Date date = new Date(epoch);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        return formatted;
       // Log.d("date: ", formatted);
//        format.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
//        formatted = format.format(date);
//        System.out.println(formatted);
    }

    public int getRating(double rating){
        int temp = R.drawable.rating4_2;
        return temp;
    }


    public TransactionAdapter(Context context, ArrayList<Group> groups){
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition){
        Child chList = groups.get(groupPosition).getTransactionDetail();
        return chList;
    }

    public Object getGroup(int groupPosition, int childPosition){
        Group group = groups.get(groupPosition);
        return group;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Child child = (Child) getChild(groupPosition, childPosition);
        Group grp = (Group) getGroup(groupPosition,childPosition);

        if (convertView == null) {
            LayoutInflater inflaInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflaInflater.inflate(R.layout.child, null);
        }


        ImageView icon = (ImageView) convertView.findViewById(R.id.imgIcon);
        icon.setImageResource(grp.getImg());
        //icon.setImageResource(R.drawable.chairs);

//        ImageView call = (ImageView) convertView.findViewById(R.id.callButton);
//        call.setImageResource(grp.getCallImg());
//        //call.setImageResource(R.drawable.call);


        ImageView rating = (ImageView) convertView.findViewById(R.id.itemRating);
        rating.setImageResource(getRating(1.2));


        TextView name = (TextView) convertView.findViewById(R.id.itemName);
        name.setText(grp.getItem().toString());

//        TextView distance = (TextView) convertView.findViewById(R.id.distance);
//        distance.setText(child.get().toString());


        TextView requestedBy = (TextView) convertView.findViewById(R.id.requestedBy);
        requestedBy.setText("Requested by "+ getName(child.getTakerId()));


        TextView requestedOn = (TextView) convertView.findViewById(R.id.requestDate);
        requestedOn.setText("Requested on "+getDate(child.getRequestDate()));


        TextView owner = (TextView) convertView.findViewById(R.id.owner);
        owner.setText(getName(child.getGiverId()));


        TextView borrowedOn = (TextView) convertView.findViewById(R.id.borrowDate);
        borrowedOn.setText("Borrowed on "+getDate(child.getBorrowDate()));


        TextView dueDate = (TextView) convertView.findViewById(R.id.dueDate);
        dueDate.setText("Due on "+ getDate(child.getDueDate()));


        TextView isBroken = (TextView) convertView.findViewById(R.id.isBroken);
        isBroken.setText(getIsBroken(child.isBroken()));


        TextView isEnhanced = (TextView) convertView.findViewById(R.id.isEnhanced);
        isEnhanced.setText(getIsEnhanced(child.isEnhanced()));

        TextView status = (TextView) convertView.findViewById(R.id.status);
        status.setText(child.getTransactionStatus().toString());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return 1;//chList.size();
    }

    //@Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.row_layout, null);
        }


        ImageView iv = (ImageView) convertView.findViewById(R.id.imgIcon);
        iv.setImageResource(group.getImg());

        TextView tnv = (TextView) convertView.findViewById(R.id.itemName);
        tnv.setText(group.getItem());

        TextView tsv = (TextView) convertView.findViewById(R.id.itemStatus);
        tsv.setText(group.getStatus());

        int imgId = group.getCallImg();
        if(group.getStatus()=="Declined"){
                imgId = R.drawable.search;
        }
        else if(group.getStatus()=="Complete"){
            imgId = R.drawable.rate_3;
        }

        ImageButton call = (ImageButton) convertView.findViewById(R.id.callButton);
        call.setImageResource(imgId);
        call.setFocusable(false);

        return convertView;
    }

    //@Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}

/**
 * Created by shiv on 28/2/15.
// */
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//public class  extends ArrayAdapter<Transactions> {
//
//    Context context;
//    int layoutResourceId;
//
//    Transactions[] data=null;
//
//    public TransactionAdapter(Context context,int layoutResourceId, Transactions[] data){
//        super(context, layoutResourceId, data);
//        this.context = context;
//        this.data = data ;
//        this.layoutResourceId = layoutResourceId ;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        View row = convertView;
//        TransactionHolder holder = null;
//        if(row == null)
//        {
//            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//            row = inflater.inflate(layoutResourceId, parent, false);
//
//            holder = new TransactionHolder();
//            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
//            holder.itemName = (TextView)row.findViewById(R.id.itemName);
//            holder.itemStatus = (TextView)row.findViewById(R.id.itemStatus);
//            holder.callButton = (ImageView)row.findViewById(R.id.callButton);
//
//            row.setTag(holder);
//        }
//        else
//        {
//            holder = (TransactionHolder)row.getTag();
//        }
//
//        Transactions transaction = data[position];
//        holder.imgIcon.setImageResource(transaction.img);
//        holder.itemName.setText(transaction.item);
//        holder.itemStatus.setText(transaction.status);
//        holder.callButton.setImageResource(transaction.callImg);
//
//        return row;
//    }
//
//
//    static class TransactionHolder{
//        ImageView imgIcon;
//        ImageView callButton;
//        TextView itemName;
//        TextView itemStatus;
//
//    }
//
//}
