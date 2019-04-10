package com.travelguide.ui.fragments.attractionDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelguide.R;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.PlaceResult;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.fragments.searchPlace.calendar.DatePickerFragment;
import com.travelguide.ui.main.MainActivity;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttractionDetailFragment extends BaseFragment implements AttractionDetailMvpView, DatePickerFragment.OnSelectedDate{

    public static final String TAG = AttractionDetailFragment.class.getSimpleName();

    private static final String PARAM_SEARCH_RESULT = "SEARCH_RESULT";
    private static final String PARAM_ITINERARY = "PARAM_ITINERARY";

    PlaceResult searchPlaceResult;

    Itinerary itinerary;

    @BindView(R.id.tv_ratingAttraction)
    TextView mRating;

    @BindView(R.id.tv_nameAttraction)
    TextView mAttraction;

    @Inject
    AttractionDetailMvpPresenter<AttractionDetailMvpView> mPresenter;

    public static AttractionDetailFragment getInstance(PlaceResult searchPlaceResult, Itinerary itinerary) {
        Bundle args = new Bundle();
        args.putParcelable(PARAM_SEARCH_RESULT, searchPlaceResult);
        args.putParcelable(PARAM_ITINERARY, itinerary);
        AttractionDetailFragment attractionDetailFragment = new AttractionDetailFragment();
        attractionDetailFragment.setArguments(args);
        return attractionDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            searchPlaceResult = bundle.getParcelable(PARAM_SEARCH_RESULT);
            itinerary = bundle.getParcelable(PARAM_ITINERARY);
        }else{
            Log.e(TAG,"Error on create");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_attraction_detail,container,false);

        JodaTimeAndroid.init(getContext());

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);


        ((MainActivity) getActivity()).showFAB();

        mAttraction.setText(searchPlaceResult.name);
        String rating = "Rating " + String.valueOf(searchPlaceResult.rating);
        mRating.setText(rating);
        return view;

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

        addAttraction(searchPlaceResult.name, localDate);

    }


    private void addAttraction(String name,LocalDate date){
            LocalDate dayBegin = itinerary.getDayBegin();
            int quantityDays = Days.daysBetween(dayBegin, date).getDays();
            List<String> attractions = itinerary.getList_days().get(quantityDays).getAttractions();
            if(attractions == null){
                attractions = new ArrayList<>();
            }
            attractions.add(name);
            itinerary.getList_days().get(quantityDays).setAttractions(attractions);
            Log.d(TAG,"Adding to itinerary " + itinerary.getList_days().get(quantityDays).getAttractions());
            mPresenter.updateItinerary(itinerary);
    }

}
