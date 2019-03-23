package com.one.direction.nabehha.ui.signup;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.one.direction.nabehha.InjectionUtils;
import com.one.direction.nabehha.MainActivity;
import com.one.direction.nabehha.R;
import com.one.direction.nabehha.SignUpActivity;
import com.one.direction.nabehha.data.database.model.User;
import com.one.direction.nabehha.databinding.SignupFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                loadingUi(true);
                mViewModel.sendUserDataToWebService(binding.fullNameSignUpET.getText().toString(),binding.emailSignUpET.getText().toString(),
                        binding.passwordSignUpET.getText().toString(),
                        new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        goToTripsHome(response.body());
                                    }
                                    else
                                        Toast.makeText(getContext(), "Not register correctly", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(getContext(), "Not register correctly", Toast.LENGTH_SHORT).show();

                                loadingUi(false);
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.e("View Model", "Unable to submit post to API. " + t.getMessage() + "    " + t.getCause() + " " + t.toString());
                            }
                        });
            }
        });

    }
    private void goToTripsHome(final User user) {
//        loadingUi(false);
        if (user.getEmail() != null) {
            mViewModel.saveUserInfo(user);
            startActivity(new Intent(getActivity(), MainActivity.class));

        } else {
            Toast.makeText(getContext(),
                    "You Are Not Registered or wrong password",
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void loadingUi(boolean isLoading) {
        if (isLoading) {
            binding.loginProgress.setVisibility(View.VISIBLE);
        } else {
            binding.loginProgress.setVisibility(View.GONE);
        }
        binding.emailSignUpET.setEnabled(!isLoading);
        binding.fullNameSignUpET.setEnabled(!isLoading);
        binding.passwordSignUpET.setEnabled(!isLoading);
        binding.signUpBtn.setEnabled(!isLoading);
    }

}
