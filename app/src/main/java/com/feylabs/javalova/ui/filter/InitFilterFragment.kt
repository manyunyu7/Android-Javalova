package com.feylabs.javalova.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.feylabs.core.domain.*
import com.feylabs.core.domain.DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem
import com.feylabs.core.domain.DrinkGeneralCategory.DrinkGeneralCategoryItem
import com.feylabs.core.domain.DrinkGlassCategory.DrinkGlassCategoryItem
import com.feylabs.core.domain.DrinkIngredientCategory.DrinkIngredientCategoryItem
import com.feylabs.core.util.AppResult.*
import com.feylabs.javalova.R
import com.feylabs.javalova.base.BaseFragment
import com.feylabs.javalova.databinding.FragmentFilterBinding
import com.feylabs.javalova.databinding.FragmentHomeBinding
import com.feylabs.javalova.databinding.FragmentListDrinkBinding
import com.feylabs.javalova.databinding.FragmentSearchNameBinding
import com.feylabs.javalova.ui.home.adapter.AlcoholicAdapter
import com.feylabs.javalova.ui.home.adapter.GeneralAdapter
import com.feylabs.javalova.ui.home.adapter.GlassAdapter
import com.feylabs.javalova.ui.home.adapter.IngredientAdapter
import com.feylabs.javalova.ui.list.adapter.ListDrinkAdapter
import com.feylabs.javalova.ui.search.SearchViewModel
import com.feylabs.javalova.ui.search.adapter.SearchAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class InitFilterFragment : BaseFragment() {

    val viewModel: SearchViewModel by viewModel()
    private var _binding: FragmentFilterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapterListDrink by lazy { SearchAdapter() }

    override fun initUI() {
    }

    override fun initObserver() {
        viewModel.categoryAlcoholicLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> {
                    showLoading(false)
                    showToast(getString(R.string.label_an_error_occured) + ": ${it.message}")
                }
                is Loading -> {
                    showLoading(true)
                }
                is Success -> {
                    showLoading(false)
                    it.data?.let {
                        setupSpinnerAlcoholic(it)
                    }
                }
            }
        }
        viewModel.categoryIngredientLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> {
                    showLoading(false)
                    showToast(getString(R.string.label_an_error_occured) + ": ${it.message}")
                }
                is Loading -> {
                    showLoading(true)
                }
                is Success -> {
                    showLoading(false)
                    it.data?.let {
                        setupSpinnerIngredient(it)
                    }
                }
            }
        }
        viewModel.categoryGeneralLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> {
                    showLoading(false)
                }
                is Loading -> {
                    showLoading(true)
                }
                is Success -> {
                    showLoading(false)
                    it.data?.let {
                        setupSpinnerCategory(it)
                    }
                }
            }
        }
        viewModel.categoryGlassLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> {
                    showLoading(false)
                }
                is Loading -> {
                    showLoading(true)
                }
                is Success -> {
                    it.data?.let {
                        setupSpinnerGlassIngredient(it)
                    }
                    showLoading(false)
                }
            }
        }
    }

    private fun setupSpinnerGlassIngredient(it: List<DrinkGlassCategoryItem>) {
        val spinnerArray: MutableList<String> = mutableListOf()
        spinnerArray.add(getString(R.string.title_choose_item))
        it.forEachIndexed { index, data ->
            spinnerArray.add(data.strGlass)
        }
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, spinnerArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGlass.adapter = adapter
    }

    private fun setupSpinnerCategory(it: List<DrinkGeneralCategoryItem>) {
        val spinnerArray: MutableList<String> = mutableListOf()
        spinnerArray.add(getString(R.string.title_choose_item))
        it.forEachIndexed { index, data ->
            spinnerArray.add(data.strCategory)
        }
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, spinnerArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter
    }

    private fun setupSpinnerIngredient(it: List<DrinkIngredientCategoryItem>) {
        val spinnerArray: MutableList<String> = mutableListOf()
        spinnerArray.add(getString(R.string.title_choose_item))
        it.forEachIndexed { index, data ->
            spinnerArray.add(data.strIngredient1)
        }
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, spinnerArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIngredient.adapter = adapter
    }

    private fun setupSpinnerAlcoholic(it: List<DrinkAlcoholicCategoryItem>) {
        val spinnerArray: MutableList<String> = mutableListOf()
        spinnerArray.add(getString(R.string.title_choose_item))
        it.forEachIndexed { index, data ->
            spinnerArray.add(data.strAlcoholic)
        }
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, spinnerArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAlcohol.adapter = adapter
    }

    override fun initAction() {
        binding.btnFilter.setOnClickListener {

            if (getSelectedAlcohol() == null && getSelectedGlass() == null && getSelectedCategory() == null
                && getSelectedIngredient() == null
            ) {
                showToast(getString(R.string.message_please_fill_filter))
            } else
                findNavController().navigate(
                    R.id.nav_listDrinkFragment,
                    bundleOf(
                        "ingredient" to getSelectedIngredient(),
                        "glass" to getSelectedGlass(),
                        "category" to getSelectedCategory(),
                        "alcohol" to getSelectedAlcohol(),
                    )
                )
        }
    }

    private fun getSelectedAlcohol(): String? {
        binding.spinnerAlcohol.apply {
            return if (selectedItem.toString() == getString(R.string.title_choose_item)) {
                null
            } else
                selectedItem.toString()
        }
    }

    private fun getSelectedCategory(): String? {
        binding.spinnerCategory.apply {
            return if (selectedItem.toString() == getString(R.string.title_choose_item)) {
                null
            } else
                selectedItem.toString()
        }
    }

    private fun getSelectedGlass(): String? {
        binding.spinnerGlass.apply {
            return if (selectedItem.toString() == getString(R.string.title_choose_item)) {
                null
            } else
                selectedItem.toString()
        }
    }

    private fun getSelectedIngredient(): String? {
        binding.spinnerIngredient.apply {
            return if (selectedItem.toString() == getString(R.string.title_choose_item)) {
                null
            } else
                selectedItem.toString()
        }
    }

    override fun initData() {
        viewModel.getCategoryAlcoholic()
        viewModel.getCategoryGeneral()
        viewModel.getGlassCategory()
        viewModel.getCategoryIngredient()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun showLoading(value: Boolean) {
        if (value)
            binding.loadingCenterProgressBar.makeVisible()
        else binding.loadingCenterProgressBar.makeGone()
    }


}