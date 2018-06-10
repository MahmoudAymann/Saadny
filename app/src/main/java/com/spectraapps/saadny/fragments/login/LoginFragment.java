package com.spectraapps.saadny.fragments.login;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.spectraapps.saadny.LoginActivity;
import com.spectraapps.saadny.R;
import com.spectraapps.saadny.helper.BaseBackPressedListener;
import com.spectraapps.saadny.util.ListSharedPreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.btn_pass_visibility)
    ImageButton btn_pass_visibility;
    @BindView(R.id.loginBtn)
    Button btn_signin;
    @BindView(R.id.signupBtn)
    Button btn_signup;

    ListSharedPreference.Set setSharedPreference;
    ListSharedPreference.Get getSharedPreference;

    boolean isPasswordShown;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        setSharedPreference = new ListSharedPreference.Set(LoginFragment.this.getActivity().getApplicationContext());
        getSharedPreference = new ListSharedPreference.Get(LoginFragment.this.getActivity().getApplicationContext());

        ButterKnife.bind(this, rootView);
        fireBackButtonEvent();
        initUI();

        return rootView;
    }

    private void initUI() {
        passwordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (passwordET.length() > 0 || passwordET.length() == 0) {
                        passwordET.setInputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                } else {
                    if (passwordET.length() > 0)
                        passwordET.setInputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    else {
                        passwordET.setInputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                    }
                }
            }
        });
    }

    private void fireBackButtonEvent() {
        try {
            ((LoginActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()) {
                @Override
                public void onBackPressed() {
                    getActivity().finish();
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }//end back pressed

    @OnClick(R.id.btn_pass_visibility)
    public void onBtnPassVisibleClick() {
        if (isPasswordShown) {
            btn_pass_visibility.setImageResource(R.drawable.ic_visibility_24dp);
            passwordET.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isPasswordShown = false;
        } else {
            btn_pass_visibility.setImageResource(R.drawable.ic_visibility_off_24dp);
            passwordET.setInputType(InputType.TYPE_CLASS_TEXT);
            isPasswordShown = true;
        }
    }

    @OnClick(R.id.loginBtn)
    public void onLoginClick() {
        Intent myIntent = new Intent();
        myIntent.setClassName("com.spectraapps.saadny", "com.spectraapps.saadny.MainActivity");
        startActivity(myIntent);
        getActivity().finish();
    }

    @OnClick(R.id.signupBtn)
    public void onRegisterClick() {
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction()
                .replace(R.id.login_frameLayout, new RegisterFragment()).commit();
    }

}//end class LoginFragment
