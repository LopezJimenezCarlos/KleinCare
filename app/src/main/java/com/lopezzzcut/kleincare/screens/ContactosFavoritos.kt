package com.lopezzzcut.kleincare.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
fun ContactosFavoritos(navController: NavController, context: Context) {
    val searchText = remember { mutableStateOf("") }
    val contactos = remember { mutableStateOf(getContactos()) }

    Scaffold(
        containerColor = Color(0xFFEBEBEB),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ContactosFavoritoscontent(navController = navController, contactos = contactos.value, searchText.value,context)
            }
        },
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(32.dp))
                TopAppBar(
                    title = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Icono de búsqueda",
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(8.dp),
                                tint = Color.Black
                            )
                        }
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                    actions = {
                        buscador(searchText = searchText, onSearchTextChanged = { query ->
                            searchText.value = query
                            contactos.value = getContactos().filter {
                                it.Nombre.contains(query, ignoreCase = true) || it.telefono.contains(query)
                            }
                        }, context = context)
                    }
                )
            }
        },
        bottomBar = {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    BottomBar(navController = navController)
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    )
}

@Composable
fun ContactosFavoritoscontent(navController: NavController, contactos: List<Numero>, searchText: String,context: Context) {
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
                text = "Contactos favoritos",
                fontFamily = Poppins,
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )
        }
        items(contactos) { contacto ->
            ContactoCard(nombre = contacto.Nombre, telefono = contacto.telefono, imagen = contacto.imagen, context = context)
        }
    }
}

data class Numero(
    val Nombre: String,
    val telefono: String,
    val imagen: Int
)



fun getContactos(): List<Numero> {
    return mutableListOf(
        Numero("Maria Jose", "123456789", R.drawable.personajoven1),
        Numero("Hija Silvia", "987654321", R.drawable.personajoven3),
        Numero("Nieto Alfonso", "123456789", R.drawable.personajoven2)
        // Añade más contactos aquí
    )

}


@Composable
fun ContactoCard(nombre: String, telefono: String, imagen: Int,context: Context) {
    val colorTarjeta = Color(0xfff1e5d7)
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTarjeta,
            contentColor = Color.Black
        ),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
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
                        text = nombre,
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(4.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = "Llamar",
                            tint = Color(0xff006600),
                            modifier = Modifier
                                .padding(end =3.dp)
                                .size(16.dp)
                        )
                        Text(
                            text = telefono,
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        )
                    }
                }

                Button(
                    elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 7.dp),
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$telefono")
                       context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = "Llamar",
                            tint = Color.Green,
                        )
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun buscador(
    searchText: MutableState<String>,
    onSearchTextChanged: (String) -> Unit,
    context: Context,
) {
    OutlinedTextField(
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.Black,
            containerColor = Color.Transparent
        ),
        value = searchText.value,
        onValueChange = onSearchTextChanged,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.searchicon),
                contentDescription = "Icono de búsqueda",
                modifier = Modifier
                    .size(34.dp)
                    .padding(8.dp),
                tint = Color.Black
            )
        },
        placeholder = {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Buscar...",
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 21.sp
                )
            }
        },
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .height(55.dp)
            .width(300.dp)
            .padding(end = 4.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        textStyle = TextStyle(
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 21.sp
        )
    )
}

@Preview
@Composable
fun ContactosFavoritoPreview() {
    ContactosFavoritos(rememberNavController(), LocalContext.current)
}