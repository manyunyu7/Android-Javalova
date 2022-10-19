package com.feylabs.javalova.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.feylabs.core.domain.*
import com.feylabs.core.util.AppResult.*
import com.feylabs.javalova.R
import com.feylabs.javalova.base.BaseFragment
import com.feylabs.javalova.databinding.FragmentCocktailDetailBinding
import com.feylabs.javalova.databinding.FragmentHomeBinding
import com.feylabs.javalova.ui.home.adapter.AlcoholicAdapter
import com.feylabs.javalova.ui.home.adapter.GeneralAdapter
import com.feylabs.javalova.ui.home.adapter.GlassAdapter
import com.feylabs.javalova.ui.home.adapter.IngredientAdapter
import com.feylabs.javalova.util.ImageViewUtils.loadImageFromURL
import org.koin.android.viewmodel.ext.android.viewModel

class DetailCocktailFragment : BaseFragment() {

    val viewModel: DetailCocktailViewModel by viewModel()
    private var _binding: FragmentCocktailDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapterIngredient by lazy { IngredientRecipeAdapter() }

    override fun initUI() {
        binding.rv.apply {
            setHasFixedSize(true)
            this.adapter = adapterIngredient
            this.layoutManager = setLayoutManagerLinear()
        }
    }

    override fun initObserver() {
        viewModel.detailLiveData.observe(viewLifecycleOwner) {
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
                        if (it.drinks.isEmpty().not()) {
                            setupDetailData(it.drinks.first())
                        } else {
                            showToast(getString(R.string.title_data_not_found))
                            findNavController().navigateUp()
                        }
                    } ?: run {
                        showToast(getString(R.string.title_data_not_found))
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }

    private fun setupDetailData(data: DrinkDetail) {
        binding.ivMainImage.loadImageFromURL(requireContext(), data.strDrinkThumb)
        binding.tvMain.text = data.strDrink
        binding.tvGlass.text = data.strGlass
        binding.tvCategory.text = data.strCategory
        binding.tvTitleRecipe
        adapterIngredient.setWithNewData(data.getDetailedIngredients())
        adapterIngredient.notifyDataSetChanged()
    }

    override fun initAction() {
    }

    override fun initData() {
        val ids = arguments?.getString("id").orEmpty()
        viewModel.detail(ids)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun showLoading(value: Boolean) {
        if (value)
            binding.loadingCenterProgressBar.makeVisible()
        else binding.loadingCenterProgressBar.makeGone()
    }

}


private fun DrinkDetail.getDetailedIngredients(): MutableList<MyIngredientModel> {
    val mlist = mutableListOf<MyIngredientModel>()
    this.apply {
        mlist.add(MyIngredientModel(strIngredient1, strMeasure1))
        mlist.add(MyIngredientModel(strIngredient2, strMeasure2))
        mlist.add(MyIngredientModel(strIngredient3, strMeasure3))
        mlist.add(MyIngredientModel(strIngredient4, strMeasure4))
        mlist.add(MyIngredientModel(strIngredient5, strMeasure5))
        mlist.add(MyIngredientModel(strIngredient6, strMeasure6))
        mlist.add(MyIngredientModel(strIngredient7, strMeasure7))
        mlist.add(MyIngredientModel(strIngredient8, strMeasure8))
        mlist.add(MyIngredientModel(strIngredient9, strMeasure9))
        mlist.add(MyIngredientModel(strIngredient10, strMeasure10))
        mlist.add(MyIngredientModel(strIngredient11, strMeasure11))
        mlist.add(MyIngredientModel(strIngredient12, strMeasure12))
        mlist.add(MyIngredientModel(strIngredient13, strMeasure13))
        mlist.add(MyIngredientModel(strIngredient14, strMeasure14))
        mlist.add(MyIngredientModel(strIngredient15, strMeasure15))
    }

    return mlist
}
