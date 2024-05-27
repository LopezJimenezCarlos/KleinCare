package com.lopezzzcut.kleincare

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lopezzzcut.kleincare.ui.theme.Poppins
import com.lopezzzcut.whatcut.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController:NavController,name:String) {

    TopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier.padding(16.dp),

        title = {
            Column(modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start){
                Text(
                    text = "Hola,",
                    fontFamily = Poppins,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(end= 6.dp)


                )
                Text(
                    text = name,
                    fontFamily = Poppins,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xff292929),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(end= 6.dp)


                )
            }

        },
        actions = {


            IconButton(
                onClick = {
                    navController.navigate(AppScreens.Inicio.route)
                },
                modifier = Modifier
                    .padding(start = 4.dp, end = 8.dp)
                    .size(60.dp)
                    .clip(CircleShape),
            ) {

                    Image(
                        painter = painterResource(R.drawable.senormayor),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
            }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar2(navController:NavController) {

    TopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        title = {

            IconButton(onClick =
            { navController.navigateUp()
            },
                modifier = Modifier.size(34.dp)) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button",
                    tint = Color(0xff292929),
                    modifier = Modifier.fillMaxSize())

            }

        },
        actions = {


            IconButton(
                onClick = {
                    navController.navigate(AppScreens.Inicio.route)
                },
                modifier = Modifier
                    .padding(start = 4.dp, end = 8.dp)
                    .size(60.dp)
                    .clip(CircleShape),
            ) {

                Image(
                    painter = painterResource(R.drawable.senormayor),
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

        }
    )
}

@Preview
@Composable
fun TopBarPreview() {
        TopBar(navController = rememberNavController(),name="Jesus Hernandez Parreno")

}