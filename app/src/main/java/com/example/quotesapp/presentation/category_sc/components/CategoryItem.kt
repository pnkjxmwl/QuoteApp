package com.example.quotesapp.presentation.category_sc.components

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.quotesapp.R


@Composable
fun CategoryItem(
     category:String,
     imageRes:Int,
     onClick: () -> Unit
){
    Card (
        modifier = Modifier
        .padding(8.dp)
        .aspectRatio(1f)
        .clickable(onClick = onClick)

    ){
        Box(modifier = Modifier) {
            AsyncImage(
                model = imageRes,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = category,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
                    .background(Color.Black.copy(alpha = 0.2f))
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CategoryItemPreview(){
    CategoryItem(category = "Happiness",
        imageRes = R.drawable.ic_launcher_background,
        onClick = {})
}