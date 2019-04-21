package com.travelguide.ui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;

import butterknife.Unbinder;

public class BaseDialog extends DialogFragment implements DialogMvpView {

    private  BaseActivity mActivity;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void showLoading(String title, String message) {
        if (mActivity != null) {
            mActivity.showLoading(title, message);
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void showAlertMessage(String message, String textPositiveButton,
                                 DialogInterface.OnClickListener onClickListenerPositive,
                                 String textNegativeButton,
                                 DialogInterface.OnClickListener onClickListenerNegative) {
        if(mActivity != null){
            mActivity.showAlertMessage(message,textPositiveButton,onClickListenerPositive,
                    textNegativeButton,onClickListenerNegative);
        }
    }

}
