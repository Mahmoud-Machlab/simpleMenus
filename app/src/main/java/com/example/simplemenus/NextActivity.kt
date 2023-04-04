package com.example.simplemenus

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.simplemenus.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNextBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvOutput.setOnCreateContextMenuListener { menu, view, menuInfo ->
            menuInflater.inflate(R.menu.menu_color,menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_red -> binding.root.setBackgroundColor(Color.RED)
            R.id.action_green -> binding.root.setBackgroundColor(Color.GREEN)
        }

        return true
    }
}