package com.hexadecimal.canakkalegezirehberi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hexadecimal.canakkalegezirehberi.R
import com.hexadecimal.canakkalegezirehberi.model.MonumentsEntity


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-01 - 15:56


class MonumentsListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.adapter_item_monuments_list, parent, false)
) {

    private val anitResim: ImageView
    private val anitIsmi: TextView
    private val anitAciklama: TextView
    private val anitKonum: TextView
    private val anitCheckBox: CheckBox

    init {
        anitResim = itemView.findViewById(R.id.monument_ImageView)
        anitIsmi = itemView.findViewById(R.id.monument_TitleTxt)
        anitAciklama = itemView.findViewById(R.id.monument_DescriptionTxt)
        anitKonum = itemView.findViewById(R.id.monument_LocationTxt)
        anitCheckBox = itemView.findViewById(R.id.monumentList_CheckBox)

    }

    fun bind(
        monumentsEntity: MonumentsEntity,
        onItemClickListener: (monumentsEntity: MonumentsEntity) -> Unit
    ) {

        anitResim.setBackgroundResource(monumentsEntity.anitResim)
        anitIsmi.text = monumentsEntity.anitIsmi
        anitAciklama.text = monumentsEntity.anitAciklama
        anitKonum.text = monumentsEntity.anitKonum

        itemView.setOnClickListener {

            onItemClickListener(monumentsEntity)


            // Todo; do the checkbox process in here
        }
    }

}