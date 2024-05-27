package com.lopezzzcut.kleincare.screens

import android.annotation.SuppressLint
import android.content.Context
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pastillas(navController: NavController,context: Context) {

    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                pastillascontent(navController = navController,)
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

@Composable
fun pastillascontent(navController: NavController){
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
                .padding(start = 16.dp),
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


            }
        }
        item {
            ColumnaPastillas1()
        }
        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
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


            }
        }
        item {
            ColumnaPastillas2()
        }


    }
}
@Composable
fun cardPastilla(pastilla:String,
                 descripcion:String,
                 imagen: Int,
               ){
    var checked by remember { mutableStateOf(false) }
    val colorBoton = if (checked) Color(0xffffc061) else Color.White
    val colorTarjeta =
        when (pastilla) {
            "Ibuprofeno" -> Color(0xffe6f6ec)
            "Paracetamol" -> Color(0xffeff5e9)
            "Lanoxin" -> Color(0xffefe4e0)
            "Losartan" -> Color(0xfff7f1e1)
            else -> Color(0xffFFC061)
        }
    val colorImagen =
        when (pastilla) {
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
                    painter = painterResource(imagen),
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
                text = pastilla,
                fontFamily = Poppins,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
            )
            Text(
                text = descripcion,
                fontFamily = Poppins,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Start,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick= {
            checked = !checked
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorBoton,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .size(60.dp)
        ){
            if (checked) {
                Image(painter = painterResource(id =R.drawable.whitecheck ),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Fit)
            }
        }

        }

    }

}
@Composable
fun ColumnaPastillas1() {
    val pastillas = listOf(
        Triple(R.drawable.ibuprofenoimagen, "Ibuprofeno","1 pastilla cada 8 horas"),
        Triple(R.drawable.paracetamolimagen, "Paracetamol","1 pastilla cada 8 horas"),

    )

    Column(modifier = Modifier.padding(8.dp)) {
        pastillas.forEachIndexed { index,pastilla ->
            cardPastilla(
                imagen = pastilla.first,
                pastilla = pastilla.second,
                descripcion = pastilla.third,


            )
        }
    }
}
@Composable
fun ColumnaPastillas2() {
    val pastillas = listOf(

        Triple(R.drawable.lanoxin, "Lanoxin","1 pastilla despues de cena"),
        Triple(R.drawable.losartan, "Losartan","1 pastilla cada 12 horas")
    )

    Column(modifier = Modifier.padding(8.dp)) {
        pastillas.forEachIndexed { index,pastilla ->
            cardPastilla(
                imagen = pastilla.first,
                pastilla = pastilla.second,
                descripcion = pastilla.third,


                )
        }
    }
}
@Composable
fun FilaDias() {
    var selectedCardIndex by remember { mutableStateOf(0) }
    val days = listOf(
        Pair("L", "01"),
        Pair("M", "02"),
        Pair("X", "03"),
        Pair("J", "04"),
        Pair("V", "05")
    )

    LazyRow(modifier = Modifier.padding(8.dp)) {
        items(days) { day ->
            val index = days.indexOf(day)
            tarjetaDia(
                dia = day.first,
                ndia = day.second,
                isSelected = index == selectedCardIndex,
                onClick = { selectedCardIndex = index }

            )
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun tarjetaDia(dia: String, ndia: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color(0xffffc061) else Color(0xfff7f8fa)

        Card(
            shape = RoundedCornerShape(21.dp),
            colors= CardDefaults.cardColors(
                containerColor = backgroundColor,
                contentColor = Color.Black
            ),

            modifier = Modifier
                .height(110.dp)
                .width(70.dp)
                .padding(8.dp)
                .clickable { onClick() },) {
            Column(modifier =Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(8.dp)
                        .height(30.dp)
                        .width(30.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = ndia,
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = dia,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                )

            }
        }
}
/*@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerPastillas(){
    val pagerState = rememberPagerState()
    var tomada1 = remember { mutableStateOf(false) }
    var disponible1 = remember { mutableStateOf(false) }
    var tomada2 = remember { mutableStateOf(false) }
    var disponible2 = remember { mutableStateOf(false) }

        HorizontalPager(state = pagerState,modifier = Modifier
            ,
            contentPadding =  PaddingValues(start = 70.dp,end = 70.dp),
            verticalAlignment = Alignment.Top, pageCount = 2) { page ->

            when (page) {
                0 -> {
                    MedicacionCard(
                        imagen = R.drawable.ibuprofeno,
                        texto = "Ibuprofeno",
                        tomada= tomada1
                        )

                }

                1 -> {
                    MedicacionCard(
                        imagen = R.drawable.paracetamol,
                        texto = "Paracetamol",
                        tomada = tomada2
                    )

                }
            }
            if(tomada1.value) {
                if (!disponible1.value) {
                    popUp(texto = "Ibuprofeno", disponible1)
                }
            }
            if(tomada2.value) {
                if (!disponible2.value) {
                    popUp(texto = "Paracetamol", disponible2)
                }
            }



    }
    ElevatedButton(
        onClick = {
            when (pagerState.currentPage) {
            0 -> tomada1.value = true

            1 -> tomada2.value = true
        } },
        elevation =ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp,
            disabledElevation = 0.dp),
        shape = RoundedCornerShape(16.dp),
        enabled = when (pagerState.currentPage){
            0 -> !tomada1.value
            1 ->!tomada2.value
            else -> {true}
        },
        modifier = Modifier
            .width(300.dp)
            .padding(16.dp)
            .height(70.dp),
        colors = ButtonDefaults.elevatedButtonColors(

            containerColor = Color(0xffD9D9D9),
            disabledContainerColor = Color(0xffB8B8B8),
        ))
        {
        Text(
            text = "He tomado la medicación",
            fontFamily = Poppins,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()

        )


    }

}
*/

@Composable
fun popUp(texto: String,cerrar:MutableState<Boolean>){
    AlertDialog(
        containerColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        title = { Text(
            text = "ENHORABUENA!",
            fontFamily = Poppins,
            fontSize = 28.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()

        ) },
        tonalElevation = 8.dp,
        dismissButton = {
            ElevatedButton(
                onClick = { cerrar.value = true },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color(0xffD9D9D9),
                ),
                modifier = Modifier
                    .width(300.dp)
                    .padding(16.dp)
                    .height(70.dp)
            ) {
                Text(
                    text = "Cerrar",
                    fontFamily = Poppins,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()

                )
            }
        },
        text = {
            Text(
            text = "Te has tomado el $texto",
            fontFamily = Poppins,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()

        ) },
        onDismissRequest = {  cerrar.value = true},
        confirmButton = { /*TODO*/ })
}
@Composable
fun MedicacionCard(pastilla:String,
                   descripcion:String,
                   imagen:Int,
                   tomada:MutableState<Boolean>){
    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        colors= CardDefaults.elevatedCardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        modifier = Modifier
            .height(215.dp)
            .width(215.dp)
            .padding(8.dp),



    ) {
        Text(
            text = pastilla,
            fontFamily = Poppins,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        )
        Image(painter = painterResource(id = imagen),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp))

    }
}


@Preview
@Composable
fun PastillasPreview() {
    ColumnaPastillas1()
}