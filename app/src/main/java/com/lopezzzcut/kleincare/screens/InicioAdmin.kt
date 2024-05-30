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
import com.lopezzzcut.kleincare.BottomBar2
import com.lopezzzcut.kleincare.R
import com.lopezzzcut.kleincare.TopBar
import com.lopezzzcut.kleincare.TopBar3
import com.lopezzzcut.kleincare.ui.theme.Poppins
import com.lopezzzcut.whatcut.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioAdmin(navController: NavController,context: Context) {

    Scaffold (
        containerColor = Color(0xFFEBEBEB),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                inicioadmincontent(navController = navController,)
            }
        },
        topBar = {
            Column() {
                Spacer(modifier = Modifier.height(32.dp))
                TopBar3(navController = navController, name = "Jesus Hernandez Parreno")
            }
                    },
        bottomBar= {
            Column {

            Row(modifier= Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                BottomBar2(navController = navController)
            }
            Spacer(modifier =Modifier.height(32.dp))
        } }
        )


}

@Composable
fun inicioadmincontent(navController: NavController){

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
                ruta = AppScreens.pastillasAdmin.route,
                navController = navController
            )
            botonCard(
                imagen = R.drawable.doctoraemoji,
                texto = "Citas",
                ruta = AppScreens.visitasAdmin.route,
                navController = navController
            )
            botonCard(
                imagen = R.drawable.phoneemoji,
                texto = "Contactos",
                ruta = AppScreens.contactosAdmin.route,
                navController = navController
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        MediacionCard(navController,true)
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Sus proximas citas",
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
@Preview
fun InicioAdminPreview(){
    InicioAdmin(navController = rememberNavController(), context = LocalContext.current )
}
