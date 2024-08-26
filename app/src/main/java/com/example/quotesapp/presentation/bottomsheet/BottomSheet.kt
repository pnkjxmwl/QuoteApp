package com.example.quotesapp.presentation.bottomsheet

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@Composable
fun BottomSheetContent(
    quote: String,
    author: String,
    isSaved:Boolean,
    showResetIcon:Boolean=true,
    onSaveClicked: () -> Unit,
    onNewQuoteClicked: () -> Unit,
) {

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Box(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Quote text

            Box(
                modifier = Modifier
                    .heightIn(max = 200.dp)
                    .verticalScroll(scrollState)
                    .padding(16.dp),

            ) {
                Text(
                    text = quote,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Author text
            Text(
                text = "- $author",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .padding()
                    .align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons for Save and New Quote
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = onSaveClicked
                ) {
                    Icon(
                        imageVector = if(isSaved) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Save"
                    )
                }
                IconButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(quote))
                        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share"
                    )
                }
                if(showResetIcon){
                IconButton(
                    onClick = onNewQuoteClicked
                ) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "New Quote"
                    )
                }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    BottomSheetContent(
        onSaveClicked = {},
        onNewQuoteClicked = {},
        author = "pankaj semwal",
        quote = "Procrastination is one of the most common and deadliest of diseases and its toll on success and happiness is heavy.",
        isSaved = false,
    )
}