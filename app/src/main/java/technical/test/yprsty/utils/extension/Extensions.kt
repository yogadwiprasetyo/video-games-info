package technical.test.yprsty.utils.extension

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

fun <T: RecyclerView.Adapter<*>> RecyclerView.setup(
    adapterView: T,
    customLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
) {
    layoutManager = customLayoutManager
    adapter = adapterView
}

fun ImageView.loadImage(source: Any) {
    Glide.with(this.context)
        .load(source)
        .into(this)
}