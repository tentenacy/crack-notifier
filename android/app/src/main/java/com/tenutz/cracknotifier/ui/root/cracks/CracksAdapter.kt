package com.tenutz.cracknotifier.ui.root.cracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tenutz.cracknotifier.data.paging.entity.Cracks
import com.tenutz.cracknotifier.databinding.ItemCrackBinding
import com.tenutz.cracknotifier.util.getPxFromDp


class MyActivityViewHolder(private val binding: ItemCrackBinding, private val listener: (String) -> Unit): RecyclerView.ViewHolder(binding.root) {

    var id: String? = null

    init {
        binding.constraintIcracksContainer.setOnClickListener {
            id?.let(listener)
        }
    }

    fun bind(crack: Cracks.Crack) {
        binding.crack = crack
        Glide.with(binding.root)
            .asBitmap()
            .load(crack.imageUrl)
            .transform(CenterCrop(), RoundedCorners(binding.root.context.getPxFromDp(16f)))
            .into(binding.imageIcracksThumbnail)
    }
}

class CracksAdapter(
    private val listener: (String) -> Unit
): PagingDataAdapter<Cracks.Crack, MyActivityViewHolder> (
    COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyActivityViewHolder {
        return MyActivityViewHolder(ItemCrackBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: MyActivityViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Cracks.Crack>() {
            override fun areItemsTheSame(oldItem: Cracks.Crack, newItem: Cracks.Crack): Boolean {
                return oldItem.seq == newItem.seq
            }

            override fun areContentsTheSame(oldItem: Cracks.Crack, newItem: Cracks.Crack): Boolean {
                return oldItem == newItem
            }
        }
    }
}