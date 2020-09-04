package com.remideboer.madlevel2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
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
            etReminder.text.toString().takeIf { it.isNotBlank() }
                ?.let {// isNotBlank geeft een null terug als wel een blank string, daar kunnen we gebruik van maken met een safe call
                    list.add(Reminder(etReminder.text.toString()))
                    // trigger update, een standaard methode van recyclerview
                    viewAdapter.notifyDataSetChanged()
                }
            Log.d("MainActivity", "onClick: ${list.size}, $list")
        }
    }
}
