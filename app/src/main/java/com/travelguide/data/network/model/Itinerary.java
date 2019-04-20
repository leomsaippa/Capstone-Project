package com.travelguide.data.network.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.travelguide.data.db.DateConverter;
import com.travelguide.data.db.LocalDateConverter;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.List;

@Entity(tableName = "itinerary")
@TypeConverters({DateConverter.class, LocalDateConverter.class})
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

    @SerializedName("day_begin")
    @Expose
    private LocalDate dayBegin;

    @SerializedName("day_end")
    @Expose
    private LocalDate dayEnd;

    @SerializedName("list_days")
    @Expose
    @ColumnInfo(name = "list_days")
    private List<Day> list_days;


    @SerializedName("photo_reference")
    @Expose
    @ColumnInfo
    public String photo_reference;


    public Itinerary(String name, Integer number_days, LocalDate dayBegin, LocalDate dayEnd,
                     List<Day> list_days, String photo_reference) {
        this.name = name;
        this.number_days=number_days;
        this.dayBegin = dayBegin;
        this.dayEnd = dayEnd;
        this.list_days=list_days;
        this.photo_reference = photo_reference;
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

    public LocalDate getDayBegin() {
        return dayBegin;
    }

    public void setDayBegin(LocalDate dayBegin) {
        this.dayBegin = dayBegin;
    }

    public LocalDate getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(LocalDate dayEnd) {
        this.dayEnd = dayEnd;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public static Creator<Itinerary> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
