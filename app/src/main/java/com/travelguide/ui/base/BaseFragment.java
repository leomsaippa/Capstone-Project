package com.travelguide.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.travelguide.di.component.ActivityComponent;
import com.travelguide.utils.CommonUtils;

import butterknife.Unbinder;

public class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;
    private ProgressDialog mProgressDialog;

    private Unbinder mUnBinder;

    protected ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }


    protected void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
        }
    }
    @Override
    public void showLoading(String title, String message) {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext(), title, message);
    }



    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
        mProgressDialog = null;
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
