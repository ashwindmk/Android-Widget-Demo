package layout;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.ashwin.example.widgetdemo.MainActivity;
import com.ashwin.example.widgetdemo.R;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link MyWidgetConfigurationActivity MyWidgetConfigurationActivity}
 */
public class MyWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        CharSequence widgetText = MyWidgetConfigurationActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget_provider_layout);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Home button
        Intent homeIntent = new Intent(context, MainActivity.class);
        PendingIntent homePendingIntent = PendingIntent.getActivity(context, 0, homeIntent, 0);
        views.setOnClickPendingIntent(R.id.home_button, homePendingIntent);

        // Edit button
        Intent editIntent = new Intent(context, MyWidgetConfigurationActivity.class);
        editIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //editIntent.setData(Uri.withAppendedPath(Uri.parse("abc" + "://widget/id/"), String.valueOf(appWidgetId)));
        PendingIntent editPendingIntent = PendingIntent.getActivity(context, 1, editIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.edit_button, editPendingIntent);

        // Google button
        Intent googleIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
        PendingIntent googlePendingIntent = PendingIntent.getActivity(context, 2, googleIntent, 0);
        views.setOnClickPendingIntent(R.id.google_button, googlePendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            MyWidgetConfigurationActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

