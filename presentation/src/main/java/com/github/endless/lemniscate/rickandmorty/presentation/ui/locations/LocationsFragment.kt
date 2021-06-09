package com.github.endless.lemniscate.rickandmorty.presentation.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.endless.lemniscate.rickandmorty.R
import com.github.endless.lemniscate.rickandmorty.databinding.FragmentLocationsBinding
import com.github.endless.lemniscate.rickandmorty.domain.models.Location
import com.github.endless.lemniscate.rickandmorty.presentation.ui.locations.recycler.ItemClickListener
import com.github.endless.lemniscate.rickandmorty.presentation.ui.locations.recycler.LocationsRecyclerAdapter

class LocationsFragment: Fragment(), ItemClickListener {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LocationsListViewModel by viewModels()
    private lateinit var locationsAdapter: LocationsRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.locations.observe(viewLifecycleOwner, { locations ->
            locationsAdapter.updateList(locations)
        })

        binding.swipeContainer.setOnRefreshListener {
            viewModel.getLocations(refresh = true)
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner, { isRefreshing ->
            if(!isRefreshing) {
                binding.swipeContainer.isRefreshing = false
            }
        })


        viewModel.message.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideProgressBar() {
        //paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        //paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20 //QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.getLocations()
                isScrolling = false
            } else {
                binding.locationsRecyclerView.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupRecyclerView() {
        locationsAdapter = LocationsRecyclerAdapter(this)
        binding.locationsRecyclerView.apply {
            adapter = locationsAdapter
            addOnScrollListener(this@LocationsFragment.scrollListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClicked(item: Location) {
        //TODO add bundle
        findNavController().navigate(R.id.action_navigation_locations_to_locationDetailsFragment)
    }

}