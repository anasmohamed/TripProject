package com.one.direction.nabehha.ui.signup;
import android.arch.lifecycle.ViewModel;

import com.one.direction.nabehha.data.database.DatabaseClient;
import com.one.direction.nabehha.data.database.model.User;

public class SignUpViewModel extends ViewModel {
    // TODO: Implement the ViewModel

SignUpViewModel()

    boolean signUp(User user)
    {
        DatabaseClient.getInstance().getAppDatabase().userDao().insertUser();

        return  true;
    }
}
