package com.samagra.stencilmobiles.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun CardListMolecule(
    cardItems: List<CardAttributes>,
    cardStyles: CardStyles = CardStyles()
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cardItems.size) { index ->
            CardMolecule(
                cardAttributes = cardItems[index],
                cardStyles = cardStyles
            )
        }
    }
}
