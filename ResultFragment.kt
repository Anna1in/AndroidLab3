package com.example.labandr3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.File


class ResultFragment : Fragment() {

    private var activityCallback: MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            activityCallback = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val textViewResult: TextView = view.findViewById(R.id.textViewResult)
        val textViewAllResults: TextView = view.findViewById(R.id.textViewAllResults)
        val buttonCancel: Button = view.findViewById(R.id.buttonCancel)
        val buttonOpen: Button = view.findViewById(R.id.buttonOpen)

        val selectedCompany = arguments?.getString("company")
        val selectedProduct = arguments?.getString("product")

        textViewResult.text = "Компанія: $selectedCompany\nТовар: $selectedProduct"

        buttonCancel.setOnClickListener {
            activityCallback?.goBackToInput()
        }

        buttonOpen.setOnClickListener {
            val savedData = readFromFile()
            if (savedData.isEmpty()) {
                textViewAllResults.text = "Немає збережених даних"
            } else {
                textViewAllResults.text = savedData
            }
        }
        return view
    }

    companion object {
        fun newInstance(company: String, product: String): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putString("company", company)
            args.putString("product", product)
            fragment.arguments = args
            return fragment
        }
    }

    private fun readFromFile(): String {
        val fileName = "selections.txt"
        val file = File(requireContext().filesDir, fileName)
        return if (file.exists()) {
            file.readText()
        } else {
            "Відомості в файлі відсутні."
        }
    }
}