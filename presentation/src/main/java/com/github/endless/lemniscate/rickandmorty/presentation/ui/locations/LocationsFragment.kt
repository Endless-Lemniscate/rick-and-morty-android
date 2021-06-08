package com.github.endless.lemniscate.rickandmorty.presentation.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.endless.lemniscate.rickandmorty.databinding.FragmentLocationsBinding
import com.github.endless.lemniscate.rickandmorty.presentation.ui.locations.recycler.LocationsRecyclerAdapter

class LocationsFragment : Fragment() {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LocationsListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()

        val adapter = LocationsRecyclerAdapter()
        binding.locationsRecyclerView.adapter = adapter

        viewModel.locations.observe(viewLifecycleOwner, { locations ->
            adapter.updateList(locations)
        })
    }

}