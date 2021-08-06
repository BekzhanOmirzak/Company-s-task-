package com.example.taskfromcompany.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.taskfromcompany.R
import com.example.taskfromcompany.model.PersonalInformation
import com.example.taskfromcompany.ui.PersonalInfFragment
import com.example.taskfromcompany.util.TempDataStorage
import com.example.taskfromcompany.viewmodel.InformationViewModel
import com.example.taskfromcompany.viewmodel.LoginPageViewModel

class TestingActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)

        loginViewModel = ViewModelProvider(this).get(LoginPageViewModel::class.java)

        loginViewModel.getApiTokenRest(20234561, "ladevi31")
        TempDataStorage.initializeSharedPreferences(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PersonalInfFragment()).commit()
        }



    }


}