package com.travelguide.ui.fragments.searchPlace;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.travelguide.R;
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.attractionList.AttractionListFragment;
import com.travelguide.ui.fragments.searchPlace.calendar.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPlaceFragment extends BaseFragment implements SearchPlaceMvpView, DatePickerFragment.OnSelectedDate {

    public static final String TAG = SearchPlaceFragment.class.getSimpleName();
    private static final int AUTOCOMPLETE_REQUEST_CODE = 1 ;

    @BindView(R.id.btn_search)
    Button mBtnSearch;

    @BindView(R.id.tv_beginningTravel)
    TextView mTextBeginTravel;

    @BindView(R.id.tv_endTravel)
    TextView mTextEndTravel;

    @BindView(R.id.placeName)
    EditText mPlaceName;

    boolean beginCall = false;

    @Inject
    SearchPlaceMvpPresenter<SearchPlaceMvpView> mPresenter;

    public static SearchPlaceFragment getInstance() {
        Bundle args = new Bundle();
        SearchPlaceFragment searchPlaceFragment = new SearchPlaceFragment();
        searchPlaceFragment.setArguments(args);
        return searchPlaceFragment;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_search_place,container,false);


        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);

        mPresenter.setApiEndPoint();

        return view;

    }

    @OnClick(R.id.btn_search)
    public void onClickBtnSearch(View view){
        mPlaceName.requestFocus();
        mPresenter.onBtnSearchClick(mPlaceName.getText().toString());

    }

    @OnClick(R.id.tv_endTravel)
    public void onClickEndTravel(View view){
        beginCall = false;
        showCalendar();
    }

    @OnClick(R.id.tv_beginningTravel)
    public void onClickBeginningTravel(View view){
        beginCall = true;
        showCalendar();
    }

    public void showCalendar(){
        DatePickerFragment fragment = new DatePickerFragment();
        if(getFragmentManager()!=null)
            fragment.show(getChildFragmentManager(),DatePickerFragment.TAG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSelectedDate(int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        //TODO adicionar logica para verificar se a data de volta é menor que a de ida
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        if(beginCall){
            mTextBeginTravel.setText(dateFormat.format(calendar.getTime()));
            beginCall = false;
        }else{
            mTextEndTravel.setText(dateFormat.format(calendar.getTime()));
            beginCall = true;
        }

    }

    @Override
    public void onErrorEmptyPlace() {
        mPlaceName.setError(getString(R.string.error_empty_place));
    }

    @Override
    public void openAttractionListFragment(SearchPlaceResponse placeResponse) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, AttractionListFragment.getInstance(placeResponse), AttractionListFragment.TAG)
                .commit();
    }
}
