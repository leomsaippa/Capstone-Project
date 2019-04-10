package com.travelguide.ui.fragments.itineraryDay;

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

import java.util.List;

public class ItineraryDayAdapter extends RecyclerView.Adapter<ItineraryDayAdapter.ItineraryDayAdapterViewHolder> {

    public static final String TAG = ItineraryDayAdapter.class.getSimpleName();

    private final ItineraryDayAdapterOnClickHandler mClickHandler;

    private List<String> attractionsList;

    public void setAttractions(List<String> attractions) {
        if(attractionsList != null){
            this.attractionsList.addAll(attractions);
        }else{
            Log.e(TAG,"Attractions is empty!");
            this.attractionsList = attractions;
        }
        notifyDataSetChanged();
    }

    public void clear(){
        if(attractionsList !=null){
            attractionsList.clear();
        }else{
            Log.d(TAG,"Can't clear. dayList list is null!");
        }
    }


    public interface ItineraryDayAdapterOnClickHandler {
        void onClick (String attraction);
    }

    public ItineraryDayAdapter(ItineraryDayAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }


    @NonNull
    @Override
    public ItineraryDayAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.itinerary_day_item,viewGroup, false);
        return new ItineraryDayAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItineraryDayAdapterViewHolder holder, int position) {
        holder.bind(attractionsList.get(position));
    }
    @Override
    public int getItemCount() {
        if(attractionsList ==null){
            return 0;
        }
        return attractionsList.size();
    }

    public class ItineraryDayAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView mTvAttraction;
        final TextView mTvHour;
        final ImageView mIvattraction;

        public ItineraryDayAdapterViewHolder(View itemView) {
            super(itemView);
            mTvAttraction = itemView.findViewById(R.id.tv_attraction_day);
            mTvHour = itemView.findViewById(R.id.tv_attraction_hour);
            mIvattraction = itemView.findViewById(R.id.iv_attraction_day);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick " + getAdapterPosition());
//            mClickHandler.onClick(dayList.get((getAdapterPosition())).toString());
        }

        public void bind(String attraction) {

            //todo adicionar hour and image
            mTvAttraction.setText(attraction);
            mTvHour.setText("09-11");
//            Picasso.get().load("").into(mIvattraction);
        }
    }
}
