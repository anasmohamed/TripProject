package com.one.direction.nabehha;


import android.app.Activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Window;

import com.one.direction.nabehha.databinding.ReminderDialogFragmentBinding;

public class ReminderDialogFragment extends AppCompatDialog {
    ReminderDialogFragmentBinding myReminderDialogFragmentBinding;
    private Context mContext;

    public ReminderDialogFragment(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reminder_dialog_fragment);
        myReminderDialogFragmentBinding = DataBindingUtil.setContentView((Activity) mContext, R.layout.reminder_dialog_fragment);
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
