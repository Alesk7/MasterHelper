package alesk.com.masterhelper.presentation.project.objects.projectObject.objects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.data.entities.ProjectObject
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_parent_object.view.*

class ParentObjectsAdapter(
        private val context: Context?,
        private val onItemClick: (ProjectObject) -> Unit
): RecyclerView.Adapter<ParentObjectsAdapter.ViewHolder>() {

    lateinit var items: List<ProjectObject>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item_parent_object, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.objectName

        fun bind(item: ProjectObject) {
            name.text = item.name
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

}