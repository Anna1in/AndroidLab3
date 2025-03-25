package com.example.labandr3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var inputFragment: InputFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, InputFragment())
                .commit()
        }
    }
    fun showResult(selectedCompany :String, selectedProduct:String){
        val resultFragment = ResultFragment.newInstance(selectedCompany, selectedProduct)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, resultFragment)
            .addToBackStack(null)  // Додаємо у стек, щоб повернутися назад
            .commit()
    }
    fun goBackToInput() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, InputFragment()) // Замінюємо на InputFragment
            .commit()
    }


}
