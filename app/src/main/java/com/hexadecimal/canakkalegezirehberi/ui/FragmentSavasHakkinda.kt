package com.hexadecimal.canakkalegezirehberi.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.hexadecimal.canakkalegezirehberi.R
import kotlinx.android.synthetic.main.fragment_savas_hakkinda.*


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-22 - 04:46

class FragmentSavasHakkinda : Fragment() {

    private lateinit var player: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_savas_hakkinda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savas_Detayı_Txt.movementMethod = ScrollingMovementMethod()

        player = MediaPlayer.create(context,R.raw.canakkaleses)

        savas_Hakk_Record.setOnClickListener {
            if (player.isPlaying) {
                player.pause()
                handler.removeCallbacks(runnable)
            } else {
                player.start()
                initializeSeekBar()
            }

            savas_Hakkinda_SeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    if (b) {
                        player.seekTo(i * 1000)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                }
            })
        }
    }

    // we use the companion object to access this class's members with only the class name
    // it is like static in java, companion object is a singleton
    companion object {
        fun newInstance() = FragmentSavasHakkinda()
    }
    private fun initializeSeekBar() {
        savas_Hakkinda_SeekBar.max = player.seconds

        runnable = Runnable {
            savas_Hakkinda_SeekBar.progress = player.currentSeconds

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    val MediaPlayer.seconds:Int
        get() {
            return this.duration / 1000
        }


    // Extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition/1000
        }

}