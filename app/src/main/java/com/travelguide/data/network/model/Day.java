package com.travelguide.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Day implements Parcelable {

    @SerializedName("attractions")
    @Expose
    private List<Attraction> attractions;

    //Position day. For example (travel from April 1 to April 8. First day will have id 0
    @SerializedName("id")
    @Expose
    private int id;

    public Day(int id){
        this.id = id;
    }

    protected Day(Parcel in) {
        attractions = in.createTypedArrayList(Attraction.CREATOR);
        id = in.readInt();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }


    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(attractions);
        dest.writeInt(id);
    }
}
