package com.example.sevenways

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sevenways.ui.theme.SevenwaysTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SevenwaysTheme {
                Scaffold(
                    topBar = {  // Change this from bottomBar to topBar
                        TopAppBar(
                            title = {
                                Text(
                                    text = "7 Ways to Be Successful",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            },
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                ) { innerPadding ->
                    TipsList(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun TipsList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tips) { tip ->
            TipCard(tip = tip)
        }
    }
}

data class Tip(val title: String, val description: String, val imageRes: Int)

val tips = listOf(
    Tip("Adapt", "Fleksibel dalam menghadapi perubahan.", R.drawable.adapt),
    Tip("Discipline", "Kedisiplinan adalah kunci.", R.drawable.dicipline),
    Tip("Health", "Prioritaskan kesehatan Anda.", R.drawable.health),
    Tip("Time", "Atur waktu dengan bijaksana.", R.drawable.time),
    Tip("Take Action", "Berani memulai adalah langkah awal.", R.drawable.take),
    Tip("Focus", "Fokus meningkatkan produktivitas.", R.drawable.focus),
    Tip("Goals", "Tujuan memberi arah hidup.", R.drawable.goals)
)

@Composable
fun TipCard(tip: Tip, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        onClick = { expanded = !expanded }  // Toggle expanded state on click
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = tip.imageRes),
                contentDescription = tip.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tip.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            AnimatedVisibility(visible = expanded, enter = fadeIn(animationSpec = tween(300)), exit = fadeOut(animationSpec = tween(300))) {
                Text(
                    text = tip.description,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
