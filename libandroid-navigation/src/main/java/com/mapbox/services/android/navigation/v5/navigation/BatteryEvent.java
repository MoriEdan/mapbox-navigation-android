package com.mapbox.services.android.navigation.v5.navigation;

import android.annotation.SuppressLint;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
@SuppressWarnings("ParcelableCreator")
class BatteryEvent extends NavigationPerformanceEvent implements Parcelable {
  private static final String BATTERY_PERCENTAGE_KEY = "battery_percentage";

  BatteryEvent(String sessionId, float batteryPercentage) {
    super(sessionId);

    addCounter(new FloatCounter(BATTERY_PERCENTAGE_KEY, batteryPercentage));
  }
}
