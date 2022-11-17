package com.markvtls.feature_cart.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.markvtls.core.navigation.NavigationActions
import com.markvtls.feature_cart.databinding.FragmentCartBinding
import com.markvtls.feature_cart.domain.model.CartInfo
import com.markvtls.feature_cart.domain.model.CartItem
import com.markvtls.feature_cart.presentation.CartViewModel
import com.markvtls.feature_cart.presentation.adapters.CartFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*


/**Cart UI.*/
@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()

    private val adapter = CartFragmentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    NavigationActions.toMainScreen(findNavController())
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCart()
        }

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.backButton.setOnClickListener {
            NavigationActions.toMainScreen(findNavController())
        }

        viewModel.cart?.asLiveData()?.observe(viewLifecycleOwner) { cart ->
            loadCartInfo(cart)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun loadCartInfo(cart: CartInfo) {
        with(binding) {
            totalPrice.text = resources.getString(
                com.markvtls.core.R.string.total_price_us,
                NumberFormat.getNumberInstance(Locale.US).format(cart.total)
            )
            deliveryCost.text = cart.delivery
            loadCartItems(cart.basket)
            finishLoading()
        }
    }

    private fun loadCartItems(cart: List<CartItem>) {

        val recyclerView = binding.recyclerview

        recyclerView.adapter = adapter
        adapter.apply {
            items = cart
        }

    }

    /**Loading animation.*/
    private fun finishLoading() {
        binding.loading.visibility = View.INVISIBLE
        binding.mainLayout.visibility = View.VISIBLE
    }
}