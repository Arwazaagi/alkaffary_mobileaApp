package com.example.azeaage.mobileapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;


public class Products_Fragment extends Fragment {

    private Products_Fragment.OnFragmentInteractionListener mListener;

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
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.product_table);
        for (int i = 0; i < 10; i++)
        {
            TableRow tableRow = new TableRow(getActivity());
            Button button = new Button(getActivity());
            button.setText("1");
            tableRow.addView(button);

            button = new Button(getActivity());
            button.setText("2");
            tableRow.addView(button);

            View row = inflater.inflate(R.layout.product_to_sale, container,false);
          //  button.setText("3");
            tableRow.addView(row);
          /*  View row = inflater.inflate(R.layout.product_to_sale, container,false);
            View row1 = inflater.inflate(R.layout.product_to_sale, container,false);
            View row2 = inflater.inflate(R.layout.product_to_sale, container,false);

            tableRow.addView(row);


            tableRow.addView(row1);


            tableRow.addView(row2);
*/
            tableLayout.addView(tableRow);
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
