package com.markvtls.feature_details.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.markvtls.core.navigation.NavigationActions
import com.markvtls.feature_details.databinding.FragmentDetailsBinding
import com.markvtls.feature_details.databinding.ImageCardBinding
import com.markvtls.feature_details.presentation.DetailsViewModel
import com.markvtls.feature_details.presentation.adapters.TabsAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage


@AndroidEntryPoint
class DetailsFragment : Fragment() {



    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()

    private val tabsTitles = listOf("Shop", "Details", "Features")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDetails()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabsAdapter = TabsAdapter(this)
        val viewPager = binding.pager
        viewPager.adapter = tabsAdapter

        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout,viewPager) {tab, position ->
            tab.text = tabsTitles[position]
        }.attach()
        viewModel.details?.asLiveData()?.observe(viewLifecycleOwner) {
            println(it)
            loadImages(it.images)
            binding.rating.rating = it.rating.toFloat()
        }


        with(binding) {

            backButton.setOnClickListener {
                NavigationActions.toMainScreen(findNavController())
            }

            toCartButton.setOnClickListener {
                NavigationActions.toCart(findNavController())
            }

        }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun loadImages(imagesURL: List<String>) {


        val imageCarousel = binding.itemImages

        imageCarousel.carouselListener = object : CarouselListener {

            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater,
                parent: ViewGroup
            ): ViewBinding {
                return ImageCardBinding.inflate(layoutInflater, parent, false)
            }

            override fun onBindViewHolder(
                binding: ViewBinding,
                item: CarouselItem,
                position: Int
            ) {
                val currentBinding = binding as ImageCardBinding
                currentBinding.imageView.apply { setImage(item, org.imaginativeworld.whynotimagecarousel.R.drawable.carousel_default_placeholder) }
                }
        }

        val list = mutableListOf<CarouselItem>()

        imagesURL.forEach { url ->
            list.add(CarouselItem(imageUrl = url))
        }

        imageCarousel.setData(list)
        finishLoading()
    }

    private fun finishLoading() {
        binding.loading.visibility = View.INVISIBLE
        binding.mainLayout.visibility = View.VISIBLE
    }
}