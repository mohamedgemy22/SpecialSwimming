package com.qersh.swimmig_schole_api.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.qersh.swimmig_schole_api.R
import com.qersh.swimmig_schole_api.adapters.FavouritfragmentAdapter
import com.qersh.swimmig_schole_api.dp.NoteDatabase
import com.qersh.swimmig_schole_api.mainViewModel.NoteViewModel
import com.qersh.swimmig_schole_api.mainViewModel.NoteViewModelFactory
import com.qersh.swimmig_schole_api.model.note
import com.qersh.swimmig_schole_api.repositories.NoteRepository


class fragment_favorite : Fragment(R.layout.fragment_favorite) {
    private lateinit var viewModel: NoteViewModel
    private lateinit var modelFactory: NoteViewModelFactory
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavouritfragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        modelFactory =
            NoteViewModelFactory(
                NoteRepository(
                    NoteDatabase.getDatabase(requireContext()).noteDoa()
                )
            )
        viewModel = ViewModelProvider(this, modelFactory).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(viewLifecycleOwner) {
            setUpRecyclerView(view, it)
        }
    }

    private fun setUpRecyclerView(view: View, note: List<note>) {
        recyclerView = view.findViewById(R.id.fr2_recycler_viewFavourit)
        adapter = FavouritfragmentAdapter(note)
        recyclerView.adapter = adapter

    }


}