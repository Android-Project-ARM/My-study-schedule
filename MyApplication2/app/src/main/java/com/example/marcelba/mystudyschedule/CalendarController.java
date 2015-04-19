package com.example.marcelba.mystudyschedule;

        import android.content.ContentResolver;
        import android.database.Cursor;
        import android.util.Log;

        import android.content.ContentValues;
        import android.content.Context;
        import android.net.Uri;
        import android.provider.CalendarContract;
        import android.provider.CalendarContract.Calendars;

        import java.util.Calendar;

public class CalendarController {

    final public static String CALENDAR_NAME = "mystudySchedule";
    final public static String CALENDAR_ACCOUNT_NAME = "localStudy";

    private long calID;

    // Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.
    public static final String[] EVENT_PROJECTION = new String[] {
            Calendars._ID,                           // 0
            Calendars.ACCOUNT_NAME,                  // 1
            Calendars.CALENDAR_DISPLAY_NAME,         // 2
            Calendars.OWNER_ACCOUNT                  // 3
    };

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

    public CalendarController(Context ctx){
        // Run query
        Cursor cur = null;
        ContentResolver cr = ctx.getContentResolver();
        Uri uri = Calendars.CONTENT_URI;
        String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND ("
                + Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[] {CALENDAR_ACCOUNT_NAME, CalendarContract.ACCOUNT_TYPE_LOCAL,
                CALENDAR_ACCOUNT_NAME};
        // Submit the query and get a Cursor object back.
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

        if(cur.moveToNext())
        {
            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            // displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            //accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            // ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
        }else
        {
            CreateCalendar(ctx);
        }

    }

    private void CreateCalendar(Context ctx) {

        Uri target = Uri.parse(CalendarContract.Calendars.CONTENT_URI.toString());
        target = target.buildUpon().appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDAR_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL).build();

        ContentValues values = new ContentValues();
        values.put(Calendars.ACCOUNT_NAME, CALENDAR_ACCOUNT_NAME);
        values.put(Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
        values.put(Calendars.NAME, CALENDAR_NAME);
        values.put(Calendars.CALENDAR_DISPLAY_NAME, CALENDAR_NAME);
        values.put(Calendars.CALENDAR_COLOR, 0x00FF00);
        values.put(Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_READ);
        values.put(Calendars.OWNER_ACCOUNT, CALENDAR_ACCOUNT_NAME);
        values.put(Calendars.VISIBLE, 1);
        values.put(Calendars.SYNC_EVENTS, 1);
        values.put(Calendars.CALENDAR_TIME_ZONE, "Europe/Rome");

        Uri newCalendar = ctx.getContentResolver().insert(target, values);
        long id = Long.parseLong(newCalendar.getLastPathSegment() );
        calID = id;
    }



}