package com.example.monitoringitik

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.monitoringitik.databinding.ActivityMonitoringBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.longdo.mjpegviewer.MjpegView.MODE_FIT_WIDTH


class MonitoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMonitoringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonitoringBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val newIp = intent?.getStringExtra("IP") ?: ""

        if (newIp == "") {
            Toast.makeText(this, "Ip Address Tidak Tersedia", Toast.LENGTH_SHORT).show()
        }

        // val ipAddress = "http://$newIp/mjpeg/1"


        val viewer = binding.camView
        viewer.mode = MODE_FIT_WIDTH
        viewer.isAdjustHeight = true
        viewer.supportPinchZoomAndPan = true
        viewer.msecWaitAfterReadImageError
        viewer.setUrl(newIp)
        viewer.startStream()


        // Firebase Get Count
        Firebase.database.reference.child("Itik")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    binding.tvCountItik.text = snapshot.value.toString()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })


    }


    override fun onResume() {
        super.onResume()
        binding.camView.startStream()
    }

    override fun onPause() {
        super.onPause()
        binding.camView.stopStream()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.camView.stopStream()
    }


}