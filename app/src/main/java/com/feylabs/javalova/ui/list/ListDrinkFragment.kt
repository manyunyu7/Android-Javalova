package com.feylabs.javalova.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.feylabs.core.domain.DrinkGeneral
import com.feylabs.core.util.AppResult.*
import com.feylabs.javalova.R
import com.feylabs.javalova.base.BaseFragment
import com.feylabs.javalova.databinding.FragmentHomeBinding
import com.feylabs.javalova.databinding.FragmentListDrinkBinding
import com.feylabs.javalova.ui.home.adapter.AlcoholicAdapter
import com.feylabs.javalova.ui.home.adapter.GeneralAdapter
import com.feylabs.javalova.ui.home.adapter.GlassAdapter
import com.feylabs.javalova.ui.home.adapter.IngredientAdapter
import com.feylabs.javalova.ui.list.adapter.ListDrinkAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ListDrinkFragment : BaseFragment() {


    val viewModel: ListDrinkViewModel by viewModel()
    private var _binding: FragmentListDrinkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapterListDrink by lazy { ListDrinkAdapter() }

    var alcohol: String? = ""
    var category: String? = ""
    var ingredient: String? = ""
    var glass: String? = ""
    var lastItem: DrinkGeneral? = null

    override fun initUI() {
        binding.rvIngredient.apply {
            layoutManager = setLayoutManagerGridVertical(2)
            adapter = adapterListDrink
        }

        binding.btnLoadMore.makeGone()

        alcohol = arguments?.getString("alcohol")
        category = arguments?.getString("category")
        ingredient = arguments?.getString("ingredient")
        glass = arguments?.getString("glass")
    }

    override fun initObserver() {
        viewModel.drinkListLiveData.observe(viewLifecycleOwner) {
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
                        if (it.isEmpty()) {
                            binding.btnLoadMore.makeGone()
                        } else {
                            adapterListDrink.addNewData(it.toMutableList())
                            binding.btnLoadMore.makeVisible()
                            lastItem = adapterListDrink.data.last()
                        }
                    } ?: run {
                        binding.btnLoadMore.makeGone()
                    }
                }
            }
        }
    }

    override fun initAction() {
        adapterListDrink.setupAdapterInterface(object :ListDrinkAdapter.ItemInterface{
            override fun onclick(model: DrinkGeneral) {
                findNavController().navigate(R.id.nav_detailCocktailFragment, bundleOf(
                    "id" to model.idDrink
                ))
            }
        })

        binding.btnLoadMore.setOnClickListener {
            viewModel.getDrinkList(
                category = category,
                glass = glass,
                ingredient = ingredient,
                alcoholic = alcohol,
                lastValue = lastItem?.idDrink
            )
        }
    }

    override fun initData() {
        viewModel.getDrinkList(
            category = category,
            glass = glass,
            ingredient = ingredient,
            alcoholic = alcohol,
            lastValue = lastItem?.idDrink
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListDrinkBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun showLoading(value: Boolean) {
        if (value)
            binding.loadingCenterProgressBar.makeVisible()
        else binding.loadingCenterProgressBar.makeGone()
    }


}