package com.example.taskfromcompany.ui

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskfromcompany.R
import com.example.taskfromcompany.databinding.ActivityMenuBinding
import com.example.taskfromcompany.util.TempDataStorage
import com.example.taskfromcompany.viewmodel.InformationViewModel

class MenuActivity : AppCompatActivity() {

    private val TAG = "Menu"
    private lateinit var binding: ActivityMenuBinding
    private lateinit var informationViewModel: InformationViewModel
    private lateinit var passFragment: OptionsFragment.PassFragment
    private lateinit var toolBar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        informationViewModel = ViewModelProvider(this).get(InformationViewModel::class.java)
        initViewProperties()
        handlingBackHomeColor()

        binding.btnPersonalInf.setOnClickListener {
            Intent(this,PersonalInfActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.btnTrading.setOnClickListener {
             
        }

    }

    private fun handlingBackHomeColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            toolBar.overflowIcon?.setColorFilter(
                BlendModeColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    ), BlendMode.SRC_ATOP
                )
            )
        } else {
            toolBar.overflowIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.white
                ), PorterDuff.Mode.SRC_ATOP
            )
        }
    }

    private fun initViewProperties() {
        toolBar = findViewById(R.id.tool_bar)
        toolBar.title = ""
        informationViewModel = ViewModelProvider(this).get(InformationViewModel::class.java)
        setSupportActionBar(toolBar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.log_out) {
            TempDataStorage.saveUser(null)
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
            return true
        }
        return false
    }


}