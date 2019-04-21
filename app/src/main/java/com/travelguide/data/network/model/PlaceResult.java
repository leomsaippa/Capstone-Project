package com.travelguide.data.network.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceResult implements Parcelable {
    @SerializedName("formatted_address")
    @Expose
    public String formattedAddress;

    @SerializedName("geometry")
    @Expose
    public Geometry geometry;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("id")
    @Expose
    public String internationalPhoneNumber;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("opening_hours")
    @Expose
    public OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    public List<Photos> photos;
    @SerializedName("place_id")
    @Expose
    public String placeId;
    @SerializedName("plus_code")
    @Expose
    public com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult.PlusCode plusCode;
    @SerializedName("rating")
    @Expose
    public Double rating;
    @SerializedName("reference")
    @Expose
    public String reference;
    @SerializedName("types")
    @Expose
    public List<String> types;
    @SerializedName("user_ratings_total")
    @Expose
    public Integer userRatingsTotal;


    protected PlaceResult(Parcel in) {
        formattedAddress = in.readString();
        icon = in.readString();
        internationalPhoneNumber = in.readString();
        name = in.readString();
        placeId = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
        reference = in.readString();
        types = in.createStringArrayList();
        if (in.readByte() == 0) {
            userRatingsTotal = null;
        } else {
            userRatingsTotal = in.readInt();
        }
    }

    public static final Creator<PlaceResult> CREATOR = new Creator<PlaceResult>() {
        @Override
        public PlaceResult createFromParcel(Parcel in) {
            return new PlaceResult(in);
        }

        @Override
        public PlaceResult[] newArray(int size) {
            return new PlaceResult[size];
        }
    };

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInternationalPhoneNumber() {
        return internationalPhoneNumber;
    }

    public void setInternationalPhoneNumber(String internationalPhoneNumber) {
        this.internationalPhoneNumber = internationalPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult.PlusCode getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(com.google.android.libraries.places.api.internal.impl.net.pablo.PlaceResult.PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Integer getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setUserRatingsTotal(Integer userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    @Override
    public String toString() {
        return "PlaceResult{" +
                "formattedAddress='" + formattedAddress + '\'' +
                ", geometry=" + geometry +
                ", icon='" + icon + '\'' +
                ", internationalPhoneNumber='" + internationalPhoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", openingHours=" + openingHours +
                ", photos=" + photos +
                ", placeId='" + placeId + '\'' +
                ", plusCode=" + plusCode +
                ", rating=" + rating +
                ", reference='" + reference + '\'' +
                ", types=" + types +
                ", userRatingsTotal=" + userRatingsTotal +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(formattedAddress);
        dest.writeString(icon);
        dest.writeString(internationalPhoneNumber);
        dest.writeString(name);
        dest.writeString(placeId);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(rating);
        }
        dest.writeString(reference);
        dest.writeStringList(types);
        if (userRatingsTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userRatingsTotal);
        }
    }
}
