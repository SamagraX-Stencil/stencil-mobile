package com.samagra.stencilmobiles.molecules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileCardMolecule(
    profileCardAttributes: ProfileCardAttributes,
    profileCardStyles: ProfileCardStyles = ProfileCardStyles()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(
                    width = 4.dp,
                    brush = Brush.linearGradient(colors = profileCardStyles.gradientColors)
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            profileCardAttributes.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        brush = Brush.linearGradient(colors = profileCardStyles.gradientColors)
                    ),
                    color = Color.Transparent,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 3.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(brush = Brush.linearGradient(colors = profileCardStyles.gradientColors))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 20.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = profileCardStyles.labelColor, fontSize = profileCardStyles.labelFontSize, fontWeight = profileCardStyles.labelFontWeight)) {
                                append("${profileCardAttributes.label1}: ")
                            }
                            withStyle(style = SpanStyle(color = profileCardStyles.valueColor, fontSize = profileCardStyles.valueFontSize, fontWeight = profileCardStyles.valueFontWeight)) {
                                append(profileCardAttributes.value1)
                            }
                        },
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = profileCardStyles.labelColor, fontSize = profileCardStyles.labelFontSize, fontWeight = profileCardStyles.labelFontWeight)) {
                                append("${profileCardAttributes.label2}: ")
                            }
                            withStyle(style = SpanStyle(color = profileCardStyles.valueColor, fontSize = profileCardStyles.valueFontSize, fontWeight = profileCardStyles.valueFontWeight)) {
                                append(profileCardAttributes.value2)
                            }
                        },
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = profileCardStyles.labelColor, fontSize = profileCardStyles.labelFontSize, fontWeight = profileCardStyles.labelFontWeight)) {
                                append("${profileCardAttributes.label3}: ")
                            }
                            withStyle(style = SpanStyle(color = profileCardStyles.valueColor, fontSize = profileCardStyles.valueFontSize, fontWeight = profileCardStyles.valueFontWeight)) {
                                append(profileCardAttributes.value3)
                            }
                        },
                        modifier = Modifier.padding(bottom = 0.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp)
                ) {
                    profileCardAttributes.imageRes?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Profile Image",
                            modifier = profileCardStyles.imageModifier
                        )
                    }

                    profileCardAttributes.badgeText?.let {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = profileCardStyles.badgeTextFontSize,
                                fontWeight = profileCardStyles.badgeTextFontWeight
                            ),
                            color = profileCardStyles.valueColor,
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 5.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

data class ProfileCardAttributes(
    val name: String? = "",
    val label1: String? = "जनपद",
    val value1: String? = "",
    val label2: String? = "UDISE",
    val value2: String? = "",
    val label3: String? = "ब्लॉक",
    val value3: String? = "",
    val imageRes: Int?,
    val badgeText: String? = null,
)

data class ProfileCardStyles(
    val gradientColors: List<Color> = listOf(Color.Gray),
    val labelColor: Color = Color.Black,
    val valueColor: Color = Color.Blue,
    val labelFontSize: TextUnit = 16.sp,
    val labelFontWeight: FontWeight = FontWeight.Normal,
    val valueFontSize: TextUnit = 16.sp,
    val valueFontWeight: FontWeight = FontWeight.Bold,
    val badgeTextFontSize: TextUnit = 12.sp,
    val badgeTextFontWeight: FontWeight = FontWeight.Bold,
    val imageModifier: Modifier = Modifier.width(64.dp).height(64.dp),
)









