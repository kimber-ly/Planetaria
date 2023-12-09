package com.example.planetaria.mars.realEstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planetaria.databinding.FragmentMarsRealEstateBinding

class RealEstateFragment : Fragment() {
    private lateinit var binding: FragmentMarsRealEstateBinding
    private lateinit var viewModel: RealEstateViewModel
    private lateinit var adapter: RealEstateRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarsRealEstateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.realEstateRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = RealEstateRecyclerAdapter(emptyList())
        binding.realEstateRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            RealEstateViewModelFactory())[RealEstateViewModel::class.java]

        viewModel.data.observe(viewLifecycleOwner){ realEstateDataList ->
            adapter.updateData(realEstateDataList)

            binding.realEstateProgressBar.visibility = View.GONE
        }
    }

}