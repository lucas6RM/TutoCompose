package com.mercierlucas.tutocompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mercierlucas.tutocompose.ui.theme.TutoComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutoComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*Greeting(
                        name = "Android",
                    )*/
                    //InputWord(modifier = Modifier.background(Purple80))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun InputWord(modifier: Modifier = Modifier){
    var nameState by remember { mutableStateOf("") }
    OutlinedTextField(
        value = nameState,
        onValueChange =
        {
            newWord ->
            nameState = newWord
        }
        , modifier = modifier)
}

@Composable
fun CustomText(text: String, color: Color, modifier: Modifier) {
    var nameState by remember { mutableStateOf("Lucas") }

    Row {
        Text(text = text, color = color, modifier = modifier)


        OutlinedTextField(
            value = nameState,
            onValueChange =
            { newWord ->
                nameState = newWord
            }, modifier = modifier)
    }
}

@Composable
fun CustomText2() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Bonjour",
            color = Color.Blue,
            modifier = Modifier
                .background(Color.Yellow)
                //.fillMaxWidth()

        )

        Spacer(Modifier.height(100.dp))


        Text(
            text = "Toto",
            color = Color.Magenta,
            modifier = Modifier
                .background(Color.Cyan)
            //.fillMaxWidth()
        )

    }
}

@Composable
fun ExoBoxSuperposition() {
    Box(modifier = Modifier.fillMaxSize()){

        Row(
            Modifier
                .background(Color.Red)
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(20.dp)
        ){}

        Column(
            Modifier
                .background(Color.Blue)
                .align(Alignment.Center)
                .fillMaxHeight()
                .width(20.dp)
        ){}

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExoScaffold() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (
        Modifier.background(Color.Blue),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Top App bar") },
                colors = topAppBarColors(
                    containerColor = Color.Cyan,
                    titleContentColor = Color.Gray
                )
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // for showing snackbar in onClick for example:
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Snackbar Test"
                    )
                }
            }) {Text("+")} },
        content = {
            ExoBoxSuperposition()
            Text("Contenu de la vue", Modifier.padding(it))},
        bottomBar = {
            BottomAppBar() {
                Text("AppBar du bas")
            }
        }
    )

}


@Composable
fun MyFirstLazyColumn(modifierCustom: Modifier) {
    val myList = listOf("mon","tue","wed","thu","fri","sat","sun")
    LazyColumn (
        modifier = modifierCustom
        ){
        items(items = myList){
            MyItemView(string = it)
        }
    }
}

@Composable
fun MyItemView(string:String){
    Text(text = string,
        Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .background(Color.Black)
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .background(Color.Magenta)
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .background(Color.White)
            .padding(50.dp)
            .clickable(enabled = true) {
                println(string)
            },
        fontSize = 30.sp,
    )
}


@Composable
fun ExoCoil() {
    AsyncImage(
        model =ImageRequest.Builder(LocalContext.current)
            .data("https://upload.wikimedia.org/wikipedia/commons/" +
                    "3/33/Mr._Bean_2011.jpg",
            ).crossfade(true)
            .build(),
        contentDescription = null,
        Modifier.fillMaxSize(),
        //placeholder = painterResource(android.R.drawable.alert_dark_frame)
    )
}


@Composable
fun ExoMardiMatin() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ){

        MyFirstLazyColumn(Modifier.weight(1F))

        Button(
            onClick = {},
            modifier = Modifier.padding(vertical = 20.dp)
        ){
            Text(text = "Valider")
        }
    }
}



@Composable
fun ExoViewModelCompteurView(compteur : Int, onClick: () -> Unit) {
    //val viewModel : MyViewModel = viewModel()
    //val count by viewModel.compteurLiveData.observeAsState() // en var on pourra faire un setter
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(text = compteur.toString())
        Button(onClick = onClick) {
            Text(text = "+1")
        }
    }
}

@Composable
fun ExoViewModelCompteurScreen(){
    val viewModel : MyViewModel = viewModel()
    val count by viewModel.compteurLiveData.observeAsState(0) // en var on pourra faire un setter
    ExoViewModelCompteurView(compteur = count) { viewModel.increment() }

    //equivalent
    ExoViewModelCompteurView(compteur = count, viewModel::increment)
}

@Composable
fun ExoStateFlowView(compteur : Int, onClick: () -> Unit) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(text = compteur.toString())
        Button(onClick = onClick) {
            Text(text = "+1")
        }
    }
}

@Composable
fun ExoStateFlowScreen(){
    val viewModel : MyViewModel2 = viewModel()
    val count by viewModel.compteurStateFlow.collectAsState()
    ExoStateFlowView(compteur = count) { viewModel.increment() }
}

@Composable
fun CompteurScreen(){

    val viewModel : MyViewModel3 = viewModel()
    val count by viewModel.compteurStateFlow.collectAsState()

    ExoStateFlowView(compteur = count) { viewModel.increment() }

    // gestion des events
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {//Launched effect est une coroutine
        // qui se déclenche que quand la valeur en paramètre change (true)
        viewModel.goToOtherScreenSF.collect{
            if(it)
                Toast.makeText(context, "Changer d'écran", Toast.LENGTH_LONG).show()
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TutoComposeTheme {
        //Greeting("Youpi Android")
        //InputWord(modifier = Modifier.background(Purple80))
        /*CustomText(text="Hello World", color= Color.Yellow,
            Modifier
                .padding(3.dp)
                .background(color = Color.Gray))*/
        //CustomText2()
        //ExoBoxSuperposition()
        //ExoScaffold()
        //MyFirstLazyColumn()
        //ExoCoil()
        // ExoMardiMatin()
        //ExoViewModelCompteurView(compteur = 0, {})
        //ExoViewModelCompteurScreen()
        //ExoStateFlowScreen()
        CompteurScreen()
    }
}
