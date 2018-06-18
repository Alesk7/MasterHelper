package alesk.com.masterhelper.presentation.project.jobs

import alesk.com.masterhelper.R
import alesk.com.masterhelper.data.entities.Job
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_job.view.*

class JobsAdapter(private val context: Context?,
                  private val onDelete: (Job, Int) -> Unit,
                  private val onStatusChanged: (Boolean, Job) -> Unit
): RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    lateinit var items: List<Job>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_job, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(position, items[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.name
        val quantity = itemView.quantity
        val unit = itemView.unit
        val isComplete = itemView.isComplete
        val deleteButton = itemView.deleteButton

        fun bind(pos: Int, item: Job) {
            name.text = item.name
            quantity.text = item.quantity.toString()
            unit.text = item.unit
            isComplete.isChecked = item.isComplete
            if(isComplete.isChecked) isComplete.text = context?.getString(R.string.complete)

            isComplete.setOnCheckedChangeListener { button, isChecked ->
                button.text = if(isChecked) context?.getString(R.string.complete) else
                    context?.getString(R.string.notComplete)
                onStatusChanged(isChecked, item)
            }
            deleteButton.setOnClickListener { onDelete(item, pos) }
        }
    }

}