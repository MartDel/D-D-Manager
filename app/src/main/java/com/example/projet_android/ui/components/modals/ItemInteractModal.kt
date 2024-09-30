package com.example.projet_android.ui.components.modals

 import androidx.compose.material3.AlertDialog
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.ui.tooling.preview.Preview
 import com.example.projet_android.R
 import com.example.projet_android.model.Item
 import com.example.projet_android.ui.components.buttons.OutlineButton
 import com.example.projet_android.ui.components.buttons.ValidateButton
 import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun ItemInteractModal(
    showDialog: Boolean,
    item: Item?,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    isAddingItem: Boolean = false
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Use ${item?.name}")
            },
            text = {
                Text(text = "Do you want to use ${item?.name} ?")
            },
            confirmButton = {
                ValidateButton(
                    text = "Yes",
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    isLoading = isAddingItem
                )
            },
            dismissButton = {
                OutlineButton("No", onClick = onDismiss)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemInteractModalPreview() {
    ProjetAndroidTheme {
        ItemInteractModal(true, Item("1", "TestItem", R.drawable.bow.toString()))
    }
}