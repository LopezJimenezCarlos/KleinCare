package com.lopezzzcut.kleincare.screens

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import com.lopezzzcut.kleincare.ui.theme.Poppins
import com.lopezzzcut.whatcut.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inicio(navController: NavController,context: Context) {

    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                iniciocontent(navController = navController,)
            }
        },
        topBar = {
            Column() {
                Spacer(modifier = Modifier.height(32.dp))
                TopBar(navController = navController, name = "Jesus Hernandez Parreno")
            }
                    },
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
fun iniciocontent(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            botonCard(
                imagen = R.drawable.pillemoji,
                texto = "Medicación",
                ruta = AppScreens.Pastillas.route,
                navController = navController
            )
            botonCard(
                imagen = R.drawable.doctoraemoji,
                texto = "Citas",
                ruta = AppScreens.citas.route,
                navController = navController
            )
            botonCard(
                imagen = R.drawable.phoneemoji,
                texto = "Contactos",
                ruta = AppScreens.contactos.route,
                navController = navController
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        MediacionCard(navController,medicacion = false)
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Tus proximas citas",
            fontFamily = Poppins,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        )
        LazyRow(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()) {
            item {
                ProximasCitas(
                    imagen = R.drawable.doctoraejemplo2,
                    "04/05/2024 12:15",
                    consulta = "Neurologia",
                    navController = navController

                )
                ProximasCitas(
                    imagen = R.drawable.doctorejemplo1,
                    "07/05/2024 9:00",
                    consulta = "Urologia",
                    navController = navController

                )
                ProximasCitas(
                    imagen = R.drawable.doctorejemplo3,
                    "14/05/2024 18:45",
                    consulta = "Cardiologia",
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun MediacionCard(navController: NavController,medicacion:Boolean){
    val texto = if (medicacion) "Se ha tomado la medicación" else "Tomate la mediacion"
    val texto2 = if (medicacion) "Pulsa para ver \nlo que ha tomado" else "Pulsa para ver\n que debes tomar."
    val imagen = if (medicacion) R.drawable.tickverde else R.drawable.alertyellow
    val color = if (medicacion) Color(0xff82B680) else Color(0xffF06A6A)
    Card (shape = RoundedCornerShape(16.dp),
        colors= CardDefaults.cardColors(
            containerColor = color,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .padding(8.dp)){
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxWidth()) {
            Text(
                text = texto,
                fontFamily = Poppins,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(16.dp)

            )
            Image(
                painter = painterResource(id = imagen), contentDescription = "",
                modifier = Modifier
                    .padding(8.dp, end = 16.dp)
                    .size(40.dp),
            )
        }
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp,Alignment.Start)){
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = texto2,
                    fontFamily = Poppins,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(16.dp),


                )

            }
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End,modifier = Modifier.weight(1f)) {

                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { navController.navigate(AppScreens.Pastillas.route)},modifier = Modifier.padding(end= 8.dp,bottom = 24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {

                    Icon(painter = painterResource(id = R.drawable.righticon),
                        contentDescription ="Icono flecha derecha",
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF0a1532))
                }
            }

        }
    }
}
@Composable
fun ProximasCitas(imagen: Int, doctor: String, consulta: String,navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffE0E0E0),
            contentColor = Color.Black
        ), modifier = Modifier.padding(start = 16.dp,end = 16.dp )

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
            }

                Button(
                    onClick = { navController.navigate(AppScreens.citas.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffCCCCCC)),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Ver detalles",
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

@Composable
fun InicioCard(texto:String,ruta:String,navController: NavController,imagen:Int){
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
            .clickable {
                navController.navigate(ruta)
            }
            .height(215.dp)
            .width(168.dp)
            .padding(8.dp)


    ) {
        Text(
            text = texto,
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
                .padding(16.dp)
                .size(70.dp),
            )

    }
}
@Composable
fun botonCard(imagen: Int,texto: String, navController:NavController,ruta: String){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color(0xfff1e5d7),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
                .clickable {
                    navController.navigate(ruta)
                },
        ) {
            Image(painter = painterResource(id = imagen),
                contentDescription ="Icono de imagen",
                modifier = Modifier
                    .padding(28.dp)
                    .fillMaxSize() )

        }
        Text(
            text = texto,
            fontFamily = Poppins,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier


        )
    }

}

@Composable
@Preview
fun InicioPreview(){
    Inicio(navController = rememberNavController(), context = LocalContext.current )
}
