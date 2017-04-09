package com.example.azeaage.mobileapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpandableLVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpandableLVFragment extends Fragment {

    private LinkedHashMap<String,BrandInfo> brandlist = new LinkedHashMap<String,BrandInfo>();
    private DataAdapter listadapter ;
    private ArrayList<BrandInfo> deptlist = new ArrayList<BrandInfo>();
    private ExpandableListView expandableListView ;






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ExpandableLVFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpandableLVFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpandableLVFragment newInstance(String param1, String param2) {
        ExpandableLVFragment fragment = new ExpandableLVFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_expandable_lv, container, false);

        LoadData();
        expandableListView =(ExpandableListView)view.findViewById(R.id.expandableListView);
        listadapter =new DataAdapter(ExpandableLVFragment.this.getActivity(),deptlist);
        expandableListView.setAdapter(listadapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                BrandInfo headerinfo = deptlist.get(groupPosition);

                //display
                Toast.makeText(getContext(), "Header is :"+headerinfo.getName(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                BrandInfo headerinfo =deptlist.get(groupPosition);
                //childinfo
                ListInfo detailinfo = headerinfo.getList().get(groupPosition);
                //display
                Toast.makeText(getContext(), "Clicked on :"+headerinfo.getName()+"/"+detailinfo.getName(),Toast.LENGTH_SHORT).show();
               return false;
            }
        });

           return view;
    }
       private void expand_all ()
       {
           int count = listadapter.getGroupCount();
           for (int i =0 ;1<count;i++)
           {
               expandableListView.expandGroup(i);
           }
       }

    //collapse methode
    private void collapse_all ()
    {
        int count = listadapter.getGroupCount();
        for (int i =0 ;1<count;i++)
        {
            expandableListView.collapseGroup(i);
        }

    }

private void LoadData ()
{
    addProduct("Bed Rooms","TURKI");
    addProduct("Bed Rooms","MALAYSIA");
    addProduct("Bed Rooms","CHINA");
    addProduct("Dining","TURKI");
    addProduct("Dining","CHINA");
    addProduct("Sofa","CHINA");
    addProduct("Sofa","TURKI");
    addProduct("Sofa","USA");
    addProduct("Wallpaper","CHINA");
    addProduct("Wallpaper","USA");
    addProduct("Wallpaper","HOLAND");
    addProduct("Wallpaper","GERMAN");
    addProduct("Wallpaper","BELGIUM");
    addProduct("Wallpaper","KOREA");
    addProduct("Wallpaper","FRANCE");
    addProduct("Parquet","CHINA");
    addProduct("Parquet","GERMAN");
    addProduct("Parquet","BELGIUM");
    addProduct("Other","CARPETS");
    addProduct("Other","ACCESSORIES");
    addProduct("Other","MOKEET");
    addProduct("Other","TABLES");
    addProduct("Other","GRASS");
    addProduct("Other","ORAC");
    addProduct("Other","VINYL");

}

    //add product to list method
    private int addProduct (String brand, String List )
    {
        int grouppostion;
        ArrayList<BrandInfo> arrayList;
        BrandInfo headerinfo = brandlist.get(brand);
        if (headerinfo==null)
        {
        headerinfo = new BrandInfo();
            headerinfo.setName(brand);
            brandlist .put(brand,headerinfo);
            deptlist.add(headerinfo);
        }
ArrayList<ListInfo> Listlist = headerinfo.getList();
        int listsize = Listlist.size();
        listsize++;

        ListInfo detailinfo = new ListInfo();
        detailinfo.setName(List);
        detailinfo.setSequence(String.valueOf(listsize));
        Listlist.add(detailinfo);
        headerinfo.setList(Listlist);

        //groupPosution
        grouppostion=deptlist.indexOf(headerinfo);
        return grouppostion;
    }
}



