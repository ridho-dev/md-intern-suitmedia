package com.ridhodev.firstquestion.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ridhodev.firstquestion.R
import com.ridhodev.firstquestion.data.UserAdapter
import com.ridhodev.firstquestion.data.LoadingStateAdapter
import com.ridhodev.firstquestion.databinding.ActivityThirdScreenBinding

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel: ThirdScreenViewModel by viewModels()
    private lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.tbUserList
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_keyboard_arrow_left_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvUser.layoutManager = LinearLayoutManager(this@ThirdScreenActivity)

        getUsers()

        swipeContainer = binding.swipeRefreshLayout
        swipeContainer.setOnRefreshListener {
            getUsers()
            swipeContainer.isRefreshing = false
        }
    }

    private fun getUsers() {
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter { adapter.retry() }
        )
        adapter.itemCount < 1
        viewModel.users.observe(this) {
            if (it == null) {
                binding.ivUserNotFound.visibility = View.VISIBLE
                binding.tvUserNotFound.visibility = View.VISIBLE
            } else {
                binding.ivUserNotFound.visibility = View.INVISIBLE
                binding.tvUserNotFound.visibility = View.INVISIBLE
                adapter.submitData(lifecycle, it)
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}