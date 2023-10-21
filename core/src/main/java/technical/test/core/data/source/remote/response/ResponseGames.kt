package technical.test.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseGames(

	@field:SerializedName("next")
	val next: String?,

	@field:SerializedName("previous")
	val previous: String?,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<GameItem>
)

data class GameItem(

	@field:SerializedName("background_image")
	val backgroundImage: String?,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("playtime")
	val playtime: Int
)
