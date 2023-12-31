package arezou.ghorbani.s1_livedata.room.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arezou.ghorbani.s1_livedata.R
import arezou.ghorbani.s1_livedata.databinding.FragmentNotesListBinding
import arezou.ghorbani.s1_livedata.room.NotesActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class NotesListFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentNotesListBinding

    //Other
    private val noteAdapter by lazy { NoteAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Click
            addNoteBtn.setOnClickListener {
                findNavController().navigate(R.id.actionListToAdd)
            }
            //Get data
            (activity as NotesActivity).noteDb.noteDao().getAllNotes().observe(viewLifecycleOwner) {
                noteAdapter.differ.submitList(it)

                notesRecycler.apply {
                    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = noteAdapter
                }
            }
        }
    }

}