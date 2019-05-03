package com.hexadecimal.canakkalegezirehberi.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hexadecimal.canakkalegezirehberi.model.MonumentsEntity


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-01 - 16:07

class MonumentsAdapter(
    var monumentsList: List<MonumentsEntity>? = null,
    val onItemClickListener: (monumentsEntity: MonumentsEntity) -> Unit
) : RecyclerView.Adapter<MonumentsListViewHolder>() {

    // kullanacagimiz ui verdik
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonumentsListViewHolder =
        MonumentsListViewHolder(parent)

    // listelenecek item sayisini verdik
    // data source un size i
    override fun getItemCount(): Int {
        monumentsList?.let {
            return it.size
        }
        return 0
    }

    // data yi ilgili view lara bağlayan yer
    // icinde çizilen pozisyondaki item row un degeri var
    override fun onBindViewHolder(holder: MonumentsListViewHolder, position: Int) {

        monumentsList?.let {
            holder.bind(it[position], onItemClickListener)
        }
    }

    fun setNewMonumentsList(monumentsList: List<MonumentsEntity>) {
        this.monumentsList = monumentsList
        notifyDataSetChanged()
    }

}