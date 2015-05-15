package com.example.naveenmarikar.smartshare1;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by naveenmarikar on 24/02/15.
 */
public class SplashFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash,
                container, false);

        TextView tx = (TextView)view.findViewById(R.id.smartshare);
        TextView tx1 = (TextView)view.findViewById(R.id.getstarted);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),"Futura Light BT.ttf");
        tx.setTypeface(custom_font);
        tx1.setTypeface(custom_font);
        return view;
    }

}
