package com.example.quotesapp.presentation.savedquote_sc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesapp.R
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.presentation.MainViewModel

fun String.toCamelCase(): String {
    return this.lowercase().replaceFirstChar { it.uppercase() }
}


@Composable
fun SavedQuoteItem(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel= hiltViewModel(),
    quote: Quote
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                viewModel.quote = quote
            },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Card(
            modifier = Modifier
                .padding(8.dp)
                .weight(.25f)
                .background(Color.Transparent)
            ,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(Color.Transparent) ,
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.quote),
                    modifier = Modifier.padding(2.dp),
                    contentDescription = null
                )
            }
        }


        Column(modifier = Modifier.weight(.75f)) {

            Text(text = quote.quote,
                fontFamily = FontFamily.Monospace,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
                )

//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
                Text(
                    text =  quote.author,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                )
                Text(
                    text = quote.category.toCamelCase(),
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
//            }


        }


    }

}


@Preview(showBackground = true)
@Composable
fun SavedQuoteItemPreview() {
    SavedQuoteItem(modifier = Modifier,
        quote = Quote(
            id=1,
            quote = "The only way to do great things is love what you do",
            author = "Steve Jobs",
            category = "Inspirational"
        )
        )
}