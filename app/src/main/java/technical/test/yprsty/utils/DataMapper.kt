package technical.test.yprsty.utils

import technical.test.yprsty.data.source.locale.entity.GameEntity
import technical.test.yprsty.data.source.remote.response.GameItem
import technical.test.yprsty.data.source.remote.response.ResponseGame
import technical.test.yprsty.domain.model.Game

object DataMapper {

    fun mapGameResponseToDomain(game: ResponseGame): Game = Game(
        id = game.id,
        name = game.name,
        uriBackgroundImage = game.backgroundImage,
        playTime = game.playtime,
        rating = game.rating,
        description = game.description,
        releasedDate = game.released,
    )

    fun mapGameItemResponseToDomain(game: GameItem): Game = Game(
        id = game.id,
        name = game.name,
        uriBackgroundImage = game.backgroundImage,
        playTime = game.playtime,
        rating = game.rating,
    )

    fun mapGamesResponseToDomain(games: List<GameItem>): List<Game> =
        games.map { mapGameItemResponseToDomain(it) }

    fun mapGameEntityToDomain(entity: GameEntity): Game = Game(
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

    fun mapDomainToGameEntity(game: Game): GameEntity = GameEntity(
        id = game.id,
        name = game.name,
        description = game.description,
        releasedDate = game.releasedDate,
        urlBackgroundImage = game.uriBackgroundImage,
        playTime = game.playTime,
        rating = game.rating,
        isFavorite = game.isFavorite
    )
}