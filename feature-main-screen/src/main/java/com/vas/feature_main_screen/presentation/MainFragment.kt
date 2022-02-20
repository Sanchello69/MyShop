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
import com.vas.feature_main_screen.databinding.FragmentMainBinding
import com.vas.feature_main_screen.di.MainComponentViewModel
import com.vas.feature_main_screen.domain.model.BestSeller
import com.vas.feature_main_screen.domain.model.HomeStore
import com.vas.feature_main_screen.presentation.adapter.BestSellerAdapter
import com.vas.feature_main_screen.presentation.adapter.CategoryAdapter
import com.vas.feature_main_screen.presentation.adapter.HotSalesAdapter
import com.vas.feature_main_screen.presentation.utils.Status
import kotlinx.android.synthetic.main.filter_layout.view.*
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

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

        setupViewModel()
        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupViewModel() {
        //val apiHelper = ApiHelper(RetrofitClient.apiInterface)
        //val viewModelFactory = MainViewModelFactory(apiHelper)

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
    }

    private fun setupObservers() {
        viewModel.getMain().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("status", "SUCCESS")
                        Log.d("status", "${it.data}")
                        adapterHotSales.data = it.data!!.listHomeStore
                        adapterBestSeller.data = it.data!!.listBestSeller
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

    private fun initBrandSpinner() {
        binding.filter.brandSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            arrayOf("Samsung ", "Apple", "Xiaomi", "Huawei", "Lenovo ", "Motorola"))

        binding.filter.brandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(context, "Selected ${binding.filter.brandSpinner.selectedItem}", Toast.LENGTH_SHORT)
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
                Toast.makeText(context, "Selected ${binding.filter.priceSpinner.selectedItem}", Toast.LENGTH_SHORT)
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
                Toast.makeText(context, "Selected ${binding.filter.sizeSpinner.selectedItem}", Toast.LENGTH_SHORT)
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
                Toast.makeText(context, "Click $category", Toast.LENGTH_SHORT).show()
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
        }
    }
}