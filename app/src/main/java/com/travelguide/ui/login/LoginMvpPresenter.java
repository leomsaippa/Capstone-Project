package com.travelguide.ui.login;

import com.travelguide.ui.base.MvpPresenter;

public interface LoginMvpPresenter <V extends LoginMvpView> extends MvpPresenter<V> {


    void openMainActivity();
}
