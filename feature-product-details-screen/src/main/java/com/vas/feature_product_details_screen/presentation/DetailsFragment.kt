package com.vas.feature_product_details_screen.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vas.feature_product_details_screen.R

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val title = arguments?.getString("title")
        Toast.makeText(context, "$title", Toast.LENGTH_LONG).show()


        return inflater.inflate(R.layout.fragment_details, container, false)
    }

}