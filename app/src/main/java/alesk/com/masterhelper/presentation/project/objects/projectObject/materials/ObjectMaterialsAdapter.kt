package alesk.com.masterhelper.presentation.project.objects.projectObject.materials

import alesk.com.masterhelper.R
import alesk.com.masterhelper.data.entities.Material
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_object_binding.view.*

class ObjectMaterialsAdapter(
        private val context: Context?,
        private val onItemClick: (Material) -> Unit
): RecyclerView.Adapter<ObjectMaterialsAdapter.ViewHolder>() {

    lateinit var items: List<Material>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item_object_binding, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.name
        val quantity = itemView.quantity
        val unit = itemView.unit

        fun bind(item: Material) {
            name.text = item.name
            quantity.text = item.quantity.toString()
            unit.text = item.unit
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

}