package com.example.basiclayoutscodelab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        modifier = modifier.height(300.dp),
        rows = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(10) { item ->
            FavoriteCollectionCard("Nature meditation")
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    str: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.height(80.dp)
    ) {
        Row(
            modifier = modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier.size(80.dp).clip(RectangleShape),
                painter = painterResource(id = R.drawable.image2),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Text(
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(horizontal = 16.dp),
                text = str
            )
        }
    }
}

@Preview(name = "FavoriteCollectionCard")
@Composable
private fun PreviewFavoriteCollectionCard() {
    //FavoriteCollectionCard("Nature meditation")
    FavoriteCollectionsGrid()
}