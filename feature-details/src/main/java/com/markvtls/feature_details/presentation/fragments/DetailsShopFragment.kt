package com.markvtls.feature_details.presentation.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.markvtls.feature_details.R
import com.markvtls.feature_details.databinding.FragmentDetailsShopBinding
import com.markvtls.feature_details.presentation.DetailsViewModel
import java.text.NumberFormat
import java.util.*

/**Details shop UI.*/
internal class DetailsShopFragment : Fragment() {


    private var _binding: FragmentDetailsShopBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.details?.asLiveData()?.observe(viewLifecycleOwner) { details ->

            with(binding) {
                CPUText.text = details.CPU
                cameraText.text = details.camera
                RAMText.text = details.ssd
                storageText.text = details.sd

                loadColors(details.color)
                loadCapacity(details.capacity)

                addToCartButton.text = resources.getString(
                    com.markvtls.core.R.string.add_to_cart,
                    NumberFormat.getCurrencyInstance(Locale.US).format(details.price)
                )

                colorOneButton.setOnClickListener {
                    colorOne.performClick()
                }

                colorTwoButton.setOnClickListener {
                    colorTwo.performClick()
                }

                capacityOptionOneButton.setOnClickListener {
                    capacityOne.performClick()
                }

                capacityOptionTwoButton.setOnClickListener {
                    capacityTwo.performClick()
                }

                colorOne.setOnClickListener {
                    onColorButtonClicked(it)
                }
                colorTwo.setOnClickListener {
                    onColorButtonClicked(it)
                }

                capacityOne.setOnClickListener {
                    onCapacityButtonClicked(it)
                }

                capacityTwo.setOnClickListener {
                    onCapacityButtonClicked(it)
                }


            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun loadColors(colors: List<String>) {

        val colorOptionOne = binding.colorOneButton
        val colorOptionTwo = binding.colorTwoButton

        when (colors.size) {
            0 -> {
                colorOptionOne.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.white))
                colorOptionTwo.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.white))
            }
            1 -> {
                colorOptionOne.backgroundTintList = ColorStateList.valueOf(colors[0].toColorInt())
                colorOptionTwo.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.white))
            }
            2 -> {
                colorOptionOne.backgroundTintList = ColorStateList.valueOf(colors[0].toColorInt())
                colorOptionTwo.backgroundTintList = ColorStateList.valueOf(colors[1].toColorInt())
            }
        }
    }

    private fun loadCapacity(capacity: List<String>) {
        val capacityOptionOne = binding.capacityOptionOneButton
        val capacityOptionTwo = binding.capacityOptionTwoButton

        when (capacity.size) {
            0 -> {
                capacityOptionOne.text = getString(com.markvtls.core.R.string.out_of_stock)
                capacityOptionTwo.text = getString(com.markvtls.core.R.string.out_of_stock)
            }
            1 -> {
                capacityOptionOne.text = getString(com.markvtls.core.R.string.capacity, capacity[0])
                capacityOptionTwo.text = getString(com.markvtls.core.R.string.out_of_stock)
            }
            2 -> {
                capacityOptionOne.text = getString(com.markvtls.core.R.string.capacity, capacity[0])
                capacityOptionTwo.text = getString(com.markvtls.core.R.string.capacity, capacity[1])
            }
        }
    }

    /**Handling color selection.*/
    private fun onColorButtonClicked(view: View) {
        if (view is RadioButton) {

            val checked = view.isChecked

            binding.apply {
                colorOneButton.setImageResource(com.markvtls.core.R.drawable.ic_empty_space)
                colorTwoButton.setImageResource(com.markvtls.core.R.drawable.ic_empty_space)
            }

            when (view.getId()) {
                R.id.color_one ->
                    if (checked) {
                        binding.colorOneButton.setImageResource(com.markvtls.core.R.drawable.ic_check)
                    }
                R.id.color_two ->
                    if (checked) {
                        binding.colorTwoButton.setImageResource(com.markvtls.core.R.drawable.ic_check)
                    }
            }
        }
    }


    /**Handling capacity selection.*/
    private fun onCapacityButtonClicked(view: View) {
        if (view is RadioButton) {

            val checked = view.isChecked

            binding.apply {
                capacityOptionOneButton.apply {
                    setTextColor(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.dark_gray))
                    backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
                }
                capacityOptionTwoButton.apply {
                    setTextColor(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.dark_gray))
                    backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
                }
            }

            when (view.getId()) {
                R.id.capacity_one ->
                    if (checked) {
                        binding.capacityOptionOneButton.apply {
                            setTextColor(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.white))
                            backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.orange))
                        }
                    }
                R.id.capacity_two ->
                    if (checked) {
                        binding.capacityOptionTwoButton.apply {
                            setTextColor(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.white))
                            backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),com.markvtls.core.R.color.orange))
                        }
                    }
            }
        }
    }
}