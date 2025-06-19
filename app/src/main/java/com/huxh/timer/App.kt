package com.huxh.timer

import android.app.Application
import android.content.pm.ApplicationInfo
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy.Builder
import com.huxh.timer.util.ProfileVerifierLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {


    @Inject
    lateinit var profileVerifierLogger: ProfileVerifierLogger

    override fun onCreate() {
        super.onCreate()

        setStrictModePolicy()

        // Initialize Sync; the system responsible for keeping data in the app up to date.
        profileVerifierLogger()
    }

    /**
     * Return true if the application is debuggable.
     */
    private fun isDebuggable(): Boolean {
        return 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
    }

    /**
     * Set a thread policy that detects all potential problems on the main thread, such as network
     * and disk access.
     *
     * If a problem is found, the offending call will be logged and the application will be killed.
     */
    private fun setStrictModePolicy() {
        if (isDebuggable()) {
            StrictMode.setThreadPolicy(
                Builder().detectAll().penaltyLog().build(),
            )
        }
    }
}
