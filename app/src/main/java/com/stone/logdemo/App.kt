package com.stone.logdemo

import android.app.Application
import android.content.Context
import com.stone.log.Logs

/**
 * Created By: sqq
 * Created Time: 18/5/30 下午5:43.
 */
class App : Application() {


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Logs.init(BuildConfig.DEBUG, "log-demo")
    }
}