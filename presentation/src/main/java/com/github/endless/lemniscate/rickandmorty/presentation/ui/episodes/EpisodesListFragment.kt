package com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes

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
import com.github.endless.lemniscate.rickandmorty.databinding.FragmentEpisodesListBinding
import com.github.endless.lemniscate.rickandmorty.domain.models.Episode
import com.github.endless.lemniscate.rickandmorty.presentation.models.toParcelable
import com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes.recycler.ItemClickListener
import com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes.recycler.EpisodesRecyclerAdapter

class EpisodesListFragment: Fragment(), ItemClickListener {

    private var _binding: FragmentEpisodesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EpisodesListViewModel by viewModels()
    private lateinit var locationsAdapter: EpisodesRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentEpisodesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.episodes.observe(viewLifecycleOwner, { episodes ->
            locationsAdapter.updateList(episodes)
        })

        binding.swipeContainer.setOnRefreshListener {
            viewModel.getEpisodes(refresh = true)
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner, { isRefreshing ->
            if(!isRefreshing) {
                binding.swipeContainer.isRefreshing = false
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it) {
                showProgressBar()
                return@observe
            }
            hideProgressBar()

        })

        viewModel.message.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
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
                viewModel.getEpisodes()
                isScrolling = false
            } else {
                binding.episodesRecyclerView.setPadding(0, 0, 0,
                    resources.getDimension(R.dimen._15sdp).toInt())
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
        locationsAdapter = EpisodesRecyclerAdapter(this)
        binding.episodesRecyclerView.apply {
            adapter = locationsAdapter
            addOnScrollListener(this@EpisodesListFragment.scrollListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClicked(episode: Episode) {
        val direction = EpisodesListFragmentDirections
            .actionNavigationEpisodesToEpisodeDetailsFragment(episode.toParcelable())
        findNavController().navigate(direction)
    }

}