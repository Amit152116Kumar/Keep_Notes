package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ListItemBinding
import com.example.notes.db.Note

class RecyclerAdapter(private val context: Context,
                      private val listener:DeleteNote) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var items= listOf<Note>()


    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val binding=ListItemBinding.bind(itemView)
        val textfield:TextView=binding.textfield
        val button: ImageButton =binding.deleteButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val viewHolder=ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
        viewHolder.button.setOnClickListener{
            listener.onDeleteClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote=items[position].text
        holder.textfield.text=currentNote
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNotes(updatedItems:List<Note>){
        items=updatedItems
        notifyDataSetChanged()
    }

}

interface DeleteNote{
    fun onDeleteClicked(note: Note)
}