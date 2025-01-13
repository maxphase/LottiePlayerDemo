package com.example.lottieplayerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import java.io.IOException

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // Change background to light grey
                    color = MaterialTheme.colorScheme.surfaceVariant // This is a built-in light grey color
                ) {
                    // Add WindowInsets.safeDrawing to handle camera cutout
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding() // This handles status bar and navigation bar
                            .windowInsetsPadding(
                                WindowInsets.safeDrawing // This handles camera cutout
                            )
                    ) {
                        LottiePlayer()
                    }
                }
            }
        }
    }
}

@Composable
fun LottiePlayer() {
    val context = LocalContext.current
    var animations by remember { mutableStateOf(listOf<String>()) }
    var currentAnimationIndex by remember { mutableStateOf(0) }
    var isPlaying by remember { mutableStateOf(true) }

    // Load animations list
    LaunchedEffect(Unit) {
        try {
            animations = context.assets.list("animations")?.toList() ?: emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    val composition by rememberLottieComposition(
        spec = if (animations.isNotEmpty()) {
            LottieCompositionSpec.Asset("animations/${animations.getOrNull(currentAnimationIndex)}")
        } else {
            LottieCompositionSpec.Asset("animations/default.json")
        }
    )

    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition, isPlaying) {
        if (isPlaying) {
            lottieAnimatable.animate(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        } else {
            lottieAnimatable.snapTo(progress = lottieAnimatable.progress)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = animations.getOrNull(currentAnimationIndex) ?: "default.json",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Animation view
        LottieAnimation(
            composition = composition,
            progress = { lottieAnimatable.progress },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        // Controls
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (animations.isNotEmpty()) {
                        currentAnimationIndex = (currentAnimationIndex - 1 + animations.size) % animations.size
                    }
                }
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    isPlaying = !isPlaying
                }
            ) {
                Text(if (isPlaying) "Pause" else "Play")
            }

            Button(
                onClick = {
                    if (animations.isNotEmpty()) {
                        currentAnimationIndex = (currentAnimationIndex + 1) % animations.size
                    }
                }
            ) {
                Text("Next")
            }
        }
    }
}