package gerardosuarez.codetestgerardosuarez.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public final class StringUtils {

    public final static String EMPTY_STRING = "";
    public static final int INT_TEN = 10;
    public static final String ZERO_STRING = "0";
    public static final String SLASH = "/";
    public static final String TODAY = "Today";

    @NonNull
    public static String changeNullByEmptyString(@Nullable String chain) {
        return chain == null ? EMPTY_STRING : chain;
    }

    public static boolean isEmpty(@Nullable String text) {
        return text == null || text.isEmpty();
    }

    @NonNull
    public static String addZeroToStart(int number) {
        String formatNumber;
        if (number < INT_TEN) {
            formatNumber = ZERO_STRING + number;
        } else {
            formatNumber = number + EMPTY_STRING;
        }
        return formatNumber;
    }

    public static String formatDateWithTodayLogic(@NonNull String dateString) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTimeInMillis(date.getTime());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String dayString = addZeroToStart(day);
        String monthString = addZeroToStart(month);
        String finalDate = dayString + monthString + year;
        if (dateString.equalsIgnoreCase(finalDate)) {
            return TODAY;
        } else {
            return formatString(dateString);
        }
    }

    private static String formatString(@NonNull String date) {
        if (date.length() != 8) return EMPTY_STRING;
        if (StringUtils.isEmpty(date)) return EMPTY_STRING;
        return date.substring(0, 2) +
                SLASH +
                date.substring(2, 4);
    }
}
