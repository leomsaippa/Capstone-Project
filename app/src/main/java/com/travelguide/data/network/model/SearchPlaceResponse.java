package com.travelguide.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


//TODO refactor removing this class
public class SearchPlaceResponse {

    @SerializedName("html_attributions")
    @Expose
    private List<String> htmlAttributions;



}
