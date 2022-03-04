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
import androidx.viewpager2.widget.ViewPager2
import com.vas.core.presentation.utils.Status
import com.vas.feature_product_details_screen.R
import com.vas.feature_product_details_screen.databinding.FragmentDetailsBinding
import com.vas.feature_product_details_screen.di.DetailsComponentViewModel
import com.vas.feature_product_details_screen.domain.model.DetailsModel
import com.vas.feature_product_details_screen.presentation.adapter.CapacityAdapter
import com.vas.feature_product_details_screen.presentation.adapter.ColorAdapter
import com.vas.feature_product_details_screen.presentation.adapter.ImageAdapter
import kotlinx.android.synthetic.main.details_layout.view.*
import kotlinx.android.synthetic.main.fragment_details.view.*
import java.lang.Math.abs
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    private var adapterColor: ColorAdapter = ColorAdapter()
    private var adapterCapacity: CapacityAdapter = CapacityAdapter()
    private lateinit var adapterImage: ImageAdapter

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
        initColorRecyclerView()
        initCapacityRecyclerView()
        initImageViewPager()
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

                        loadingUI(it.data!!)
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

    private fun loadingUI(data: DetailsModel) {

        binding.detailLayout.apply {
            titleTextView.text = data.title
            cameraTextView.text = data.camera
            processorTextView.text = data.cpu
            sdTextView.text = data.sd
            ssdTextView.text = data.ssd

            ratingBar.rating = data.rating.toFloat()

            likeImageView.setImageResource(
                if (data.isFavorites)
                    R.drawable.ic_like
                else
                    R.drawable.ic_no_like
            )
        }

        adapterColor.data = data.color
        adapterCapacity.data = data.capacity
        adapterImage.data = data.images
    }

    private fun initColorRecyclerView() {

        binding.detailLayout.colorRecyclerView.adapter = adapterColor

        adapterColor.onClickListener = object : ColorAdapter.OnColorClickListener{
            override fun onColorClick(item: String, position: Int) {
                adapterColor.clickPosition = position
                adapterColor.notifyDataSetChanged()
            }
        }
    }

    private fun initCapacityRecyclerView() {

        binding.detailLayout.capacityRecyclerView.adapter = adapterCapacity

        adapterCapacity.onClickListener = object : CapacityAdapter.OnCapacityClickListener{
            override fun onCapacityClick(item: String, position: Int) {
                adapterCapacity.clickPosition = position
                adapterCapacity.notifyDataSetChanged()
            }
        }
    }

    private fun initImageViewPager() {
        adapterImage = ImageAdapter(requireContext())

        binding.imagesViewPager.adapter = adapterImage

        binding.imagesViewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
        }
        binding.imagesViewPager.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.imagesViewPager.addItemDecoration(itemDecoration)
    }
}