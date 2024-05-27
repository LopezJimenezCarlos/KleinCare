package com.lopezzzcut.kleincare.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun Visitas(navController: NavController,context: Context) {

    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Visitascontent(navController = navController,context)
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
                BottomBar(navController = navController)
            }
            Spacer(modifier =Modifier.height(32.dp))
        } }
        )


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Visitascontent(navController: NavController,context: Context){
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
                ProximasCitasDetalles(
                    imagen = it.imagen,
                    doctor = it.doctor,
                    consulta = it.consulta,
                    hora = it.hora,
                    lugar = it.lugar,
                    context = context
                )
            }
        }







    }
}


@SuppressLint("QueryPermissionsNeeded")
@Composable
fun ProximasCitasDetalles(imagen: Int, doctor: String, consulta: String,hora: String,lugar: String,context: Context )  {

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
    }


}
@SuppressLint("" +
        "")
fun abrirGoogleMaps(context: Context, lugar: String) {
    val uri = Uri.parse("geo:0,0?q=$lugar")
    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
    mapIntent.setPackage("com.google.android.apps.maps")

    // Verificar si hay una actividad que puede manejar este Intent
    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {
        // Si no se puede abrir Google Maps, mostrar un mensaje en el registro
        Log.e("Maps", "No se puede abrir Google Maps")
        // También podrías mostrar un Toast o una Snackbar para notificar al usuario
    }
}
data class Cita(
    val dia: Int,
    val imagen: Int,
    val doctor: String,
    val consulta: String,
    val hora: String,
    val lugar: String
)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    currentMonth: YearMonth = YearMonth.now(),
    highlightedDays: List<Int> ,

    onDateSelected: (Cita) -> Unit = {},
    citas: List<Cita>
) {

    val selectedCita = remember { mutableStateOf<Cita?>(null) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
            fontFamily = Poppins,
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
        )

        // Week Days Header
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    fontFamily = Poppins,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
        }

        // Days
        val daysInMonth = currentMonth.lengthOfMonth()
        val firstDayOfWeek = currentMonth.atDay(1).dayOfWeek.value % 7 // Adjust for Sunday start

        Column {
            // Generate days grid
            val weeks = (daysInMonth + firstDayOfWeek) / 7 + 1
            for (week in 0 until weeks) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (day in 0 until 7) {
                        val dayOfMonth = week * 7 + day - firstDayOfWeek + 1
                        if (dayOfMonth in 1..daysInMonth) {
                            val esCita = dayOfMonth in highlightedDays
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .clickable {
                                        selectedCita.value = citas.find { it.dia == dayOfMonth }
                                        selectedDate.value = currentMonth.atDay(dayOfMonth)
                                        selectedCita.value?.let { onDateSelected(it) }

                                    }
                                    .background(
                                        color = if (esCita) Color(0xffffc061).copy(alpha = 0.76F) else Color.Transparent,
                                        shape = CircleShape,
                                    ).padding(16.dp)
                            ) {
                                Text(text = dayOfMonth.toString(),
                                    color = if (esCita) Color.White else Color.Black,
                                    fontFamily = Poppins,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier)

                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun VisitassPreview() {
    Visitas(rememberNavController(), LocalContext.current)
}