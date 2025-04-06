package testing.jetpack.compose.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import testing.jetpack.compose.R
import testing.jetpack.compose.ui.theme.JetPackComposeTheme

@Composable
fun SplashScreen(
    onSplashScreenFinished: () -> Unit = {}
) {
    var imageSaturation by remember { mutableFloatStateOf(0f) }

    val animateSaturation = animateFloatAsState(
        targetValue = imageSaturation,
        animationSpec = tween(1500), label = "Countdown",
        finishedListener = {
            imageSaturation = if (imageSaturation == 0f) 1f else 0f
        }
    )

    LaunchedEffect(Unit) {
        imageSaturation = 1f
    }

    val imageModifier = Modifier
        .size(150.dp)
        .clip(RoundedCornerShape(16.dp))
        .border(
            BorderStroke(
                1.dp,
                JetPackComposeTheme.colors.primary
            ),
            RoundedCornerShape(16.dp)
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = JetPackComposeTheme.colors.backGround01)
            .safeDrawingPadding()
    ) {

        Image(
            painter = painterResource(R.drawable.test_image_3),
            contentDescription = "AppIcon",
            contentScale = ContentScale.Crop,
            modifier = imageModifier.align(Alignment.Center),
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToSaturation(
                    animateSaturation.value
                )
            })
        )

        Button(
            onClick = { onSplashScreenFinished.invoke() },
            colors = ButtonColors(
                JetPackComposeTheme.colors.primary,
                JetPackComposeTheme.colors.inkButton,
                JetPackComposeTheme.colors.primary,
                JetPackComposeTheme.colors.primary
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
                .fillMaxWidth(fraction = 0.5f)
                .aspectRatio(3.5f)
        ) {
            Text("Start")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    JetPackComposeTheme {
        SplashScreen()
    }
}