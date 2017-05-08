package com.example.azeaage.mobileapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.azeaage.mobileapp.R;
import com.example.azeaage.mobileapp.adapters.salesProductsAdapter;
import com.example.azeaage.mobileapp.background;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import objects.Products;


public class Products_Fragment extends Fragment {

    private Products_Fragment.OnFragmentInteractionListener mListener;
    private ListView salesProductListView;
    ArrayList<Products> productsArrayList;
    String response;

    public Products_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        onButtonPressed("Products");
        View view= inflater.inflate(R.layout.fragment_products_, container, false);
        salesProductListView= (ListView) view.findViewById(R.id.salesProductsListView);
        productsArrayList=new ArrayList<>();

        productsArrayList.add(new Products(112,"غرفة نوم", "BedRoom","furniture","furniture",10000,true));
        productsArrayList.add(new Products(132,"غرفة طعام", "Dining Room","furniture","furniture",20000,true));

        salesProductListView.setAdapter(new salesProductsAdapter(productsArrayList,getContext()));

        background b=new background(getContext());
        try {
             response =b.execute("GetProductsList","1","01","100.0","10000.0").get();
            System.out.println("Products array json"+response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String data) {
        if (mListener != null) {
            mListener.onFragmentInteraction(data);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Products_Fragment.OnFragmentInteractionListener) {
            mListener = (Products_Fragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String data);
    }

}
