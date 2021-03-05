

package com.berghachi.msf.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.berghachi.msf.R
import com.berghachi.msf.databinding.UserItemBinding
import com.berghachi.msf.domain.model.User


class UserAdapter(val showDetail: (user: User) -> Unit,val deleteUser: (user: User) -> Unit) :
    ListAdapter<User, UserAdapter.RecentGiftsItemViewHolder>(
        DiffCallback
    ) {

    private var list = mutableListOf<User>()

    class RecentGiftsItemViewHolder(private var binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }



    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Override on create view holder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentGiftsItemViewHolder {

        return RecentGiftsItemViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    /**
     * Override on bind view holder and set click listener
     * bind cup and a boolean for showing selected item
     */
    override fun onBindViewHolder(holder: RecentGiftsItemViewHolder, position: Int) {
        val user = getItem(position)
        holder.itemView.setOnClickListener {
            showDetail(user)
        }
        holder.itemView.findViewById<ImageView>(R.id.iv_delete).setOnClickListener {
            deleteUser(user)
        }

        holder.bind(user)
    }

}
