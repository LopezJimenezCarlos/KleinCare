package com.lopezzzcut.kleincare.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastillasAdmin(navController: NavController,context: Context) {

    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                pastillasadmincontent(navController = navController,)
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
fun pastillasadmincontent(navController: NavController){
    var checked = remember { mutableStateOf(false) }
    var verDias by remember { mutableStateOf(true) }
    var icono = if (verDias) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    var añadirPastilla = remember { mutableStateOf(false) }
    var nombrePastilla = remember { mutableStateOf("") }
    var cantidadPastilla = remember { mutableStateOf("") }
    var frecuenciaPastilla = remember { mutableStateOf("") }
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
                text = "Medicación",
                fontFamily = Poppins,
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)

            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Junio 2024",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier


                )

                IconButton(onClick = { verDias = !verDias },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(start = 8.dp)) {
                    Icon(imageVector = icono,
                        contentDescription = "Desplegar",
                        tint =Color.Black,
                        modifier= Modifier.fillMaxSize())

                }

            }
            if (verDias){
                FilaDias()
            }
        }
        item {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Mañana",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier


                )

                Image(painter = painterResource(id = R.drawable.sunicon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .padding(start = 8.dp))

                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { añadirPastilla.value = true }) {


                    Image(
                        painter = painterResource(id = R.drawable.addicon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 8.dp)
                    )

                }

            }

        }
        item {
            ColumnaPastillas1Admin()
        }
        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Noche",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier


                )

                Image(painter = painterResource(id = R.drawable.nighticon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .padding(start = 8.dp))

                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { añadirPastilla.value = true }) {


                    Image(
                        painter = painterResource(id = R.drawable.addicon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 8.dp)
                    )

                }

            }

        }
        item {
            ColumnaPastillas2Admin()
        }


    }
    if (añadirPastilla.value) {
        AñadirPastilla(nombrePastilla = nombrePastilla, cantidadPastilla = cantidadPastilla, frecuenciaPastilla = frecuenciaPastilla, añadirPastilla = añadirPastilla)
    }
}
@Composable
fun ColumnaPastillas2Admin() {
    val pastillas = listOf(

        Triple(R.drawable.lanoxin, "Lanoxin", "1 pastilla despues de cena"),
        Triple(R.drawable.losartan, "Losartan", "1 pastilla cada 12 horas")
    )

    Column(modifier = Modifier.padding(8.dp)) {
        pastillas.forEachIndexed { index, pastillas ->
            cardPastillaAdmin(
               pastillas

                )
        }
    }
}
    @Composable
fun ColumnaPastillas1Admin() {
    val pastillas = listOf(
        Triple(R.drawable.ibuprofenoimagen, "Ibuprofeno","1 pastilla cada 8 horas"),
        Triple(R.drawable.paracetamolimagen, "Paracetamol","1 pastilla cada 8 horas"),

        )

    Column(modifier = Modifier.padding(8.dp)) {
        pastillas.forEachIndexed { index,pastillas ->
            cardPastillaAdmin(
                pastillas = pastillas
                )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun cardPastillaAdmin(pastillas: Triple<Int, String, String>){
    val imagen = mutableStateOf(pastillas.first)
    val pastilla =mutableStateOf( pastillas.second)
    val descripcion = mutableStateOf(pastillas.third)
    var checked =  remember { mutableStateOf(false) }
    val colorBoton = if (checked.value) Color(0xffffc061) else Color.White
    val colorTarjeta =
        when (pastilla.value) {
            "Ibuprofeno" -> Color(0xffe6f6ec)
            "Paracetamol" -> Color(0xffeff5e9)
            "Lanoxin" -> Color(0xffefe4e0)
            "Losartan" -> Color(0xfff7f1e1)
            else -> Color(0xffFFC061)
        }
    val colorImagen =
        when (pastilla.value) {
            "Ibuprofeno" -> Color(0xffd8eee1)
            "Paracetamol" -> Color(0xffe6eddd)
            "Lanoxin" -> Color(0xffefdcd6)
            "Losartan" -> Color(0xfff2edda)
            else -> Color(0xffFFC061)
        }



    Card(shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTarjeta,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)){
            Box(modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colorImagen)
                .padding(8.dp)) {
                Image(
                    painter = painterResource(imagen.value),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            }
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)){
                Text(
                    text = pastilla.value,
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                )
                Text(
                    text = descripcion.value,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick= {
                checked.value = !checked.value
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorBoton,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .size(60.dp)
            ){
                    Image(painter = painterResource(id =R.drawable.editicon ),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Fit)

            }


        }
        if (checked.value){
           EditarPastilla(nombrePastilla = pastilla, frecuenciaPastilla = descripcion , editarPastilla = checked)
        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirPastilla(nombrePastilla: MutableState<String>, cantidadPastilla: MutableState<String>, frecuenciaPastilla: MutableState<String>, añadirPastilla: MutableState<Boolean>){

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
                            text = "Agregar medicación",
                            fontFamily = Poppins,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(16.dp)
                        )
                        OutlinedTextField(
                            value = nombrePastilla.value ,
                            onValueChange = { nombrePastilla.value  = it},
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
                            value = cantidadPastilla.value ,
                            onValueChange = { cantidadPastilla.value  = it},
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
                            value = "",
                            onValueChange = { },
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
                            onClick = { añadirPastilla.value  = false}, shape = RoundedCornerShape(16.dp),
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
                            text = "Agregar medicación",
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
                            text = "¿Desea agregar la medicación?",
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
                            onClick = { añadirPastilla.value = false }, shape = RoundedCornerShape(16.dp),
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPastilla(nombrePastilla: MutableState<String>, frecuenciaPastilla: MutableState<String>, editarPastilla: MutableState<Boolean>){

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
                        text = "Agregar medicación",
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(16.dp)
                    )
                    OutlinedTextField(
                        value = nombrePastilla.value ,
                        onValueChange = { nombrePastilla.value  = it},
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
                        value = frecuenciaPastilla.value ,
                        onValueChange = { frecuenciaPastilla.value  = it},
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
                        value = frecuenciaPastilla.value ,
                        onValueChange = { frecuenciaPastilla.value  = it },
                        placeholder = {
                            Text(
                                "Frecuencia", fontFamily = Poppins,
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
                        onClick = { editarPastilla.value  = false}, shape = RoundedCornerShape(16.dp),
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
                        text = "Agregar medicación",
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
                        text = "¿Desea agregar la medicación?",
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
                        onClick = { editarPastilla.value = false }, shape = RoundedCornerShape(16.dp),
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
@Preview
@Composable
fun ADminPastillasPreviw (
    navController: NavController = rememberNavController(),
    context: Context = LocalContext.current
){
    PastillasAdmin(navController = navController,context = context)
}
