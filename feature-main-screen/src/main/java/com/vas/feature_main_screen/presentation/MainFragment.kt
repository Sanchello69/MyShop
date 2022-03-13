package com.vas.feature_main_screen.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.vas.feature_main_screen.databinding.FragmentMainBinding
import com.vas.feature_main_screen.di.MainComponentViewModel
import com.vas.feature_main_screen.domain.model.BestSeller
import com.vas.feature_main_screen.domain.model.HomeStore
import com.vas.feature_main_screen.presentation.adapter.BestSellerAdapter
import com.vas.feature_main_screen.presentation.adapter.CategoryAdapter
import com.vas.feature_main_screen.presentation.adapter.HotSalesAdapter
import com.vas.core.presentation.utils.Status
import com.vas.core.utils.Result
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.navigation.NavCommand
import com.vas.navigation.navigate
import kotlinx.android.synthetic.main.bottom_navigation.view.*
import kotlinx.android.synthetic.main.filter_layout.view.*
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var analytics: FirebaseAnalytics

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var mainNavCommandProvider: MainNavCommandProvider

    private val bundle = Bundle()

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var adapterHotSales: HotSalesAdapter
    private lateinit var adapterBestSeller: BestSellerAdapter

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainComponentViewModel>()
            .newDetailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        analytics = Firebase.analytics

        setupViewModel()
        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    private fun setupUI() {
        initCategoryRecyclerView()
        initHotSalesViewPager()
        initBestSellerRecyclerView()
        initBrandSpinner()
        initPriceSpinner()
        initSizeSpinner()
        initBottomButtonCart()
        initLocationButton()
    }

    private fun setupObservers() {
        viewModel.main.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {

                    Log.d("status", "SUCCESS")

                    result.data?.let { if (it.isNotEmpty()){
                        adapterHotSales.data = it.last().listHomeStore
                        adapterBestSeller.data = it.last().listBestSeller
                    } }

                }
                Result.Status.LOADING -> Log.d("status", "LOADING")
                Result.Status.ERROR -> Log.d("status", "ERROR")
            }
        })
    }

    private fun initBrandSpinner() {
        binding.filter.brandSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            arrayOf("Samsung ", "Apple", "Xiaomi", "Huawei", "Lenovo ", "Motorola"))

        binding.filter.brandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("filter", "Selected ${binding.filter.brandSpinner.selectedItem}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //ничего не выбрано
            }
        }
    }

    private fun initPriceSpinner() {
        binding.filter.priceSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            arrayOf("less $300", "$300 - $500", "$501 - $1000", "more $1000"))

        binding.filter.priceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("filter", "Selected ${binding.filter.priceSpinner.selectedItem}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //ничего не выбрано
            }
        }
    }

    private fun initSizeSpinner() {
        binding.filter.sizeSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            arrayOf("less 4.5", "4.5 to 5.5 inches", "more 5.5"))

        binding.filter.sizeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("filter", "Selected ${binding.filter.sizeSpinner.selectedItem}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //ничего не выбрано
            }
        }
    }

    private fun initCategoryRecyclerView() {
        val adapterCategory = CategoryAdapter()
        adapterCategory.data = listOf("Phones", "Books", "Computer", "Health",
            "Phones", "Books", "Computer", "Health")

        binding.categoryRecyclerView.adapter = adapterCategory

        adapterCategory.onClickListener = object : CategoryAdapter.OnCategoryClickListener{
            override fun onCategoryClick(category: String, position: Int) {

                sendAnalytics(category, "Select category")

                //Toast.makeText(context, "Click $category", Toast.LENGTH_SHORT).show()
                adapterCategory.clickPosition = position
                adapterCategory.notifyDataSetChanged()
            }
        }
    }

    private fun initHotSalesViewPager() {
        adapterHotSales = HotSalesAdapter(requireContext())

        binding.hotSalesViewPager.adapter = adapterHotSales

        adapterHotSales.onClickListener = object : HotSalesAdapter.OnHotSalesClickListener{
            override fun onBuyNowClick(homeStore: HomeStore) {

                sendAnalytics(homeStore.title, "Hot sales")

                Toast.makeText(context, "Click ${homeStore.title}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initBestSellerRecyclerView() {
        adapterBestSeller = BestSellerAdapter(requireContext())

        binding.bestSellerRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.bestSellerRecyclerView.adapter = adapterBestSeller

        adapterBestSeller.onClickListener = object : BestSellerAdapter.OnBestSellerClickListener{
            override fun onLikeClick(bestSeller: BestSeller) {
                //должна быть какая то отправка данных, что он favorite (not favorite)
                Toast.makeText(context, "Click ${bestSeller.title}", Toast.LENGTH_LONG).show()
            }

            override fun onItemClick(title: String) {

                sendAnalytics(title, "Best seller")

                bundle.putString("title", title)
                navigate(NavCommand(
                    action = mainNavCommandProvider.toDetails.action,
                    args = bundle))
            }
        }
    }

    private fun initBottomButtonCart() {
        binding.bottomNavigation.cartImageView.setOnClickListener {
            navigate(mainNavCommandProvider.toCart)
        }
    }

    private fun initLocationButton() {
        binding.locationImageView.setOnClickListener {
            navigate(mainNavCommandProvider.toMaps)
        }
    }

    private fun sendAnalytics(itemName: String, contentType: String) {
        val bundle = Bundle()

        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType)

        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}