package com.jk.hw_6_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jk.hw_6_1.databinding.ActivityForResultBinding
import com.jk.hw_6_1.utils.Key

class ActivityForResult : AppCompatActivity() {
    private lateinit var binding: ActivityForResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {
        val data = intent.getStringExtra(Key.KEY_FOR_INTENT)
        with(binding) {
            etResult.setText(data)
            btnBack.setOnClickListener {
                if (etResult.text.isNotEmpty()) {
                    setResult(RESULT_OK,
                        Intent().putExtra(Key.KEY_FOR_SECOND_ACTIVITY_RESULT,
                            etResult.text.toString()))
                    finish()
                } else {
                    Toast.makeText(this@ActivityForResult,
                        getString(R.string.cannotbeempty),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
}