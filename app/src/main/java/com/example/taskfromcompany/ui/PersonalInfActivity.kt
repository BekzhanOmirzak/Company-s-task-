package com.example.taskfromcompany.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.taskfromcompany.R
import com.example.taskfromcompany.model.PersonalInformation
import com.example.taskfromcompany.util.TempDataStorage
import com.example.taskfromcompany.viewmodel.InformationViewModel
import kotlin.reflect.full.memberProperties

class PersonalInfActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var linearContainer: LinearLayout
    private lateinit var showLinearLayout: LinearLayout
    private lateinit var personalViewModel: InformationViewModel
    private lateinit var tool_bar: Toolbar
    private val TAG = "PersonalInfActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_inf)
        initViewProperties()
        initObservingPersonalInformation()
        initToolBar()
        initHomeUpButton()

    }

    private fun initHomeUpButton() {
        tool_bar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    private fun initToolBar() {
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
    }

    private fun initObservingPersonalInformation() {
        personalViewModel.returnPersonalInformation().observe(this) {
            if (it != null) {
                progressBar.visibility = View.GONE
                showLinearLayout.visibility = View.VISIBLE
                for (k in PersonalInformation::class.memberProperties) {
                    val text = TextView(this)
                    text.setLayoutParams(
                        LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                    )
                    text.text = "${k.name}:${k.get(it)}"
                    Log.i(TAG, "onViewCreated: ${text.text}")
                    text.textSize = 20.0f
                    linearContainer.addView(text)
                    Log.i(TAG, "onViewCreated: ${TempDataStorage.getCurUser()?.urlToken}")
                }
            }
        }
    }

    private fun initViewProperties() {
        progressBar = findViewById(R.id.progress_bar)
        linearContainer = findViewById(R.id.parent_linear)
        showLinearLayout = findViewById(R.id.show_linear)
        personalViewModel = ViewModelProvider(this).get(InformationViewModel::class.java)
        personalViewModel.startRequesting()
        progressBar.visibility = View.VISIBLE
        showLinearLayout.visibility = View.GONE
        tool_bar = findViewById(R.id.tool_bar)
        tool_bar.title = ""
    }


}