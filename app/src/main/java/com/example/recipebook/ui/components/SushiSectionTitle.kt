package com.example.recipebook.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipebook.ui.theme.RecipeBookTheme

@Composable
fun SushiSectionTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showBackground = true)
@Composable
fun SushiSectionTitlePreview() {
    RecipeBookTheme {
        SushiSectionTitle(title = "Popular sushi")
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun SushiSectionTitleDarkPreview() {
    RecipeBookTheme(darkTheme = true) {
        SushiSectionTitle(title = "Popular sushi")
    }
}
