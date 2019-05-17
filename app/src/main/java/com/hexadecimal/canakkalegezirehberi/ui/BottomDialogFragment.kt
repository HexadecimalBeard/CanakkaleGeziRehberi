package com.hexadecimal.canakkalegezirehberi.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hexadecimal.canakkalegezirehberi.R


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-17 - 02:41


open class BottomDialogFragment : BottomSheetDialogFragment() {


    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

}
