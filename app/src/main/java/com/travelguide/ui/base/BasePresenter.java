package com.travelguide.ui.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import com.travelguide.data.DataManager;
import com.travelguide.utils.rx.SchedulerProvider;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final DataManager mDataManager;
    private final CompositeDisposable mCompositeDisposable;
    private final SchedulerProvider mSchedulerProvider;

    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    protected DataManager getDataManager() {
        return mDataManager;
    }


    protected SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }
    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {

    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    protected V getMvpView() {
        return mMvpView;
    }


}
