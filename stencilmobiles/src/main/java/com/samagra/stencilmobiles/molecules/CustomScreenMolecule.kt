package com.samagra.stencilmobiles.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomScreen(
    title: String,
    subtitle: String,
    progress: Float? = null,
    resultBoxData: ResultBoxData? = null,
    bottomImageRes: Int? = null,
    topImageRes: Int? = null,
    topImageModifier: Modifier? = null,
    bottomImageAlignment: Alignment.Horizontal = Alignment.End,
    bottomImageModifier: Modifier? = null,
    backgroundColor: Color? = null,
    buttonConfig: ButtonConfig? = null,
    additionalButtons: List<ButtonConfig> = emptyList(),
    titleFontSize: TextUnit = 24.sp,
    subtitleFontSize: TextUnit = 16.sp,
    resultFontSize: TextUnit = 18.sp,
    buttonFontSize: TextUnit = 16.sp,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor ?: Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            topImageRes?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = topImageModifier ?: Modifier
                        .width(180.dp)
                        .height(180.dp)
                        .padding(bottom = 16.dp)
                )

            }

            progress?.let {
                LinearProgressIndicator(
                    progress = { it },
                    modifier = Modifier.height(6.dp),
                    color = Color.Blue,
                    trackColor = Color.Gray,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                text = title,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold,
                color = if (backgroundColor != Color.White) Color.White else Color(0xFF30347F),
                modifier = Modifier.padding(vertical = 3.dp)
            )

            resultBoxData?.let {
                ResultBox(
                    resultBoxData = it,
                    resultFontSize = resultFontSize
                )
            }

            Text(
                text = subtitle,
                fontSize = subtitleFontSize,
                color = if (backgroundColor != Color.White) Color.White else Color(0xFF5E5D5C),
                modifier = Modifier.padding(vertical = 3.dp).padding(start=16.dp,end=16.dp).align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }

        bottomImageRes?.let {
            if (bottomImageModifier != null) {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = bottomImageModifier.align(bottomImageAlignment)
                )
            } else {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .align(bottomImageAlignment)
                        .padding(16.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(bottom = 10.dp)
                .padding(16.dp)
        ) {
            buttonConfig?.let {
                CustomButton(buttonConfig = it, buttonFontSize = buttonFontSize)
            }

            additionalButtons.forEach { buttonConfig ->
                CustomButton(buttonConfig = buttonConfig, buttonFontSize = buttonFontSize)
            }
        }
    }
}

@Composable
fun ResultBox(resultBoxData: ResultBoxData, resultFontSize: TextUnit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(8.dp))
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(2.dp, Color(0xFF2F3293), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        ResultRow(
            label = resultBoxData.totalWordsText,
            value = resultBoxData.totalWordsCount,
            fontSize = resultFontSize,
            color = Color.Black
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp))
        ResultRow(
            label = resultBoxData.correctWordsText,
            value = resultBoxData.correctWordsCount,
            fontSize = resultFontSize,
            color = Color.Green
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp))
        ResultRow(
            label = resultBoxData.wrongWordsText,
            value = resultBoxData.wrongWordsCount,
            fontSize = resultFontSize,
            color = Color.Red
        )
    }
}

@Composable
fun ResultRow(label: String, value: String, fontSize: TextUnit, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = color,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(15.dp)
                .height(1.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
        Text(
            text = value,
            color = color,
            fontSize = fontSize,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
fun CustomButton(buttonConfig: ButtonConfig, buttonFontSize: TextUnit) {
    Button(
        onClick = buttonConfig.onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF2F3293))
    ) {
        Text(text = buttonConfig.text, fontSize = buttonFontSize, color = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        buttonConfig.iconRes?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
        }
    }
}

data class ResultBoxData(
    val totalWordsText: String,
    val totalWordsCount: String,
    val correctWordsText: String,
    val correctWordsCount: String,
    val wrongWordsText: String,
    val wrongWordsCount: String,
)

data class ButtonConfig(
    val text: String,
    val iconRes: Int? = null,
    val onClick: () -> Unit,
)



