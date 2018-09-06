package com.stone.logdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.stone.log.Logs
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testLog()

        testJson.setOnClickListener {
            toast("click to test json ")
            testLog()
        }

        testWrap.setOnClickListener {
            toast("click test wrap")
            Logs.e("onCreate: begin test the wrap")
            doAsync {
                Logs.w("onCreate: Android Log 未封装输出===========")
                Log.d("logs", getLargeString())//测试中输出至QQ835
                Logs.w("onCreate: Android Log 未封装输出===========")

                Logs.i("onCreate: ${getLargeString()}")//输出至QQ10000
            }
        }
    }

    private fun testLog() {
        //测试JSON输出
        Logs.json("{\"employees\":[{\"firstName\":\"Bill\",\"lastName\":\"Gates\"},{\"firstName\":\"George\",\"lastName\":\"Bush\"},{\"firstName\":\"Thomas\",\"lastName\":\"Carter\"}]}")
    }

    private fun getLargeString(): String {
        val builder = StringBuilder()
        for (i in 0..10000) {
            builder.append("QQ").append(i)
        }
        return builder.toString()
    }
}
