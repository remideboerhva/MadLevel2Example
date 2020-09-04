package com.remideboer.madlevel2example

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // de lijst lazy aanmaken, en mutable
    private val list by lazy { mutableListOf<Reminder>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // het is niet nodig attributen te maken voor de UI zaken op dit moment
        // we hebben een layout manager nodig zodat de recyclervewi dingen op het scherm kan zetten
        val viewManager = LinearLayoutManager(this)
        // deze moeten we aan de recycler view koppelen
        rvReminders.layoutManager = viewManager // maak gebruik van synthetic ipv databinding
        // de adapter om de list data naar list item te vertalen
        val viewAdapter =
            ReminderAdapter(list) // omdat we een onclick hier zetten geen attribuut nodig
        rvReminders.adapter = viewAdapter

        // we willen toch ergens op kunnen klikken en dat het iets leuks doet
        btnAddReminder.setOnClickListener {
            // eerst valideren, graag geen lege strings in de lijst
            // beetje spelen met de Kotlin functies om te zien wat mogelijk is ipv een normale if
            // en direct String te gebruiken ipv nogmaals uit etReminder halen
            // niet dat het er perse sneller of leesbaarder van wordt.
            etReminder.text.toString().takeIf { it.isNotBlank() } ?.let {// isNotBlank geeft een null terug als wel een blank String, daar kunnen we gebruik van maken met een safe call
                    list.add(Reminder(it))
                    // trigger update, een standaard methode van recyclerview
                    viewAdapter.notifyDataSetChanged()
                } ?: run {// Elvis operator gaat werken als er NullPointer uit komt
                Snackbar.make(etReminder, getString(R.string.et_blank_error), Snackbar.LENGTH_SHORT)
                    .show()
            }
            Log.d("MainActivity", "onClick: ${list.size}, $list") // here to show list content
        }

        rvReminders.run {
            rvReminders.adapter = viewAdapter

            // we willen toch ergens op kunnen klikken en dat het iets leuks doet
            btnAddReminder.setOnClickListener {
                // eerst valideren, graag geen lege strings in de lijst
                // beetje spelen met de Kotlin functies om te zien wat mogelijk is ipv een normale if
                // en direct String te gebruiken ipv nogmaals uit etReminder halen
                // niet dat het er perse sneller of leesbaarder van wordt.
                etReminder.text.toString().takeIf { it.isNotBlank() } ?.let {// isNotBlank geeft een null terug als wel een blank String, daar kunnen we gebruik van maken met een safe call
                        list.add(Reminder(it))
                        // trigger update, een standaard methode van recyclerview
                        viewAdapter.notifyDataSetChanged()
                    } ?: run {// Elvis operator gaat werken als er NullPointer uit komt
                    Snackbar.make(etReminder, getString(R.string.et_blank_error), Snackbar.LENGTH_SHORT)
                        .show()
                }
                Log.d("MainActivity", "onClick: ${list.size}, $list") // here to show list content
            }

            rvReminders.run {
                ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        // we doen hier niets
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        // on swipe left item verwijderen
                        // we hebben de positie al, voer het verwijderen meteen uit
                        viewHolder.adapterPosition.run {
                            list.removeAt(this)
                            viewAdapter.notifyDataSetChanged()
                            Log.d("MainActivity", "onClick: ${list.size}, $list") // here to show list content
                        }
                    }
                }).attachToRecyclerView(this)
            }

            // we kunnen ook nog een observer aan de adapter koppelen
            // gezien we een context nodig hebben, maar de this in anonieme klasse naar de Observer
            // verwijst, kunnen we let en it gebruiken en hoeven we geen tijdelijke variabele
            // of attribuut aan te maken
            context.let {
                viewAdapter.registerAdapterDataObserver(object: AdapterDataObserver(){
                    override fun onChanged() {
                        Toast.makeText(it, "Data changed!!! size: ${list.size}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

    }
}
