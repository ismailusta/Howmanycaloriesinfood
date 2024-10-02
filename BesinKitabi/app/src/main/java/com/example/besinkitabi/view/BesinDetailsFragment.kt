package com.example.besinkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.besinkitabi.R
import com.example.besinkitabi.databinding.FragmentBesinDetailsBinding
import com.example.besinkitabi.utils.gorselIndir
import com.example.besinkitabi.utils.placeholderYap
import com.example.besinkitabi.viewmodel.BesinDetailsViewModel

class BesinDetailsFragment : Fragment() {
    private var _binding: FragmentBesinDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BesinDetailsViewModel
    var besinId =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBesinDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BesinDetailsViewModel::class.java)
        arguments?.let {
            besinId = BesinDetailsFragmentArgs.fromBundle(it).besinId
        }
        viewModel.roomVerisiniAl(besinId)
        observeLiveData()

    }
    private fun observeLiveData() {
        viewModel.besinLiveData.observe(viewLifecycleOwner){
            if (it != null) {
                binding.textbesinAdi.text = it.besin_adi
                binding.textbesinKalori.text = it.besin_kalori
                binding.textbesinKarbonhidrat.text = it.besin_karbonhidrat
                binding.textbesinProtein.text = it.besin_protein
                binding.textbesinYag.text = it.besin_yag
                binding.imageView.gorselIndir(it.besin_gorsel, placeholderYap(requireContext()))
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}