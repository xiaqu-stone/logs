package com.stone.logdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stone.log.Logs
import kotlinx.android.synthetic.main.activity_main.*
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
    }

    private fun testLog() {
        //测试JSON输出
        Logs.json("{\"employees\":[{\"firstName\":\"Bill\",\"lastName\":\"Gates\"},{\"firstName\":\"George\",\"lastName\":\"Bush\"},{\"firstName\":\"Thomas\",\"lastName\":\"Carter\"}]}")
    }
}
