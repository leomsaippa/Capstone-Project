package com.travelguide.data.db;

import android.arch.persistence.room.TypeConverter;

import org.joda.time.LocalDate;

public class LocalDateConverter {

    @TypeConverter
    public static LocalDate stringToDate(String data) {
        return data == null ? null : new LocalDate(data);
    }

        @TypeConverter
        public static String fromDate(LocalDate date){
            return date == null ? null : date.toString();
        }

}
