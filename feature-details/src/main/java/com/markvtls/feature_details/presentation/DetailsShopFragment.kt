package com.markvtls.feature_details.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.markvtls.feature_details.R
import com.markvtls.feature_details.databinding.FragmentDetailsShopBinding
import org.imaginativeworld.whynotimagecarousel.utils.setImage


class DetailsShopFragment : Fragment() {


    private var _binding: FragmentDetailsShopBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels( ownerProducer = { requireParentFragment() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.details?.asLiveData()?.observe(viewLifecycleOwner) {

            with(binding) {
                CPUText.text = it.CPU
                cameraText.text = it.camera
                RAMText.text = it.ssd
                storageText.text = it.sd

                colorOneButton.backgroundTintList = ColorStateList.valueOf(it.color[0].toColorInt())
                colorTwoButton.backgroundTintList = ColorStateList.valueOf(it.color[1].toColorInt())

                capacityOptionOneButton.text = getString(com.markvtls.core.R.string.capacity,it.capacity[0])
                capacityOptionTwoButton.text = getString(com.markvtls.core.R.string.capacity,it.capacity[1])

                addToCartButton.text = getString(com.markvtls.core.R.string.add_to_cart, it.price.toString())

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


    private fun onColorButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            binding.apply {
                colorOneButton.setImageResource(com.markvtls.core.R.drawable.ic_empty_space)
                colorTwoButton.setImageResource(com.markvtls.core.R.drawable.ic_empty_space)
            }
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.color_one->
                    if (checked) {
                        binding.colorOneButton.setImageResource(com.markvtls.core.R.drawable.ic_check)
                        println("Color one selected")
                    }
                R.id.color_two ->
                    if (checked) {
                        println("Color two selected")
                        binding.colorTwoButton.setImageResource(com.markvtls.core.R.drawable.ic_check)
                    }
            }
        }
    }


    private fun onCapacityButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            binding.apply {
                capacityOptionOneButton.apply {
                    setTextColor(resources.getColor(com.markvtls.core.R.color.dark_gray))
                    backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
                }
                capacityOptionTwoButton.apply {
                    setTextColor(resources.getColor(com.markvtls.core.R.color.dark_gray))
                    backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
                }
            }

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.capacity_one->
                    if (checked) {
                        binding.capacityOptionOneButton.apply {
                            setTextColor(resources.getColor(com.markvtls.core.R.color.white))
                            backgroundTintList = ColorStateList.valueOf(resources.getColor(com.markvtls.core.R.color.orange))
                        }
                    }
                R.id.capacity_two ->
                    if (checked) {
                        binding.capacityOptionTwoButton.apply {
                            setTextColor(resources.getColor(com.markvtls.core.R.color.white))
                            backgroundTintList = ColorStateList.valueOf(resources.getColor(com.markvtls.core.R.color.orange))
                        }
                    }
            }
        }
    }
}