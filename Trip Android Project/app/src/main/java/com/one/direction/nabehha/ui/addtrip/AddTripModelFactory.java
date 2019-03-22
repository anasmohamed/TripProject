package com.one.direction.nabehha.ui.addtrip;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.one.direction.nabehha.Reminder;
import com.one.direction.nabehha.data.network.TripRepository;
import com.one.direction.nabehha.data.network.UserRepository;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class AddTripModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final TripRepository mRepository;

    public AddTripModelFactory(TripRepository repository) {
        this.mRepository = repository;

    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddTripViewModel(mRepository);
    }

}
