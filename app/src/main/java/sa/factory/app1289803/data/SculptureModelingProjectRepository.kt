package sa.factory.app1289803.data

import sa.factory.app1289803.model.SculptureModelingProject
import sa.factory.app1289803.model.SculptureRenderAngle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface SculptureModelingProjectRepository {
    fun getAvailableCatalog(): Flow<List<SculptureRenderAngle>>
    fun getActiveRequest(): Flow<SculptureModelingProject?>
    suspend fun submitRequest(request: SculptureModelingProject): Result<String>
}

class InMemorySculptureModelingProjectRepository : SculptureModelingProjectRepository {
    private val _catalog = MutableStateFlow<List<SculptureRenderAngle>>(listOf(
        SculptureRenderAngle("1", "تحويل التصميم إلى نموذج ثلاثي الأبعاد عالي الدقة", "نمذجة دقيقة تحافظ على الشكل الأصلي والانحناءات والنسب مع إعداد النموذج للاستخدام في الطباعة أو العرض أو المعرض.", 5000L, "verified"),
        SculptureRenderAngle("2", "رندرات واقعية متعددة الزوايا", "إنتاج رندرات واقعية بإعدادات إضاءة ومواد متقدمة من زوايا متعددة مع مخرجات جاهزة للنشر والتحميل.", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<SculptureModelingProject?>(null)

    override fun getAvailableCatalog(): Flow<List<SculptureRenderAngle>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<SculptureModelingProject?> = _active.asStateFlow()

    override suspend fun submitRequest(request: SculptureModelingProject): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
