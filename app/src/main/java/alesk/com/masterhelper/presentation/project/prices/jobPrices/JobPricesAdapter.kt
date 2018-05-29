package alesk.com.masterhelper.presentation.project.prices.jobPrices

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.models.JobModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.list_item_price.view.*

class JobPricesAdapter(
        val context: Context?,
        val onPriceChanged: (JobModel) -> Unit
): RecyclerView.Adapter<JobPricesAdapter.ViewHolder>() {

    lateinit var items: List<JobModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_price, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position], onPriceChanged)

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.name
        val quantity = itemView.quantity
        val parentObjectName = itemView.parentObjectName
        val unitPrice = itemView.unitPrice
        val sum = itemView.sum

        fun bind(item: JobModel, onPriceChanged: (JobModel) -> Unit) {
            var isSumCalculating = false
            var isUnitPriceCalculating = false
            name.text = item.name
            quantity.text = String.format("%.1f %s", item.quantity, item.unit)

            unitPrice.setText(item.unitPrice.toString())
            unitPrice.onChange {
                item.unitPrice = it.toDoubleOrNull() ?: 0.0
                isSumCalculating = true
                if(!isUnitPriceCalculating) {
                    item.priceSum = item.unitPrice * item.quantity
                    sum.setText(String.format("%.2f", item.priceSum))
                    onPriceChanged(item)
                }
                isSumCalculating = false
            }

            sum.setText(item.priceSum.toString())
            sum.onChange {
                item.priceSum = it.toDoubleOrNull() ?: 0.0
                isUnitPriceCalculating = true
                if(!isSumCalculating) {
                    item.unitPrice = item.priceSum / item.quantity
                    unitPrice.setText(String.format("%.2f", item.unitPrice))
                    onPriceChanged(item)
                }
                isUnitPriceCalculating = false
            }

            //parentObjectName.text = item.unit
        }
    }

    fun EditText.onChange(cb: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) { cb(s.toString()) }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

}