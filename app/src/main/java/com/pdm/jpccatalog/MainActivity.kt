package com.pdm.jpccatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pdm.jpccatalog.model.Routes.*
import com.pdm.jpccatalog.ui.theme.JpcCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JpcCatalogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigate()
                }
            }
        }
    }
}

@Composable
fun DialogMain() {
    var myText by remember { mutableStateOf("") }
    var ena by remember { mutableStateOf(true) }

    Column() {
        //GreetingPreview()
        var show by rememberSaveable {mutableStateOf(false)}
        Button(onClick = {show = !show}){
            Text("Clíckame")
        }
        Text(text = "clickado = $show")
        if(show)
            MyCustomDialog(
                onDismiss = { show = false },
                onConfirm = { show = false })
        SimpleRecyclerView()
    }
}


//@Preview(showBackground = true)
@Composable
fun Navigate() {
    var ena by remember { mutableStateOf(true) }
    var selected by remember { mutableStateOf("Yo") }
    val myCheckboxes = checkOptions(listOf("David", "Mindblowing", "Estudia"))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        val navigationController = rememberNavController()
        NavHost(navController = navigationController, startDestination = MainScreen.route) {
            composable(MainScreen.route) { Screen1(navigationController) }
            composable(Pantalla2.route) { Screen2(navigationController) }
            composable(Pantalla3.route) { Screen3(navigationController) }
            composable(
                Pantalla4.route,
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) {
                Screen4(
                    navigationController,
                    it.arguments?.getString("name").orEmpty()
                )
            }
            composable(
                Pantalla5.route,
                arguments = listOf(navArgument("name") { defaultValue = "Pepe" })
            ) {
                Screen5(
                    navigationController,
                    it.arguments?.getString("name").orEmpty()
                )
            }
        }
//            AdvSlider()
//            MyDropDownMenu()
//            MyBadgeBox()
//            MyDivider()
//            MyCard()
//            MyRadioButtonList(selected) { selected = it }
//            MyTriStateCheckbox()
//            myCheckboxes.forEach {
//                MyCheckBoxTextComplete(checkData = it)
//            }
//            MyTextFieldAdv()
//            MyTextFieldOutlined()
//            MyButton(ena = true, onClickListener = { ena = false })
//            MyImage()
//            MySwitch()
//            MyCheckBox()
//            MyCheckBoxText()
    }
}

//@Preview
@Composable
fun EasyPreview() {
    JpcCatalogTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            NavigationBar {
                NavigationBarItem(icon = { MyBadgeBox() }, selected = false, onClick = {})
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu() {
    var text by remember { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var desserts = listOf("Café", "Helado", "Chocolate", "Tarta", "Fruta")
    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth(),
            enabled = false,
            readOnly = true
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            desserts.forEach { dessert ->
                DropdownMenuItem(text = { Text(text = dessert) },
                    onClick = {
                        expanded = false
                        text = dessert
                    })
            }
        }
    }
}

@Composable
fun MyDivider() {
    Divider(Modifier.fillMaxWidth(), color = Color.Blue)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeBox() {
    BadgedBox(badge = {
        Text(text = "1")
    }) {
        Icon(imageVector = Icons.Default.Star, contentDescription = "")
    }
}

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Green, contentColor = Color.Blue),
        border = BorderStroke(4.dp, Color.Red)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text("Ejemplo")
            Text("Ejemplo2")
        }
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth().padding(6.dp)) {
            RadioButton(selected = name == "Yo", onClick = { onItemSelected("Yo") })
            Text("Yo")
        }
        Row(Modifier.fillMaxWidth().padding(6.dp)) {
            RadioButton(selected = name == "Tu", onClick = { onItemSelected("Tu") })
            Text("Tu")
        }
        Row(Modifier.fillMaxWidth().padding(6.dp)) {
            RadioButton(selected = name == "El", onClick = { onItemSelected("El") })
            Text("El")
        }
        Row(Modifier.fillMaxWidth().padding(6.dp)) {
            RadioButton(selected = name == "Nosotros", onClick = { onItemSelected("Nosotros") })
            Text("Nosotros")
        }
    }
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth()) {
        RadioButton(
            selected = false, onClick = {}, enabled = false, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Green,
                disabledUnselectedColor = Color.Blue
            )
        )
        Text("Ejemplo")
    }
}

