/*
 * Copyright 2020 Lalafo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mkhytarmkhoian.demo;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class App extends Application {

  public static boolean isTablet;
  public static boolean isSmallTablet;
  public static float density;


  public static boolean isDebug() {
    return BuildConfig.DEBUG;
  }

  private static App instance;
  public static App getInstance() {
    return instance;
  }

  @Override public void onCreate() {
    super.onCreate();
    instance = this;

    isTablet = getResources().getBoolean(R.bool.is_tablet);
    density = getResources().getDisplayMetrics().density;
    isSmallTablet = isSmallTablet(density);
  }

  public boolean isSmallTablet(final float density){
    final WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    final Display display = wm.getDefaultDisplay();

    final Point size = new Point();
    display.getSize(size);

    final float minSide = Math.min(size.x, size.y) / density;
    return minSide <= 700;
  }
}