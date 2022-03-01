package com.vas.feature_product_details_screen.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.vas.core.presentation.utils.Status
import com.vas.feature_product_details_screen.R
import com.vas.feature_product_details_screen.databinding.FragmentDetailsBinding
import com.vas.feature_product_details_screen.di.DetailsComponentViewModel
import kotlinx.android.synthetic.main.details_layout.view.*
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DetailsComponentViewModel>()
            .newDetailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val title = arguments?.getString("title")
        Toast.makeText(context, "$title", Toast.LENGTH_LONG).show()

        setupViewModel()
        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DetailsViewModel::class.java)
    }

    private fun setupUI() {

    }

    private fun setupObservers() {
        //по сути здесь мы должны передавать arguments?.getString("title") в метод getDetails(),
        //чтобы получить от API детали конкретной модели товара, но в API подробная информация есть
        //только по одной модели, поэтому метод getDetails() без аргументов
        viewModel.getDetails().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("status", "SUCCESS")
                        Log.d("status", "${it.data}")

                        binding.detailLayout.titleTextView.text = it.data!!.title
                        binding.detailLayout.cameraTextView.text = it.data!!.camera
                        binding.detailLayout.processorTextView.text = it.data!!.cpu
                        binding.detailLayout.sdTextView.text = it.data!!.sd
                        binding.detailLayout.ssdTextView.text = it.data!!.ssd

                        binding.detailLayout.ratingBar.rating = it.data!!.rating.toFloat()

                        binding.detailLayout.likeImageView.setImageResource(
                            if (it.data!!.isFavorites)
                                R.drawable.ic_like
                            else
                                R.drawable.ic_no_like
                        )
                    }
                    Status.ERROR -> {
                        Log.d("status", "ERROR ${it.message}")
                    }
                    Status.LOADING -> {
                        Log.d("status",  "LOADING")
                    }
                }
            }
        })
    }
}