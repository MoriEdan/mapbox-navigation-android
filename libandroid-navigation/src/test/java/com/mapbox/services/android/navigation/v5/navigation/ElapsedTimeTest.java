package com.mapbox.services.android.navigation.v5.navigation;

import com.mapbox.services.android.navigation.v5.exception.NavigationException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElapsedTimeTest {

  private static final double DELTA = 1E-10;

  @Test(expected = NavigationException.class)
  public void errorThrownIfEndCalledBeforeStart() {
    new ElapsedTime().end();
  }

  @Test
  public void elapsedTime_returnsCorrectTimeInSeconds() {
    ElapsedTime elapsedTime = new ElapsedTime();

    elapsedTime.start();
    elapsedTime.end();

    long start = elapsedTime.getStart();
    long end = elapsedTime.getEnd();
    long elapsedTimeInNanoseconds = end - start;
    double elapsedTimeInSeconds = elapsedTimeInNanoseconds / 1e+9;
    double roundedTime =  Math.round(elapsedTimeInSeconds * 100d) / 100d;
    assertEquals(elapsedTime.getElapsedTime(), roundedTime, DELTA);
  }
}
