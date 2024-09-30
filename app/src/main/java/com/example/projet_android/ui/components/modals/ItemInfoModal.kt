package com.example.projet_android.ui.components.modals

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.R
import com.example.projet_android.model.Item
import com.example.projet_android.ui.components.buttons.ValidateButton
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun ItemInfoModal(
    showDialog: Boolean,
    item: Item?,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    val context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = item!!.getResId(context)),
                        contentDescription = item.type,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(4.dp).size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            },
            text = {
                Text(text = "Description : ${item?.description}")
            },
            confirmButton = {
                ValidateButton("Ok", onClick = {
                    onConfirm()
                    onDismiss()
                })
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemInfoModalPreview() {
    ProjetAndroidTheme {
        ItemInfoModal(true, Item("1", "g jkregjerngrenglernglkergnekl", R.drawable.bow.toString()))
    }
}