package com.example.simplemenus

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import com.example.simplemenus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar :Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.mytoolbar.root
        toolbar.title = "Meine App mit Menü"
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        val search = menu!!.findItem(R.id.app_bar_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Was soll gesucht werden?"
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, "Suche nach $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return true
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_settings -> {
                Toast.makeText(this, "Settings wurde getippt", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_next ->{ // weiter zur nächsten Activity
                val intent = Intent(this,NextActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_send ->{
                val intent = Intent(Intent.ACTION_SENDTO).apply {
//                    type = "message/rfc822"
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("empfaenger@email.de"))
                    putExtra(Intent.EXTRA_SUBJECT, "Betreffzeile")
                    putExtra(Intent.EXTRA_TEXT, "Hier steht der Inhalt der E-Mail")
                    setPackage("com.google.android.gm")
                }
                if(intent.resolveActivity(packageManager)!= null)
                    startActivity(intent)
                else
                    Toast.makeText(this@MainActivity, "Kein E-Mail-Programm vorhanden", Toast.LENGTH_SHORT)
                        .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}