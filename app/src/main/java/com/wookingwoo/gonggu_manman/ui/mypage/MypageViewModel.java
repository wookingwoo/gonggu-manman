package com.wookingwoo.gonggu_manman.ui.mypage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MypageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MypageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Preparing for my page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}