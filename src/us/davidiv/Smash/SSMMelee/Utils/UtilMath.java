package us.davidiv.Smash.SSMMelee.Utils;

import java.text.DecimalFormat;

public class UtilMath {

    public static String rt2d(Double value) {
        DecimalFormat df = new DecimalFormat("###.#");
        return ("" + Double.valueOf(df.format(value)));
    }

    public static String fInt2(int i) {
        if (i < 10) {return "0" + i;}
        else return i + "";
    }

    public static String timeHMS(double seconds) {
        int minutes = ((int) seconds/60);
        seconds -= minutes*60;
        int hours = (minutes/60);
        minutes -= hours*60;
        return (fInt2(hours) + ":" + fInt2(minutes) + ":" + fInt2((int) seconds));
    }

}
