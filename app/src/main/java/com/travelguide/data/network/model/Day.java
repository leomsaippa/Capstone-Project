package com.travelguide.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day implements Parcelable {

    @SerializedName("selected_attractions")
    @Expose
    private Integer selected_attractions;

    protected Day(Parcel in) {
        if (in.readByte() == 0) {
            selected_attractions = null;
        } else {
            selected_attractions = in.readInt();
        }
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (selected_attractions == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(selected_attractions);
        }
    }
}
