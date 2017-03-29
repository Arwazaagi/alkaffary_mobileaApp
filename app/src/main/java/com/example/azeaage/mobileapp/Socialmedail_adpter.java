package com.example.azeaage.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by AZahrani on 3/26/2017.
 */

public class Socialmedail_adpter extends BaseExpandableListAdapter implements View.OnClickListener {
    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String,List<String >> expandableListDetail;
    String [] url=new String[15];

    public Socialmedail_adpter(Context context, List<String> expandableListTitle,HashMap<String,List<String>>expandableListDetail)
    {
        this.context= context;
        this.expandableListTitle =expandableListTitle;
        this.expandableListDetail= expandableListDetail;


    }


    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public int getGroupCount()
    {

        return this.expandableListTitle.size();

    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();

    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition ) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getGroupId(int listPosition) {


        return listPosition;
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {

        return expandedListPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_layout, null);

        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.parent_txt);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;


    }
    TextView expandedListTextView;
    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);

        if (convertView==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout, null);

        }
        expandedListTextView = (TextView) convertView
                .findViewById(R.id.child_text);
        expandedListTextView.setText(expandedListText);
        expandedListTextView.setOnClickListener(this);




        return convertView;
    }

    private void hyperLink(int index) {

        System.out.print("******************     "+index);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url[index]));
        context.startActivity(intent);




    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true ;
    }

    @Override
    public void onClick(View v) {
    TextView textView= (TextView)v;
        if(textView.getText().equals("Follow us on Alkaffary Twitter")){
            url[0]="https://twitter.com/alkaffarygroup?lang=ar";
            hyperLink(0);
        }
        else if(textView.getText().equals("Follow us on HighPoint Twitter")){
            url[1]="https://twitter.com/HighPoint_SA?lang=ar";
            hyperLink(1);
        }

        else if(textView.getText().equals("Follow us on ModernPlace Twitter"))
        {
            url[2]="https://twitter.com/modernpalacesa?lang=ar";
            hyperLink(2);

        }
        else if(textView.getText().equals("Follow us on Alkaffary instgram"))
        {
            url[3]="https://www.instagram.com/alkaffarygroup/";
            hyperLink(3);
        }
        else if(textView.getText().equals("Follow us on HighPoint instgram"))
        {
            url[4]="https://www.instagram.com/highpoint_sa/";
            hyperLink(4);
        }
        else if(textView.getText().equals("Follow us on ModernPlace instgram"))
        {
            url[5]="https://www.instagram.com/modernpalacesa/";
            hyperLink(5);
        }
        else if(textView.getText().equals("like us on Facebook"))
        {
            url[6]="https://www.facebook.com/HighPoint.saudi";
            hyperLink(6);
        }
        else if(textView.getText().equals("Follow us on snapchat"))
        {
            url[7]="https://www.snapchat.com/add/kg.hp ";
            hyperLink(7);
        }
    }

}
