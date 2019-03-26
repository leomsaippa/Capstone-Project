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
import com.travelguide.data.network.model.PlaceResult;
import com.travelguide.ui.base.BaseFragment;
import com.travelguide.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttractionDetailFragment extends BaseFragment implements AttractionDetailMvpView{

    public static final String TAG = AttractionDetailFragment.class.getSimpleName();

    private static final String PARAM_SEARCH_RESULT = "SEARCH_RESULT";

    PlaceResult searchPlaceResult;

    @BindView(R.id.tv_ratingAttraction)
    TextView mRating;

    @BindView(R.id.tv_nameAttraction)
    TextView mAttraction;

    @Inject
    AttractionDetailMvpPresenter<AttractionDetailMvpView> mPresenter;

    public static AttractionDetailFragment getInstance(PlaceResult searchPlaceResult) {
        Bundle args = new Bundle();
        args.putParcelable(PARAM_SEARCH_RESULT, searchPlaceResult);
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
        }else{
            Log.e(TAG,"Error on create");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_attraction_detail,container,false);


        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        mPresenter.onAttach(this);


        ((MainActivity) getActivity()).showFAB();

        mAttraction.setText(searchPlaceResult.name);
        String rating = "Rating " + String.valueOf(searchPlaceResult.rating);
        mRating.setText(rating);
        return view;

    }

}
