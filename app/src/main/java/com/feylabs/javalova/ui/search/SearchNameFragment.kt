package com.feylabs.javalova.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.feylabs.core.domain.DrinkDetail
import com.feylabs.core.util.AppResult.*
import com.feylabs.javalova.R
import com.feylabs.javalova.base.BaseFragment
import com.feylabs.javalova.databinding.FragmentSearchNameBinding
import com.feylabs.javalova.ui.search.adapter.SearchAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class SearchNameFragment : BaseFragment() {


    val viewModel: SearchViewModel by viewModel()
    private var _binding: FragmentSearchNameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapterListDrink by lazy { SearchAdapter() }

    override fun initUI() {
        binding.rv.apply {
            layoutManager = setLayoutManagerGridVertical(2)
            adapter = adapterListDrink
        }
        binding.btnLoadMore.makeGone()
    }

    override fun initObserver() {
        viewModel.searchLiveData.observe(viewLifecycleOwner) {
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
                        if (it.drinks.isEmpty()) {
                            showToast("No Data")
                        } else {
                            adapterListDrink.addNewData(it.drinks.toMutableList())
                        }
                    } ?: run {
                        showToast("No Data")
                    }
                }
            }
        }
    }

    override fun initAction() {
        adapterListDrink.setupAdapterInterface(object : SearchAdapter.ItemInterface {
            override fun onclick(model: DrinkDetail) {
                findNavController().navigate(
                    R.id.nav_detailCocktailFragment, bundleOf(
                        "id" to model.idDrink
                    )
                )
            }
        })

        binding.searchView.requestFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterListDrink.data.clear()
                adapterListDrink.notifyDataSetChanged()
                return false
            }
        })

    }

    override fun initData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchNameBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun showLoading(value: Boolean) {
        if (value)
            binding.loadingCenterProgressBar.makeVisible()
        else binding.loadingCenterProgressBar.makeGone()
    }


}