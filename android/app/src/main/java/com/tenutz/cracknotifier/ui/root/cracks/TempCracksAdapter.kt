package com.tenutz.cracknotifier.ui.root.cracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tenutz.cracknotifier.databinding.ItemCrackBinding
import com.tenutz.cracknotifier.util.dummy.DummyCracks
import com.tenutz.cracknotifier.util.getDpFromPx
import com.tenutz.cracknotifier.util.getPxFromDp
import kotlin.math.round

class TempCracksAdapter(val listener: (Int) -> Unit): RecyclerView.Adapter<TempCracksAdapter.ViewHolder>() {

    var items: List<DummyCracks> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: ItemCrackBinding, listener: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {

        var id: Int? = null

        init {
            binding.constraintIcracksContainer.setOnClickListener {
                id?.let(listener)
            }
        }

        fun bind(cracks: DummyCracks) {
            id = cracks.id
            binding.region = cracks.region
            binding.createdAt = cracks.createdAt
            binding.accuracy = cracks.accuracy.toInt().toString()
            Glide.with(binding.root)
                .asBitmap()
                .load(cracks.imageUrl)
                .transform(CenterCrop(), RoundedCorners(binding.root.context.getPxFromDp(16f)))
                .into(binding.imageIcracksThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCrackBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}