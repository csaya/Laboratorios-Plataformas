package com.example.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio4.ui.theme.Laboratorio4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            Laboratorio4Theme(darkTheme = isDarkTheme) {
                AppSurface(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDarkTheme = !isDarkTheme }
                )
            }
        }
    }
}

@Composable
fun AppSurface(
    isDarkTheme: Boolean = false,
    onThemeChange: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppContent(
                modifier = Modifier.padding(innerPadding),
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }
    }
}

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = false,
    onThemeChange: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // IMAGEN AGREGADA AQUÍ - usa la imagen por defecto del proyecto
        Image(
            painter = painterResource(id = R.drawable.unnamed),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 24.dp)
        )

        // Título principal
        Text(
            text = "Bienvenido a la App AeroVuelos",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .widthIn(max = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) 600.dp else 300.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        // Indicador del tema actual
        Text(
            text = if (isDarkTheme) "Tema actual: Oscuro 🌙" else "Tema actual: Claro ☀️",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Texto descriptivo
        Text(
            text = " Esta aplicación es una prueba para el inicio de aplicación de venta de vuelos Nacionales e Internacionales",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .widthIn(max = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) 700.dp else 400.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        // Botón para cambiar tema
        Button(
            onClick = onThemeChange,
            modifier = Modifier.widthIn(min = 150.dp)
        ) {
            Text(
                text = if (isDarkTheme) "Cambiar a Claro" else "Cambiar a Oscuro",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

// Previews para testing
@Preview(showBackground = true, name = "Modo Claro - Teléfono")
@Composable
fun LightPhonePreview() {
    Laboratorio4Theme(darkTheme = false) {
        AppSurface(
            isDarkTheme = false,
            onThemeChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Modo Oscuro - Teléfono")
@Composable
fun DarkPhonePreview() {
    Laboratorio4Theme(darkTheme = true) {
        AppSurface(
            isDarkTheme = true,
            onThemeChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Modo Claro - Tableta", widthDp = 600, heightDp = 800)
@Composable
fun LightTabletPreview() {
    Laboratorio4Theme(darkTheme = false) {
        AppSurface(
            isDarkTheme = false,
            onThemeChange = {}
        )
    }
}

@Preview(showBackground = true, name = "Modo Oscuro - Tableta", widthDp = 600, heightDp = 800)
@Composable
fun DarkTabletPreview() {
    Laboratorio4Theme(darkTheme = true) {
        AppSurface(
            isDarkTheme = true,
            onThemeChange = {}
        )
    }
}