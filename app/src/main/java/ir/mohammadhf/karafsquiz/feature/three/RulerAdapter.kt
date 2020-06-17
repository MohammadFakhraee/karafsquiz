package ir.mohammadhf.karafsquiz.feature.three

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.mohammadhf.karafsquiz.databinding.ItemRulerHeightBinding
import ir.mohammadhf.karafsquiz.databinding.ItemRulerWidthBinding
import ir.mohammadhf.karafsquiz.model.data.Ruler

class WidthRulerAdapter() :
    ListAdapter<Ruler, WidthRulerAdapter.RulerViewHolder>(RulerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulerViewHolder =
        RulerViewHolder(
            ItemRulerWidthBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RulerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class RulerViewHolder(private val binding: ItemRulerWidthBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(ruler: Ruler) {
            binding.root.layoutParams.width = ruler.length.toInt()
            binding.textView.text = ruler.type
        }
    }
}

class HeightRulerAdapter() :
    ListAdapter<Ruler, HeightRulerAdapter.RulerViewHolder>(RulerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulerViewHolder =
        RulerViewHolder(
            ItemRulerHeightBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RulerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class RulerViewHolder(private val binding: ItemRulerHeightBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(ruler: Ruler) {
            binding.root.layoutParams.height = ruler.length.toInt()
            binding.textView.text = ruler.type
        }
    }
}

class RulerDiffUtil : DiffUtil.ItemCallback<Ruler>() {
    override fun areItemsTheSame(oldItem: Ruler, newItem: Ruler): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Ruler, newItem: Ruler): Boolean =
        oldItem == newItem
}