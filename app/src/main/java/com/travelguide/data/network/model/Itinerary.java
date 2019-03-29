package com.travelguide.data.network.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.travelguide.data.db.DateConverter;

import java.util.List;

@Entity(tableName = "itinerary")
@TypeConverters(DateConverter.class)
public class Itinerary implements Parcelable {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("number_days")
    @Expose
    private Integer number_days;
    @SerializedName("list_days")
    @Expose
    private List<Day> list_days;


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (number_days == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(number_days);
        }
        dest.writeTypedList(list_days);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Itinerary(Integer id, String name, Integer number_days, List<Day> list_days) {
        this.id = id;
        this.name = name;
        this.number_days=number_days;
        this.list_days=list_days;
    }

    @Ignore
    public Itinerary(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name =  in.readString();
        this.number_days = (Integer) in.readValue(Integer.class.getClassLoader());
        in.readList(list_days, Day.class.getClassLoader());
    }


    public static final Creator<Itinerary> CREATOR = new Creator<Itinerary>() {
        @Override
        public Itinerary createFromParcel(Parcel in) {
            return new Itinerary(in);
        }

        @Override
        public Itinerary[] newArray(int size) {
            return new Itinerary[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber_days() {
        return number_days;
    }

    public void setNumber_days(Integer number_days) {
        this.number_days = number_days;
    }

    public List<Day> getList_days() {
        return list_days;
    }

    public void setList_days(List<Day> list_days) {
        this.list_days = list_days;
    }
}
