package com.travelguide.ui.fragments.searchPlace;

import android.content.DialogInterface;
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
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.SearchPlaceResponse;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.attractionList.AttractionListFragment;
import com.travelguide.ui.fragments.searchPlace.calendar.DatePickerFragment;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPlaceFragment extends BaseFragment implements SearchPlaceMvpView, DatePickerFragment.OnSelectedDate {

    public static final String TAG = SearchPlaceFragment.class.getSimpleName();
    Itinerary itinerary;

    @BindView(R.id.btn_search)
    Button mBtnSearch;

    @BindView(R.id.tv_beginningTravel)
    TextView mTextBeginTravel;

    @BindView(R.id.tv_endTravel)
    TextView mTextEndTravel;

    @BindView(R.id.placeName)
    EditText mPlaceName;

    boolean beginCall = false;
    LocalDate dateBegin;
    LocalDate dateEnd;

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

        JodaTimeAndroid.init(getContext());

        mPresenter.setApiEndPoint();

        return view;

    }

    @OnClick(R.id.btn_search)
    public void onClickBtnSearch(View view){
        mPlaceName.requestFocus();

        mPresenter.getFormatedAddress(mPlaceName.getText().toString());


    }

    @Override
    public void search(String photo_reference) {
        itinerary = mPresenter.onBtnSearchClick(mPlaceName.getText().toString(),dateBegin,dateEnd,photo_reference);
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
    public void onSelectedDate(int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        TimeZone timeZone = calendar.getTimeZone();
        DateTimeZone jodaDateTimeZone = DateTimeZone.forID(timeZone.getID());
        DateTime dateTime = new DateTime(calendar.getTimeInMillis(), jodaDateTimeZone);

        LocalDate localDate = dateTime.toLocalDate();

        if(beginCall){
            dateBegin = localDate;
            mTextBeginTravel.setText(localDate.toString());
            beginCall = false;
        }else{
            dateEnd = localDate;
            mTextEndTravel.setText(localDate.toString());
            beginCall = true;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onErrorEmptyPlace() {
        mPlaceName.setError(getString(R.string.error_empty_place));
    }

    @Override
    public void onErrorEmptyBeginTravel() {
        mTextBeginTravel.setError(getString(R.string.error_empty_begin_travel));
    }

    @Override
    public void onErrorEmptyEndTravel() {
        mTextEndTravel.setError(getString(R.string.error_empty_end_travel));
    }

    @Override
    public void onErrorInvalidDate() {
        mTextEndTravel.setError(getString(R.string.error_invalid_date));
    }


    @Override
    public void openAttractionListFragment(SearchPlaceResponse placeResponse, Itinerary itinerary) {
        if(getActivity()!=null){
            if(getActivity().getSupportFragmentManager()!=null){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, AttractionListFragment.getInstance(placeResponse, itinerary), AttractionListFragment.TAG)
                        .addToBackStack(AttractionListFragment.TAG)
                        .commit();

            }
        }
    }

    @Override
    public void showInternetError() {
        DialogInterface.OnClickListener positiveDialog = (dialog, which) -> {
            onClickBtnSearch(getView());
        };

        showAlertMessage(getString(R.string.something_wrong),
                getString(R.string.try_again),
                positiveDialog,
                getString(R.string.ok),
                null);
    }

    @Override
    public void showZeroResultError() {
        showAlertMessage(getString(R.string.zero_results),
                getString(R.string.ok),
                null,
                null,
                null);
    }
}
