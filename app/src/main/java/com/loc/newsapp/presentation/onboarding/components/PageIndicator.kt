package com.loc.newsapp.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.loc.newsapp.R
import com.loc.newsapp.presentation.onboarding.Dimens
import com.loc.newsapp.ui.theme.BlueGray

@Composable
fun PageIndicator(
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = colorResource(id = R.color.primary_color),
    unselectedColor: Color = BlueGray,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(pageSize){ page ->
            Box(
                modifier = Modifier
                    .size(Dimens.IndicatorSize).clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}