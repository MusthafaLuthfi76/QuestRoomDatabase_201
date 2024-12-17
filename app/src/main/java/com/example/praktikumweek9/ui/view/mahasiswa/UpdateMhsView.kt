package com.example.praktikumweek9.ui.view.mahasiswa

import android.R.id.message
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.TableInfo
import com.example.praktikumweek9.ui.costumwidget.customTopAppBar
import com.example.praktikumweek9.ui.viewmodel.PenyediaViewModel
import com.example.praktikumweek9.ui.viewmodel.UpdateMhsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMhsViewModel = viewModel (factory = PenyediaViewModel.Factory)// Inisialisasi View Model
) {
    val uiState = viewModel.updateUIState // Ambil UI state dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() } // Snackbar State
    val coroutineScope = rememberCoroutineScope()

    //Observasi perubahan snackBarMessage
    LaunchedEffect (uiState.snackBarMessage) {
        println("LaunchedEffect triggered")
        uiState.snackBarMessage?.let {message ->
            println("Snackbar message received: $message")
            coroutineScope.launch{
                println("Launching coroutine for snackbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, // Tempatkan snackbar di scaffold
        topBar = {
            customTopAppBar(
                judul = "Edit Mahasiswa",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            // Isi Body
            InsertBodyMhs(
                uiState = uiState,
                onValueChange = {updatedEvent ->
                    viewModel.updateState(updatedEvent) // Update state di viewmodel
                },
                onClick = {
                    coroutineScope.launch{
                        if (viewModel.validateFields()){
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main){
                                onNavigate() // Navigasi di main thread
                            }
                        }
                    }
                }
            )
        }
    }
}