package com.samagra.stencilmobiles.molecules


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginMolecule(
    loginCommonModel: LoginCommonModel,
    loginCommonStyle: LoginCommonStyle = LoginCommonStyle(),
    loginModel: LoginModel = LoginModel(),
    otpModel: OtpModel = OtpModel(),
    loginStyle: LoginStyle = LoginStyle(),
    otpStyle: OtpStyle = OtpStyle()
) {
    var genericText by remember { mutableStateOf(loginModel.genericInput) }
    var passwordText by remember { mutableStateOf(loginModel.passwordInput ?: "") }
    var passwordVisible by remember { mutableStateOf(false) }
    var borderColor by remember { mutableStateOf(Color.LightGray) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            loginCommonModel.title?.let {
                Text(
                    text = it,
                    style = loginCommonStyle.titleTextStyle
                )
            }

            Spacer(modifier = Modifier.height(loginCommonStyle.gapTitleSubtitle))

        loginCommonModel.subtitle?.let {
            Text(
                text = it,
                style = loginCommonStyle.subtitleTextStyle
            )
        }


            Spacer(modifier = Modifier.height(loginCommonStyle.gapSubtitleTextfield))


        if (otpModel.isOtpScreen) {
            OtpInputAtom(
                otpInput = otpModel.otpInput,
                onOtpInputChange = {
                    otpModel.otpInput = it
                    otpModel.onOtpInputChange(it)
                },
                numberOfDigits = otpModel.numberOfOtpDigits,
                otpInputColor = otpStyle.otpInputColor,
                onImeAction = otpModel.onImeAction,
                textFieldBackgroundColor = otpStyle.textFieldBackgroundColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = otpModel.resendOtpText,
                textDecoration = TextDecoration.Underline,
                style = otpStyle.resendTextStyle,
                modifier = Modifier
                    .clickable { otpModel.onResendOtpClick() }
                    .padding(horizontal = 16.dp)
            )
        } else {
            GenericInputFieldMolecule(
                input = genericText,
                onInputChange = {
                    genericText = it
                    loginModel.onGenericInputChange(it)
                },
                hint = loginModel.inputHint ?: "",
                inputType = loginModel.inputType,
                textFieldBackgroundColor = loginStyle.textFieldBackgroundColor,
                onImeAction = loginModel.onImeAction,
                textStyle = loginStyle.inputTextStyle
            )

            Spacer(modifier = Modifier.height(8.dp))

            loginModel.passwordInput?.let {
                OutlinedTextField(
                    value = passwordText,
                    onValueChange = {
                        passwordText = it
                        loginModel.onPasswordInputChange(it)
                    },
                    placeholder = {
                        Text(
                            text = "Password",
                            style = loginStyle.inputTextStyle,
                            color = Color.Gray,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .background(loginStyle.textFieldBackgroundColor)
                        .onFocusChanged { focusState ->
                            borderColor = if (focusState.isFocused) Color.Black else Color.LightGray
                        },
                    textStyle = loginStyle.inputTextStyle,
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = loginModel.forgetPasswordText,
                    style = loginStyle.forgetTextStyle,
                    modifier = Modifier
                        .clickable { loginModel.onForgotPasswordClick() }
                        .padding(horizontal = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = loginCommonModel.onButtonClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(loginCommonStyle.buttonBackgroundColor)
        ) {
            Text(
                text = loginCommonModel.buttonText,
                style = loginCommonStyle.buttonTextStyle
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


data class LoginCommonModel(
    val title: String? = null,
    val subtitle: String? = null,
    val buttonText: String,
    val onButtonClick: () -> Unit,
)

data class LoginCommonStyle(
    val titleTextStyle: TextStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = Color.Black),
    val subtitleTextStyle: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, color = Color.Gray),
    val buttonTextStyle: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White),
    val titleColor: Color = Color.Black,
    val buttonBackgroundColor: Color = Color.Blue,
    val gapTitleSubtitle : Dp = 20.dp,
    val gapSubtitleTextfield : Dp = 20.dp
)

data class LoginModel(
    val inputHint: String? = null,
    val inputType: InputType = InputType.MOBILE,
    val genericInput: String = "",
    val onGenericInputChange: (String) -> Unit = {},
    val passwordInput: String? = null,
    val onForgotPasswordClick: () -> Unit = {},
    val onPasswordInputChange: (String) -> Unit = {},
    val forgetPasswordText: String = "Forgot Password?",
    val onImeAction: () -> Unit = {},
)

data class OtpModel(
    val isOtpScreen: Boolean = false,
    var otpInput: String = "",
    val onOtpInputChange: (String) -> Unit = {},
    val numberOfOtpDigits: Int = 4,
    val resendOtpText: String = "OTP फिर से भेजें",
    val onResendOtpClick: () -> Unit = {},
    val onImeAction: () -> Unit = {}
)

data class LoginStyle(
    val buttonTextColor: Color = Color.White,
    val inputTextStyle: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, color = Color.Black),
    val textFieldBackgroundColor: Color = Color.White,
    val forgetTextStyle: TextStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, color = Color.Blue, textDecoration = TextDecoration.Underline)
)

data class OtpStyle(
    val otpInputColor: Color = Color.Black,
    val textFieldBackgroundColor: Color = Color.White,
    val resendTextStyle: TextStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, color = Color.Blue, textDecoration = TextDecoration.Underline)
)




