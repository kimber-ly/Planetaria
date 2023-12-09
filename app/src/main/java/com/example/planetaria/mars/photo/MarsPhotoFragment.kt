package com.example.planetaria.mars.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.planetaria.databinding.FragmentMarsPhotoBinding

class MarsPhotoFragment : Fragment() {
    private lateinit var binding: FragmentMarsPhotoBinding
    private lateinit var viewModel: MarsPhotoViewModel
    private lateinit var adapter: MarsPhotoRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMarsPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.marsPhotoRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = MarsPhotoRecyclerAdapter(emptyList())
        binding.marsPhotoRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, MarsPhotoViewModelFactory())[MarsPhotoViewModel::class.java]

        viewModel.photos.observe(viewLifecycleOwner){ marsPhotoDataList ->
            adapter.updateData(marsPhotoDataList)
            binding.marsPhotoProgressBar.visibility = View.GONE
        }
    }

}