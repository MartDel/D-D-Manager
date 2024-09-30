package com.example.projet_android.ui.components.modals

 import androidx.compose.material3.AlertDialog
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.LaunchedEffect
 import androidx.compose.runtime.getValue
 import androidx.compose.runtime.livedata.observeAsState
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.platform.LocalContext
 import androidx.compose.ui.tooling.preview.Preview
 import androidx.compose.ui.unit.dp
 import androidx.hilt.navigation.compose.hiltViewModel
 import com.example.projet_android.model.Item
 import com.example.projet_android.ui.alerts.showShortAlert
 import com.example.projet_android.ui.components.buttons.OutlineButton
 import com.example.projet_android.ui.components.lists.AllItemsList
 import com.example.projet_android.ui.components.progress.CircularProgress
 import com.example.projet_android.ui.theme.ProjetAndroidTheme
 import com.example.projet_android.view_models.ItemViewModel
 import com.example.projet_android.view_models.states.RequestState

@Composable
fun SelectItemModal(
    showDialog: Boolean,
    onDismiss: () -> Unit = {},
    onConfirm: (Item) -> Unit = {}
) {
    val itemViewModel: ItemViewModel = hiltViewModel()
    val fetchItemsState by itemViewModel.fetchItemsState.observeAsState()

    LaunchedEffect(Unit) {
        itemViewModel.fetchItems()
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Select an item")
            },
            text = {
                fetchItemsState?.let { state ->
                    when (state) {
                        is RequestState.Error -> {
                            showShortAlert(LocalContext.current, state.message)
                        }

                        is RequestState.Success<*> -> {
                            val items = (state.data as List<*>).map { it as Item }
                            AllItemsList(
                                items,
                                onItemClick = { item ->
                                    onConfirm(item)
                                    onDismiss()
                                }
                            )
                        }

                        RequestState.Loading -> {
                            CircularProgress(Color.Black, 30.dp)
                        }
                    }
                }
            },
            confirmButton = {
                OutlineButton("Cancel", onClick = onDismiss)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectItemModalPreview() {
    ProjetAndroidTheme {
        SelectItemModal(true)
    }
}