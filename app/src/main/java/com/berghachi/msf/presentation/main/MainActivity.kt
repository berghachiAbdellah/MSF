package com.berghachi.msf.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.berghachi.msf.R
import com.berghachi.msf.datasource.model.ApiStatus
import com.berghachi.msf.domain.model.User
import com.berghachi.msf.presentation.detail.DetailActivity
import com.berghachi.msf.presentation.main.adapter.UserAdapter
import com.berghachi.msf.utils.Constant
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserAdapter
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadData()

        observeData()


        swiperefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun observeData() {
        mainViewModel.users.observe(this, {
            adapter.submitList(it as MutableList<User>?)
        })

        mainViewModel.status.observe(this, {
            when (it) {
                ApiStatus.LOADING -> {
                    showLoading()
                }
                else -> {
                    hideLoading()
                }
            }
        })

        mainViewModel.userDeleted.observe(this, Observer {
            if (it != null) {
                if (it) {
                    loadData()
                }
            }
        })
    }

    /**
     * Show loading
     *
     */
    private fun showLoading() {

        progressBar.visibility = View.VISIBLE
    }

    /**
     * Hide loading
     *
     */
    private fun hideLoading() {
        swiperefresh.isRefreshing = false
        progressBar.visibility = View.GONE
    }

    /**
     * Init recycler view
     *
     */
    private fun initRecyclerView() {
        adapter = UserAdapter(showDetail = {
            showDetail(it)
        }, deleteUser = {
            deleteUser(it)
        })
        recycler_test.adapter = adapter
    }

    private fun deleteUser(user: User) {
        if (user.hired == "Y") {
            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.delete))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(
                    R.string.delete
                ) { p0, p1 ->
                    mainViewModel.deleteUser(user.id)
                }
                .setNegativeButton(
                    R.string.cancel
                ) { p0, p1 ->

                }
            dialog.show()
        } else {
            Toast.makeText(this, getString(R.string.hired_user), Toast.LENGTH_SHORT).show()
        }

    }

    private fun showDetail(user: User) {
        startActivity(Intent(this, DetailActivity::class.java).putExtra(Constant.DEV_EXTRA, user))
    }

    /**
     * Load data
     *
     */
    private fun loadData() {
        mainViewModel.getDevs()
    }


}