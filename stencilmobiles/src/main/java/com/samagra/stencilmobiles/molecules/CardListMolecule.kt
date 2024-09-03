package com.samagra.stencilmobiles.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardListMolecule(
    cardItems: List<CardAttributes>,
    cardStyles: List<CardStyles>
) {
    val itemCount = minOf(cardItems.size, cardStyles.size)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (index in 0 until itemCount) {
            CardMolecule(
                cardAttributes = cardItems[index],
                cardStyles = cardStyles[index]
            )
        }
    }
}
