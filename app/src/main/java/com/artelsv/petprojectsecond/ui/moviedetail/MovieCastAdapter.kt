package com.artelsv.petprojectsecond.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.artelsv.petprojectsecond.R
import com.artelsv.petprojectsecond.databinding.ItemPersonBinding
import com.artelsv.petprojectsecond.domain.model.movie.credits.Cast

class MovieCastAdapter(
    private val clickListener: (clickData: Cast) -> Unit,
) : ListAdapter<Cast, MovieCastAdapter.ViewHolder>(DiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    class ViewHolder private constructor(
        private val binding: ItemPersonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Cast,
            onClickListener: (clickData: Cast) -> Unit,
        ) {
            binding.ivPerson.load("https://image.tmdb.org/t/p/w500${item.profilePath}") {
                error(when (item.gender) {
                    0 -> R.drawable.ic_empty_man
                    1 -> R.drawable.ic_empty_women
                    else -> R.drawable.ic_empty_man
                })
            }

            binding.tvName.text =
                binding.root.context.getString(R.string.item_person_name, item.name, item.character)

            binding.mcvContainer.setOnClickListener {
                onClickListener(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemPersonBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )

                return ViewHolder(binding)
            }
        }
    }
}

private object DiffItemCallback : DiffUtil.ItemCallback<Cast>() {

    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}