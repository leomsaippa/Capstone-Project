package com.travelguide.ui.fragments.attractionList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.travelguide.R;
import com.travelguide.data.network.model.Itinerary;
import com.travelguide.data.network.model.PlaceResult;

import java.util.List;

public class AttractionsAdapter extends RecyclerView.Adapter<AttractionsAdapter.AttractionsAdapterViewHolder> {

    public static final String TAG = AttractionsAdapter.class.getSimpleName();

    private final AttractionsAdapterOnClickHandler mClickHandler;

    private List<PlaceResult> searchPlaceResponseList;

    private Itinerary itinerary;

    void setSearchPlaceResponseList(List<PlaceResult> placeResultList) {
        if(searchPlaceResponseList != null){
            this.searchPlaceResponseList.addAll(placeResultList);
        }else{
            Log.e(TAG,"Search place response is empty!");
            this.searchPlaceResponseList = placeResultList;
        }
        notifyDataSetChanged();
    }

    void clear(){
        if(searchPlaceResponseList !=null){
            searchPlaceResponseList.clear();
        }else{
            Log.d(TAG,"Can't clear. AttractionResultList list is null!");
        }
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public interface AttractionsAdapterOnClickHandler {
        void onClick (PlaceResult searchPlaceResponse, Itinerary itinerary);
    }

    public AttractionsAdapter(AttractionsAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }


    @NonNull
    @Override
    public AttractionsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.attraction_list_item,viewGroup, false);
        return new AttractionsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionsAdapterViewHolder holder, int position) {
        PlaceResult placeResult = searchPlaceResponseList.get(position);
        holder.bind(searchPlaceResponseList.get(position).name,
                searchPlaceResponseList.get(position).photos.get(0).photoReference,
                searchPlaceResponseList.get(position).rating);

    }

    @Override
    public int getItemCount() {
        if(searchPlaceResponseList ==null){
            return 0;
        }
        return searchPlaceResponseList.size();
    }

    public class AttractionsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView mTvName;
        final ImageView mIvAttraction;
        final TextView mTvRating;

        public AttractionsAdapterViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mIvAttraction = itemView.findViewById(R.id.iv_attraction);
            mTvRating = itemView.findViewById(R.id.tv_rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick " + getAdapterPosition());
            mClickHandler.onClick(searchPlaceResponseList.get((getAdapterPosition())),itinerary);
        }

        void bind(String formattedAddress, String poster_path, Double rating) {
            mTvName.setText(formattedAddress);
            mTvRating.setText(String.valueOf(rating));
            Picasso.get().load(poster_path).into(mIvAttraction);
        }
    }
}
