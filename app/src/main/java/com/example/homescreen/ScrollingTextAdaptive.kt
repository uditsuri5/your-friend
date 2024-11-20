import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.R

class ScrollingTextAdaptive(private val services: List<Int>) :
    RecyclerView.Adapter<ScrollingTextAdaptive.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val textView: TextView = view.findViewById(R.id.textItem)
        val imageView: ImageView =view.findViewById(R.id.imagemoving)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.scrolling_text_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(services[position])
    }

    override fun getItemCount() = services.size
}
