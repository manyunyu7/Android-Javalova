package com.feylabs.javalova

import android.os.Bundle
import androidx.navigation.findNavController
import com.feylabs.javalova.base.BaseActivity
import com.feylabs.javalova.databinding.ActivityContainerBinding

class ContainerActivity : BaseActivity() {
    private lateinit var binding: ActivityContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_activity_container)
    }
}