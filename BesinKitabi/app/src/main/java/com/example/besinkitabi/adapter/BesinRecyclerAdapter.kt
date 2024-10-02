package com.example.besinkitabi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.besinkitabi.databinding.BesinRecyclerRowBinding
import com.example.besinkitabi.model.Besin
import com.example.besinkitabi.utils.gorselIndir
import com.example.besinkitabi.utils.placeholderYap
import com.example.besinkitabi.view.BesinFragmentDirections
import com.example.besinkitabi.viewmodel.BesinViewModel

class BesinRecyclerAdapter(val besinListesi : ArrayList<Besin>) : RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>(){
    class BesinViewHolder(val binding: BesinRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val binding = BesinRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BesinViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }
    fun besinlistesiniGuncelle(yenilist : List<Besin>){
        besinListesi.clear()
        besinListesi.addAll(yenilist)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        holder.binding.txtrowbesinadi.text=besinListesi[position].besin_adi
        holder.binding.txtrowbesindetay.text=besinListesi[position].besin_kalori
        holder.itemView.setOnClickListener {
            val action  = BesinFragmentDirections.actionBesinFragment2ToBesinDetailsFragment2(besinListesi[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView1.gorselIndir(besinListesi[position].besin_gorsel,
            placeholderYap(holder.itemView.context)
        )
    }

}