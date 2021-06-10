package com.github.endless.lemniscate.rickandmorty.presentation.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.github.endless.lemniscate.rickandmorty.databinding.FragmentLocationDetailsBinding

class LocationDetailsFragment : Fragment() {

    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<LocationDetailsFragmentArgs>()
    private val location by lazy { args.location }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = location.name
        binding.type.text = location.type
        binding.dimension.text = location.dimension
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}