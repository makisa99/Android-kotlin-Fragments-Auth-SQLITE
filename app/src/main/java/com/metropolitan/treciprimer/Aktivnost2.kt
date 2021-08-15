package com.metropolitan.treciprimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metropolitan.treciprimer.databinding.ActivityAktivnost2Binding

class Aktivnost2 : AppCompatActivity() {
    private lateinit var binding: ActivityAktivnost2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAktivnost2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var zatxt: String = "Username: " + intent.getStringExtra("un")
        binding.txtUser.text = zatxt
        zatxt = "Password: " + intent.getStringExtra("pw")
        binding.txtPw.text = zatxt
    }
}