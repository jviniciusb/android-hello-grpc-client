package com.jviniciusb.android_hello_grpc_client.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jviniciusb.android_hello_grpc_client.R
import com.jviniciusb.android_hello_grpc_client.databinding.ActivityMainBinding
import com.jviniciusb.android_hello_grpc_client.domain.services.GreeterServiceClient
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    /*It's not a good practice to use this client direclty here, it should be
    * behind ViewModel, Repository, DataSource, etc... layers*/
    private val greeterClient by inject<GreeterServiceClient>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btCallService.setOnClickListener {
            sayHello()
        }
    }

    private fun sayHello() {
        binding.tvMessage.text = getString(R.string.calling_service)
        lifecycleScope.launch {
            val message = greeterClient.sayHello(binding.tiName.text.toString())
            binding.tvMessage.text = message
        }
    }
}