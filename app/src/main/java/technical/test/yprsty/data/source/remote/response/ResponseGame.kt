package technical.test.yprsty.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseGame(

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("playtime")
	val playtime: Int,

	@field:SerializedName("rating")
	val rating: Int,

	@field:SerializedName("released")
	val released: String
)
