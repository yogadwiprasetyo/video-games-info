package technical.test.yprsty.data.source.locale.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val releasedDate: String,
    val urlBackgroundImage: String,
    val playTime: Int,
    val rating: String,
    var isFavorite: Boolean = false
)
