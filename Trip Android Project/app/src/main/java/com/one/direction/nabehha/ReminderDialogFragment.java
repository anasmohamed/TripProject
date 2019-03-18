package com.one.direction.nabehha;

import android.app.AlertDialog;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

import com.one.direction.nabehha.databinding.ReminderDialogFragmentBinding;

public class ReminderDialogFragment extends AppCompatDialogFragment {
    ReminderDialogFragmentBinding binder;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binder = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.reminder_dialog_fragment, null, false);
        builder.setTitle("Login");


        return builder.create();

    }
}