@Composable
fun MyTriStateCheckbox() {
    var state by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        TriStateCheckbox(state = state, onClick = {
            state = when (state) {
                ToggleableState.Indeterminate -> ToggleableState.On
                ToggleableState.On -> ToggleableState.Off
                ToggleableState.Off -> ToggleableState.Indeterminate
            }
        })
        Spacer(modifier = Modifier.width(8.dp))
        Text("Ejemplo")
    }
}

@Composable
fun checkOptions(titles: List<String>): List<CheckboxData> {
    return titles.map { title ->
        var state by rememberSaveable { mutableStateOf(false) }
        CheckboxData(
            title = title,
            selected = state,
            onCheckedChange = { newState -> state = newState }
        )
    }
}

@Composable
fun MyCheckBoxTextComplete(checkData: CheckboxData) {

    Row(
        Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkData.selected,
            onCheckedChange = { checkData.onCheckedChange(!checkData.selected) },
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Yellow,
                checkmarkColor = Color.Blue
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Ejemplo")
    }
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(true) }

    Checkbox(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Yellow,
            checkmarkColor = Color.Blue
        )
    )

}

@Composable
fun MyCheckBoxText() {
    var state by rememberSaveable { mutableStateOf(true) }

    Row(Modifier.padding(8.dp)) {
        Checkbox(
            checked = state,
            onCheckedChange = { state = !state },
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Yellow,
                checkmarkColor = Color.Blue
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Ejemplo")
    }
}

@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(false) }
    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Magenta,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Cyan.copy(0.2f),
            disabledCheckedTrackColor = Color.Yellow,
            disabledCheckedThumbColor = Color.Yellow
        )
    )
}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Ejemplito")
        Text(text = "Ejemplito", color = Color.Blue)
        Text(text = "Ejemplito", fontWeight = FontWeight.Bold)
        Text(text = "Ejemplito", fontFamily = FontFamily.Cursive)
        Text(text = "Ejemplito", textDecoration = TextDecoration.LineThrough)
        Text(text = "Ejemplito", textDecoration = TextDecoration.Underline)
        Text(
            text = "Ejemplito",
            textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.Underline,
                    TextDecoration.LineThrough
                )
            )
        )
        Text(text = "Ejemplito", fontSize = 30.sp)
    }
}

@Composable
fun MyButton(ena: Boolean, onClickListener: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding((24.dp))
    ) {
        Button(
            onClick = { onClickListener(ena) },
            enabled = ena,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.Blue
            ),
            border = BorderStroke(5.dp, Color.Black)
        ) {
            Text("Hola")
        }
        OutlinedButton(
            onClick = { onClickListener },
            enabled = ena,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Red,
                containerColor = Color.DarkGray
            ),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Hola")
        }
        TextButton(onClick = { onClickListener }) {
            Text(text = "TextButton")
        }
    }
}

@Composable
fun MyImage() {
    Column() {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Launcher",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldOutlined() {
    var myText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Introduce tu nombre") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Blue
        ),
        leadingIcon = { Icon(Icons.Outlined.AccountCircle, contentDescription = "MAIL") })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldAdv() {
    var myText by remember { mutableStateOf("") }
    TextField(
        value = myText,
        onValueChange = {
            myText = if (it.contains("a")) {
                it.replace("a", "")
            } else {
                it
            }
        },
        label = { Text(text = "Introduce tu nombre") })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText,
        onValueChange = { myText = it })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldHoisting(name: String, onValueChanged: (String) -> Unit) {
    TextField(value = name, onValueChange = { onValueChanged(it) })
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
