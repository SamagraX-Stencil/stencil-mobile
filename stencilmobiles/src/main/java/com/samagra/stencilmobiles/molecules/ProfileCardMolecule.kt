package com.samagra.stencilmobiles.molecules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

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
                    width = profileCardStyles.borderWidth,
                    brush = Brush.linearGradient(colors = profileCardStyles.gradientColors)
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            profileCardAttributes.name?.let {

                if(profileCardStyles.nameTextColor != null){
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall,
                        color = profileCardStyles.nameTextColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 3.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                } else{
                    Text(
                        text = it,
                        style = profileCardStyles.nameTextStyle.copy(
                            brush = Brush.linearGradient(colors = profileCardStyles.gradientColors)
                        ),
                        color = Color.Transparent,
                        modifier = Modifier
                            .padding(bottom = 3.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
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
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(top = 18.dp)
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
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp, top = profileCardStyles.gapImageDivider),
                    horizontalAlignment = Alignment.End
                ) {
                    profileCardAttributes.imageUrl?.let {
                        AsyncImage(
                            model = profileCardAttributes.imageUrl,
                            contentDescription = "Profile Image",
                            modifier = profileCardStyles.imageModifier
                        )
                    }

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
                                fontWeight = profileCardStyles.badgeTextFontWeight,
                                textAlign = TextAlign.Center
                            ),
                            color = profileCardStyles.valueColor,
                            modifier = profileCardStyles.badgeTextModifier
                                .padding(top = 8.dp, bottom = 5.dp)
                                .align(Alignment.End)
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
    val imageUrl: String? = null,
    val imageRes: Int? = null,
    val badgeText: String? = null,
)

data class ProfileCardStyles(
    val gradientColors: List<Color> = listOf(Color(0xFF3D3C3C)),
    val borderWidth: Dp = 4.dp,
    val nameTextColor: Color? = null,
    val nameTextStyle: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
    val labelColor: Color = Color.Black,
    val valueColor: Color = Color.Blue,
    val labelFontSize: TextUnit = 14.sp,
    val labelFontWeight: FontWeight = FontWeight.Normal,
    val valueFontSize: TextUnit = 14.sp,
    val valueFontWeight: FontWeight = FontWeight.Bold,
    val badgeTextFontSize: TextUnit = 12.sp,
    val badgeTextFontWeight: FontWeight = FontWeight.Bold,
    val imageModifier: Modifier = Modifier.width(64.dp).height(64.dp),
    val badgeTextModifier: Modifier = Modifier.width(100.dp),
    val gapImageDivider: Dp = 0.dp
)









