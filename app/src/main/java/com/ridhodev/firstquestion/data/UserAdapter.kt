package com.ridhodev.firstquestion.data

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ridhodev.firstquestion.data.response.DataItem
import com.ridhodev.firstquestion.databinding.UserItemBinding
import com.ridhodev.firstquestion.ui.SecondScreenActivity

class UserAdapter: PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        } else {
            Log.d("TAGG", "getData: NULL")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    class MyViewHolder(private val binding: UserItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            val fullName = "${data.firstName} ${data.lastName}"
            binding.tvUser.text = fullName
            binding.tvEmail.text = data.email
            Glide.with(itemView)
                .load(data.avatar)
                .circleCrop()
                .into(binding.ivUser)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_USER, fullName)
                itemView.context.startActivity(intent)
            }
        }


    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


}