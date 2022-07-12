package com.ridhodev.firstquestion.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import com.ridhodev.firstquestion.R
import com.ridhodev.firstquestion.databinding.ActivityFirstScreenBinding

@Suppress("DEPRECATION")
class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding
    private var name: String = ""
    private var palindrome: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnCheck.setOnClickListener { isPalindrome() }
            btnNext.setOnClickListener {
                name = binding.etName.text.toString().trim()
                val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_NAME, name)
                startActivity(intent)
            }
        }

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = Color.TRANSPARENT
            }
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun isPalindrome() {
        palindrome = binding.etPalindrome.text.toString().trim().replace("\\s".toRegex(), "")

        val reversedPalindrome = palindrome.reversed()
        val isPalindrome = palindrome.equals(reversedPalindrome, ignoreCase = true)

        if (isPalindrome) {
            showDialog("isPalindrome")
        } else {
            showDialog("not Palindrome")
        }
    }

    private fun showDialog(palindrome: String) {
        val dialog = Dialog(this)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(R.layout.dialogsheetlayout)
            window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setGravity(Gravity.CENTER)
            }
            show()
        }

        val result = dialog.findViewById<TextView>(R.id.tv_isPalindrome)
        result.text = palindrome
    }
}