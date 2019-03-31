package com.travelguide.ui.fragments.itineraryList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelguide.R;
import com.travelguide.data.network.model.Itinerary;

import java.util.List;

public class ItineraryAdapter extends RecyclerView.Adapter<ItineraryAdapter.ItineraryAdapterViewHolder> {

    public static final String TAG = ItineraryAdapter.class.getSimpleName();

    private List<Itinerary> itineraryList;


    private final ItineraryAdapter.ItineraryAdapterOnClickerHandler mClickHandler;

    void setItineraryList(List<Itinerary> listItinerary){
        if(itineraryList != null){
            this.itineraryList.addAll(listItinerary);
        }else{
            Log.e(TAG,"Itinerary Adpater is empty");
            this.itineraryList = listItinerary;
        }
        notifyDataSetChanged();
    }

    void clear(){
        if(itineraryList !=null){
            itineraryList.clear();
        }else{
            Log.d(TAG,"Can't clear. AttractionResultList list is null!");
        }
    }

    @NonNull
    @Override
    public ItineraryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.attraction_list_item,viewGroup, false);
        return new ItineraryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItineraryAdapterViewHolder itineraryAdapterViewHolder, int i) {
        itineraryAdapterViewHolder.bind();
    }

    @Override
    public int getItemCount() {
        if(itineraryList ==null){
            return 0;
        }
        return itineraryList.size();
    }


    public interface ItineraryAdapterOnClickerHandler{
        void onCLick (Itinerary itinerary);
    }


    public ItineraryAdapter(ItineraryAdapterOnClickerHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }



    public class ItineraryAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ItineraryAdapterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick " + getAdapterPosition());
            mClickHandler.onCLick(itineraryList.get((getAdapterPosition())));
        }

        void bind() {
        }
    }


}
