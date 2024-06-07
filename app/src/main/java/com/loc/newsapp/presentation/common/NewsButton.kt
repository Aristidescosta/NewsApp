package com.loc.newsapp.presentation.common

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.R
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.loc.newsapp.ui.theme.WhiteGray

@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.primary_color),
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = WhiteGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsButtonPreview() {
    NewsAppTheme {
        NewsButton(text = "Próximo", {  })
    }
}
@Preview(showBackground = true)
@Composable
private fun NewsTextButtonPreview() {
    NewsAppTheme {
        NewsTextButton(text = "Voltar", {  })
    }
}