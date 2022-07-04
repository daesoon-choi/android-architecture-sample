package io.github.chihacker.android.architecture.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import io.github.chihacker.android.architecture.R
import io.github.chihacker.android.architecture.databinding.ActivityMainBinding
import io.github.chihacker.android.architecture.ui.main.model.User

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        observeUi()
    }

    //ui 관련 셋팅
    private fun setupUi() {
        binding.btnAge.setOnClickListener {
            viewModel.onClickBtnAge()
        }

        binding.btnUser.setOnClickListener {
            viewModel.onClickBtnUser()
        }
    }

    private fun observeUi() {
        viewModel.mainViewState.observe(this, Observer {
            when(it) {
                is MainViewState.Loading -> updateLoadingUi()
                is MainViewState.Error -> updateErrorUi()
                is MainViewState.Content -> updateUserUi(it.user)
            }
        })
    }

    private fun updateLoadingUi() {

    }

    private fun updateErrorUi() {

    }

    private fun updateUserUi(user: User) {
        binding.name.text = "Name : ${user.name}"
        binding.age.text = "Age : ${user.age}"
        binding.kage.text = "Korean Age : ${user.kage}"
    }


}