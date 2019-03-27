package com.travelguide.ui.base;

public interface MvpView {

    void showLoading(String title, String message);

    void hideLoading();
}
