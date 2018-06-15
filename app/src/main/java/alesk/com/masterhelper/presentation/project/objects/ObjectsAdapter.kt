package alesk.com.masterhelper.presentation.project.objects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.models.ObjectModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_object.view.*

class ObjectsAdapter(
        private val context: Context,
        private val listener: (ObjectModel) -> Unit
): RecyclerView.Adapter<ObjectsAdapter.ViewHolder>() {

    lateinit var items: List<ObjectModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_object, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.name
        val parentName = itemView.parentObjectName

        fun bind(item: ObjectModel) {
            name.text = item.name
            itemView.setOnClickListener { listener(item) }
            if(item.parentObjectName.isNullOrEmpty()){
                parentName.visibility = View.GONE
                return@bind
            }
            parentName.text = item.parentObjectName
        }
    }

}