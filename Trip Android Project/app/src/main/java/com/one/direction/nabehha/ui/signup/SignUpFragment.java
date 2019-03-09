package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.one.direction.nabehha.R;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;
    private EditText signUpFullNameET;
    private EditText signUpEmailET;
    private EditText signUpPasswordET;
    private Button signUpBtn;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        signUpFullNameET = getView().findViewById(R.id.fullNameSignUpET);
        signUpEmailET =getView().findViewById(R.id.emailSignUpET);
        signUpPasswordET = getView().findViewById(R.id.passwordSignUpET);
        signUpBtn = getView().findViewById(R.id.signUpBtn);
        // TODO: Use the ViewModel

    }

}
