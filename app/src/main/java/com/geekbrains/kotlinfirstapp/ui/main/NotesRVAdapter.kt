package com.geekbrains.kotlinfirstapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.kotlinfirstapp.R
import com.geekbrains.kotlinfirstapp.data.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesRVAdapter: RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))

    override fun getItemCount(): Int =notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(itemView){
            title.text=note.title
            body.text=note.text
            setBackgroundColor(note.color)
        }

    }
}