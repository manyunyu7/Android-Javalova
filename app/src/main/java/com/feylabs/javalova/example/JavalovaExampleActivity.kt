package com.feylabs.javalova.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.feylabs.core.data.local.DrinkListDAO
import com.feylabs.core.domain.DrinkGeneral
import com.feylabs.core.util.AppResult
import com.feylabs.javalova.R
import com.feylabs.javalova.base.BaseActivity
import com.feylabs.javalova.databinding.ActivityJavalovaExampleBinding
import org.koin.android.viewmodel.ext.android.viewModel

@Deprecated("Unused, only for testing")
class JavalovaExampleActivity : BaseActivity() {

    private val binding by lazy { ActivityJavalovaExampleBinding.inflate(layoutInflater) }

    private val viewModel: JavalovaExampleViewModel by viewModel()

    var alcohol: String? = ""
    var category: String? = ""
    var ingredient: String? = ""
    var glass: String? = ""
    var lastItem: DrinkGeneral? = null

    var list = mutableListOf<DrinkGeneral>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.categoryAlcoholicLiveData.observe(this) {
            if (it is AppResult.Success) {
                binding.tvAlcoholic.text = "Alcoholic " + it.data.toString()
            }
        }
        viewModel.categoryGlassLiveData.observe(this) {
            if (it is AppResult.Success) {
                binding.tvGlass.text = "Glass " + it.data.toString()
            }
        }
        viewModel.categoryGeneralLiveData.observe(this) {
            if (it is AppResult.Success) {
                binding.tvGeneral.text = "General " + it.data.toString()
            }
        }
        viewModel.categoryIngredientLiveData.observe(this) {
            if (it is AppResult.Success) {
                binding.tvIngredient.text = "Ingredient " + it.data.toString()
            }
        }

        binding.btnLoadMore.setOnClickListener {
            alcohol = binding.etAlcoholic.text.toString().emptyOrNull()
            glass = binding.etGlass.text.toString().emptyOrNull()
            ingredient = binding.etIngredient.text.toString().emptyOrNull()
            category = binding.etCategory.text.toString().emptyOrNull()
            viewModel.getDrinkList(
                category = category,
                glass = glass,
                ingredient = ingredient,
                alcoholic = alcohol,
                lastValue = lastItem?.idDrink
            )
        }

        viewModel.drinkListLiveData.observe(this) {
            if (it is AppResult.Success) {
                val data = it.data
                var text = ""
                data?.forEachIndexed { index, drinkGeneral ->
                    lastItem = drinkGeneral
                    list.add(drinkGeneral)
                }

                list.forEachIndexed { index, drinkGeneral ->
                    text += "${index + 1}. ${drinkGeneral.strDrink} - ${drinkGeneral.idDrink}\n"
                }
                binding.tv.text = text
            }
        }

        binding.btnFindId.setOnClickListener {
            var id = binding.etId.text.toString()
            viewModel.detail(id)
        }

        binding.btnFindKeyword.setOnClickListener {
            val keyword = binding.etKeyword.text.toString()
            viewModel.search(keyword)
        }

        viewModel.searchLiveData.observe(this){
            if(it is AppResult.Success){
                binding.tv.text=it.data?.drinks.toString()
            }
        }

        viewModel.detailLiveData.observe(this){
            if(it is AppResult.Success){
                binding.tv.text=it.data?.drinks.toString()
            }
        }


        binding.btnFind.setOnClickListener {
            list.clear()
            alcohol = binding.etAlcoholic.text.toString().emptyOrNull()
            glass = binding.etGlass.text.toString().emptyOrNull()
            ingredient = binding.etIngredient.text.toString().emptyOrNull()
            category = binding.etCategory.text.toString().emptyOrNull()
            lastItem = null
            viewModel.getDrinkList(
                category = category,
                glass = glass,
                ingredient = ingredient,
                alcoholic = alcohol,
                lastValue = lastItem?.idDrink
            )
        }

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

    fun String.emptyOrNull(): String? {
        if (this == "") {
            return null
        } else return this
    }
}