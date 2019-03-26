package com.travelguide.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchPlaceResponse implements Parcelable {

    @SerializedName("html_attributions")
    @Expose
    private List<String> htmlAttributions;

    @SerializedName("results")
    @Expose
    private List<PlaceResult> placeResult;


    protected SearchPlaceResponse(Parcel in) {
        htmlAttributions = in.createStringArrayList();
    }

    public static final Creator<SearchPlaceResponse> CREATOR = new Creator<SearchPlaceResponse>() {
        @Override
        public SearchPlaceResponse createFromParcel(Parcel in) {
            return new SearchPlaceResponse(in);
        }

        @Override
        public SearchPlaceResponse[] newArray(int size) {
            return new SearchPlaceResponse[size];
        }
    };

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<PlaceResult> getPlaceResult() {
        return placeResult;
    }

    public void setPlaceResult(List<PlaceResult> placeResult) {
        this.placeResult = placeResult;
    }

    @Override
    public String toString() {
        return "SearchPlaceResponse{" +
                "html_attributions=" + htmlAttributions +
                ", results=" + placeResult +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(htmlAttributions);
    }
}
