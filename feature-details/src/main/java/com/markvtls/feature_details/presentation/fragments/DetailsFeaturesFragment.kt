package com.markvtls.feature_details.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.markvtls.feature_details.databinding.FragmentDetailsFeaturesBinding

/**Details features UI.*/
internal class DetailsFeaturesFragment : Fragment() {

    private var _binding: FragmentDetailsFeaturesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsFeaturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}