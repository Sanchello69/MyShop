package com.vas.feature_cart_screen.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import com.vas.core.presentation.utils.Status
import com.vas.feature_cart_screen.R
import com.vas.feature_cart_screen.databinding.FragmentMyCartBinding
import com.vas.feature_cart_screen.di.CartComponentViewModel
import com.vas.feature_cart_screen.domain.model.CartModel
import com.vas.feature_cart_screen.presentation.adapter.CartAdapter
import javax.inject.Inject


class MyCartFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: CartViewModelFactory

    private lateinit var binding: FragmentMyCartBinding
    private lateinit var viewModel: CartViewModel

    private lateinit var adapterCart: CartAdapter

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CartComponentViewModel>()
            .newCartComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyCartBinding.inflate(inflater, container, false)

        setupViewModel()
        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CartViewModel::class.java)
    }

    private fun setupUI() {
        initCartRecyclerView()
        initBackButton()
    }

    private fun setupObservers() {
        viewModel.getCart().observe(viewLifecycleOwner, Observer {
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

    private fun loadingUI(data: CartModel) {
        binding.apply {
            setTotalTextView.text = "$${data.total}"
            setDeliveryTextView.text = data.delivery
        }

        adapterCart.data = data.basket
    }

    private fun initCartRecyclerView() {
        adapterCart = CartAdapter(requireContext())
        binding.cartRecyclerView.adapter = adapterCart
    }

    private fun initBackButton() {
        binding.backImageView.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }
}