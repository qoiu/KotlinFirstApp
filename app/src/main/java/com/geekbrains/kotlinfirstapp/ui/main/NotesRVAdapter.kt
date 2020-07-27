package com.geekbrains.kotlinfirstapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.entity.Note
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_note.view.*

class NotesRVAdapter(val onItemClick: ((Note)->Unit)?=null): RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))

    override fun getItemCount(): Int =notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])


    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(note: Note) = with(itemView){
            title.text=note.title
            body.text=note.text
            val color = when (note.color) {
                Note.Color.ORANGE -> R.color.color_orange
                Note.Color.GREEN -> R.color.color_green
                Note.Color.RED -> R.color.color_red
                Note.Color.YELLOW -> R.color.color_yellow
                Note.Color.BLUE -> R.color.color_blue
                Note.Color.WHITE -> R.color.color_white
                Note.Color.VIOLET -> R.color.color_violet
            }
            setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
            setOnClickListener {
                onItemClick?.invoke(note)
            }
        }


    }
}