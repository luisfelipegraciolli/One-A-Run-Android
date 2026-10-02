package com.shiwa.onearun.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shiwa.onearun.ui.screens.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel : HomeViewModel = HomeViewModel(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {HomeScreenTopBar()}
    ) { innerPadding ->
        HomeScreenOptions(
            onStartRaceClick = { viewModel.onStartRaceClick() },
            onEnterRaceClick = { viewModel.onEnterRaceClick() },
            modifier = modifier.padding(innerPadding)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(text = "One A Run")
        },
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
    )
}

@Composable
fun HomeScreenOptions(
    onStartRaceClick: () -> Unit,
    onEnterRaceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        StartRaceButton(
            onClick = onStartRaceClick
        )
        EnterRaceButton(
            onClick = onEnterRaceClick
        )
    }
}

@Composable
fun EnterRaceButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Text(text = "Enter Race")
    }
}

@Composable
fun StartRaceButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Text(text = "Start Race")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}