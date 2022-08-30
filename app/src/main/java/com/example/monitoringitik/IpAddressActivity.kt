package com.example.monitoringitik

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.monitoringitik.databinding.ActivityIpAddressBinding

class IpAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIpAddressBinding
    private var ip = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIpAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnSendIpAddress.isEnabled = false


        val inputIpAddress = binding.edtInputIpAddress

        inputIpAddress.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (inputIpAddress.toString().isNotEmpty()){
                    binding.btnSendIpAddress.isEnabled = true
                    ip = inputIpAddress.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) { }

        })


        binding.btnSendIpAddress.setOnClickListener {
            val intent = Intent(this, MonitoringActivity::class.java)
            intent.putExtra("IP", ip)
            startActivity(intent)
        }

    }
}