package sa.factory.app1289803.model

import kotlinx.serialization.Serializable

@Serializable
data class SculptureRenderAngle(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class SculptureModelingProject(
    val requestId: String,
    val items: List<SculptureRenderAngle>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: SculptureModelingProjectStatus = SculptureModelingProjectStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class SculptureModelingProjectStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
