package com.example.figueroafinalactivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<Integer> selectedColor = new MutableLiveData<>();

    public void setSelectedColor(int color) {
        selectedColor.setValue(color);
    }

    public LiveData<Integer> getSelectedColor() {
        return selectedColor;
    }
}

