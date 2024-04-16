package com.example.farmtrade.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmtrade.data.db.ChatUiModel
import com.example.farmtrade.ui.theme.PurpleGrey80
import com.example.farmtrade.ui.viewmodels.ChatScreenViewModel

@Composable
fun ChatScreen(viewModel: ChatScreenViewModel = viewModel()) {
    val conversation by viewModel.conversation.collectAsState()


    Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
    ) {
        ChatPage(
                model = ChatUiModel(
                        messages = conversation,
                        addressee = ChatUiModel.Author.bot
                ),
                onSendChatClickListener = { msg -> viewModel.sendChat(msg) },
                modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun ChatPage(
    model: ChatUiModel,
    onSendChatClickListener: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Support Chat",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth()
        )

        ConstraintLayout(modifier = Modifier.weight(1f)) {
            val (messages, chatBox) = createRefs()

            val listState = rememberLazyListState()
            LaunchedEffect(model.messages.size) {
                listState.animateScrollToItem(model.messages.size)
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(messages) {
                        top.linkTo(parent.top)
                        bottom.linkTo(chatBox.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(16.dp)
            ) {
                items(model.messages) { item ->
                    ChatItem(item)
                }
            }
            ChatBox(
                onSendChatClickListener,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .constrainAs(chatBox) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}


@Composable
fun ChatItem(message: ChatUiModel.Message) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .align(if (message.isFromMe) Alignment.End else Alignment.Start)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (message.isFromMe) 48f else 0f,
                        bottomEnd = if (message.isFromMe) 0f else 48f
                    )
                )
                .background(PurpleGrey80)
                .padding(16.dp)
        ) {
            Text(text = message.text)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBox(
    onSendChatClickListener: (String) -> Unit,
    modifier: Modifier
) {
    var chatBoxValue by remember { mutableStateOf(TextFieldValue("")) }
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = chatBoxValue,
            onValueChange = { newText ->
                chatBoxValue = newText
            },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(text = "Type something")
            }
        )
        IconButton(
            onClick = {
                val msg = chatBoxValue.text
                if (msg.isBlank()) return@IconButton
                onSendChatClickListener(chatBoxValue.text)
                chatBoxValue = TextFieldValue("")
            },
            modifier = Modifier
                .clip(CircleShape)
                .background(color = PurpleGrey80)
                .align(Alignment.CenterVertically)
                .width(40.dp)
                .height(60.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Send",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
    }
}