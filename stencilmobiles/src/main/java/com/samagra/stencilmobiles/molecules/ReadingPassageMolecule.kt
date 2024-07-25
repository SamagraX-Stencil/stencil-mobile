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
    passageModel: PassageModel,
    passageStyle: PassageStyle
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        passageModel.progress?.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 26.dp)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LinearProgressIndicator(
                    progress = { it },
                    modifier = Modifier.weight(1f),
                    color = Color.Blue,
                    trackColor = Color.Gray,
                )
                Text(
                    text = "${(it * 100).toInt()}%",
                    fontSize = 14.sp,
                    color = Color.Blue,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        val words = passageModel.passage.split(" ")
        val readWords = words.take(passageModel.readWordsCount).joinToString(" ")
        val unreadWords = words.drop(passageModel.readWordsCount).joinToString(" ")

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
                    fontSize = passageStyle.fontSize,
                    lineHeight = passageStyle.lineHeight,
                )
            }

            passageModel.imageRes?.let {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Optional Image",
                            modifier = (passageStyle.imageModifier?.align(passageStyle.imageAlignment) ?: Modifier
                                .width(150.dp)
                                .height(150.dp))
                                .align(passageStyle.imageAlignment)
                        )
                    }
                }
            }
        }
    }
}

data class PassageModel(
    val passage: String,
    val progress: Float? = null,
    val readWordsCount: Int = 0,
    val imageRes: Int? = null
)

data class PassageStyle(
    val fontSize: TextUnit = 40.sp,
    val lineHeight: TextUnit = 70.sp,
    val imageAlignment: Alignment.Horizontal = Alignment.End,
    val imageModifier: Modifier? = null
)
