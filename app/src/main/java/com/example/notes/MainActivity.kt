package com.example.notes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.db.Note
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DeleteNote {

    private lateinit var viewModel: NoteViewModel
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // viewmodel Initialization
        viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        // Recyclerview Adapter
        val mAdapter =RecyclerAdapter(this,this)
        binding.recyclerview.adapter=mAdapter


        // LiveData Observer
        MainScope().launch {
            viewModel.allNotes.collect  {
                mAdapter.updateNotes(it)
            }
        }

        //Submit Button OnclickListener
        binding.submitButton.setOnClickListener{
            val noteText=binding.inputText.text.toString()
            if (noteText.isNotEmpty()){
                viewModel.insertNote(Note(noteText))
                binding.inputText.text.clear()
                Toast.makeText(this, "${noteText} is Inserted", Toast.LENGTH_SHORT).show()

            }
        }

    }
    override fun onDeleteClicked(note:Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} is Deleted", Toast.LENGTH_SHORT).show()
    }
}