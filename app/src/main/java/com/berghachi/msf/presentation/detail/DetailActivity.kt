package com.berghachi.msf.presentation.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.berghachi.msf.databinding.ActivityDetailBinding
import com.berghachi.msf.domain.model.User
import com.berghachi.msf.utils.Constant

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        user = intent.getParcelableExtra(Constant.DEV_EXTRA)
        binding.user = user
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}