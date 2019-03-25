package com.travelguide.ui.fragments.attractionList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.travelguide.R;
import com.travelguide.data.network.model.SearchPlaceResponse;

import java.util.List;

public class AttractionsAdapter extends RecyclerView.Adapter<AttractionsAdapter.AttractionsAdapterViewHolder> {

    public static final String TAG = AttractionsAdapter.class.getSimpleName();

    private final AttractionsAdapterOnClickHandler mClickHandler;

    private List<SearchPlaceResponse> searchPlaceResponseList;


    void setSearchPlaceResponseList(List<SearchPlaceResponse> searchPlaceResponseList) {
        if(searchPlaceResponseList != null){
            this.searchPlaceResponseList.addAll(searchPlaceResponseList);

        }else{
            this.searchPlaceResponseList = searchPlaceResponseList;
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

    public interface AttractionsAdapterOnClickHandler {
        void onClick (SearchPlaceResponse searchPlaceResponse);
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
    public void onBindViewHolder(@NonNull AttractionsAdapterViewHolder attractionsAdapterViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        if(searchPlaceResponseList ==null){
            return 0;
        }
        return searchPlaceResponseList.size();
    }

    public class AttractionsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView mIvAttraction;

        public AttractionsAdapterViewHolder(View itemView) {
            super(itemView);
            mIvAttraction = itemView.findViewById(R.id.iv_attraction);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick " + getAdapterPosition());
            mClickHandler.onClick(searchPlaceResponseList.get((getAdapterPosition())));
        }

        void bind(String poster_path) {

        }
    }
}
