package com.travelguide.ui.base;

import android.content.DialogInterface;

public interface MvpView {

    void showLoading(String title, String message);

    void hideLoading();

    void showAlertMessage(String message,
                          String textPositiveButton,
                          DialogInterface.OnClickListener onClickListenerPositive,
                          String textNegativeButton,
                          DialogInterface.OnClickListener onClickListenerNegative);

}
