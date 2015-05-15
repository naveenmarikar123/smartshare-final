package com.example.naveenmarikar.smartshare1;

/**
 * Created by shiv on 22/3/15.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class ExpListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<InventGroup> groups;



    public ExpListAdapter(Context context, ArrayList<InventGroup> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        InventChild chList = groups.get(groupPosition).getItems();
        return chList;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        InventChild child = (InventChild) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.inventchild, null);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.imgIcon);
        icon.setImageResource(child.getImage());

        TextView category = (TextView) convertView.findViewById(R.id.category);
        category.setText(child.getCategory().toString());


        TextView involved = (TextView) convertView.findViewById(R.id.involvements);
        involved.setText("Involved in " + Integer.toString(child.getInvolvedIn()));


        TextView ratingByOwner = (TextView) convertView.findViewById(R.id.ratingByOwner);
        ratingByOwner.setText("Owner rated as " + Integer.toString(child.getItemRatingByOwner()));


        TextView ratingCount = (TextView) convertView.findViewById(R.id.ratingCount);
        ratingCount.setText("Total ratings " + Integer.toString(child.getRatingCount()));


        ImageView rating = (ImageView) convertView.findViewById(R.id.itemRating);
        rating.setImageResource(child.getRating());


        TextView desc = (TextView) convertView.findViewById(R.id.itemDesc);
        desc.setText(child.getDescription().toString());

        ToggleButton ts;
        ts = (ToggleButton) convertView.findViewById(R.id.itemStatus);
        ts.setFocusable(false);
        if (child.getItemStatus() == "Out");
            ts.setEnabled(false);


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return 1;//chList.size();
    }

    @Override
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
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        InventGroup group = (InventGroup) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group, null);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.imgIcon);
        iv.setImageResource(group.getItemImg());

        TextView tnv = (TextView) convertView.findViewById(R.id.itemName);
        tnv.setText(group.getItemName());

        ToggleButton tsv;
        tsv = (ToggleButton) convertView.findViewById(R.id.itemStatus);
        tsv.setFocusable(false);
        Log.d(">>>>>>>>>>" + Integer.toString(getGroupCount()) + " IsExpanded ==>" + Boolean.toString(isExpanded), group.getItemStatus());
        if (group.getItemStatus() == "Out")
            tsv.setEnabled(false);

//        tsv.setText(group.getItemStatus());


//        tsv.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                String sts =group.getItemStatus();
//                if(sts=="Hidden") {
//                    if (tsv.isChecked()) {
//                        group.setItemStatus("Shared");
//                    } else {
//                        group.setItemStatus("Hidden");
//                    }
//                }else if(sts=="Shared"){
//                    if (tsv.isChecked()){
//                        group.setItemStatus("Hidden");
//                    } else {
//                        group.setItemStatus("Shared");
//                    }
//                }
//                tsv.setText(group.getItemStatus());
//            }
//        });

//        TextView tx = (TextView) convertView.findViewById(R.id.itemStatus);
//        tx.setText(group.getItemStatus());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    ExpandableListView expListView;

//    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//         Log.d("onGroupClick:", "worked");
//         parent.smoothScrollToPosition(groupPosition);
//         if (parent.isGroupExpanded(groupPosition)) {
//            parent.collapseGroup(groupPosition);
//         } else {
//            parent.expandGroup(groupPosition);
//         }
//
//            return false;
//        }

}