package com.example.anim

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,
ConnectivityReceiver.ConnectivityReceiverListener {


    private fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT in 19..20){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean){
        val winParameters=window.attributes
        if(on) {
            winParameters.flags = winParameters.flags or bits
        }else{
            winParameters.flags=winParameters.flags and bits.inv()
        }
        window.attributes=winParameters
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarTransparent()
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnSave.setOnClickListener{
            val name  =  favname.text.toString()
            editor.apply{
                putString("favSong",name)
                apply()
            }
        }
        btnLoad.setOnClickListener{
            val name = sharedPref.getString("favSong",null)
            favname.setText(name)
        }


            var an = AnimationUtils.loadAnimation(this,R.anim.spin)
            img.startAnimation(an)

        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    btnAbout.setOnClickListener()
    {
        startActivity(Intent(this,About::class.java))
    }

    btnSong.setOnClickListener()
    {

        startActivity(Intent(this,Playlist::class.java))

    }

    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }
    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
          Toast.makeText(applicationContext,"You are offline",Toast.LENGTH_LONG).show()
        }
        else { Toast.makeText(applicationContext,"You are online",Toast.LENGTH_LONG).show()}
    }
}
