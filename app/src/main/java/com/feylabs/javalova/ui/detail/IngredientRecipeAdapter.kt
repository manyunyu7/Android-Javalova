package com.feylabs.javalova.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.feylabs.javalova.R
import com.feylabs.javalova.ui.detail.MyIngredientModel as AdapterModel
import com.feylabs.javalova.util.ImageViewUtils.loadImageFromURL
import com.feylabs.javalova.databinding.ItemIngredientsCategoryBinding
import com.feylabs.javalova.databinding.ItemRecipeIngredientBinding  as AdapterBinding

class IngredientRecipeAdapter : RecyclerView.Adapter<IngredientRecipeAdapter.AdapterViewHolder>() {

    val data = mutableListOf<AdapterModel>()
    lateinit var adapterInterface: ItemInterface

    var page = 1

    fun setWithNewData(data: MutableList<AdapterModel>) {
        this.data.clear()
        var tempData = mutableListOf<AdapterModel>()
        data.forEachIndexed { index, myIngredientModel ->
            if (myIngredientModel.name != null)
                tempData.add(myIngredientModel)
        }
        this.data.addAll(tempData)
        notifyDataSetChanged()
    }

    fun addNewData(newData: MutableList<AdapterModel>, newPage: Int = this.page) {
        newData.forEachIndexed { index, data ->
            if (data.name != null) {
                this.data.add(data)
            }
            notifyItemInserted(itemCount - 1)
        }
        this.page = newPage
    }

    fun setupAdapterInterface(obj: ItemInterface) {
        this.adapterInterface = obj
    }

    inner class AdapterViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: AdapterBinding = AdapterBinding.bind(itemView)

        fun onBInd(model: AdapterModel) {
            val mContext = binding.root.context

            binding.root.animation = AnimationUtils.loadAnimation(
                mContext,
                R.anim.fade_transition_animation
            )

            if (::adapterInterface.isInitialized) {
                binding.root.setOnClickListener {
                    adapterInterface.onclick(model)
                }
            }

            binding.tvMain.text = model.name
            binding.tvDescription.text = model.measurement
            binding.ivMainImage.loadImageFromURL(
                mContext,
                mContext.getString(R.string.ingredients_url) + "${model.name}.png"
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_ingredient, parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.onBInd(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ItemInterface {
        fun onclick(model: AdapterModel)
    }
}