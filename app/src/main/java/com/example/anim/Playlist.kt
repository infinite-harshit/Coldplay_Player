package com.example.anim

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.play.*

class Playlist : AppCompatActivity() {
    val imageId = arrayOf<Int>(R.drawable.hfod,R.drawable.parchutes,R.drawable.ghoststories,R.drawable.hfod,
        R.drawable.xandy,R.drawable.robth,R.drawable.unbroken,R.drawable.myloxyloto,R.drawable.everydaylife,R.drawable.mots)

    val songName = arrayOf<String>("Everglow","Yellow","A Sky Full of Stars","Hymn for the weekend","Fix you","The Scientist",
        "Miracles","Paradise","Orphans","Higher Power")

    val songLink = arrayOf<String>("https://www.youtube.com/watch?v=xn_1hFdE-5g","https://www.youtube.com/watch?v=yKNxeF4KMsY",
        "https://www.youtube.com/watch?v=VPRjCeoBqrI","https://www.youtube.com/watch?v=YykjpeuMNEk","https://www.youtube.com/watch?v=k4V3Mo61fJM",
        "https://www.youtube.com/watch?v=RB-RcX5DS5A","https://www.youtube.com/watch?v=7atDQreame4","https://www.youtube.com/watch?v=1G4isv_Fylg",
        "https://www.youtube.com/watch?v=PXKYA-zmzTY","https://www.youtube.com/watch?v=3lfnR7OhZY8")

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
        setContentView(R.layout.play)
        setStatusBarTransparent()
        supportActionBar?.hide()

        val myListAdapter = MyListAdapter(this,songName,imageId)
        listView.adapter = myListAdapter

        listView.setOnItemClickListener(){
                adapterView, view, position, id ->
            Intent(Intent.ACTION_VIEW).setData(Uri.parse(songLink[position])).apply { startActivity(this) }
        }

    }
}
