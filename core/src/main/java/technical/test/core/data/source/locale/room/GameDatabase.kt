package technical.test.core.data.source.locale.room

import androidx.room.Database
import androidx.room.RoomDatabase
import technical.test.core.data.source.locale.entity.GameEntity

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}