package com.feylabs.javalova.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.feylabs.core.domain.DrinkGeneralCategory.DrinkGeneralCategoryItem as AdapterModel
import com.feylabs.javalova.R
import com.feylabs.javalova.databinding.ItemDrinksCompactBinding as AdapterBinding
import com.feylabs.javalova.databinding.ItemCategoryBinding
import com.feylabs.javalova.util.ImageViewUtils.loadImageFromURL

class GeneralAdapter : RecyclerView.Adapter<GeneralAdapter.AdapterViewHolder>() {

    val data = mutableListOf<AdapterModel>()
    lateinit var adapterInterface: ItemInterface

    var page = 1

    fun setWithNewData(data: MutableList<AdapterModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addNewData(newData: MutableList<AdapterModel>, newPage: Int = this.page) {
        newData.forEachIndexed { index, data ->
            this.data.add(data)
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
                R.anim.anim_falldown
            )

            if (::adapterInterface.isInitialized) {
                binding.root.setOnClickListener {
                    adapterInterface.onclick(model)
                }
            }

            binding.tvSecondary.visibility=View.GONE
            binding.tvMain.text = model.strCategory
            binding.ivMainImage.loadImageFromURL(mContext,"")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_drinks_compact, parent, false)
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