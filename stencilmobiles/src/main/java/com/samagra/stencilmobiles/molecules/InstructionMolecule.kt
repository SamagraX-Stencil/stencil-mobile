package com.samagra.stencilmobiles.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InstructionMolecule(
    topInstructionModel: TopInstructionModel,
    bottomInstructionModel: BottomInstructionModel = BottomInstructionModel(),
    topInstructionStyle: TopInstructionStyle = TopInstructionStyle(),
    bottomInstructionStyle: BottomInstructionStyle = BottomInstructionStyle(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(topInstructionStyle.backgroundColor ?: Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            topInstructionModel.topImageRes?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = topInstructionStyle.topImageModifier ?: Modifier
                        .width(180.dp)
                        .height(180.dp)
                        .padding(bottom = 16.dp)
                )
            }

            topInstructionModel.progress?.let {
                LinearProgressIndicator(
                    progress = { it },
                    modifier = Modifier.height(6.dp),
                    color = Color.Blue,
                    trackColor = Color.Gray,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            topInstructionModel.title?.let {
                androidx.compose.material3.Text(
                    text = it,
                    fontSize = topInstructionStyle.titleFontSize,
                    fontWeight = FontWeight.Bold,
                    color = if (topInstructionStyle.backgroundColor != Color.White) Color.White else Color(
                        0xFF30347F
                    ),
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }

            topInstructionModel.resultBoxDataList?.let {
                ResultBox(
                    resultBoxDataList = topInstructionModel.resultBoxDataList
                )
            }

            topInstructionModel.subtitle?.let {
                androidx.compose.material3.Text(
                    text = it,
                    fontSize = topInstructionStyle.subtitleFontSize,
                    color = if (topInstructionStyle.backgroundColor != Color.White) Color.White else Color(
                        0xFF5E5D5C
                    ),
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                        .padding(start = 16.dp, end = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
            }
        }

        bottomInstructionModel.bottomImageRes?.let {
            if (bottomInstructionStyle.bottomImageModifier != null) {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = bottomInstructionStyle.bottomImageModifier.align(
                        bottomInstructionStyle.bottomImageAlignment
                    )
                )
            } else {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .align(bottomInstructionStyle.bottomImageAlignment)
                        .padding(16.dp)
                )
            }
        }


        bottomInstructionModel.buttonConfig?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .padding(bottom = 10.dp)
                    .padding(16.dp)
            ) {
                CustomButton(
                    buttonConfig = it.copy(buttonFontSize = it.buttonFontSize),
                    buttonFontSize = it.buttonFontSize
                )

                bottomInstructionModel.additionalButtons.forEach { buttonConfig ->
                    CustomButton(
                        buttonConfig = buttonConfig.copy(buttonFontSize = buttonConfig.buttonFontSize),
                        buttonFontSize = buttonConfig.buttonFontSize
                    )
                }
            }
        }

    }
}

@Composable
fun ResultBox(resultBoxDataList: List<ResultBoxData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(8.dp))
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(2.dp, Color(0xFF2F3293), shape = RoundedCornerShape(8.dp)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(resultBoxDataList) { data ->
            Column {
                ResultRow(
                    label = data.labelText,
                    value = data.valueText,
                    fontSize = data.valueTextStyle.fontSize,
                    color = data.valueTextStyle.color
                )
            }
        }
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
        androidx.compose.material3.Text(
            text = buttonConfig.text,
            fontSize = buttonFontSize,
            color = Color.White
        )
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
    val labelText: String,
    val valueText: String,
    val labelTextStyle: TextStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
    val valueTextStyle: TextStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
)


data class ButtonConfig(
    val text: String,
    val iconRes: Int? = null,
    val onClick: () -> Unit,
    val buttonFontSize: TextUnit = 16.sp,
)

data class TopInstructionModel(
    val title: String? = null,
    val subtitle: String? = null,
    val progress: Float? = null,
    val resultBoxDataList: List<ResultBoxData>? = null,
    val topImageRes: Int? = null,
)

data class BottomInstructionModel(
    val bottomImageRes: Int? = null,
    val buttonConfig: ButtonConfig? = null,
    val additionalButtons: List<ButtonConfig> = emptyList()
)

data class TopInstructionStyle(
    val backgroundColor: Color? = Color.White,
    val topImageModifier: Modifier? = null,
    val titleFontSize: TextUnit = 24.sp,
    val subtitleFontSize: TextUnit = 16.sp,
    val resultFontSize: TextUnit = 18.sp,
)

data class BottomInstructionStyle(
    val bottomImageAlignment: Alignment.Horizontal = Alignment.End,
    val bottomImageModifier: Modifier? = null,
)




