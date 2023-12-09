package com.example.planetaria.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.planetaria.R
import com.example.planetaria.databinding.FragmentApodBinding
import com.squareup.picasso.Picasso


class ApodFragment : Fragment() {

    private lateinit var binding: FragmentApodBinding
    private lateinit var viewModel: ApodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ApodViewModelFactory())[ApodViewModel::class.java]

        viewModel.apodData.observe(viewLifecycleOwner) { data ->
            binding.apodTitleText.text = data.title
            binding.apodDateText.text = data.date
            Picasso.get()
                .load(data.url)
                .placeholder(R.drawable.baseline_image_24)
                .into(binding.apodImg)
            binding.apodExplanation.text = data.explanation
        }
    }

}