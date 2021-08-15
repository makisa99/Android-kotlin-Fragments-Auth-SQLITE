package com.metropolitan.treciprimer

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.metropolitan.treciprimer.baza.Baza
import com.metropolitan.treciprimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var mMediaPlayer: MediaPlayer? = null
    internal var helper = Baza(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //helper.insertData("vanja","vukos") //OVO JE ZA INSERT
        Log.d("Svi", helper.getAllUserData().toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.glavni, menu)
        return true
    }

    fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.water)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.adresa) {
            val intent = Intent(this, MapaActivity::class.java)
            startActivity(intent)
            return true
        }
        if (id == R.id.audio) {
            playSound()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}