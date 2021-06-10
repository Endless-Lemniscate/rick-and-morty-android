package com.github.endless.lemniscate.rickandmorty.presentation.ui.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.github.endless.lemniscate.rickandmorty.databinding.FragmentEpisodeDetailsBinding

class EpisodeDetailsFragment : Fragment() {

    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EpisodeDetailsFragmentArgs>()
    private val episode by lazy { args.episode }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = episode.name
        binding.airDate.text = episode.air_date
        binding.episode.text = episode.episode
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}