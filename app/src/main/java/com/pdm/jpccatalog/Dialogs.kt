package com.pdm.jpccatalog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyConfirmDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        title = { MyTitleDialog("Set backup account", Modifier.padding(24.dp)) },
        text = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                var status by rememberSaveable {mutableStateOf("")}
                MyTitleDialog(text = "Select ringtone", modifier = Modifier.padding(8.dp))
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Color.LightGray)
                MyRadioButtonList(name = status) {status = it}
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                    color = Color.LightGray)
                Row(modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)){
                    TextButton(onClick = { }) {
                        Text(text = "CANCEL")
                    }
                    TextButton(onClick = { }) {
                        Text(text = "OK")
                    }
                }
            }
        },
        onDismissRequest = { onDismiss() },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
                Text("Confirm")
            }
        }
    )
}

@Composable
fun MyCustomDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        title = { MyTitleDialog("Set backup account", Modifier) },
        text = {
            Column(
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                AccountItem("prueba@mail.com", R.drawable.avatar)
                AccountItem("prueba2@mail.com", R.drawable.avatar)
                AccountItem("Añadir nueva cuenta", R.drawable.add)
            }
        },
        onDismissRequest = { onDismiss() },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
                Text("Confirm")
            }
        }
    )
}

@Composable
fun MyAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        icon = { Icon(Icons.Default.Info, contentDescription = "") },
        title =
        {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                text = "AlertDialog",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "This area typically contains the supportive text " +
                        "which presents the details regarding the Dialog's purpose."
            )
        },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Dismiss")
            }
        })
}

@Composable
fun MySpacer(value: Int) {
    Spacer(modifier = Modifier.width(value.dp))
}

@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(
            text = email,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun MyTitleDialog(text: String, modifier: Modifier = Modifier.padding(12.dp)) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}