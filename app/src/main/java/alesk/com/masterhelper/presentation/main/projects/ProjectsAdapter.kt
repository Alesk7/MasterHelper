package alesk.com.masterhelper.presentation.main.projects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.data.entities.Project
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_project.view.*

class ProjectsAdapter(
        private val context: Context?,
        private val listener: (Project) -> Unit
) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    lateinit var items: List<Project>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_project, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position])

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val projectName = itemView.projectName
        val client = itemView.client
        val clientPhone = itemView.clientPhone
        val address = itemView.address
        val jobsDescription = itemView.jobsDescription

        fun bind(item: Project) {
            projectName.text = item.name
            client.text = item.client.name
            clientPhone.text = item.client.phoneNumber
            address.text = item.address
            if(item.address.isBlank()){
                address.text = context?.getString(R.string.emptyAddress)
                address.setTextColor(context!!.resources.getColor(R.color.textSecondary))
            }
            jobsDescription.text = item.jobsDescription
            if(item.jobsDescription.isBlank()){
                jobsDescription.text = context?.getString(R.string.emptyDescription)
                jobsDescription.setTextColor(context!!.resources.getColor(R.color.textSecondary))
            }
            itemView.setOnClickListener{ listener(item) }
        }
    }

}