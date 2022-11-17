package com.markvtls.phonemarket

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
/**Main app requested by Hilt.*/
class MainApp : Application()