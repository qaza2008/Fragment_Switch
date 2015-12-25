package com.androidso.FragmentSwitchDemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.androidso.FragmentSwitchDemo.R;

/**
 * Created by mac on 15/12/23.
 */
public class FragmentB extends Fragment {
    public static final String TAG = FragmentB.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText("FragmentB ");
        return view;
    }
}
