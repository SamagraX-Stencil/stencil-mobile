package com.samagra.stencilmobiles.molecules

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpInputAtom(
    otpInput: String,
    onOtpInputChange: (String) -> Unit,
    numberOfDigits: Int = 4,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    otpInputColor: Color = Color.Black,
    onImeAction: (() -> Unit)? = null,
    textFieldBackgroundColor: Color = Color.White
) {
    val focusRequesters = remember { List(numberOfDigits) { FocusRequester() } }
    var maskedChars by remember { mutableStateOf(List(numberOfDigits) { true }) }

    LaunchedEffect(otpInput) {
        if (otpInput.length == numberOfDigits) {
            focusRequesters[numberOfDigits - 1].requestFocus()
        } else if (otpInput.isEmpty()) {
            focusRequesters[0].requestFocus()
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until numberOfDigits) {
            var borderColor by remember { mutableStateOf(Color.LightGray) }

            OutlinedTextField(
                value = if (otpInput.length > i) {
                    if (maskedChars[i]) "*" else otpInput[i].toString()
                } else "",
                onValueChange = { newOtp ->
                    if (newOtp.length <= 1) {
                        val updatedOtp = otpInput.toMutableList().apply {
                            if (newOtp.isNotEmpty()) {
                                if (size > i) {
                                    this[i] = newOtp[0]
                                } else {
                                    add(newOtp[0])
                                }
                                maskedChars = maskedChars.toMutableList().apply {
                                    this[i] = true
                                }
                                if (i < numberOfDigits - 1) {
                                    focusRequesters[i + 1].requestFocus()
                                } else {
                                    onImeAction?.invoke()
                                }
                            } else if (size > i) {
                                removeAt(i)
                                maskedChars = maskedChars.toMutableList().apply {
                                    this[i] = false
                                }
                            }
                        }.joinToString("")

                        onOtpInputChange(updatedOtp)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(3.dp)
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(textFieldBackgroundColor)
                    .focusRequester(focusRequesters[i])
                    .onFocusChanged { focusState ->
                        borderColor = if (focusState.isFocused) Color.Black else Color.LightGray
                        if (focusState.isFocused && otpInput.length > i) {
                            maskedChars = maskedChars.toMutableList().apply {
                                this[i] = false
                            }
                        } else if (!focusState.isFocused && otpInput.length > i) {
                            maskedChars = maskedChars.toMutableList().apply {
                                this[i] = true
                            }
                        }
                    }
                    .onKeyEvent { event ->
                        if (event.key == Key.Backspace && event.type == KeyEventType.KeyUp) {
                            if (otpInput.length > i) {
                                val updatedOtp = otpInput.toMutableList().apply {
                                    removeAt(i)
                                }.joinToString("")

                                onOtpInputChange(updatedOtp)
                                if (i > 0) {
                                    focusRequesters[i - 1].requestFocus()
                                }
                            } else if (i > 0) {
                                focusRequesters[i - 1].requestFocus()
                            }
                            true
                        } else {
                            false
                        }
                    },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    color = otpInputColor
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (i == numberOfDigits - 1) ImeAction.Done else ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (i < numberOfDigits - 1) {
                            focusRequesters[i + 1].requestFocus()
                        }
                    },
                    onDone = {
                        onImeAction?.invoke()
                    }
                ),
                singleLine = true,
                maxLines = 1
            )
        }
    }
}



















