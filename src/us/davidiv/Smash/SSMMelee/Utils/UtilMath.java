package us.davidiv.Smash.SSMMelee.Utils;

import java.text.DecimalFormat;

public class UtilMath {

    public static String rt2d(Double value) {
        DecimalFormat df = new DecimalFormat("###.##");
        return ("" + Double.valueOf(df.format(value)));
    }


}
