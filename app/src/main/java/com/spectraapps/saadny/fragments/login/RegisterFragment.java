package com.spectraapps.saadny.fragments.login;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.spectraapps.saadny.R;
import com.spectraapps.saadny.models.spinners.PhoneSpinnerModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    @BindView(R.id.nameET)
    EditText nameET;
    @BindView(R.id.mobileET)
    EditText mobileET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.phoneCountry_spinner)
    Spinner spinner;
    private List<PhoneSpinnerModel> rowItems;
    String type;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,rootView);
        initUI(rootView);
        return rootView;
    }

    private void initUI(View rootView) {

    }

}
