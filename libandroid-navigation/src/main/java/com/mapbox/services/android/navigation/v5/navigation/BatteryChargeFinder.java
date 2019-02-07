package com.mapbox.services.android.navigation.v5.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

class BatteryChargeFinder {

  private static final int UNAVAILABLE_BATTERY_LEVEL = -1;
  private static final int DEFAULT_BATTERY_LEVEL = -1;
  private static final int DEFAULT_SCALE = 100;
  private static final float PERCENT_SCALE = 100.0f;

  float obtainPercentage(Context context) {
    Intent batteryStatus = registerBatteryUpdates(context);
    if (batteryStatus == null) {
      return UNAVAILABLE_BATTERY_LEVEL;
    }
    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, DEFAULT_BATTERY_LEVEL);
    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, DEFAULT_SCALE);
    return (level / (float) scale) * PERCENT_SCALE;
  }

  private Intent registerBatteryUpdates(Context context) {
    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    return context.registerReceiver(null, filter);
  }
}
