package com.example.heroes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HeroAdapter(private val dataSet: MutableList<Hero>, val onClick: (Hero) -> Unit) :
    RecyclerView.Adapter<HeroAdapter.HeroItemViewHolder>() {

    companion object {
        const val TAG = "HeroAdapter"
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class HeroItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var description: TextView
        var ranking: TextView
        var layout: ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View
            name = view.findViewById(R.id.textView_heroItem_name)
            description = view.findViewById(R.id.textView_heroItem_description)
            ranking = view.findViewById(R.id.textView_heroItem_ranking)
            layout = view.findViewById(R.id.layout_heroItem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HeroItemViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_hero, viewGroup, false)
        return HeroItemViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: HeroItemViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.name.text = dataSet[position].name
        viewHolder.description.text = dataSet[position].description
        viewHolder.ranking.text = dataSet[position].ranking.toString()
        viewHolder.layout.setOnClickListener {
            onClick(dataSet[position])
            Log.d(TAG, "onBindViewHolder: ${viewHolder.name.text} Clicked!")
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
