package com.example.note.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.data.modelTest.Task
import com.example.note.navigation.NavigationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var selectedTabIndex by remember { mutableIntStateOf(1) }

    val tabs = listOf(
        painterResource(R.drawable.ic_task),
        painterResource(R.drawable.ic_note)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Setting",
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {}
            )

            Spacer(Modifier.width(50.dp))

            TabRow(
                selectedTabIndex = selectedTabIndex,
                indicator = { },
                divider = {}
            ) {
                tabs.forEachIndexed { index, icon ->
                    val isSelected = selectedTabIndex == index
                    Tab(
                        modifier = Modifier.background(MaterialTheme.colorScheme.background),
                        selected = isSelected,
                        onClick = { selectedTabIndex = index },
                        icon = {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                painter = icon,
                                contentDescription = null,
                                tint = if (isSelected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.secondary
                            )
                        }
                    )
                }
            }

        }

        val allItemsNote by viewModel.notes.collectAsState()
        val selectedNotes by viewModel.selectedNotes

        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (viewModel.isInSelectionMode()) {
                        Row {
                            IconButton(onClick = { viewModel.clearSelection() }) {
                                Icon(Icons.Default.Close, contentDescription = "لغو انتخاب")
                            }
                            IconButton(onClick = { viewModel.deleteSelectedNotes() }) {
                                Icon(Icons.Default.Delete, contentDescription = "حذف")
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.width(96.dp))
                    }

                    Text(
                        modifier = Modifier.padding(end = 20.dp),
                        text = if (viewModel.isInSelectionMode()) {
                            "${selectedNotes.size} انتخاب شده"
                        } else {
                            "یادداشت‌ها"
                        },
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )


        var query by remember { mutableStateOf("") }

        val filteredItemsNote = remember(query, allItemsNote) {
            if (query.isBlank()) allItemsNote
            else allItemsNote.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.detail.contains(query, ignoreCase = true) ||
                        it.date.contains(query, ignoreCase = true)
            }
        }

        val allItemsTask = listOf(
            Task("test3", false),
            Task("test4", true)
        )

        val filtersItemsTask = remember(query) {
            if (query.isBlank()) allItemsTask
            else allItemsTask.filter {
                it.title.contains(query, ignoreCase = true)
            }
        }

        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = {
                Text(
                    text = if (selectedTabIndex == 1) "جست و جوی یادداشت ها"
                    else "جست و جوی کارها",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Clear",
                    modifier = Modifier.clickable {
                        query = ""
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(10.dp)
                .clip(CircleShape),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedTextColor = MaterialTheme.colorScheme.surface,
            ),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
        )


        when (selectedTabIndex) {
            0 -> {
                if (filtersItemsTask.isNotEmpty()) {
                    LazyColumn {
                        items(filtersItemsTask) { it ->
                            TaskItem(title = it.title, state = it.isChecked)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "کامل شده 1",
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                } else
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add_task),
                            null,
                            modifier = Modifier.size(80.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(Modifier.height(20.dp))

                        Text(
                            text = "هیچ کاری موجود نیست",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
            }

            1 -> {
                if (filteredItemsNote.isNotEmpty()) {
                    LazyColumn {
                        items(
                            items = filteredItemsNote,
                            key = { it.id }
                        ) { noteEntity ->
                            NoteItem(
                                title = noteEntity.title,
                                detail = noteEntity.detail,
                                date = noteEntity.date,
                                isSelected = selectedNotes.contains(noteEntity.id),
                                modifier = Modifier
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = {
                                                if (viewModel.isInSelectionMode()) {
                                                    viewModel.toggleSelection(noteEntity.id)
                                                } else {
                                                    navController.navigate(
                                                        NavigationScreen.Item.createRoute(
                                                            noteEntity.id
                                                        )
                                                    )
                                                }
                                            },
                                            onLongPress = {
                                                viewModel.toggleSelection(noteEntity.id)
                                            }
                                        )
                                    }
                            )
                        }
                    }
                } else
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add_notes),
                            null,
                            modifier = Modifier.size(80.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(Modifier.height(20.dp))

                        Text(
                            text = "هیچ یادداشتی موجود نیست",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
            }
        }
    }
}
