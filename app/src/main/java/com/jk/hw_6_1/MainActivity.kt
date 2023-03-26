package com.jk.hw_6_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.jk.hw_6_1.databinding.ActivityMainBinding
import com.jk.hw_6_1.utils.Key

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
        resultLauncher =
            this@MainActivity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                when (result.resultCode) {
                    Activity.RESULT_OK -> {
                        // logic
                        binding.editText.setText(result.data?.getStringExtra(Key.KEY_FOR_SECOND_ACTIVITY_RESULT))
                    }
                }
            }
    }

    private fun initClicker() {
        with(binding) {
            btnEnter.setOnClickListener {
                if (binding.editText.text.toString().isNotEmpty()) {
                    val data = Intent(this@MainActivity,
                        ActivityForResult::class.java)
                    data.putExtra(Key.KEY_FOR_INTENT, binding.editText.text.toString())
                    resultLauncher.launch(data)
                } else {
                    Toast.makeText(this@MainActivity, getString(R.string.fillthefields), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
