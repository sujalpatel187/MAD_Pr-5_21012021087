package com.sujalpatel.mad_pr_5_21012021087

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val browseBtn: Button = findViewById(R.id.btn_browse)
        val editTextBrowser: EditText = findViewById(R.id.editTextText)
        var text = editTextBrowser.text.toString()
        browseBtn.setOnClickListener {
            clickBrowes((text))
        }
        val callBtn: Button = findViewById(R.id.btn_call)
        val call_input: EditText = findViewById(R.id.editTextPhone)
        callBtn.setOnClickListener {
            clickCall(call_input.text.toString())
        }
        val callLogBtn:Button=findViewById(R.id.btn_calllog)
        callLogBtn.setOnClickListener {
            clickCalllog()
        }
        val galleryBtn:Button=findViewById(R.id.btn_gallry)
        galleryBtn.setOnClickListener {
            clickGallery()
        }
        val cameraBtn:Button=findViewById(R.id.btn_camera)
        cameraBtn.setOnClickListener {
            clickCamera()
        }
        val alarmBtn:Button=findViewById(R.id.btn_alarm)
        alarmBtn.setOnClickListener {
            clickAlarm()
        }

    }

    fun clickBrowes(url: String) {
        Intent(Intent.ACTION_VIEW,Uri.parse(url))
            .also { startActivity(it) }
    }

    fun clickCall(pno: String) {
        Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:$pno"))
            .also { startActivity(it) }
    }

    fun clickCalllog() {
        val i = Intent()
        i.action = Intent.ACTION_VIEW
        i.data = Uri.parse("content://call_log/calls/1")
        if (i.resolveActivity(packageManager) != null) {
            startActivity(i)
        }
        else{
            try{
                Intent(Intent.ACTION_VIEW).setType(CallLog.Calls.CONTENT_TYPE).also { startActivity(it) }
            }
            catch (e:Exception){
                Log.e("MainActivity", "clickCalllog: "+e.message, e)
            }
        }


    }

    fun clickGallery() {
        Intent(Intent.ACTION_VIEW).setType("image/*").also { startActivity(it) }
    }

    fun clickCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { startActivity(it)}
    }

    fun clickAlarm() {
       Intent(AlarmClock.ACTION_SHOW_ALARMS).also { startActivity(it) }
    }
}
