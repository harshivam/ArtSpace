package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceUI()
                }
            }
        }
    }


@Preview(showSystemUi = true)
@Composable
fun ArtSpaceUI() {
    renderApp()
}

@Composable
fun Image(modifier: Modifier = Modifier, currentImage: Int, contentScale: ContentScale, titleRes: Int) {
    androidx.compose.foundation.Image(
        painter = painterResource(id = currentImage),
        contentDescription = stringResource(id = titleRes),  // Dynamic content description
        contentScale = contentScale
    )
}

@Composable
fun ArtWorkTitle(modifier: Modifier = Modifier, text: Int) {
    Text(text = stringResource(text), fontSize = 20.sp, fontWeight = FontWeight.Medium)
}
@Composable
fun renderApp() {
    var result by remember { mutableIntStateOf(1) }
    val imageRes = when (result) {
        1 -> R.drawable.image1
        2 -> R.drawable.image4
        3 -> R.drawable.image2
        4 -> R.drawable.guernica
        5 -> R.drawable.monalisa
        6 -> R.drawable.portrait_de_l_artiste_sans_barbe
        7 -> R.drawable.the_persistence_of_memory
        8 -> R.drawable.a_sunday_afternoon_on_the_island_of_la_grande_jatte
        9 -> R.drawable.starry_night
        10 -> R.drawable.the_screean
        11 -> R.drawable.whistler_s_mother
        else -> R.drawable.monalisa
    }
    val titleRes = when (result) {
        1 -> R.string.artwork1
        2 -> R.string.artwork4
        3 -> R.string.artwork2
        4 -> R.string.Guernica
        5 -> R.string.monalisa
        6 -> R.string.portrait_de_l_artiste_sans_barbe
        7 -> R.string.the_persistence_of_memory
        8 -> R.string.starry_night
        9 -> R.string.starry_night
        10 -> R.string.the_scream
        11 -> R.string.artwork1
        else -> R.string.artwork1
    }

    Column(
        modifier = Modifier.height(600.dp).fillMaxWidth(), // Updated height for more consistency
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .height(500.dp) // Set fixed width and height for image container
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(32.dp),
            shadowElevation = 16.dp,
            border = BorderStroke(2.dp, Color.Gray)
        ) {
            Image(
                currentImage = imageRes,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit, // Use "Fit" to maintain aspect ratio without cropping
                titleRes = titleRes
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        ArtWorkTitle(text = titleRes, modifier = Modifier.background(Color.DarkGray))

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    result = if (result > 1) result - 1 else 11
                },
                Modifier
                    .width(120.dp)
                    .padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.previous))
            }
            Button(
                onClick = {
                    result = if (result < 11) result + 1 else 1
                },
                Modifier
                    .width(120.dp)
                    .padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}
