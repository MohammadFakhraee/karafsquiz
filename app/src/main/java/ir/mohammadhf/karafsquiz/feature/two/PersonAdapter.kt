package ir.mohammadhf.karafsquiz.feature.two

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.mohammadhf.karafsquiz.R
import ir.mohammadhf.karafsquiz.databinding.ItemPersonBinding
import ir.mohammadhf.karafsquiz.model.data.Person

class PersonAdapter : ListAdapter<Person, PersonAdapter.PersonViewHolder>(PersonDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class PersonViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(person: Person) {
            var outputStr =
                "${person.firstName} ${itemView.context.getString(R.string.is_related_to)} "
            outputStr += getRelativeString(person.relations)
            binding.txt.text = outputStr
        }

        // The "relative string maker" has brought here because I was thinking about 2 things:
        // 1. It is the views logic not the view-model's.
        // 2. We can access to the strings file with context
        // which view-model should not have access to.
        private fun getRelativeString(relatives: List<String>): String {
            if (relatives.isEmpty())
                return itemView.context.getString(R.string.no_one)

            var output = ""
            for ((index, relativeStr) in relatives.withIndex()) {
                output += when (index) {
                    0 -> relativeStr
                    relatives.size - 1 ->
                        " ${itemView.context.getString(R.string.ampersand)} $relativeStr"
                    else ->
                        "${itemView.context.getString(R.string.comma)} $relativeStr"
                }
            }
            output += itemView.context.getString(R.string.dot)

            return output
        }

    }
}

class PersonDiffUtil : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem

}