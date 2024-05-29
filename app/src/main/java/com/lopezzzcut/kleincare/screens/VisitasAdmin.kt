package com.lopezzzcut.kleincare.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lopezzzcut.kleincare.BottomBar
import com.lopezzzcut.kleincare.BottomBar2
import com.lopezzzcut.kleincare.R
import com.lopezzzcut.kleincare.TopBar
import com.lopezzzcut.kleincare.TopBar2
import com.lopezzzcut.kleincare.ui.theme.Poppins
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitasAdmin(navController: NavController,context: Context) {

    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                VisitasAdmincontent(navController = navController,context)
            }

        },
        topBar = {
            Column() {
                Spacer(modifier = Modifier.height(32.dp))
                TopBar2(navController = navController)
            }        },
        bottomBar= {
            Column {

            Row(modifier= Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                BottomBar2(navController = navController)
            }
            Spacer(modifier =Modifier.height(32.dp))
        } }
        )


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirVisita(nombreDoctor: MutableState<String>, tipo: MutableState<String>, fecha: MutableState<String>, añadirVisita: MutableState<Boolean>) {

    AlertDialog(
        containerColor = Color(0xFFEBEBEB),
        onDismissRequest = { /*TODO*/ },
        confirmButton = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),

        title = {
            var confirmar by remember { mutableStateOf(true) }
            if (confirmar) {
                Column(modifier = Modifier.fillMaxWidth(),) {
                    Text(
                        text = "Añadir Cita",
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(16.dp)
                    )
                    OutlinedTextField(

                        value = nombreDoctor.value,
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        onValueChange = { nombreDoctor.value = it },
                        placeholder = {
                            Text(
                                "Nombre del doctor", fontFamily = Poppins,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        )
                    OutlinedTextField(
                        value = tipo.value,
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        onValueChange = { tipo.value = it },
                        placeholder = {
                            Text(
                                "Tipo de consulta", fontFamily = Poppins,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        )
                    OutlinedTextField(
                        value = fecha.value,
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        onValueChange = { fecha.value = it },
                        placeholder = {
                            Text(
                                "Fecha y hora", fontFamily = Poppins,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        )

                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        placeholder = {
                            Text(
                                "Imagen", fontFamily = Poppins,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cameraicon),
                                    contentDescription = "Seleccionar imagen",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    )
                    Button(
                        onClick = { confirmar = false }, shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Añadir",
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                    Button(
                        onClick = { añadirVisita.value = false },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(
                            text = "Cancelar",
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Agregar cita",
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "¿Desea agregar la cita?",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = { añadirVisita.value = false },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Confirmar",
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                    Button(
                        onClick = { confirmar = true }, shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(
                            text = "Cancelar",
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                }
            }
        },
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VisitasAdmincontent(navController: NavController,context: Context){
    var nombreDoctor = remember { mutableStateOf("") }
    var tipo = remember { mutableStateOf("") }
    var fecha = remember { mutableStateOf("") }
    var añadirVisita = remember { mutableStateOf(false) }
    val citas = listOf(
        Cita(2, R.drawable.doctorejemplo1, "Dr. Smith", "Consulta general", "10:00", "Hospital Central"),
        Cita(6, R.drawable.doctoraejemplo2, "Dra. Johnson", "Consulta pediátrica", "11:00", "Clínica Salud"),
        // Añade más citas aquí
        Cita(10, R.drawable.doctorejemplo3, "Dr. Jimenez", "Consulta de especialidad", "17:00", "Clínica de Fisioterapia Villaviciosa"),
        Cita(14, R.drawable.doctoraejemplo2, "Dra. Johnson", "Consulta de especialidad", "11:00", "Clínica Salud"),
        Cita(18, R.drawable.doctorejemplo1, "Dr. Smith", "Consulta de urgencia", "9:40", "Hospital Central"),
    )
    var selectedCita by remember { mutableStateOf<Cita?>(citas.firstOrNull()) }

    var verDias by remember { mutableStateOf(true) }
    var icono = if (verDias) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        item {

            Text(
                text = "Proximas citas",
                fontFamily = Poppins,
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)

            )
            Text(
                text = "Presione el día para ver detalles",
                fontFamily = Poppins,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)

            )


            CalendarView(
                modifier = Modifier.fillMaxWidth(),
                currentMonth = YearMonth.now(),
                onDateSelected = { cita ->
                    selectedCita = cita
                },
                citas = citas,
                highlightedDays = citas.map { it.dia }
            )

        }
        item{


            selectedCita?.let {
                ProximasCitasDetallesAdmin(
                    imagen = it.imagen,
                    doctor = it.doctor,
                    consulta = it.consulta,
                    hora = it.hora,
                    lugar = it.lugar,
                    context = context
                )
            }
        }
        item {
            Button(
                onClick = { añadirVisita.value = true },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffE1CBA3)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = "Añadir visita",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

            }
        }
        item {
            if (añadirVisita.value) {
                AñadirVisita(nombreDoctor = nombreDoctor, tipo = tipo, fecha = fecha, añadirVisita = añadirVisita)
            }
        }







    }
}


@SuppressLint("QueryPermissionsNeeded")
@Composable
fun ProximasCitasDetallesAdmin(imagen: Int, doctor: String, consulta: String,hora: String,lugar: String,context: Context )  {
    var editarVisita = remember { mutableStateOf(false) }
    var doctorMutable = remember { mutableStateOf(doctor) }
    var tipoMutable = remember { mutableStateOf(consulta) }
    var horaMutable = remember { mutableStateOf(hora) }
    var lugarMutable = remember { mutableStateOf(lugar) }
    val colorTarjeta =
        when (doctor) {
            "Dr. Smith" -> Color(0xfff7f1e1)
            "Dra. Johnson" -> Color(0xffeff5e9)
            "Dr. Jimenez" -> Color(0xffefe4e0)
            else -> Color(0xffFFC061)
        }
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor =colorTarjeta,
            contentColor = Color.Black
        ), modifier = Modifier.padding(start = 16.dp,end = 16.dp, bottom = 8.dp,top = 8.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), // Añadir padding para evitar que los elementos se peguen a los bordes
            verticalArrangement = Arrangement.Center, // Distribuir el espacio entre elementos
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imagen), contentDescription = "",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                    Text(
                        text = doctor,
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(4.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        Image(
                            painter = painterResource(id = R.drawable.farmaciaicon),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(14.dp)
                        )
                        Text(
                            text = consulta,
                            fontFamily = Poppins,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                    }
                }
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start,modifier =Modifier.padding(start=4.dp)) {
                    Text(
                        text = hora,
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(4.dp)
                    )



            }
                Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start,modifier =Modifier.fillMaxHeight()) {

                    IconButton(
                        onClick = {
                            editarVisita.value = true
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(32.dp),
                        content = {
                            Image(
                                painter = painterResource(id = R.drawable.editicon),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(32.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

            }

            Button(
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 7.dp),
                onClick = {
                    abrirGoogleMaps(context, lugar)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = lugar,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center, // Center text within the button


                    )

                }

            }

        }
        if (editarVisita.value) {
            EditarVisita(doctor = doctorMutable, tipo = tipoMutable, hora = horaMutable, lugar = lugarMutable, editarVisita = editarVisita)
        }
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarVisita(doctor: MutableState<String>,
                 tipo: MutableState<String>,
                 hora:MutableState<String>,
                 lugar: MutableState<String>,
                 editarVisita: MutableState<Boolean>,
                ){

    AlertDialog(
        containerColor = Color(0xFFEBEBEB),
        onDismissRequest = { /*TODO*/ },
        confirmButton = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        title = {
            var confirmar by remember { mutableStateOf(true) }
            if (confirmar) {
                Column(modifier = Modifier.fillMaxWidth(),) {
                    Text(
                        text = "Editar Cita",
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(16.dp)
                    )

                    OutlinedTextField(
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        value = doctor.value ,
                        onValueChange = { doctor.value  = it},
                        placeholder = {
                            Text(
                                "Nombre", fontFamily = Poppins,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        )
                    OutlinedTextField(
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        value = lugar.value ,
                        onValueChange = { lugar.value  = it},
                        placeholder = {
                            Text(
                                "Cantidad", fontFamily = Poppins,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        )
                    OutlinedTextField(
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        value = tipo.value ,
                        onValueChange = { tipo.value  = it},
                        placeholder = {
                            Text(
                                "Tipo de consulta", fontFamily = Poppins,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        )

                    OutlinedTextField(
                        value = hora.value,
                        onValueChange = { hora.value },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        placeholder = {
                            Text(
                                "Hora", fontFamily = Poppins,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(8.dp)
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.houricon),
                                    contentDescription = "Seleccionar hora",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    )
                    Button(
                        onClick = { confirmar = false }, shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Editar",
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                    Button(
                        onClick = { editarVisita.value  = false}, shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(
                            text = "Cancelar",
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Editar Cita",
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "¿Desea editar la cita?",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = { editarVisita.value = false }, shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Confirmar",
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                    Button(
                        onClick = { confirmar = true }, shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(
                            text = "Cancelar",
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )

                    }
                }
            }
        },
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun VisitassAdminPreview() {
    VisitasAdmin(rememberNavController(), LocalContext.current)
}