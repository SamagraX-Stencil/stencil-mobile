package com.samagra.stencilmobiles.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenericInputFieldMolecule(
    input: String,
    onInputChange: (String) -> Unit,
    hint: String,
    inputType: InputType = InputType.MOBILE,
    textStyle: TextStyle = TextStyle( fontSize = 14.sp , fontWeight = FontWeight.Normal , color = Color.Black, textAlign = TextAlign.Center),
    onImeAction: (() -> Unit)? = null,
    textFieldBackgroundColor: Color = Color.White
) {
    val maxLength = when (inputType) {
        InputType.MOBILE -> 10
        InputType.AADHAR -> 12
        InputType.USERNAME -> Int.MAX_VALUE
    }
    var borderColor by remember { mutableStateOf(Color.LightGray) }

    OutlinedTextField(
        value = input,
        onValueChange = { newInput ->
            if (newInput.length <= maxLength) {
                onInputChange(newInput)
            }
        },
        placeholder = {
            Text(
                text = hint,
                style = textStyle,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
            .background(textFieldBackgroundColor)
            .onFocusChanged { focusState ->
                borderColor = if (focusState.isFocused) Color.Black else Color.LightGray
            },
        keyboardOptions = when (inputType) {
            InputType.MOBILE, InputType.AADHAR -> KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
            InputType.USERNAME -> KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        },
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction?.invoke()
            }
        ),
        textStyle = textStyle,
        singleLine = true
    )
}


enum class InputType {
    MOBILE, AADHAR, USERNAME
}


