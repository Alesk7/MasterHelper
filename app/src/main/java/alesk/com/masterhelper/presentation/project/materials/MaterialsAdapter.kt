package alesk.com.masterhelper.presentation.project.materials

import alesk.com.masterhelper.R
import alesk.com.masterhelper.data.entities.Material
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_material.view.*

class MaterialsAdapter(private val context: Context?,
                       private val onDelete: (Material, Int) -> Unit,
                       private val onStatusChanged: (Boolean, Material) -> Unit
): RecyclerView.Adapter<MaterialsAdapter.ViewHolder>() {

    lateinit var items: List<Material>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_material, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(position, items[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.name
        val quantity = itemView.quantity
        val unit = itemView.unit
        val isPurchased = itemView.isPurchased
        val deleteButton = itemView.deleteButton

        fun bind(pos: Int, item: Material) {
            name.text = item.name
            quantity.text = item.quantity.toString()
            unit.text = item.unit
            isPurchased.isChecked = item.isPurchased
            if(isPurchased.isChecked) isPurchased.text = context?.getString(R.string.purchased)

            isPurchased.setOnCheckedChangeListener { button, isChecked ->
                button.text = if(isChecked) context?.getString(R.string.purchased) else
                    context?.getString(R.string.notPurchased)
                onStatusChanged(isChecked, item)
            }
            deleteButton.setOnClickListener { onDelete(item, pos) }
        }
    }

}