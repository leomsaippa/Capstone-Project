package com.travelguide.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos implements Parcelable {

    @SerializedName("height")
    @Expose
    public int height;

    @SerializedName("html_attributions")
    @Expose
    private List<String> htmlAttributions;

    @SerializedName("photo_reference")
    @Expose
    public String photo_reference;


    @SerializedName("width")
    @Expose
    public int width;


    protected Photos(Parcel in) {
        height = in.readInt();
        htmlAttributions = in.createStringArrayList();
        photo_reference = in.readString();
        width = in.readInt();
    }

    public static final Creator<Photos> CREATOR = new Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(height);
        dest.writeStringList(htmlAttributions);
        dest.writeString(photo_reference);
        dest.writeInt(width);
    }
}
