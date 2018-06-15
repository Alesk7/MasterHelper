package alesk.com.masterhelper.presentation.project.objects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.models.ObjectModel
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.spinner_item.view.*

class ParentObjectsSpinnerAdapter(
        spinnerContext: Context,
        resource: Int,
        items: List<ObjectModel>
): ArrayAdapter<ObjectModel>(spinnerContext, resource, items) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, v: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
        val item = getItem(position).name
        if(item == context.getString(R.string.no)) view.item.typeface = Typeface.DEFAULT
        view.item.text = item
        return view
    }

    @Override
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        view.text = getItem(position).name
        return view
    }

}