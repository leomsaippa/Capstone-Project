package com.travelguide.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.travelguide.R;
import com.travelguide.data.network.model.Day;
import com.travelguide.data.sharedPreferences.AppSharedPref;

import java.util.ArrayList;
import java.util.List;

public class AttractionWidgetService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(this.getApplicationContext());
    }

    class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        AppSharedPref appSharedPref;

        private List<Day> days;
        private List<String> attractions;


        Context mContext;

        public RecipeRemoteViewsFactory(Context applicationContext) {
            mContext = applicationContext;
            appSharedPref = new AppSharedPref(mContext);
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            days = appSharedPref.loadWidgetAttraction();
            if(days!=null) {
                for (int i = 0; i < days.size(); i++) {
                    attractions.add(String.valueOf(days.get(i).getAttractions().size()));
                }
            }
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public int getCount() {
            if (days == null) return 0;
            return days.size();
        }


        @Override
        public RemoteViews getViewAt(int position) {
            if (position == AdapterView.INVALID_POSITION || days == null ||
                    days.isEmpty()) {
                return null;
            }
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_recipe_attraction_item);

            String qtdWdiget = attractions.get(position);

            views.setTextViewText(R.id.tv_attraction_qtd_widget, qtdWdiget);
            views.setTextColor(R.id.tv_selected_widget, Color.WHITE);

            Intent fillIntent = new Intent();
            views.setOnClickFillInIntent(R.id.tv_selected_widget, fillIntent);

            return views;

        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
