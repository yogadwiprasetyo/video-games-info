package technical.test.yprsty.domain.model

data class Game(
    val id: Int,
    val name: String,
    val uriBackgroundImage: String,
    val playTime: Int,
    val rating: String,
    val releasedDate: String = "",
    val description: String = "",
    val isFavorite: Boolean = false
)
