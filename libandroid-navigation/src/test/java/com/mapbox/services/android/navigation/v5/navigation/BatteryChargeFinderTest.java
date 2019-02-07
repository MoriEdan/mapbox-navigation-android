package com.mapbox.services.android.navigation.v5.navigation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatteryChargeFinderTest {

  @Test
  public void checksBatteryPercentageIsReturned() {
    BatteryChargeFinder theBatteryChargeFinder = new BatteryChargeFinder();
    Context mockedContext = mock(Context.class);
    Intent mockedIntent = mock(Intent.class);
    BroadcastReceiver mockedBroadcastReceiver = null;
    when(mockedContext.registerReceiver(eq(mockedBroadcastReceiver), any(IntentFilter.class))).thenReturn(mockedIntent);
    when(mockedIntent.getIntExtra(eq(BatteryManager.EXTRA_LEVEL), eq(-1))).thenReturn(25);
    when(mockedIntent.getIntExtra(eq(BatteryManager.EXTRA_SCALE), eq(100))).thenReturn(100);

    float batteryLevelPercentage = theBatteryChargeFinder.obtainPercentage(mockedContext);

    assertEquals(25.0f, batteryLevelPercentage, 0.1);
  }

  @Test
  public void checksBatteryPercentageUnavailable() {
    BatteryChargeFinder theBatteryChargeFinder = new BatteryChargeFinder();
    Context mockedContext = mock(Context.class);
    Intent nullIntent = null;
    BroadcastReceiver mockedBroadcastReceiver = null;
    when(mockedContext.registerReceiver(eq(mockedBroadcastReceiver), any(IntentFilter.class))).thenReturn(nullIntent);

    float batteryLevelPercentage = theBatteryChargeFinder.obtainPercentage(mockedContext);

    assertEquals(-1.0f, batteryLevelPercentage, 0.1);
  }
}