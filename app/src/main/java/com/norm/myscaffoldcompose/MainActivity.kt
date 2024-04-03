package com.norm.myscaffoldcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.norm.myscaffoldcompose.ui.theme.MyScaffoldComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScaffoldComposeTheme {
                var text by remember {
                    mutableStateOf("")
                }
                val snackbarHost = remember {
                    SnackbarHostState()
                }
                val scope = rememberCoroutineScope()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                modifier = Modifier.shadow(8.dp),
                                title = {
                                    Text(
                                        text = "This TopAppBar!"
                                    )
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                )
                            )
                        },
                        bottomBar = {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = true,
                                    onClick = {

                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "home"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {

                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.List,
                                            contentDescription = "list"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {

                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Settings,
                                            contentDescription = "settings"
                                        )
                                    }
                                )
                            }
                        },
                        snackbarHost = {
                            SnackbarHost(
                                hostState = snackbarHost
                            )
                        },
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                onClick = {
                                    scope.launch {
                                        snackbarHost.showSnackbar(
                                            message = if (text.isEmpty()) "Text is empty" else text,
                                            duration = SnackbarDuration.Short,
                                        )
                                    }
                                },
                            ) {
                                Text(
                                    text = "Show snackbar"
                                )
                            }
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                    ) { padding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    top = padding.calculateTopPadding(),
                                    bottom = padding.calculateBottomPadding()
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            TextField(
                                value = text,
                                onValueChange = {
                                    text = it
                                },
                                label = {
                                    Text(
                                        "Enter text for snackbar"
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}