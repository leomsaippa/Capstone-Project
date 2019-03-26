package com.travelguide.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchPlaceResponse {

    @SerializedName("html_attributions")
    @Expose
    private List<String> htmlAttributions;

    @SerializedName("results")
    @Expose
    private List<PlaceResult> placeResult;


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
}
