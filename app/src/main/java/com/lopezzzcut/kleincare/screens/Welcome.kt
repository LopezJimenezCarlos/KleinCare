package com.lopezzzcut.kleincare.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lopezzzcut.kleincare.R
import com.lopezzzcut.kleincare.ui.theme.Poppins
import com.lopezzzcut.whatcut.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Welcome(navController: NavController, context: Context) {

    Scaffold (
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                welcomecontent(navController = navController,)
            }
        },
        containerColor = Color.White,
    )


}

@Composable
fun welcomecontent(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardWelcome(modifier = Modifier
            .weight(1f)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xffDE7D54), Color.Transparent),
                    start = Offset(0f, 0f), // Inicia en la parte superior
                    end = Offset(0f, 500f) // Termina en la parte inferior
                )
            ))
        Column(modifier = Modifier.weight(1f)) {
            Box() {
                Image(painterResource(id = R.drawable.material), contentDescription = "", modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.25f)
                    .padding(32.dp))

                Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "¡Bienvenido a ",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 32.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, top = 16.dp)
                    )
                    Text(
                        text = "KleinCare!",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 44.sp,
                        color = Color(0xffe18962),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp)
                    )
                    Text(
                        text = "Cuida de quienes\nmás quieres",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 34.dp)
                    )
                    Button(onClick = { navController.navigate(AppScreens.Inicio.route) },
                        modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp,top = 8.dp, bottom = 8.dp).height(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.Black),
                        shape = RoundedCornerShape(16.dp)){
                        Text(
                            text = "Soy el usuario principal",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()

                        )
                        
                    }
                    Button(onClick = { navController.navigate(AppScreens.inicioAdmin.route) },
                        modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp,top = 8.dp, bottom = 8.dp).height(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.Black),
                        shape = RoundedCornerShape(16.dp)){
                        Text(
                            text = "Soy el cuidador",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()

                        )

                    }
                }
            }
        }

    }


}
@Composable
fun CardWelcome(modifier: Modifier){

    Box(modifier =modifier,contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(370.dp)
                .height(390.dp)
                .padding(top = 40.dp)
                .background(Color(0xffDE7D54), shape = RoundedCornerShape(32.dp))
        ) {


        }
            Image(
                painter = painterResource(id = R.drawable.img_3),
                contentDescription = "", modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    , contentScale = ContentScale.Crop
            )
        }

}
@Preview
@Composable
fun PreviewWelcome(){
    Welcome(navController = rememberNavController(), LocalContext.current)
}