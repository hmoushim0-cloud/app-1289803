package sa.factory.app1289803.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1289803.data.SculptureModelingProjectRepository
import sa.factory.app1289803.model.SculptureModelingProject
import sa.factory.app1289803.model.SculptureRenderAngle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SculptureModelingProjectViewModel(
    private val repository: SculptureModelingProjectRepository
) : ViewModel() {
    val catalog: StateFlow<List<SculptureRenderAngle>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<SculptureModelingProject?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: SculptureModelingProject) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
