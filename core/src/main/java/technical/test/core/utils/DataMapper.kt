package technical.test.core.utils

import technical.test.core.data.source.locale.entity.GameEntity
import technical.test.core.data.source.remote.response.GameItem
import technical.test.core.data.source.remote.response.ResponseGame
import technical.test.core.domain.model.Game

object DataMapper {

    private fun mapGameItemResponseToDomain(game: GameItem): Game =
        Game(
            id = game.id,
            name = game.name,
            uriBackgroundImage = game.backgroundImage ?: "",
            playTime = game.playtime,
            rating = game.rating.toString(),
        )

    fun mapGamesResponseToDomain(games: List<GameItem>): List<Game> =
        games.map { mapGameItemResponseToDomain(it) }

    fun mapGameEntityToDomain(entity: GameEntity): Game =
        Game(
            id = entity.id,
            name = entity.name,
            uriBackgroundImage = entity.urlBackgroundImage,
            playTime = entity.playTime,
            rating = entity.rating,
            description = entity.description,
            releasedDate = entity.releasedDate,
            isFavorite = entity.isFavorite
        )

    fun mapGamesEntityToDomain(entities: List<GameEntity>): List<Game> =
        entities.map { mapGameEntityToDomain(it) }

    fun mapGameResponseToEntity(game: ResponseGame): GameEntity =
        GameEntity(
            id = game.id,
            name = game.name,
            description = game.description,
            releasedDate = game.released,
            urlBackgroundImage = game.backgroundImage,
            playTime = game.playtime,
            rating = game.rating.toString(),
        )
}