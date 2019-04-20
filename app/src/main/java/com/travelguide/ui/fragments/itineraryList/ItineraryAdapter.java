package com.travelguide.ui.fragments.itineraryList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelguide.R;
import com.travelguide.data.network.model.Day;
import com.travelguide.data.network.model.Itinerary;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class ItineraryAdapter extends RecyclerView.Adapter<ItineraryAdapter.ItineraryAdapterViewHolder> {

    public static final String TAG = ItineraryAdapter.class.getSimpleName();

    private List<Itinerary> itineraryList;

    Context mContext;

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

        mContext = view.getContext();
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
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
        try {
            itineraryAdapterViewHolder.bind(itineraryList.get(position).getName(),
                    itineraryList.get(position).getDayBegin().toString() + " a " +itineraryList.get(position).getDayEnd(),
                    String.valueOf(itineraryList.get(position).getNumber_days()),
                    String.valueOf(numberAttractions));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        void bind(String name, String date, String days,
                  String quantity) throws IOException {


            String daysTotal = days + " dias";
            String attractionsTotal = quantity + " atrações selecionadas";
            mTvName.setText(name);
            mTvDate.setText(date);
            mTvDays.setText(daysTotal);
            mTvAttractions.setText(attractionsTotal);

            mBtnSeeDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickHandler.onCLick(itineraryList.get((getAdapterPosition())));
                }
            });


            String photoReference = itineraryList.get(getAdapterPosition()).photo_reference;

            //TODO UTILIZAR O APIHELPER
            String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoReference + "&sensor=false&key="+mContext.getString(R.string.maps_apikey);

            if (url != null) {

                DefaultHttpClient hc = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(url);
                HttpContext context = new BasicHttpContext();
                hc.setRedirectHandler(new DefaultRedirectHandler() {
                    @Override
                    public URI getLocationURI(HttpResponse response,
                                              HttpContext context) throws org.apache.http.ProtocolException {

                        //Capture the Location header here - This is your redirected URL
                        System.out.println(Arrays.toString(response.getHeaders("Location")));

                        return super.getLocationURI(response, context);

                    }
                });

                // Response contains the image you want. If you test the redirect URL in a browser or REST CLIENT you can see it's data
                HttpResponse response = null;
                try {
                    response = hc.execute(httpget, context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response.getStatusLine().getStatusCode() == 200) {
                    // Todo: use the Image response
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        InputStream instream = null;
                        try {
                            instream = entity.getContent();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bitmap bmp = BitmapFactory.decodeStream(instream);
                        mIvAttraction.setImageBitmap(bmp);

                        try {
                            instream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println(response.getStatusLine().getStatusCode() + "");
                }
            }

        }
    }
}



