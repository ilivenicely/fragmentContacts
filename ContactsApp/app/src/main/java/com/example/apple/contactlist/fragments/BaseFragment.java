package com.example.apple.contactlist.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by zetrixwebinfotechllp on 07/02/18.
 */

public class BaseFragment extends Fragment implements View.OnClickListener {

    protected Activity mActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();

    }

    @Override
    public void onClick(View v) {

    }
}
