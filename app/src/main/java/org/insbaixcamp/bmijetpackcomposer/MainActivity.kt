package org.insbaixcamp.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.insbaixcamp.bmijetpackcomposer.ui.theme.BMIJetpackComposerTheme

// Colors
val FonsNit = Color(0xFF0F172A)
val FonsTargeta = Color(0xFF1E293B)
val BlauPrincipal = Color(0xFF38BDF8)
val VerdExit = Color(0xFF4ADE80)
val PurpuraBoto = Color(0xFF8B5CF6)
val TextGris = Color(0xFF94A3B8)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIJetpackComposerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = FonsNit
                ) { innerPadding ->
                    BMIScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BMIScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var pes by remember { mutableIntStateOf(75) }
    var alcada by remember { mutableIntStateOf(175) }
    var bmi by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(FonsNit)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Títol
            Text(
                text = "Calculadora IMC",
                color = BlauPrincipal,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
            // Text Nom
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nom de l'usuari") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BlauPrincipal,
                    unfocusedBorderColor = TextGris,
                    focusedLabelColor = BlauPrincipal,
                    unfocusedLabelColor = TextGris,
                    focusedContainerColor = FonsTargeta,
                    unfocusedContainerColor = FonsTargeta
                )
            )
            // Pes
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, PurpuraBoto, RoundedCornerShape(16.dp))
                    .background(FonsTargeta, RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Pes", color = TextGris, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$pes kg",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Button(
                            onClick = { if (pes > 1) pes-- },
                            colors = ButtonDefaults.buttonColors(containerColor = PurpuraBoto),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { pes++ },
                            colors = ButtonDefaults.buttonColors(containerColor = PurpuraBoto),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Alçada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BlauPrincipal, RoundedCornerShape(16.dp))
                    .background(FonsTargeta, RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Alçada", color = TextGris, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$alcada cm",
                        color = BlauPrincipal,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Slider(
                        value = alcada.toFloat(),
                        onValueChange = { alcada = it.toInt() },
                        valueRange = 100f..230f,
                        modifier = Modifier.fillMaxWidth(),
                        colors = SliderDefaults.colors(
                            thumbColor = BlauPrincipal,
                            activeTrackColor = BlauPrincipal,
                            inactiveTrackColor = FonsNit
                        )
                    )
                }
            }

            // Càlcul
            Button(
                onClick = {
                    val alcadaMetres = alcada.toFloat() / 100f
                    bmi = pes / (alcadaMetres * alcadaMetres)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BlauPrincipal)
            ) {
                Text(
                    text = "Calcular IMC",
                    color = FonsNit,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Resultat
            if (bmi > 0f) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, VerdExit, RoundedCornerShape(16.dp))
                        .background(FonsTargeta, RoundedCornerShape(16.dp))
                        .padding(20.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (name.isNotBlank()) "Resultat per a $name" else "El teu resultat",
                            color = TextGris,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "%.1f".format(bmi),
                            color = VerdExit,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BMIScreenPreview() {
    BMIJetpackComposerTheme {
        BMIScreen()
    }
}