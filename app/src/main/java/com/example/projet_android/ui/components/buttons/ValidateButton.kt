package com.example.projet_android.ui.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet_android.R
import com.example.projet_android.ui.theme.ProjetAndroidTheme

@Composable
fun ValidateButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    color: Color = colorResource(R.color.validate),
    fontColor: Color = Color.White
){
    val focusManager = LocalFocusManager.current

    Button(
        onClick = {
            onClick()
            focusManager.clearFocus()
        },
        modifier = modifier
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(8.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = fontColor, modifier = Modifier.size(20.dp))
        } else {
            Text(text = text, color = fontColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ValidateButtonPreview() {
    ProjetAndroidTheme {
        ValidateButton("test", {})
    }
}
