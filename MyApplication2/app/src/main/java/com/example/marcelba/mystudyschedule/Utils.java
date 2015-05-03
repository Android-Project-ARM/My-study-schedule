package com.example.marcelba.mystudyschedule;

import java.util.Calendar;

/**
 * Created by marcel on 26/04/2015.
 */
public class Utils {

    public static int dayOfTheMonth(String day) {
        int dayOfWeek = DayToInt(day);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,dayOfWeek);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int Month(String day) {
        int dayOfWeek = DayToInt(day);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,dayOfWeek);
        return c.get(Calendar.MONTH);
    }

    public static String GetWeekDay(long epoch) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(epoch);
        return IntToDay(c.get(Calendar.DAY_OF_WEEK));
    }

    private static int DayToInt(String day){
        switch(day)
        {
            case "Lunes":
                return 2;
            case "Martes":
                return 3;
            case "Miercoles":
                return 4;
            case "Jueves":
                return 5;
            case "Viernes":
                return 6;
            case "Sabado":
                return 7;
            case "Domingo":
                return 1;
            default:
                return 1;
        }
    }

    private static String IntToDay(int day){
        switch(day)
        {
            case 2:
                return "Lunes";
            case 3:
                return "Martes";
            case 4:
                return "Miercoles";
            case 5:
                return "Jueves";
            case 6:
                return "Viernes";
            case 7:
                return "Sabado";
            case 1:
                return "Domingo";
            default:
                return "Lunes";
        }
    }
}
