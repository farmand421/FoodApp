package com.farmand.foodapp.ui.detail.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.farmand.foodapp.databinding.ActivityPlayerBinding
import com.farmand.foodapp.utils.VIDEO_ID
import com.farmand.foodapp.utils.YOUTUBE_API_KEY
import com.farmand.foodapp.utils.showSnackBar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class PlayerActivity : YouTubeBaseActivity() {
    //Binding
    lateinit var binding: ActivityPlayerBinding

    //Other
    private var videoId = ""
    private lateinit var player : YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        //FollScreen
        @Suppress("DEPRECATION")
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        //GetData
        videoId = intent.getStringExtra(VIDEO_ID).toString()
        //InitViews
        binding.apply {
            val listener = object :YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer,
                    p2: Boolean
                ) {
                    player = p1
                    player.loadVideo(videoId)
                    player.play()
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    root.showSnackBar("Error")
                }
            }
            videoPlayer.initialize(YOUTUBE_API_KEY,listener)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}