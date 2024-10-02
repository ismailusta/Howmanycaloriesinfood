package com.example.besinkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besinkitabi.R
import com.example.besinkitabi.adapter.BesinRecyclerAdapter
import com.example.besinkitabi.databinding.FragmentBesinBinding
import com.example.besinkitabi.databinding.FragmentBesinDetailsBinding
import com.example.besinkitabi.services.BesinAPI
import com.example.besinkitabi.viewmodel.BesinViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BesinFragment : Fragment() {
    private var _binding: FragmentBesinBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BesinViewModel
    private val besinRecyclerAdapter = BesinRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBesinBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BesinViewModel::class.java)
        viewModel.refreshData()
        binding.besinRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.besinRecycler.adapter = besinRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.besinRecycler.visibility = View.GONE
            binding.txtBesinHataMesaji.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refreshInternetData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }
    private fun observeLiveData() {
        viewModel.besinler.observe(viewLifecycleOwner){
            besinRecyclerAdapter.besinlistesiniGuncelle(it)
            binding.besinRecycler.visibility = View.VISIBLE
        }
        viewModel.besinHataMesaji.observe(viewLifecycleOwner){
            if (it){
                binding.txtBesinHataMesaji.visibility = View.VISIBLE
                binding.besinRecycler.visibility = View.GONE
            }else{
                binding.txtBesinHataMesaji.visibility = View.GONE
            }
        }
        viewModel.besinYukleniyor.observe(viewLifecycleOwner){
            if (it){
                binding.besinRecycler.visibility = View.GONE
                binding.txtBesinHataMesaji.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.progressBar.visibility = View.GONE
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}