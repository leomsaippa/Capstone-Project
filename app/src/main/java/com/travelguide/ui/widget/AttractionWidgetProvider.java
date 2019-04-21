package com.travelguide.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.travelguide.R;
import com.travelguide.data.sharedPreferences.AppSharedPref;
import com.travelguide.ui.main.MainActivity;

public class AttractionWidgetProvider extends AppWidgetProvider {

    AppSharedPref appSharedPreferences;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        appSharedPreferences = new AppSharedPref(context);

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }


    private void updateAppWidget(Context context,
                                 AppWidgetManager appWidgetManager,
                                 int appWidgetId){

        RemoteViews views = getListDaysRemoteView(context);

        views.setTextViewText(R.id.appwidget_recipe_title, "Travel Guide");

        appWidgetManager.updateAppWidget(appWidgetId,views);

    }

    public static void updateWidget(Context context){

        Intent intent = new Intent(context, AttractionWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, AttractionWidgetProvider.class);

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        context.sendBroadcast(intent);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_listView_days);
    }

    private static RemoteViews getListDaysRemoteView(Context context){

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_attractions);

        Intent intent = new Intent(context, AttractionWidgetService.class);

        views.setRemoteAdapter(R.id.appwidget_listView_days, intent);
        views.setEmptyView(R.id.appwidget_listView_days, R.id.appwidget_empty_view);

        Intent intentPending = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intentPending, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setPendingIntentTemplate(R.id.appwidget_listView_days, pendingIntent);

        return views;
    }
}
