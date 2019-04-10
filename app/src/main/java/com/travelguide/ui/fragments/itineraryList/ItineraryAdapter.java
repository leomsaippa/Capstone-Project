package com.travelguide.ui.fragments.itineraryList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.travelguide.R;
import com.travelguide.data.network.model.Day;
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
        View view = inflater.inflate(R.layout.itinerary_list_item,viewGroup, false);
        return new ItineraryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItineraryAdapterViewHolder itineraryAdapterViewHolder, int position) {

        List<Day> list = itineraryList.get(position).getList_days();

        Log.d(TAG,"id "+itineraryList.get(position).getId());
        //Todo verificar null
        int numberAttractions = 0;
        for(int i=0;i<list.size();i++){
            Log.d(TAG, "list na position " + i);
            if(list.get(i) !=null)
                Log.d(TAG, "Lista not null");
            if(list.get(i).getAttractions()!=null){
                    numberAttractions+=list.get(i).getAttractions().size();
                    Log.d(TAG, "Adicionando numero de atrações " + numberAttractions);
                }else{
                    Log.e(TAG,"There's no attractions in day " + i +1);
                }
        }
        //todo get days
        itineraryAdapterViewHolder.bind(itineraryList.get(position).getName(),
                itineraryList.get(position).getDayBegin().toString() + " a " +itineraryList.get(position).getDayEnd(),
                "",
                //itineraryList.get(position).getPhotos().get(0),
                String.valueOf(itineraryList.get(position).getNumber_days()),
                String.valueOf(numberAttractions));
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


        final TextView mTvName;
        final TextView mTvDate;
        final ImageView mIvAttraction;
        final TextView mTvDays;
        final TextView mTvAttractions;
        final Button mBtnSeeDetails;


        public ItineraryAdapterViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_itinerary_item_name);
            mTvDate = itemView.findViewById(R.id.tv_itinerary_item_date);
            mIvAttraction = itemView.findViewById(R.id.iv_itinerary_item_image);
            mTvDays = itemView.findViewById(R.id.tv_itinerary_days);
            mTvAttractions = itemView.findViewById(R.id.selected_attractions_quantity);
            mBtnSeeDetails = itemView.findViewById(R.id.btn_itinerary_item_detail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick " + getAdapterPosition());
            mClickHandler.onCLick(itineraryList.get((getAdapterPosition())));
        }

        void bind(String name, String date, String poster_path, String days,
                  String quantity) {
            mTvName.setText(name);
            mTvDate.setText(date);
            mTvDays.setText(days);
            mTvAttractions.setText(quantity);

            mBtnSeeDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickHandler.onCLick(itineraryList.get((getAdapterPosition())));
                }
            });

//            Picasso.get().load(poster_path).into(mIvAttraction);

        }
    }


}
