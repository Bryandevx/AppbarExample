package com.example.appbarexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var favorite = false
    private lateinit var mainToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainToolBar = findViewById(R.id.main_toolbar)
        setSupportActionBar(mainToolBar)
    }

    private fun setFavoriteIcon(menuItem: MenuItem){
        val id = if (favorite) R.drawable.ic_favorite_white
        else R.drawable.ic_favorite_border_white

        menuItem.icon = ContextCompat.getDrawable(this,id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        setFavoriteIcon(menu?.findItem(R.id.favorite)!!)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite->{
                favorite = !favorite
                setFavoriteIcon(item)
            }
            R.id.share->{
                val sendIntent = Intent().apply{
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Comparte con tus contactos")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent,null)
                startActivity(shareIntent)
            }

            R.id.exit->{
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}