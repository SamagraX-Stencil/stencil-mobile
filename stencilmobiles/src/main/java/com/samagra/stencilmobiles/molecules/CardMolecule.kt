package com.samagra.stencilmobiles.molecules

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.samagra.stencilmobiles.R

@Composable
fun CardMolecule(
    cardAttributes: CardAttributes = CardAttributes(),
    cardStyles: CardStyles = CardStyles(),
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(Modifier.padding(cardStyles.contentPadding)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .clickable { cardAttributes.onCardClick?.invoke() },
            shape = RoundedCornerShape(cardStyles.cardCornerRadius.dp),
//            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            colors = CardDefaults.cardColors(cardStyles.backgroundColor),
            border = cardStyles.cardBorder
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            cardAttributes.imageRes?.let {
                                Image(
                                    painter = painterResource(id = it),
                                    contentDescription = null,
                                    modifier = cardStyles.imageModifier
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                            }

                            cardAttributes.imageUrl?.let{
                                AsyncImage(
                                    model = it,
                                    contentDescription = "Profile Image",
                                    modifier = cardStyles.imageModifier
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    cardAttributes.title?.let {
                                        Text(
                                            text = it,
                                            style = cardStyles.titleTextStyle
                                        )
                                    }
                                    cardAttributes.number?.let {
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = it,
                                            style = cardStyles.titleTextStyle
                                        )
                                    }
                                }
                                cardAttributes.subtitle?.let {
                                    Text(
                                        text = it,
                                        style = cardStyles.subtitleTextStyle,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                    }

                    Button(
                        onClick = {
                            if (cardAttributes.isExpandable) {
                                isExpanded = !isExpanded
                            } else {
                                cardAttributes.onButtonClick?.invoke()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(cardStyles.buttonBackgroundColor),
                        shape = RoundedCornerShape(cardStyles.buttonCorner),
                        modifier = cardStyles.buttonModifier,
                        border = cardStyles.buttonBorder,
                        contentPadding = cardStyles.buttonContentPadding
                    ) {
                        cardAttributes.buttonText?.let {
                            Text(
                                text = it,
                                style = cardStyles.buttonTextStyle
                            )
                        }

                        if (cardAttributes.isExpandable) {
                            Spacer(modifier = Modifier.width(6.dp))

                            if (isExpanded) {
                                Icon(
                                    painter = painterResource(cardAttributes.expandImage),
                                    tint = cardStyles.colorExpandCollapseImage,
                                    contentDescription = null,
                                    modifier = cardStyles.buttonImageModifier
                                )
                            } else {
                                Icon(
                                    painter = painterResource(cardAttributes.collapseImage),
                                    tint = cardStyles.colorExpandCollapseImage,
                                    contentDescription = null,
                                    modifier = cardStyles.buttonImageModifier
                                )
                            }
                        }
                    }

                }
            }
            if (cardAttributes.isExpandable && isExpanded && cardAttributes.attachedExpandedContent) {
                cardAttributes.expandedContent()
            }
        }
        if (cardAttributes.isExpandable && isExpanded && !cardAttributes.attachedExpandedContent) {
            cardAttributes.expandedContent()
        }
    }
}

data class CardAttributes(
    val title: String? = null,
    val number: String? = null,
    val subtitle: String? = null,
    val buttonText: String? = null,
    val imageRes: Int? = null,
    val imageUrl: String? = null,
    val expandImage: Int = R.drawable.expand,
    val collapseImage: Int = R.drawable.compress,
    val attachedExpandedContent: Boolean = false,
    val isExpandable: Boolean = false,
    val expandedContent: @Composable () -> Unit = {},
    val onCardClick: (() -> Unit)? = null,
    val onButtonClick: (() -> Unit)? = null,
)

data class CardStyles(
    val buttonTextStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White
    ),
    val titleTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    ),
    val subtitleTextStyle: TextStyle = TextStyle(fontSize = 14.sp, color = Color.Gray),
    val buttonBackgroundColor: Color = Color.White,
    val buttonModifier: Modifier = Modifier
        .width(135.dp)
        .height(28.dp),
    val buttonContentPadding: PaddingValues = PaddingValues(10.dp),
    val buttonBorder: BorderStroke? = null,
    val buttonCorner: Dp = 6.dp,
    val backgroundColor: Color = Color.White,
    val cardCornerRadius: Int = 10,
    val contentPadding: PaddingValues = PaddingValues(10.dp),
    val imageModifier: Modifier = Modifier.size(35.dp),
    val buttonImageModifier: Modifier = Modifier.size(30.dp),
    val cardBorder: BorderStroke? = BorderStroke(1.dp, Color(0x3E06753C)),
    val colorExpandCollapseImage: Color = Color.White
)

