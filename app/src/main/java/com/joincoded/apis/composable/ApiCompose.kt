package com.joincoded.apis.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.joincoded.apis.model.ApiPet
import com.joincoded.apis.viewmodel.PetViewModel
import androidx.navigation.compose.composable

@Composable
fun PetListScreen(viewModel: PetViewModel =  viewModel(), modifier: Modifier) {
    val pets = viewModel.Pets
    LazyColumn (modifier = Modifier) {
        items(pets) { pet ->
            PetItem(pet)
        }
    }
}

@Composable
fun PetItem(pet: ApiPet, petViewModel: PetViewModel= viewModel()) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

        ) {
        Row(
            Modifier
                .padding(10.dp)
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically
        )

        {

            AsyncImage(
                model = pet.image,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
            Spacer(modifier = Modifier.width(25.dp))
            Column {
                Text(
                    text = "Name:"+pet.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("Id"+pet.id.toString())
                Text("Gender"+pet.id)
                Text("Adopted"+pet.id.toString())

                }

            }
        }
    Button(onClick = {petViewModel.deletePet(pet.id)
        petViewModel.fetchPets()
    }) {
        Text("Delete")
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(petViewModel: PetViewModel = viewModel()) {
    val navController = rememberNavController()


        Scaffold(
            topBar = { TopAppBar(title = { Text("My Pet List") }) }
            ,
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("addPet")}) {
                    Text("+")
                }
            }
        ) { padding ->
            NavHost(navController = navController, startDestination = "petList") {
                composable("petList") {
                    // Ensure BookListScreen is correctly implemented and contains visible content
                    PetListScreen(petViewModel, Modifier.padding(padding))
                }
                composable("addPet") {
                    AddPetScreen(petViewModel)

                }
            }
        }
    }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddPetScreen(petViewModel: PetViewModel = viewModel()) {



    var name by remember { mutableStateOf("") }
    var adopted by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add a New Book",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            InputField(
                value = name,
                onValueChange = { name = it },
                label = "name"
            )
            InputField(
                value = adopted,
                onValueChange = { adopted = it },
                label = "adopted"
            )
            InputField(
                value = age,
                onValueChange = { age = it },
                label = "age",
                keyboardType = KeyboardType.Number
            )
            InputField(
                value = image,
                onValueChange = { image = it },
                label = "Image URL"
            )
            InputField(
                value = gender,
                onValueChange = { gender = it },
                label = "gender",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val newPet = ApiPet(
                        id = 0,
                        name= name,
                        adopted= adopted.toBoolean(),
                        age = age.toInt(),
                        gender= gender,
                        image = image
                    )
                    petViewModel.addPet(newPet)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Pet")
            }
        }
    }
}


@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String, keyboardType: KeyboardType = KeyboardType.Text) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}
