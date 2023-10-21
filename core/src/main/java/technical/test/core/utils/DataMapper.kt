package technical.test.core.utils

object DataMapper {

    private fun mapGameItemResponseToDomain(game: technical.test.core.data.source.remote.response.GameItem): technical.test.core.domain.model.Game =
        technical.test.core.domain.model.Game(
            id = game.id,
            name = game.name,
            uriBackgroundImage = game.backgroundImage ?: "",
            playTime = game.playtime,
            rating = game.rating.toString(),
        )

    fun mapGamesResponseToDomain(games: List<technical.test.core.data.source.remote.response.GameItem>): List<technical.test.core.domain.model.Game> =
        games.map { mapGameItemResponseToDomain(it) }

    fun mapGameEntityToDomain(entity: technical.test.core.data.source.locale.entity.GameEntity): technical.test.core.domain.model.Game =
        technical.test.core.domain.model.Game(
            id = entity.id,
            name = entity.name,
            uriBackgroundImage = entity.urlBackgroundImage,
            playTime = entity.playTime,
            rating = entity.rating,
            description = entity.description,
            releasedDate = entity.releasedDate,
            isFavorite = entity.isFavorite
        )

    fun mapGamesEntityToDomain(entities: List<technical.test.core.data.source.locale.entity.GameEntity>): List<technical.test.core.domain.model.Game> =
        entities.map { mapGameEntityToDomain(it) }

    fun mapGameResponseToEntity(game: technical.test.core.data.source.remote.response.ResponseGame): technical.test.core.data.source.locale.entity.GameEntity =
        technical.test.core.data.source.locale.entity.GameEntity(
            id = game.id,
            name = game.name,
            description = game.description,
            releasedDate = game.released,
            urlBackgroundImage = game.backgroundImage,
            playTime = game.playtime,
            rating = game.rating.toString(),
        )
}