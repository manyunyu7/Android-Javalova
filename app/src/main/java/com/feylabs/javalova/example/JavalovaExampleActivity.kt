package com.feylabs.javalova.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.feylabs.core.util.AppResult
import com.feylabs.javalova.R
import com.feylabs.javalova.base.BaseActivity
import com.feylabs.javalova.databinding.ActivityJavalovaExampleBinding
import org.koin.android.viewmodel.ext.android.viewModel

class JavalovaExampleActivity : BaseActivity() {

    private val binding by lazy { ActivityJavalovaExampleBinding.inflate(layoutInflater) }

    private val viewModel: JavalovaExampleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getQuote()

        viewModel.quoteLiveData.observe(this) {
            when (it) {
                is AppResult.Error -> showToast(it.message.toString())
                is AppResult.Loading -> showToast("Loading")
                is AppResult.Success -> {
                    binding.tv.text = it.data.toString()
                }
            }
        }

    }
}