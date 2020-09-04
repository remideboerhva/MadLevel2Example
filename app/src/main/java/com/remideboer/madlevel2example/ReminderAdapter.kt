package com.remideboer.madlevel2example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Description: MadLevel2Example
 * De klasse die alle losse items in een list koppelt aan een list item
 * Deze uitwerking is zonder View Binding om te laten zien
 * hoe het zonder werkt
 * Created by Rémi de Boer - r.d.d.de.boer@hva.nl - on 04/09/2020
 *
 */
class ReminderAdapter(private val list: List<Reminder>): RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    // een view holder is een klasse die de view van een list item vertegenwoordigt
    // dit kan ook een view group zijn als het een verzameling zoals een constraint layout is
    class ReminderViewHolder(view: View): RecyclerView.ViewHolder(view)

    // item view factory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        // we moeten een CView meegeven aan de Viewholder
        // we gebruiken de super View, casten is niet nodig,
        // want de ViewHolder accepteert een View
        val constraintLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reminder, parent, false)

        return ReminderViewHolder(constraintLayout)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvReminder).text = list[position].text
    }

    override fun getItemCount() = list.size
}