package com.example.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
            var showForm by remember { mutableStateOf(false) }

            Laboratorio4Theme(darkTheme = isDarkTheme) {
                AppSurface(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDarkTheme = !isDarkTheme },
                    showForm = showForm,
                    onShowFormChange = { showForm = it }
                )
            }
        }
    }
}

@Composable
fun AppSurface(
    isDarkTheme: Boolean = false,
    onThemeChange: () -> Unit = {},
    showForm: Boolean = false,
    onShowFormChange: (Boolean) -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            if (showForm) {
                FlightForm(
                    modifier = Modifier.padding(innerPadding),
                    isDarkTheme = isDarkTheme,
                    onThemeChange = onThemeChange,
                    onBackToHome = { onShowFormChange(false) }
                )
            } else {
                WelcomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    isDarkTheme = isDarkTheme,
                    onThemeChange = onThemeChange,
                    onStartBooking = { onShowFormChange(true) }
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = false,
    onThemeChange: () -> Unit = {},
    onStartBooking: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo más grande para la pantalla de inicio
        Image(
            painter = painterResource(id = R.drawable.unnamed),
            contentDescription = "Logo de AeroVuelos",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 32.dp)
        )

        // Título principal
        Text(
            text = "Bienvenido a AeroVuelos",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Subtítulo
        Text(
            text = "Tu compañía de confianza para viajar por el mundo",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Tarjeta de características
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "✈️ Vuelos Nacionales e Internacionales",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Paquetes de viaje completos",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Mejores precios garantizados",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Reservas seguras y confiables",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Botón principal para comenzar
        Button(
            onClick = onStartBooking,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Comenzar Reserva",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botones secundarios en fila
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onThemeChange,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Text(
                    text = if (isDarkTheme) "🌙 Tema" else "☀️ Tema",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Button(
                onClick = { /* Acción para información */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Text(
                    text = "ℹ️ Info",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto informativo del tema
        Text(
            text = if (isDarkTheme) "Modo oscuro activado" else "Modo claro activado",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightForm(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = false,
    onThemeChange: () -> Unit = {},
    onBackToHome: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current

    // Estados para los campos del formulario
    var origen by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var fechaIda by remember { mutableStateOf("") }
    var fechaRegreso by remember { mutableStateOf("") }
    var pasajeros by remember { mutableStateOf("1") }
    var claseVuelo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    // Estados para los dropdowns
    var expandedClase by remember { mutableStateOf(false) }
    var expandedPasajeros by remember { mutableStateOf(false) }

    // Listas para los dropdowns
    val clases = listOf("Económica", "Premium Economy", "Ejecutiva", "Primera Clase")
    val numerosPasajeros = listOf("1", "2", "3", "4", "5", "6", "7", "8")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header del formulario
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onBackToHome,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text("← Volver")
            }

            Text(
                text = "Reserva de Vuelo",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                onClick = onThemeChange,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(if (isDarkTheme) "🌙" else "☀️")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Logo más pequeño en el formulario
        Image(
            painter = painterResource(id = R.drawable.unnamed),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier
                .size(80.dp)
                .padding(bottom = 16.dp)
        )

        // FORMULARIO DE BÚSQUEDA DE VUELOS
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Búsqueda de Vuelos",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                // Fila para Origen y Destino
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = origen,
                        onValueChange = { origen = it },
                        label = { Text("Ciudad Origen") },
                        placeholder = { Text("Ej: Lima") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = destino,
                        onValueChange = { destino = it },
                        label = { Text("Ciudad Destino") },
                        placeholder = { Text("Ej: Madrid") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                }

                // Fila para Fechas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = fechaIda,
                        onValueChange = { fechaIda = it },
                        label = { Text("Fecha Ida") },
                        placeholder = { Text("DD/MM/AAAA") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = fechaRegreso,
                        onValueChange = { fechaRegreso = it },
                        label = { Text("Fecha Regreso") },
                        placeholder = { Text("DD/MM/AAAA") },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                }

                // Fila para Pasajeros y Clase
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Dropdown para Pasajeros
                    ExposedDropdownMenuBox(
                        expanded = expandedPasajeros,
                        onExpandedChange = { expandedPasajeros = !expandedPasajeros },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = pasajeros,
                            onValueChange = {},
                            label = { Text("Pasajeros") },
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPasajeros) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = expandedPasajeros,
                            onDismissRequest = { expandedPasajeros = false }
                        ) {
                            numerosPasajeros.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text("$item pasajero${if (item != "1") "s" else ""}") },
                                    onClick = {
                                        pasajeros = item
                                        expandedPasajeros = false
                                    }
                                )
                            }
                        }
                    }

                    // Dropdown para Clase
                    ExposedDropdownMenuBox(
                        expanded = expandedClase,
                        onExpandedChange = { expandedClase = !expandedClase },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = claseVuelo,
                            onValueChange = {},
                            label = { Text("Clase") },
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedClase) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = expandedClase,
                            onDismissRequest = { expandedClase = false }
                        ) {
                            clases.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {
                                        claseVuelo = item
                                        expandedClase = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // FORMULARIO DE INFORMACIÓN PERSONAL
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Información Personal",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre Completo") },
                    placeholder = { Text("Ej: Juan Pérez") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo Electrónico") },
                    placeholder = { Text("ejemplo@correo.com") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    placeholder = { Text("+51 123 456 789") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // BOTONES DE ACCIÓN
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    // Acción para limpiar formulario
                    origen = ""
                    destino = ""
                    fechaIda = ""
                    fechaRegreso = ""
                    pasajeros = "1"
                    claseVuelo = ""
                    nombre = ""
                    email = ""
                    telefono = ""
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Text("Limpiar", style = MaterialTheme.typography.bodyLarge)
            }

            Button(
                onClick = {
                    // Acción para buscar vuelos
                    // Aquí iría la lógica de búsqueda
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Buscar Vuelos", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

// Previews para testing
@Preview(showBackground = true, name = "Pantalla Inicio - Claro")
@Composable
fun LightWelcomePreview() {
    Laboratorio4Theme(darkTheme = false) {
        WelcomeScreen(
            isDarkTheme = false,
            onThemeChange = {},
            onStartBooking = {}
        )
    }
}

@Preview(showBackground = true, name = "Pantalla Inicio - Oscuro")
@Composable
fun DarkWelcomePreview() {
    Laboratorio4Theme(darkTheme = true) {
        WelcomeScreen(
            isDarkTheme = true,
            onThemeChange = {},
            onStartBooking = {}
        )
    }
}

@Preview(showBackground = true, name = "Formulario - Claro")
@Composable
fun LightFormPreview() {
    Laboratorio4Theme(darkTheme = false) {
        FlightForm(
            isDarkTheme = false,
            onThemeChange = {},
            onBackToHome = {}
        )
    }
}

@Preview(showBackground = true, name = "Formulario - Oscuro")
@Composable
fun DarkFormPreview() {
    Laboratorio4Theme(darkTheme = true) {
        FlightForm(
            isDarkTheme = true,
            onThemeChange = {},
            onBackToHome = {}
        )
    }
}