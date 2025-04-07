package testing.jetpack.compose.ui.screen.media

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.ui.screen.listuser.ListUserViewModel
import testing.jetpack.compose.ui.util.ShowUserInfo

@Composable
fun MediaScreen(
    viewModel: ListUserViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = {
                viewModel.addData()
            }) {
                Text("Add data")
            }

            OutlinedButton(onClick = {
                viewModel.shuffleData()
            }) {
                Text("Suffer data")
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        ) {
            items(viewModel.users, key = { it.userID }) { user ->
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CardItem(data = user)
                }
            }
        }
    }
}

@Composable
fun CardItem(data: LocalUser) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 400.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ShowUserInfo(user = data)
        }
    }
}