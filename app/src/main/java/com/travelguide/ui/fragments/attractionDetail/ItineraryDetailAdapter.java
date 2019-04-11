package com.travelguide.ui.fragments.attractionDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelguide.R;
import com.travelguide.data.network.model.Day;

import java.util.List;

public class ItineraryDetailAdapter extends RecyclerView.Adapter<ItineraryDetailAdapter.ItineraryDetailAdapterViewHolder> {

    public static final String TAG = ItineraryDetailAdapter.class.getSimpleName();

    private final ItineraryDetailAdapterOnClickHandler mClickHandler;

    private List<Day> dayList;

    public void setDays(List<Day> days) {
        if(dayList != null){
            this.dayList.addAll(days);
        }else{
            Log.e(TAG,"Days is empty!");
            this.dayList = days;
        }
        notifyDataSetChanged();
    }

    public void clear(){
        if(dayList !=null){
            dayList.clear();
        }else{
            Log.d(TAG,"Can't clear. dayList list is null!");
        }
    }


    public interface ItineraryDetailAdapterOnClickHandler {
        void onClick (Day day);
    }

    public ItineraryDetailAdapter(ItineraryDetailAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }


    @NonNull
    @Override
    public ItineraryDetailAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.attraction_detail_item,viewGroup, false);
        return new ItineraryDetailAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItineraryDetailAdapterViewHolder holder, int position) {
        Day day = dayList.get(position);
        holder.bind(dayList.get(position).getAttractions());
    }

    @Override
    public int getItemCount() {
        if(dayList ==null){
            return 0;
        }
        return dayList.size();
    }

    public class ItineraryDetailAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView mTvDay;
        final TextView mTvAttractions;

        public ItineraryDetailAdapterViewHolder(View itemView) {
            super(itemView);
            mTvDay = itemView.findViewById(R.id.tv_day);
            mTvAttractions = itemView.findViewById(R.id.tv_attractions_quantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick " + getAdapterPosition());
            mClickHandler.onClick(dayList.get((getAdapterPosition())));
        }

        public void bind(List<String> attractions) {
            String day = "Dia " + getAdapterPosition() + 1;
            String attractionsText = attractions.get(getAdapterPosition()) + " atrações selecionadas";
            mTvDay.setText(day);
            mTvAttractions.setText(attractionsText);
        }
    }
}
