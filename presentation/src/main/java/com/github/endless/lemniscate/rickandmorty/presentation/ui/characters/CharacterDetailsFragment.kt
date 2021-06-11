package com.github.endless.lemniscate.rickandmorty.presentation.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.github.endless.lemniscate.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.github.endless.lemniscate.rickandmorty.presentation.App
import javax.inject.Inject

class CharacterDetailsFragment : Fragment() {

    @Inject
    lateinit var glide: RequestManager

    init {
        App.applicationComponent.inject(this)
    }

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<CharacterDetailsFragmentArgs>()
    private val character by lazy { args.character }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = character.name
        binding.species.text = character.species
        binding.status.text = character.status
        binding.gender.text = character.gender

        glide.load(character.imageUrl)
            .into(binding.image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}