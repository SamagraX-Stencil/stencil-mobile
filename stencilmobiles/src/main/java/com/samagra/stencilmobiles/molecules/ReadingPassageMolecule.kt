package com.samagra.stencilmobiles.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReadingPassageMolecule(
    passage: String,
    progress: Float?= null,
    readWordsCount: Int,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    imageRes: Int? = null,
    imageAlignment: Alignment.Horizontal = Alignment.End,
    imageModifier: Modifier? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        progress?.let {
            ProgressLineBar(progress = it)
        }
        PassageDisplay(
            passage = passage,
            readWordsCount = readWordsCount,
            fontSize = fontSize,
            lineHeight = lineHeight,
            imageRes = imageRes,
            imageAlignment = imageAlignment,
            imageModifier = imageModifier
        )
    }
}



@Composable
fun PassageDisplay(
    passage: String,
    readWordsCount: Int,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    imageRes: Int? = null,
    imageAlignment: Alignment.Horizontal = Alignment.End,
    imageModifier: Modifier? = null
) {
    val words = passage.split(" ")
    val readWords = words.take(readWordsCount).joinToString(" ")
    val unreadWords = words.drop(readWordsCount).joinToString(" ")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                        append(readWords)
                        if (readWords.isNotEmpty()) append(" ")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append(unreadWords)
                    }
                },
                textAlign = TextAlign.Start,
                fontSize = fontSize,
                lineHeight = lineHeight,
            )
        }

        imageRes?.let {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (imageModifier != null) {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Optional Image",
                            modifier = imageModifier.align(imageAlignment)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Optional Image",
                            modifier = Modifier
                                .width(150.dp)
                                .height(150.dp)
                                .align(imageAlignment)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressLineBar(progress: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 26.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.weight(1f),
            color = Color.Blue,
            trackColor = Color.Gray,
        )
        Text(
            text = "${(progress * 100).toInt()}%",
            fontSize = 14.sp,
            color = Color.Blue,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}