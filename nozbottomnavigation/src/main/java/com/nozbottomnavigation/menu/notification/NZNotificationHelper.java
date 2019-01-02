package com.nozbottomnavigation.menu.notification;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

public final class NZNotificationHelper {

    private NZNotificationHelper() {
        // empty
    }

    /**
     * Get text color for given notification. If color is not set (0), returns default value.
     *
     * @param notification     AHNotification, non null
     * @param defaultTextColor int default text color for all notifications
     * @return
     */
    public static int getTextColor(@NonNull NZNotification notification, @ColorInt int defaultTextColor) {
        int textColor = notification.getTextColor();
        return textColor == 0 ? defaultTextColor : textColor;
    }

    /**
     * Get background color for given notification. If color is not set (0), returns default value.
     *
     * @param notification           AHNotification, non null
     * @param defaultBackgroundColor int default background color for all notifications
     * @return
     */
    public static int getBackgroundColor(@NonNull NZNotification notification, @ColorInt int defaultBackgroundColor) {
        int backgroundColor = notification.getBackgroundColor();
        return backgroundColor == 0 ? defaultBackgroundColor : backgroundColor;
    }

}
