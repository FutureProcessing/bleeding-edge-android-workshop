package fp.com.todo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

public class ImagesAdapter(val imageUrls: List<String>, val onClickListener: (String) -> Unit) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    public inner class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        init {
            imageView.setOnClickListener { onClickListener(imageView.getTag().toString()) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_image, parent, false)

        return ViewHolder(v as ImageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: use Picasso to load image to holder
        Picasso.with(holder.imageView.getContext()).
                load(imageUrls.elementAt(position)).
                placeholder(R.drawable.placeholder).
                into(holder.imageView)
        holder.imageView.setTag(imageUrls.elementAt(position))
    }

    override fun getItemCount(): Int {
        // TODO: return count of image URLs
        return imageUrls.size()
    }
}
