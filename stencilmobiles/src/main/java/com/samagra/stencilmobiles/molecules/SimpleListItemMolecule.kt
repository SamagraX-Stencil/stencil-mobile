package com.samagra.stencilmobiles.molecules

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun SimpleListItemMolecule(
    simpleListItemAttributes: SimpleListItemAttributes = SimpleListItemAttributes(),
    simpleListItemStyles: SimpleListItemStyles = SimpleListItemStyles(),
) {
    Column(Modifier.padding(simpleListItemStyles.listItemPadding)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .clickable { simpleListItemAttributes.onListItemClick?.invoke() },
            shape = RoundedCornerShape(simpleListItemStyles.listItemCornerRadius.dp),
//            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(simpleListItemStyles.backgroundColor),
            border = simpleListItemStyles.listItemBorder
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(simpleListItemStyles.contentPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                simpleListItemAttributes.imageRes?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        modifier = simpleListItemStyles.imageModifier
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }

                simpleListItemAttributes.imageUrl?.let{
                    AsyncImage(
                        model = it,
                        contentDescription = "Profile Image",
                        modifier = simpleListItemStyles.imageModifier
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Column(modifier = simpleListItemStyles.textColumnModifier) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        simpleListItemAttributes.title?.let {
                            Text(
                                text = it,
                                style = simpleListItemStyles.titleTextStyle
                            )
                        }
                        simpleListItemAttributes.number?.let {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = it,
                                style = simpleListItemStyles.titleTextStyle
                            )
                        }
                    }
                    simpleListItemAttributes.subtitle?.let {
                        Text(
                            text = it,
                            style = simpleListItemStyles.subtitleTextStyle,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    simpleListItemAttributes.details?.let {
                        Text(
                            text = it,
                            style = simpleListItemStyles.detailsTextStyle,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                if (simpleListItemAttributes.button1Text != null || simpleListItemAttributes.button1ImageRes != null) {
                    Button(
                        onClick = {
                            simpleListItemAttributes.onButton1Click?.invoke()
                        },
                        colors = ButtonDefaults.buttonColors(simpleListItemStyles.button1BackgroundColor),
                        shape = RoundedCornerShape(simpleListItemStyles.button1Corner),
                        modifier = simpleListItemStyles.button1Modifier,
                        border = simpleListItemStyles.button1Border,
                        contentPadding = simpleListItemStyles.button1ContentPadding
                    ) {
                        simpleListItemAttributes.button1ImageRes?.let {
                            if (simpleListItemStyles.isImgBeforeButton1Text) {
                                Icon(
                                    painter = painterResource(id = simpleListItemAttributes.button1ImageRes),
                                    contentDescription = null,
                                    modifier = simpleListItemStyles.button1ImageModifier,
                                    tint = simpleListItemStyles.button1ImageColor
                                )
                                Spacer(modifier = Modifier.width(simpleListItemStyles.gapBetweenImageAndText))
                            }
                        }

                        simpleListItemAttributes.button1Text?.let {
                            Text(
                                text = it,
                                style = simpleListItemStyles.button1TextStyle
                            )
                        }

                        simpleListItemAttributes.button1ImageRes?.let {
                            if (!simpleListItemStyles.isImgBeforeButton1Text) {
                                Spacer(modifier = Modifier.width(simpleListItemStyles.gapBetweenImageAndText))
                                Icon(
                                    painter = painterResource(id = simpleListItemAttributes.button1ImageRes),
                                    contentDescription = null,
                                    modifier = simpleListItemStyles.button1ImageModifier,
                                    tint = simpleListItemStyles.button1ImageColor
                                )
                            }
                        }
                    }
                }


                simpleListItemStyles.gapBetweenButton?.let {
                    Spacer(modifier = Modifier.width(simpleListItemStyles.gapBetweenButton))
                }

                if (simpleListItemAttributes.button2Text != null || simpleListItemAttributes.button2ImageRes != null) {
                    Button(
                        onClick = {
                            simpleListItemAttributes.onButton2Click?.invoke()
                        },
                        colors = ButtonDefaults.buttonColors(simpleListItemStyles.button2BackgroundColor),
                        shape = RoundedCornerShape(simpleListItemStyles.button2Corner),
                        modifier = simpleListItemStyles.button2Modifier,
                        border = simpleListItemStyles.button2Border,
                        contentPadding = simpleListItemStyles.button2ContentPadding
                    ) {
                        simpleListItemAttributes.button2ImageRes?.let {
                            if (simpleListItemStyles.isImgBeforeButton2Text) {
                                Icon(
                                    painter = painterResource(id = simpleListItemAttributes.button2ImageRes),
                                    contentDescription = null,
                                    modifier = simpleListItemStyles.button2ImageModifier,
                                    tint = simpleListItemStyles.button2ImageColor
                                )
                                Spacer(modifier = Modifier.width(simpleListItemStyles.gapBetweenImageAndText))
                            }
                        }
                        simpleListItemAttributes.button2Text?.let {
                            Text(
                                text = it,
                                style = simpleListItemStyles.button2TextStyle
                            )
                        }

                        simpleListItemAttributes.button2ImageRes?.let {
                            if (!simpleListItemStyles.isImgBeforeButton2Text) {
                                Spacer(modifier = Modifier.width(simpleListItemStyles.gapBetweenImageAndText))
                                Icon(
                                    painter = painterResource(id = simpleListItemAttributes.button2ImageRes),
                                    contentDescription = null,
                                    modifier = simpleListItemStyles.button2ImageModifier,
                                    tint = simpleListItemStyles.button2ImageColor
                                )
                            }
                        }
                    }
                }


            }
        }
    }
}

data class SimpleListItemAttributes(
    val title: String? = null,
    val number: String? = null,
    val subtitle: String? = null,
    val details: String? = null,
    val imageRes: Int? = null,
    val imageUrl: String? = null,
    val onListItemClick: (() -> Unit)? = null,
    val button1Text: String? = null,
    val button2Text: String? = null,
    val button1ImageRes: Int? = null,
    val button2ImageRes: Int? = null,
    val onButton1Click: (() -> Unit)? = null,
    val onButton2Click: (() -> Unit)? = null,
)

data class SimpleListItemStyles(
    val titleTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    ),
    val subtitleTextStyle: TextStyle = TextStyle(fontSize = 14.sp, color = Color.Gray),
    val detailsTextStyle: TextStyle = TextStyle(fontSize = 12.sp, color = Color.Gray),
    val backgroundColor: Color = Color.White,
    val listItemCornerRadius: Int = 10,
    val listItemPadding: PaddingValues = PaddingValues(10.dp),
    val contentPadding: PaddingValues = PaddingValues(16.dp),
    val imageModifier: Modifier = Modifier.size(35.dp),
    val listItemBorder: BorderStroke? = BorderStroke(1.dp, Color(0x3E06753C)),
    val textColumnModifier: Modifier = Modifier.padding(end = 10.dp),
    val button1TextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White
    ),
    val button1BackgroundColor: Color = Color.Transparent,
    val button1Modifier: Modifier = Modifier
        .width(135.dp)
        .height(28.dp),
    val button1ContentPadding: PaddingValues = PaddingValues(10.dp),
    val button1Border: BorderStroke? = null,
    val button1Corner: Dp = 6.dp,
    val button1ImageModifier: Modifier = Modifier.size(30.dp),
    val button2TextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White
    ),
    val button2BackgroundColor: Color = Color.Transparent,
    val button2Modifier: Modifier = Modifier
        .width(135.dp)
        .height(28.dp),
    val button2ContentPadding: PaddingValues = PaddingValues(10.dp),
    val button2Border: BorderStroke? = null,
    val button2Corner: Dp = 6.dp,
    val button2ImageModifier: Modifier = Modifier.size(30.dp),
    val gapBetweenButton: Dp? = null,
    val isImgBeforeButton1Text: Boolean = false,
    val isImgBeforeButton2Text: Boolean = false,
    val gapBetweenImageAndText: Dp = 10.dp,
    val button1ImageColor: Color = Color.Unspecified,
    val button2ImageColor: Color = Color.Unspecified,
)
