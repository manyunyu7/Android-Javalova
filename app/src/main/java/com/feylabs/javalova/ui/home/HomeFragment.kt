package com.feylabs.javalova.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.feylabs.core.domain.DrinkAlcoholicCategory
import com.feylabs.core.domain.DrinkGeneralCategory
import com.feylabs.core.domain.DrinkGlassCategory
import com.feylabs.core.domain.DrinkIngredientCategory
import com.feylabs.core.util.AppResult.*
import com.feylabs.javalova.R
import com.feylabs.javalova.base.BaseFragment
import com.feylabs.javalova.databinding.FragmentHomeBinding
import com.feylabs.javalova.ui.home.adapter.AlcoholicAdapter
import com.feylabs.javalova.ui.home.adapter.GeneralAdapter
import com.feylabs.javalova.ui.home.adapter.GlassAdapter
import com.feylabs.javalova.ui.home.adapter.IngredientAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {


    val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapterIngredient by lazy { IngredientAdapter() }
    private val adapterCategory by lazy { GeneralAdapter() }
    private val adapterGlass by lazy { GlassAdapter() }
    private val adapterAlcoholic by lazy { AlcoholicAdapter() }

    override fun initUI() {
        binding.rvIngredient.apply {
            layoutManager = setLayoutManagerHorizontal()
            adapter = adapterIngredient
        }

        binding.rvGlassType.apply {
            layoutManager = setLayoutManagerHorizontal()
            adapter = adapterGlass
        }

        binding.tvAlcoholic.apply {
            layoutManager = setLayoutManagerHorizontal()
            adapter = adapterAlcoholic
        }

        binding.rvCategory.apply {
            layoutManager = setLayoutManagerGridHorizontal(2)
            adapter = adapterCategory
        }
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
                        adapterAlcoholic.setWithNewData(it.toMutableList())
                        adapterAlcoholic.notifyDataSetChanged()
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
                        adapterIngredient.setWithNewData(it.toMutableList())
                        adapterIngredient.notifyDataSetChanged()
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
                        adapterCategory.setWithNewData(it.toMutableList())
                        adapterCategory.notifyDataSetChanged()
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
                        adapterGlass.setWithNewData(it.toMutableList())
                        adapterGlass.notifyDataSetChanged()
                    }
                    showLoading(false)
                }
            }
        }
    }

    override fun initAction() {
        adapterAlcoholic.setupAdapterInterface(object :AlcoholicAdapter.ItemInterface{
            override fun onclick(model: DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem) {
                findNavController().navigate(R.id.nav_listDrinkFragment,
                bundleOf("alcohol" to model.strAlcoholic))
            }
        })
        adapterGlass.setupAdapterInterface(object :GlassAdapter.ItemInterface{
            override fun onclick(model: DrinkGlassCategory.DrinkGlassCategoryItem) {
                findNavController().navigate(R.id.nav_listDrinkFragment,
                    bundleOf("glass" to model.strGlass))
            }
        })
        adapterCategory.setupAdapterInterface(object :GeneralAdapter.ItemInterface{
            override fun onclick(model: DrinkGeneralCategory.DrinkGeneralCategoryItem) {
                findNavController().navigate(R.id.nav_listDrinkFragment,
                    bundleOf("category" to model.strCategory))
            }
        })
        adapterIngredient.setupAdapterInterface(object :IngredientAdapter.ItemInterface{
            override fun onclick(model: DrinkIngredientCategory.DrinkIngredientCategoryItem) {
                findNavController().navigate(R.id.nav_listDrinkFragment,
                    bundleOf("ingredient" to model.strIngredient1))
            }
        })
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun showLoading(value: Boolean) {
        if (value)
            binding.loadingCenterProgressBar.makeVisible()
        else binding.loadingCenterProgressBar.makeGone()
    }

}