package testing.jetpack.compose.ui.screen.listuser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import testing.jetpack.compose.domain.model.LocalUser
import testing.jetpack.compose.ui.theme.JetPackComposeTheme
import testing.jetpack.compose.ui.util.ShowUserInfo
import kotlin.math.max
import kotlin.math.min

@Composable
fun ListUserScreen(
    viewModel: ListUserViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { viewModel.users.size })
    val coroutineScope = rememberCoroutineScope()

    Column {
        Text(text = "ListUserScreen")
        ListUserPager(
            pagerState = pagerState,
            listUser = viewModel.users,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(
                        page = max(0, pagerState.currentPage - 1)
                    )
                }
            }) { Text("Previous") }

            Text(
                "${pagerState.currentPage + 1} / ${pagerState.pageCount}",
                color = JetPackComposeTheme.colors.primary,
                textAlign = TextAlign.Center
            )

            Button(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(
                        page = min(pagerState.pageCount - 1, pagerState.currentPage + 1)
                    )
                }
            }) { Text("Next") }
        }

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


    }
}

@Composable
fun ListUserPager(
    pagerState: PagerState,
    listUser: List<LocalUser>,
    modifier: Modifier = Modifier
) {
    VerticalPager(state = pagerState, modifier = modifier) { index ->
        val item = listUser.getOrNull(index) ?: return@VerticalPager
        if (index % 2 == 0) {
            FirstViewHolder(user = item)
        } else {
            SecondViewHolder(user = item)
        }
    }
}

@Composable
fun FirstViewHolder(
    user: LocalUser,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(JetPackComposeTheme.colors.primary)
    ) {
        Row {
            Text("FirstViewHolder")
            Spacer(modifier = Modifier.width(8.dp))
            ShowUserInfo(user = user)
        }
    }
}

@Composable
fun SecondViewHolder(
    user: LocalUser,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(JetPackComposeTheme.colors.primaryRed)
    ) {
        Row {
            Text("SecondViewHolder")
            Spacer(modifier = Modifier.width(8.dp))
            ShowUserInfo(user = user)
        }
    }
}
