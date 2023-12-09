package com.example.planetaria.epic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planetaria.databinding.FragmentEpicBinding


class EpicFragment : Fragment() {
    private lateinit var binding: FragmentEpicBinding
    private lateinit var viewModel: EpicViewModel
    private lateinit var adapter: EpicRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEpicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EpicRecyclerAdapter(emptyList())
        binding.epicRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.epicRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, EpicViewModelFactory())[EpicViewModel::class.java]

        viewModel.epicData.observe(viewLifecycleOwner){ epicDataList ->
            adapter.updateData(epicDataList)

            binding.epicProgressBar.visibility = View.GONE
        }
    }

}