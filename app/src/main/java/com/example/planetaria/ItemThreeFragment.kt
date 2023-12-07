package com.example.planetaria

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ItemThreeFragment : Fragment() {

    companion object {
        fun newInstance() = ItemThreeFragment()
    }

    private lateinit var viewModel: ItemThreeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_three, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemThreeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}