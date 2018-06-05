package alesk.com.masterhelper.presentation.project.prices.materialPrices

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.models.MaterialModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.list_item_price.view.*

class MaterialPricesAdapter(
        val context: Context?,
        val onPriceChanged: (MaterialModel) -> Unit
): RecyclerView.Adapter<MaterialPricesAdapter.ViewHolder>() {

    lateinit var items: List<MaterialModel>
    var isPreventUpdate = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_price, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[holder.adapterPosition], holder.adapterPosition)

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemPosition = 0
        val name = itemView.name
        val quantity = itemView.quantity
        val parentObjectName = itemView.parentObjectName
        val unitPrice = itemView.unitPrice
        val sum = itemView.sum

        init {
            unitPrice.onChange {
                if(isPreventUpdate) return@onChange
                isPreventUpdate = true

                items[itemPosition].unitPrice = it.toDoubleOrNull() ?: 0.0
                items[itemPosition].priceSum = items[itemPosition].unitPrice * items[itemPosition].quantity
                onPriceChanged(items[itemPosition])
                sum.setText(String.format("%.2f", items[itemPosition].priceSum))
                isPreventUpdate = false
            }

            sum.onChange {
                if(isPreventUpdate) return@onChange
                isPreventUpdate = true

                items[itemPosition].priceSum = it.toDoubleOrNull() ?: 0.0
                items[itemPosition].unitPrice = items[itemPosition].priceSum / items[itemPosition].quantity
                onPriceChanged(items[itemPosition])
                unitPrice.setText(String.format("%.2f", items[itemPosition].unitPrice))
                isPreventUpdate = false
            }
        }

        fun bind(item: MaterialModel, position: Int) {
            itemPosition = position
            name.text = item.name
            quantity.text = String.format("%.2f %s", item.quantity, item.unit)
            unitPrice.setText(item.unitPrice.toString())
            sum.setText(item.priceSum.toString())
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