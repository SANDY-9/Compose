package com.sandy.textfield

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.textfield.ui.theme.TextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            TextFieldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        TextFieldExample1(this@MainActivity)
                        Spacer(modifier = Modifier.height(32.dp))
                        OutlinedTextFieldExample1(context = this@MainActivity)
                    }
                }
            }
        }
    }
}

@Composable
private fun TextFieldExample1(context: Context) {
    var filledText by remember { mutableStateOf("") }
    TextField(
        value = filledText,
        onValueChange = { filledText = it },
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Right
        ),
        label = {
            Text(text = "Enter your weight")
        },
        placeholder = {
            Text(text = "Weight")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Create,
                contentDescription = "Weight"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Persion"
            )
        },
        prefix = {
            Text(text = "$")
        },
        suffix = {
            Text(text = "Kg")
        },
        /*supportingText = {
            Text(text = "Please choose a real weight")
        },
        isError = true*/
        supportingText = {
            Text(text = "*required")
        },
        isError = false,
        //visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                Toast.makeText(context, "엔터", Toast.LENGTH_SHORT).show()
            }
        ),
        singleLine = true,
    )
}

@Composable
private fun OutlinedTextFieldExample1(context: Context) {
    var outlinedText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = outlinedText,
        onValueChange = { outlinedText = it },
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Right
        ),
        label = {
            Text(text = "Enter your weight")
        },
        placeholder = {
            Text(text = "Weight")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Create,
                contentDescription = "Weight"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Persion"
            )
        },
        prefix = {
            Text(text = "$")
        },
        suffix = {
            Text(text = "Kg")
        },
        /*supportingText = {
            Text(text = "Please choose a real weight")
        },
        isError = true*/
        supportingText = {
            Text(text = "*required")
        },
        isError = false,
        //visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                Toast.makeText(context, "엔터", Toast.LENGTH_SHORT).show()
            }
        ),
        singleLine = true,
    )
}
