package com.example.monitoringitik

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.monitoringitik.databinding.ActivityIpAddressBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class IpAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIpAddressBinding
    private lateinit var dialog: ProgressDialog
    private var ip = ""
    private val ref = FirebaseDatabase.getInstance("https://deteksi-itik-default-rtdb.firebaseio.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIpAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnSendIpAddress.isEnabled = false
        val inputIpAddress = binding.edtInputIpAddress

        dialog = ProgressDialog(this)
        dialog.setMessage("Getting Ip Address...")
        dialog.show()
        ref.reference.child("Ip").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value.toString()
                Log.d("DATA", data)
                ip = data
                inputIpAddress.setText(data)
                binding.btnSendIpAddress.isEnabled = true

                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }

        })
        Log.d("AAA",ip)


//        inputIpAddress.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (inputIpAddress.toString().isNotEmpty()) {
//                    binding.btnSendIpAddress.isEnabled = true
//                    ip = inputIpAddress.text.toString()
//                }
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//
//        })


        binding.btnSendIpAddress.setOnClickListener {
            val intent = Intent(this, MonitoringActivity::class.java)
            intent.putExtra("IP", ip)
            startActivity(intent)
        }

    }
}