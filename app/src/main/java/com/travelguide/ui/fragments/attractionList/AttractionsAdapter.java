package com.travelguide.ui.fragments.attractionList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.travelguide.R;
import com.travelguide.data.network.model.AttractionResult;

import java.util.List;

public class AttractionsAdapter extends RecyclerView.Adapter<AttractionsAdapter.AttractionsAdapterViewHolder> {

    public static final String TAG = AttractionsAdapter.class.getSimpleName();

    private final AttractionsAdapterOnClickHandler mClickHandler;

    private List<AttractionResult> attractionResultList;


    void setAttractionResultList(List<AttractionResult> attractionResultList) {
        if(attractionResultList != null){
            this.attractionResultList.addAll(attractionResultList);

        }else{
            this.attractionResultList = attractionResultList;
        }
        notifyDataSetChanged();
    }

    void clear(){
        if(attractionResultList !=null){
            attractionResultList.clear();
        }else{
            Log.d(TAG,"Can't clear. AttractionResultList list is null!");
        }
    }

    public interface AttractionsAdapterOnClickHandler {
        void onClick (AttractionResult attractionResult);
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
        if(attractionResultList ==null){
            return 0;
        }
        return attractionResultList.size();
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
            mClickHandler.onClick(attractionResultList.get((getAdapterPosition())));
        }

        void bind(String poster_path) {

        }
    }
}
