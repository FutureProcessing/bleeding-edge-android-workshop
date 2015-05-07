package fp.com.todo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

public class ImagesAdapter(val imageUrls: List<String>) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    public inner class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_image, parent, false)

        return ViewHolder(v as ImageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: use Picasso to load image to holder
    }

    override fun getItemCount(): Int {
        // TODO: return count of image URLs
        throw UnsupportedOperationException()
    }
}
