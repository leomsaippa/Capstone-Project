package com.travelguide.ui.main;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.travelguide.R;
import com.travelguide.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter <MainMvpView> mPresenter;

    @BindView(R.id.tv_error)
    TextView mError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        ButterKnife.bind(this);

        mPresenter.onAttach(this);

        mError.setText("Testando todo MVP");
    }
}
