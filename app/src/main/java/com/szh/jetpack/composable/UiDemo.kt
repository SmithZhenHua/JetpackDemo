package com.szh.jetpack.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.szh.jetpack.TEXT_FIELD_PAGE

var maxMod = Modifier
    .fillMaxHeight()
    .fillMaxWidth()

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextFieldPage() {
    val text = rememberSaveable { mutableStateOf("") }
    val error = rememberSaveable { mutableStateOf(false) }
    val password by rememberSaveable { mutableStateOf(false) }
    val current = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val toast = Toast(current)

    var textFieldValue by rememberSaveable (stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("textFieldValue"))
    }
    val padding = Modifier.padding(top = 10.dp)
    Column (modifier = maxMod, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        TextField(
            value = text.value,
            onValueChange = { it ->
                text.value = it
                error.value = it.length > 5
            },
            label = { Text(text = "TextField")},
            singleLine = true,
            placeholder = { Text(text = "张三") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "leadingIcon")},
            trailingIcon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "trailingIcon")},
            isError = error.value,  // 错误提示
            supportingText = { Text(text = "supportingText")}, //底部提醒文本
            visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),

        )
        // TextFieldValue参数重载
        TextField(
            value = textFieldValue,
            onValueChange = { it -> textFieldValue = it},
            modifier = padding,
            // 不显示下划线
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.LightGray,
                textColor = Color.Red
            ),
            shape = RoundedCornerShape(30.dp)
        )

        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { it -> textFieldValue = it},
            modifier = padding,
            label = { Text(text = "TextField")},
            singleLine = true,
            placeholder = { Text(text = "张三") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "leadingIcon")},
            trailingIcon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "trailingIcon")},
            isError = error.value,  // 错误提示
            supportingText = { Text(text = "supportingText")}, //底部提醒文本
            visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            shape = RoundedCornerShape(30.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiList(nav: NavHostController) {
    val fillMaxWidth = Modifier
        .background(color = Color.Red)
        .fillMaxHeight()
        .fillMaxWidth()

    Scaffold() { it ->
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = fillMaxWidth.padding(it)) {
            Button(onClick = {
               nav.navigate(TEXT_FIELD_PAGE)
            }) {
                Text(text = "TextField")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Show(){
    TextFieldPage()
}