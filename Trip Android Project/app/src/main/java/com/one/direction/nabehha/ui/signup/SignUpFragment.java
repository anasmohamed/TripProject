package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.databinding.SignupFragmentBinding;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;
    SignupFragmentBinding binding;
    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.signup_fragment, container, false);
        View view = binding.getRoot();
//        //here data must be an instance of the class MarsDataProvider
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SignUpModelFactory factory = InjectionUtils.provideSignUpViewModelFactory(getContext());

        mViewModel = ViewModelProviders.of(this, factory).get(SignUpViewModel.class);
      binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.sendUserDataToWebService(binding.fullNameSignUpET.getText().toString(),binding.emailSignUpET.getText().toString(), binding.passwordSignUpET.getText().toString());
            }
        });

    }


}
