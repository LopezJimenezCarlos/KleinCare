package com.lopezzzcut.kleincare.screens

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.lopezzzcut.kleincare.BottomBar2
import com.lopezzzcut.kleincare.R
import com.lopezzzcut.kleincare.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactosFavoritosAdmin(navController: NavController, context: Context) {
    val searchText = remember { mutableStateOf("") }
    val contactos = remember { mutableStateOf(getContactos()) }

    Scaffold(
        containerColor = Color(0xFFEBEBEB),
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ContactosFavoritosAdmincontent(navController = navController, contactos = contactos, searchText.value,context)
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
                    BottomBar2(navController = navController)
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    )
}

@Composable
fun ContactosFavoritosAdmincontent(
    navController: NavController, contactos: MutableState<List<Numero>>, searchText: String,
    context: Context) {
    var añadirContacto = remember { mutableStateOf(false) }
    var nombre = remember { mutableStateOf("") }
    var telefono = remember { mutableStateOf("") }

    val contactosList = contactos.value.toMutableList()

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
        items(contactos.value) { contacto ->
            ContactoCardAdmin(nombre = contacto.Nombre, telefono = contacto.telefono, imagen = contacto.imagen, context = context)
        }
        item{
            Button(onClick = { añadirContacto.value = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffE4CEB4)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(
                    text = "Agregar contacto",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

            }
        }
        item{
            if (añadirContacto.value) {
                AñadirContacto(nombre = nombre, telefono = telefono, añadirContacto = añadirContacto,contactos = contactosList, contactosState = contactos )
            }
        }
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirContacto(nombre: MutableState<String>, telefono: MutableState<String>, añadirContacto: MutableState<Boolean>,
                   contactos: MutableList<Numero>, contactosState: MutableState<List<Numero>>
) {

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
                        text = "Añadir Contacto",
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(16.dp)
                    )
                    OutlinedTextField(

                        value = nombre.value,
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        onValueChange = { nombre.value = it },
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
                        value = telefono.value,
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        onValueChange = { telefono.value = it },
                        placeholder = {
                            Text(
                                "Teléfono", fontFamily = Poppins,
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
                                "Foto de perfil", fontFamily = Poppins,
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
                        onClick = {
                            añadirContacto.value = false },
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
                        text = "Agregar contacto",
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
                        text = "¿Desea agregar la contacto?",
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
                        onClick = {
                            contactos.add(Numero(Nombre = nombre.value, telefono = telefono.value, imagen = R.drawable.profileicon))
                            añadirContacto.value = false

                            añadirContacto.value = false },
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
@Composable
fun ContactoCardAdmin(nombre: String, telefono: String, imagen: Int,context: Context) {
    val colorTarjeta = Color(0xfff1e5d7)
    var editarNumero = remember { mutableStateOf(false) }
    var nombreNuevo = remember { mutableStateOf(nombre) }
    var numeroNuevo = remember { mutableStateOf(telefono) }
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
                                .padding(end = 3.dp)
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
                        editarNumero.value = true
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
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Llamar",
                            tint = Color.Black,
                        )
                    }
                }

            }
        }
        if (editarNumero.value) {
            EditarNúmero(nombre = nombreNuevo, numero = numeroNuevo, editarNumero = editarNumero)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarNúmero(nombre: MutableState<String>,
                 numero: MutableState<String>,
                 editarNumero: MutableState<Boolean>,
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
                        text = "Editar Contacto",
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
                        value = nombre.value ,
                        onValueChange = { nombre.value  = it},
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
                        value = numero.value ,
                        onValueChange = { numero.value  = it},
                        placeholder = {
                            Text(
                                "Número de teléfono", fontFamily = Poppins,
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
                        value ="Foto de perfil",
                        onValueChange = { /*TODO*/ },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        ),
                        placeholder = {
                            Text(
                                "Foto de perfil", fontFamily = Poppins,
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
                        onClick = { editarNumero.value  = false}, shape = RoundedCornerShape(16.dp),
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
                        text = "Editar Contacto",
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
                        text = "¿Desea editar el contacto?",
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
                        onClick = { editarNumero.value = false }, shape = RoundedCornerShape(16.dp),
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
fun ContactosFavoritoAdminPreview() {
    ContactosFavoritosAdmin(rememberNavController(), LocalContext.current)
}